package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

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

    }

    /**
     * a kapott objektumot kiveszi a megfigyelt objektumok közül. Ha láda, akkor többet nem teszteli, hogy mozdítható-e a játék vége szempontjából. Ha munkás, akkor nem kerül rá többet sor.
     * @param p
     */
    public void pushableDied(Pushable p) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Floor pushableDied function.");
        SkeletonHelper.popIndent();
    }
}
