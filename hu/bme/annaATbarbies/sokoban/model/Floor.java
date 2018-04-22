package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Controller;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

import java.util.ArrayList;

/**
 * A palyat reprezentalja. Ellenorzi, hogy mikor er veget a jatek, valamint az aktiv jatekosnak pontot ad.
 * --singleton--
 */
public class Floor {

    private static Floor ourInstance;

    private ArrayList<Field> fields;
    private ArrayList<Controller> workers;
    private ArrayList<Box> boxes;

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

    }

    /**
     * meghivja az eppen aktiv jatekos gainPoint() metodusat.
     */
    public void rewardCurrentPlayer() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Floor rewardCurrentPlayer function.");

        new Worker().gainPoint();

        SkeletonHelper.popIndent();
    }

    /**
     * Inicializalo folyamat
     */
    public void Initialize() {

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
     * a kapott objektumot kiveszi a megfigyelt objektumok kozul. Ha lada, akkor tobbet nem teszteli, hogy mozdithato-e a jatek vege szempontjabol. Ha munkas, akkor nem kerul ra tobbet sor.
     *
     * @param p
     */
    public void pushableDied(Pushable p) {

    }
}
