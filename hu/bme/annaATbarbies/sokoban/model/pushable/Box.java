package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
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
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Box push function.");
    	
    	Field neighbor = new Field().getNeigbor(Direction.UP);
    	Pushable obstacle = neighbor.getObstacle();
         if (obstacle == null) {
             neighbor.accept(new Box());
         }
         else {
        	 obstacle.push(Direction.UP, new Box());
        	 obstacle = neighbor.getObstacle();
             if (obstacle == null) {
                 neighbor.accept(new Box());
              }
         }
    	SkeletonHelper.popIndent();
    }

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg. Ekkor a munkást megpróbálja összenyomni.
     */
    @Override
    public void push(Direction dir, Worker worker) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Box push function.");

        boolean dead = new Worker().crush(Direction.DOWN);

        if(dead) {
            SkeletonHelper.popIndent();
            return;
        }

        Field neighbor = new Field().getNeigbor(Direction.UP);
        Pushable obstacle = neighbor.getObstacle();
        if (obstacle == null) {
            neighbor.accept(new Box());
        } else {
            obstacle.push(Direction.UP, new Box());

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                neighbor.accept(new Box());
            }
        }

        SkeletonHelper.popIndent();
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
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Box switchMe function.");
    	new Switch().switch_();
    	SkeletonHelper.popIndent();
    }

    /**
     * meghívja a rewardCurrentPlayer() metódust, amikor egy doboz a helyére kerül.
     */
    @Override
    public void onTarget() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Box onTarget function.");
    	Floor.getInstance().rewardCurrentPlayer();
    	SkeletonHelper.popIndent();
    }
}
