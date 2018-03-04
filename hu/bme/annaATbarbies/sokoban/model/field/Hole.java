package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő lyukat reprezentálja. Definiálja, hogy mit történik, ha a lyukra lép egy tolható objektum.
 */
public class Hole extends Field {

    /**
     * leveszi a rátolódó tolható objektumot a pályáról.
     * @param p
     */
    @Override
    public void accept(Pushable p) {

    }
}
