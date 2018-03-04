package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;

/**
 * Egy ládát reprezentál. Definiálja, hogy egy láda hogyan tolható, illetve visszajelzést ad a láda állapotáról.
 */
public class Box extends Pushable {

    /**
     * Definiálja, hogy mi történik, ha egy láda tolta meg. Ekkor ha a következő mező üres, rátolódik.
     * @param dir
     * @param box
     */
    @Override
    public void push(Direction dir, Box box) {

    }

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg. Ekkor a munkást megpróbálja összenyomni.
     */
    @Override
    public void push(Direction dir, Worker worker) {

    }

    /**
     * Definiálja, hogy mi történik, ha össze akarják nyomni. Mivel egy láda nem összenyomható, ezért nem csinál semmit.
     * @return
     */
    @Override
    public boolean crush(Direction dir) {
        return false;
    }

    /**
     * igaz értékkel tér vissza, ha már biztosan nem lehet eltolni.
     * @return
     */
    public boolean amIPushable() {
        return false;
    }

    /**
     * meghívja a Switch típusú objektum switch() metódusát, ha egy doboz rámegy a kapcsolóra.
     */
    @Override
    public void switchMe(Switch sw) {

    }

    /**
     * meghívja a rewardCurrentPlayer() metódust, amikor egy doboz a helyére kerül.
     */
    @Override
    public void onTarget() {

    }
}
