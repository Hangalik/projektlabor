package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő célpont elemeket reprezentálja. Definiálja, hogy mi történik, ha valami rálép.
 */
public class Target extends Field {

    /**
     * Meghívja a ráléptetett tolható objektum onTarget() metódusát,
     * ami ha ládán hívódott meg, eltünteti a ládát és pontot ad a jelenlegi játékosnak
     * és innentől a Target mező Field-ként viselkedik.
     * @param p
     */
    @Override
    public void accept(Pushable p) {

    }
}
