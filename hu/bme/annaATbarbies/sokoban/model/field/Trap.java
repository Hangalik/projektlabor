package hu.bme.annaATbarbies.sokoban.model.field;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * A jatekban levo csapdakat reprezentalja.
 * Definialja, hogy mi tortenik, ha egy tolhato objektum csapdara lep.
 */
public class Trap extends Field {
	Logger logger = Logger.getLogger(Trap.class);
	private boolean isOpened = false;

    /**
     * Ha nyitva van megoli a ratett objektumokat
     * Ha zarva van elfogadja a ratett objektumokat 
     * @param p
     */
	@Override
    public void setPushable(Pushable p) {
    	if(isOpened) {
    		logger.debug("A csapda nyitva, ezert a ratett objektum meghal.");
    		p.die();
    	}
    	else {    		
    		logger.debug("A csapda zarva, ratett objektum rajta marad.");
    		if(pushable == null) {
    			logger.debug("A mezore tolhato objektum kerult.");
    			pushable = p;
    			p.setField(this);
    		}
    		else {
    			logger.debug("A mezon mar van tolhato objektum.");
    		}
        }
    }

    /**
     * kinyitja a csapdat, megoli aki rajta van
     */
    public void open() {
    	logger.debug("A csapda kinyilt.");
    	isOpened = true;
    	if(pushable != null) {
    		logger.debug("A csapdan volt tolhato objektum.");
    		pushable.die();    		
    	}
    	else {
    		logger.debug("A csapdan nem volt tolhato objektum.");
    	}
    }

    /**
     * bezarja a csapdat
     */
    public void close() {
    	logger.debug("A csapda bezarult.");
    	isOpened = false;
    }
    /*
     * lekeri az allapotat
     * */
    @Override
    public String getState() {
    	return isOpened?"Opened":"Closed";
    }
}
