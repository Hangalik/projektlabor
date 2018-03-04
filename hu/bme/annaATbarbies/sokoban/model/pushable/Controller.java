package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;

/**
 * Interface a játékos által irányítható dolgoknak.
 */
public interface Controller {

    /**
     * Definiálja, hogy mi történik, ha a játékos lép egy megadott irányba.
     * A metódus meghívja annak a mezőnek, a getNeighbor(Direction) metódusát, amelyen a játékos épp áll.
     * Ez a metódus visszatér azzal a szomszédjával, amire a játékos lépni akar.
     * Ekkor a játékos meghívja annak a mezőnek az accept(Worker) metódusát.
     * @param dir
     */
    void step(Direction dir);

    /**
     * Ha a játékos jó helyre tolt egy ládát, pontot ad neki.
     */
    void gainPoint();
}
