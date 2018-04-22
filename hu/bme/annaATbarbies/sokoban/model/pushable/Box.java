package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;
import org.apache.log4j.Logger;

/**
 * Egy ladat reprezental. Definialja, hogy egy lada hogyan tolhato, illetve visszajelzest ad a lada allapotarol.
 */
public class Box extends Pushable {

    private static final Logger logger = Logger.getLogger(Box.class);

    /**
     * Definialja, hogy mi tortenik, ha egy lada tolta meg. Ekkor ha a kovetkezo mezo ures, ratolodik.
     *
     * @param dir
     * @param box
     */
    @Override
    public void push(Direction dir, Box box, int strength) {
        logger.debug("push, strength csokken.");

        int remainingStrength = strength - field.getFriction();
        if (remainingStrength <= 0) {
            logger.debug("Nincs eleg ero, hogy eltolodjon ez a doboz.");
            return;
        }

        Field neighbor = field.getNeighbor(dir);
        Pushable obstacle = neighbor.getObstacle();

        if (obstacle == null) {
            logger.debug("Senki nincs elotte, lepunk.");

            neighbor.accept(this);
        } else {
            logger.debug("Akadaly, eltoljuk.");

            obstacle.push(dir, this, remainingStrength);

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);
            }
        }
    }

    /**
     * Definialja, hogy mi tortenik, ha egy munkas tolta meg. Ekkor a munkast megprobalja osszenyomni.
     */
    @Override
    public void push(Direction dir, Worker worker, int strength) {
        logger.debug("push, strength csokken.");

        boolean dead = worker.crush(Direction.oppositeDirection(dir));
        if (dead) {
            logger.debug("Valaki meghalt a tolasi lancban, ezert nem tolodik el.");
            return;
        }

        int remainingStrength = strength - field.getFriction();
        if (remainingStrength <= 0) {
            logger.debug("Nincs eleg ero, hogy eltolodjon ez a doboz.");
            return;
        }

        Field neighbor = field.getNeighbor(dir);
        Pushable obstacle = neighbor.getObstacle();

        if (obstacle == null) {
            logger.debug("Senki nincs elotte, lepunk.");

            neighbor.accept(this);
        } else {
            logger.debug("Akadaly, eltoljuk.");

            obstacle.push(dir, this, strength);

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);
            }
        }
    }

    /**
     * Hamis ertekkel ter vissza, ha mar biztosan nem lehet eltolni.
     *
     * @return
     */
    public boolean amIPushable() {
        return true;
    }

    /**
     * meghivja a Switch tipusu objektum switch() metodusat, ha egy doboz ramegy a kapcsolora.
     */
    @Override
    public void switchMe(Switch sw) {
        logger.debug("switchMe meghivja a kapott kapcsolo switch_ metodusat");

        sw.switch_();
    }

    /**
     * meghivja a rewardCurrentPlayer() metodust, amikor egy doboz a helyere kerul.
     */
    @Override
    public void onTarget() {
        logger.debug("onTarget jutalmazza az eppen soros jatekost");

        Floor.getInstance().rewardCurrentPlayer();
        die();
    }
}
