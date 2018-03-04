package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * Egy üres mező, amin tolható objektum lehet.
 * Eldönti, hogy mi történjen, ha egy tolható objektum kerülne rá.
 * A többi pályaelem alaposztálya.
 */
public class Field {

    /**
     * törli a mezőn lévő tolható objektumot.
     */
    public void removePushable() {

    }

    /**
     * saját magára teszi a rátolt objektumot, ha rajta nincs másik tolható objektum.
     * @param p
     */
    public void accept(Pushable p) {

    }

    /**
     * visszaadja azt a tolható elemet, ami a lépni kívánó elem előtt van, tehát amit el kell tolnia.
     * @return
     */
    public Pushable getObstacle() {
        return null;
    }

    /**
     * visszaadja a Direction irányban lévő szomszédos mezőt.
     * @param dir
     * @return
     */
    public Field getNeigbor(Direction dir) {
        return null;
    }
}
