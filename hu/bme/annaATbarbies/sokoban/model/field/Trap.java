package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő csapdákat reprezentálja.
 * Definiálja, hogy mi történik, ha valami a csapdára lép, valamint változtatni tudja a csapda állapotát.
 */
public class Trap extends Field {

    /**
     * Leveszi a rá lépő/tolódó dolgot a pályáról, ha nyitva van.
     * Saját magára teszi a rátolt objektumot, ha zárva van.
     * @param p
     */
    @Override
    public void accept(Pushable p) {

    }

    /**
     * kinyitja a csapdát, megöli aki rajta áll.
     */
    public void open() {

    }

    /**
     * bezárja a csapdát.
     */
    public void close() {

    }
}
