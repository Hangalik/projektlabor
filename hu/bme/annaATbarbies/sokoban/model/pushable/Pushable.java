package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;

/**
 * Absztrakt osztály. Tolhatóságot biztosítja a leszármazottjainak.
 * Rajta keresztül érthetik el a mező osztályt.
 * Definiálja, hogy mi történik, ha egy tolható objektum meghal.
 */
public abstract class Pushable {

    /**
     * Definiálja, hogy mi történik, ha egy láda tolta meg.
     * @param dir
     * @param box
     */
    public abstract void push(Direction dir, Box box);

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg.
     * @param dir
     * @param worker
     */
    public abstract void push(Direction dir, Worker worker);

    /**
     * Definiálja, hogy mi történik, ha össze akarják nyomni.
     * @param dir
     * @return
     */
    public boolean crush(Direction dir) {
        return false;
    }

    /**
     * TODO: No description in the doc...
     */
    public void die() {

    }

    /**
     * meghívja a Switch típusú objektum switch() metódusát, ha egy doboz rámegy a kapcsolóra.
     * Itt nem csinál semmit. Amelyik leszármazott akar valamit, az felülírja.
     * @param s
     */
    public void switchMe(Switch s) {

    }

    /**
     * meghívja a rewardCurrentPlayer() metódust, amikor egy doboz a helyére kerül.
     * Itt nem csinál semmit. Amelyik leszármazott akar valamit, az felülírja.
     */
    public void onTarget() {

    }
}
