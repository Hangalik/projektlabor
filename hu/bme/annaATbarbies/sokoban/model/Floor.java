package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.model.field.*;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A palyat reprezentalja. Ellenorzi, hogy mikor er veget a jatek, valamint az aktiv jatekosnak pontot ad.
 * --singleton--
 */
public class Floor {
    Logger logger = Logger.getLogger(Floor.class);
    private static Floor ourInstance;

    private ArrayList<Field> fields;
    private ArrayList<Controller> workers;
    private ArrayList<Box> boxes;
    private ArrayList<Controller> deadWorkers;
    private ArrayList<Pushable> deadBoxes;
    private int activeWorker;

    private Field floor[][];

    public static Floor getInstance() {
        if (ourInstance == null) {
            ourInstance = new Floor();
        }
        return ourInstance;
    }

    private Floor() {
        fields = new ArrayList<>();
        workers = new ArrayList<>();
        boxes = new ArrayList<>();
        deadWorkers = new ArrayList<>();
        deadBoxes = new ArrayList<>();
    }
    private Controller GetWorker_byID(int ID) {
    	Controller c=new Worker(0);
    	for(int i=0;i<workers.size();i++) {
    		if(workers.get(i).GetID()==ID)c=workers.get(i);
    	}
    	return c;
    	
    }

    /**
     * Minden lepesnel ellenorzi, hogy veget ert-e a jatek. Ha igen, akkor meghivja a Game osztaly finish() metodusat.
     */
    public void gameOver() {

        boolean finished = false;

        if (workers.size() == 0)
            finished = true;

        if (boxes.size() == 0)
            finished = true;

        boolean anyBoxPushable = false;
        for (Box box : boxes) {
            if (box.amIPushable()) {
                anyBoxPushable = true;
            }
        }

        if (finished || !anyBoxPushable) {
            Game.getInstance().finish();
            logger.debug("Jatek befejezodott");
        }
    }

    /**
     * meghivja az eppen aktiv jatekos gainPoint() metodusat.
     */
    public void rewardCurrentPlayer() {

        workers.get(activeWorker).gainPoint();
        logger.debug("Jatekos pontot kapott");

    }

    /**
     * beallitja az aktiv jatekost
     */
    public void setActiveWorker(int workernumber) {
        activeWorker = workernumber;
        logger.debug("Aktiv jatekos beallitva");
    }

    /**
     * meghivja az aktiv jatekos step fuggvenyet, a megadott iranyba
     */
    public void activePlayerMoves(Direction dir) {
        logger.debug("irany: " + dir.toString());
        GetWorker_byID(activeWorker).step(dir);
    }

    /**
     * meghivja az aktiv jatekos lubricateOil vagy lubricateHoney fuggvenyet, a megadott parametertol fuggoen
     */
    public void activePlayerlubricates(String type) {
        if (type.equals("oil"))
        	GetWorker_byID(activeWorker).lubricateOil();
        else if (type.equals("honey"))
        	GetWorker_byID(activeWorker).lubricateHoney();
    }

