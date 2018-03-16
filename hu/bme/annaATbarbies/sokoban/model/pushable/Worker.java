package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.field.Field;

public class Worker extends Pushable implements Controller {

    @Override
    public void step(Direction dir) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker step function.");

        Field neighbor = new Field().getNeigbor(Direction.UP);
        Pushable obstacle = neighbor.getObstacle();
        if (obstacle == null) {
            neighbor.accept(new Worker());
        } else {
            obstacle.push(Direction.UP, new Worker());

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                neighbor.accept(new Worker());
            }
        }

        SkeletonHelper.popIndent();
    }

    @Override
    public void gainPoint() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker gainPoint function.");
        SkeletonHelper.popIndent();
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
