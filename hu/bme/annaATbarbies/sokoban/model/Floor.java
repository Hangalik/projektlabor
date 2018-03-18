package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.field.Block;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * A pályát reprezentálja. Ellenőrzi, hogy mikor ér véget a játék, valamint az aktív játékosnak pontot ad.
 * --singleton--
 */
public class Floor {

    private static Floor ourInstance;

    public static Floor getInstance() {
        if (ourInstance == null) {
            ourInstance = new Floor();
        }
        return ourInstance;
    }

    private Floor() {
    }

    /**
     * Minden lépésnél ellenőrzi, hogy véget ért-e a játék. Ha igen, akkor meghívja a Game osztály finish() metódusát.
     */
    public void gameOver() {

    }

    /**
     * meghívja az éppen aktív játékos gainPoint() metódusát.
     */
    public void rewardCurrentPlayer() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Floor rewardCurrentPlayer function.");

        new Worker().gainPoint();

        SkeletonHelper.popIndent();
    }

    /**
     * Inicializ�l� folyamat
     */
    public void Initialize() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Floor Initialize function");

        //Create
        Field f1 = new Field();
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
        f2.setPushable(w1);

        SkeletonHelper.popIndent();
    }

    /**
     * a kapott objektumot kiveszi a megfigyelt objektumok közül. Ha láda, akkor többet nem teszteli, hogy mozdítható-e a játék vége szempontjából. Ha munkás, akkor nem kerül rá többet sor.
     *
     * @param p
     */
    public void pushableDied(Pushable p) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Floor pushableDied function.");
        SkeletonHelper.popIndent();
    }
}
