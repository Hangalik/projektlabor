package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő kapcsolókat reprezentálja.
 * Definiálja, hogy mi történik, ha valami rálép, valamint a kapcsoló állapotát változtatni tudja.
 */
public class Switch extends Field {

    /**
     * meghívja a pushable switchMe metódusát, hogy váltsa át a csapda állapotát ha láda lépett rá, ha nem, akkor nem történik semmi.
     * @param p
     */
    @Override
    public void accept(Pushable p) {

    }

    /**
     * meghívja a Trap osztály open vagy close metódusát, ami átváltja a csapda állapotát.
     */
    public void switch_() {

    }
}
