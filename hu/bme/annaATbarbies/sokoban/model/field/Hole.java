package hu.bme.annaATbarbies.sokoban.model.field;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A jatekban levo lyukat reprezentalja. Definialja, hogy mi tortenik, ha a lyukra lep egy tolhato objektum.
 */
public class Hole extends Field {
	Logger logger = Logger.getLogger(Hole.class);
    /**
     * leveszi a ratolt tolhato objektumot a palyarol.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	logger.info("A lyuk megoli a ratolt tolhato objektumot.");
    	p.die();
    }
    
    /**
     * A lyuk a lekerdezesre mindig null ertekkel ter vissza
     */
    @Override
    public Pushable getObstacle() {
		logger.info("A lyuk mezo nem ad vissza tolhato objektumot.");
		return null;
	}
}
