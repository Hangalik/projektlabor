package hu.bme.annaATbarbies.sokoban.model.field;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A jatekban levo fal elemeket reprezentalja.
 * Definialja, hogy mi tortenik, ha valami ralep.
 */
public class Block extends Field {

	Logger logger = Logger.getLogger(Block.class);
    /**
     * nem helyezi magara a tolhato objektumot, mivel a falelemen nem allhat semmi.
     * meghivja az ide lepni akaro objektum crush fuggvenyet
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	logger.debug("A fal elem mezo nem fogadta el a ratolt tolhato objektumot.");
    	//p.crush(dir);	//hmmmmmm
    }
    
    /**
     * Visszaterese null, mivel soha nem allhat rajta tolhato objektum
     */
    @Override
    public Pushable getObstacle() {
        logger.debug("A fal elem mezo nem ad vissza tolhato objektumot.");
        return null;
    }
}