    /**
     * Inicializalo folyamat
     */
    public void Initialize(String floorname) {

        clear();

        try (BufferedReader br = new BufferedReader(new FileReader(floorname))) {

            logger.debug("Sikeres fajl megnyitas.");
            String size[] = br.readLine().split(" ");
            int x = Integer.parseInt(size[0]);
            int y = Integer.parseInt(size[1]);

            floor = new Field[x][y];

            String line;

            while ((line = br.readLine()) != null) {
                logger.debug("Beolvastunk egy sort: " + line);
                String splitLine[] = line.split(" ");

                // Field-es dolgok itt vannak kezelve
                Field field = null;
                switch (splitLine[0]) {
                    case "field":
                        field = new Field();
                        break;

                    case "block":
                        field = new Block();
                        break;

                    case "hole":
                        field = new Hole();
                        break;

                    case "target":
                        field = new Target();
                        break;

                    case "switch":  // a Trap is itt lesz megadva, kulon mar nem
                        field = new Switch();
                        if (splitLine[3].equals("trap")) {
                            String trapArgs[] = {splitLine[3], splitLine[4], splitLine[5]};
                            Trap trap = new Trap();
                            placeField(trap, trapArgs);
                            ((Switch) field).setTrap(trap);
                        }
                        break;

                    default:
                        break;
                }
                placeField(field, splitLine);

                // Pushable-es dolgok itt vannak kezelve
                Pushable p = null;
                switch (splitLine[0]) {
                    case "box":
                        p = new Box();
                        boxes.add((Box) p);
                        break;

                    case "worker":
                        p = new Worker(Integer.parseInt(splitLine[3]));
                        workers.add((Worker) p);
                        break;

                    default:
                        break;
                }
                placePushable(p, splitLine);

            }
        } catch (FileNotFoundException e) {
            logger.debug("Nem talalhato a fajl");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //berakja a fieldsbe a kapottat, majd osszekeni ha kell es beallitja a szomszedokat
    private void placeField(Field f, String args[]) {
        if (f == null) return;

        logger.debug("Egy Field tipusu mezot helyezunk el.");
        fields.add(f);
        int xPos = Integer.parseInt(args[1]);
        int yPos = Integer.parseInt(args[2]);
        floor[xPos][yPos] = f;

        if (args.length > 3) {
            switch (args[3]) {
                default:
                    break;
                case "honey":
                    f.pourHoney();
                    break;
                case "oil":
                    f.pourOil();
                    break;
            }
        }

        Field n;
        if (xPos > 0 && (n = floor[xPos - 1][yPos]) != null) {
            n.setNeighbor(Direction.RIGHT, f);
            f.setNeighbor(Direction.LEFT, n);
        }
        if (xPos < floor.length - 1 && (n = floor[xPos + 1][yPos]) != null) {
            n.setNeighbor(Direction.LEFT, f);
            f.setNeighbor(Direction.RIGHT, n);
        }
        if (yPos > 0 && (n = floor[xPos][yPos - 1]) != null) {
            n.setNeighbor(Direction.DOWN, f);
            f.setNeighbor(Direction.UP, n);
        }
        if (yPos < (floor[0].length) - 1 && (n = floor[xPos][yPos + 1]) != null) {
            n.setNeighbor(Direction.UP, f);
            f.setNeighbor(Direction.DOWN, n);
        }
    }

    //nem rakja bele a workersbe vagy boxesba a kapottat!!, osszekoti a fielddel
    private void placePushable(Pushable p, String args[]) {
        if (p == null) return;

        logger.debug("Egy Pushable tipusu mezot helyezunk el.");

        int xPos = Integer.parseInt(args[1]);
        int yPos = Integer.parseInt(args[2]);

        Field field = floor[xPos][yPos];

        field.setPushable(p);
        p.setField(field);
    }

    /**
     * Letorli a meglevo palyat
     */
    public void clear() {
        workers.clear();
        boxes.clear();
        fields.clear();
        deadWorkers.clear();
        deadBoxes.clear();
        floor = null;
    }

    /**
     * a kapott objektumot kiveszi a megfigyelt objektumok kozul. Ha lada, akkor tobbet nem teszteli, hogy mozdithato-e a jatek vege szempontjabol. Ha munkas, akkor nem kerul ra tobbet sor.
     *
     * @param p
     */
    public void pushableDied(Pushable p) {
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).equals(p)) {
                Controller removed = workers.remove(i);
                deadWorkers.add(removed);
                logger.debug("Munkas meghalt");
            }
        }
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).equals(p)) {
                Pushable removed = boxes.remove(i);
                deadBoxes.add(removed);
                logger.debug("Doboz eltunt");
            }
        }
    }

    public void list(String type) {
        if (type.equals("boxes")) {
            int k = 0;
            for (int i = 0; i < boxes.size(); i++) {
                //koordinatakat kikeressuk
                for (int x = 0; x < floor.length; x++) {
                    for (int y = 0; y < floor[x].length; y++) {
                        if ((boxes.get(i)).getField().equals(floor[x][y])) {
                            System.out.print("box_" + k++ + "\talive\t" + x + " " + y);
                        }
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < deadBoxes.size(); i++) {
                System.out.println("box_" + k++ + "\tdead");
            }
        } else if (type.equals("workers")) {
            int k = 0;
            for (int i = 0; i < workers.size(); i++) {
                //koordinatakat kikeressuk
                for (int x = 0; x < floor.length; x++) {
                    for (int y = 0; y < floor[x].length; y++) {
                        if (((Worker) workers.get(i)).getField().equals(floor[x][y])) {
                            System.out.print("player_" + k++ + "\talive\t" + x + " " + y);
                        }
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < deadWorkers.size(); i++) {
                System.out.println("player_" + k++ + "\tdead");
            }
        } else if (type.equals("fields")) {
            for (Field field : fields) {
                //koordinatakat kikeressuk
                for (int x = 0; x < floor.length; x++) {
                    for (int y = 0; y < floor[x].length; y++) {
                        if (field.equals(floor[x][y])) {
                            System.out.print(x + " " + y + "\t" + field.getClass().toString() + " " + field.getContamination() + " " + field.getState());
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
