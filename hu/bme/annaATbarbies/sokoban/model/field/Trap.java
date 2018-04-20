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
     * Leveszi a raleptetett/tolt objektumot a palyarol, ha nyitva van.
     * Sajat magara teszi, ha zarva van.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	if(isOpened) {
    		logger.info("A csapda nyitva, ezert a ratolt objektum meghal.");
    		p.die();
    	}
    	else {
    		logger.info("A csapda zarva, egyszeru mezokent viselkedik.");
    		super.accept(p);
    	}
    }
    
    /**
     * Lekerdezi a csapda allapotat, ha nem aktiv, akkor
     * visszaadja azt a tolhato elemet, ami a lepni kivano elem elott van, tehat amit el kell tolnia.
     */
    @Override
    public Pushable getObstacle() {
    	return super.getObstacle();	//biztos hogy kell ez?
    }

    /**
     * kinyitja a csapdat, megoli aki rajta van
     */
    public void open() {
    	logger.info("A csapda kinyilt.");
    	isOpened = true;
    	pushable.die();
    }

    /**
     * bezarja a csapdat
     */
    public void close() {
    	logger.info("A csapda bezarult.");
    	isOpened = false;
    }
}
