package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;

public class Worker extends Pushable implements Controller{

    @Override
    public void step(Direction dir) {

    }

    @Override
    public void gainPoint() {

    }

    /**
     * Definiálja, hogy mi történik, ha egy láda tolta meg. Ekkor az állapota láda által tolttá változik.
     * @param dir
     * @param box
     */
    @Override
    public void push(Direction dir, Box box) {

    }

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg.
     * @param dir
     * @param worker
     */
    @Override
    public void push(Direction dir, Worker worker) {

    }

    /**
     * Definiálja, hogy mi történik, ha össze akarják nyomni.
     * Ha az állapota az, hogy láda tolta, akkor megöli magát.
     * @param dir
     * @return
     */
    public boolean crush (Direction dir) {
        return false;
    }
}
