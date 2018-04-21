package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.model.field.Block;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;

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
    private int activeWorker;

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
    }



    /**
     * Minden lepesnel ellenorzi, hogy veget ert-e a jatek. Ha igen, akkor meghivja a Game osztaly finish() metodusat.
     */
    public void gameOver() {
    	
    	boolean finished=false;
    	
    	if(workers.size()==0)
    		finished=true;
    	
    	if(boxes.size()==0)
    		finished=true;
    	
    	for(int i=0; i<boxes.size(); i++) {
    		if(boxes.get(i).amIPushable()==false)
    			break;
    		finished=true;	
    	}
    	
    	if(finished)
    		Game.getInstance().finish();
    		logger.debug("Jatek befejezodott");
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
    	activeWorker=workernumber;
    	logger.debug("Aktiv jatekos beallitva");
    }
    
    /**
     * meghivja az aktiv jatekos step fuggvenyet, a megadott iranyba
     */
    public void activePlayerMoves(Direction dir) {
    	workers.get(activeWorker).step(dir);
    }
    
    /**
     * meghivja az aktiv jatekos lubricateOil vagy lubricateHoney fuggvenyet, a megadott parametertol fuggoen
     */
    public void activePlayerlubricates(String type){
    	if(type.equals("oil"))
    		workers.get(activeWorker).lubricateOil();
    	else if(type.equals("honey"))
    		workers.get(activeWorker).lubricateHoney();
    }

    /**
     * Inicializalo folyamat
     */
    public void Initialize(String floorname) {

    	try (BufferedReader br = new BufferedReader(new FileReader(floorname))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	       // process the line.
    	    }
    	}
    	catch (FileNotFoundException e) {
    		logger.debug("Nem talalhato a fajl");
		
		}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	//TODO: Initialize...
        //Create
        /*Field f1 = new Field();
        Field f2 = new Field();
        Block b1 = new Block();
        Block b2 = new Block();
        Box bx1 = new Box();
        Worker w1 = new Worker();

        //SetNeighbor
        f1.setNeighbor(Direction.UP, f2);
        f2.setNeighbor(Direction.DOWN, f1);

        f1.setNeighbor(Direction.DOWN, b1);
        b1.setNeighbor(Direction.UP, f1);

        f1.setNeighbor(Direction.RIGHT, b2);
        b2.setNeighbor(Direction.LEFT, f2);

        //SetPushable
        f1.setPushable(bx1);
        f2.setPushable(w1);*/ 
    }
    
    /**
     * Letorli a meglevo palyat
     */
    public void clear() {
    	
    }

    /**
     * a kapott objektumot kiveszi a megfigyelt objektumok kozul. Ha lada, akkor tobbet nem teszteli, hogy mozdithato-e a jatek vege szempontjabol. Ha munkas, akkor nem kerul ra tobbet sor.
     *
     * @param p
     */
    public void pushableDied(Pushable p) {
    	for(int i=0; i<workers.size(); i++) {
    		if(workers.get(i)==p)
    			workers.remove(i);
    			logger.debug("Munkas meghalt");
    	}
    	for(int i=0; i<boxes.size(); i++) {
    		if(boxes.get(i)==p)
    			boxes.remove(i);
    			logger.debug("Doboz eltunt");
    	}
    }
    
    public void list(String type) {
    	if(type.equals("boxes")) {
    	for(int i=0; i<boxes.size(); i++) {
    		System.out.println(i);
    	}}
    	else if(type.equals("workers")) {
    		for(int i=0; i<workers.size(); i++) {
        		System.out.println(i);
        	}
    	}
    	else if(type.equals("fields")) {
    		for(int i=0; i<fields.size(); i++) {
        		System.out.println(i);
        	}
    	}
    }
}
