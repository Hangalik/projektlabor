package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Block;
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

            obstacle.push(dir, this, remainingStrength);

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);
            }
        }
    }

    /**
     * Hamis ertekkel ter vissza, ha mar biztosan nem lehet eltolni.
     *Ez azt jelenti, hogy 
     * @return
     */
    public boolean amIPushable() {
    	logger.debug("Lada eltolhatosaganak a vizsgalata.");
    	if(!(testMovability(Direction.LEFT) || testMovability(Direction.UP))) {
    		logger.debug("A lada bal felso sarokba akadt.");
    		return false;
    	}
    	else if(!(testMovability(Direction.LEFT) || testMovability(Direction.DOWN))) {
    		logger.debug("A lada bal also sarokba akadt.");
    		return false;
    	}
    	else if(!(testMovability(Direction.RIGHT) || testMovability(Direction.UP))) {
    		logger.debug("A lada jobb felso sarokba akadt.");
    		return false;
    	}
    	else if(!(testMovability(Direction.RIGHT) || testMovability(Direction.DOWN))) {
    		logger.debug("A lada jobb also sarokba akadt.");
    		return false;
    	}
    	logger.debug("A lada mozgathato.");
        return true;
    }
    
    /**
     * Teszteli, hogy egy lada eltolhato-e egy iranyba
     * @param d az eltolas iranya
     * @return igaz, amennyiben eltolhato
     */
    private boolean testMovability(Direction d) {
    	logger.debug("Mozgathatosag tesztelese.");
    	Field iField = field;
    	while(iField.getObstacle() instanceof Box) {
    		iField = iField.getNeighbor(d);
    	}
    	if(iField instanceof Block) {
    		logger.debug("A lada nem mozdithato el.");
    		return false;
    	}
    	else {
    		logger.debug("A lada elmozdithato");
    		return true;
    	}
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
