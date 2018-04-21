package hu.bme.annaATbarbies.sokoban.model.field;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * A jatekban levo kapcsolokat reprezentalja.
 * Definialja, hogy mi tortenik ha valami ralep, valamint a kapcsolo allapotat valtoztatni tudja.
 */
public class Switch extends Field {
	Logger logger = Logger.getLogger(Switch.class);
	
	//Kapcsolohoz tartozo csapda
	private Trap trap;
	private boolean trapOpened = false;
    /**
     * meghivja a pushable pushme metodusat, hogy valtsa at a csapda allapotat ha lada lepett ra, ha nem, akkor nem tortenik semmi.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	super.accept(p);
    	logger.debug("A kapcsolo mezo meghivja a ratolt objektum switchMe fuggvenyet.");
    	p.switchMe(this);
    }

    /**
     * meghivja a trap osztaly open vagy close metodusat, ami atvaltja a csapda allapotat.
     */
    public void switch_() {
    	logger.debug("A kapcsolo mezo allapotot valtott.");
    	if(trapOpened) {
    		trap.close();
    		trapOpened = false;
    	}
    	else {
    		trap.open();
    		trapOpened = true;
    	}
    }
    
    /**
     * meghivja a rajta levo objektum switchMe metodusat, majd torli azt
     */
    @Override
    public void removePushable() {
    	if(pushable != null) {
    		logger.debug("A kapcsolo mezo meghivja a rajta levo objektum switchMe fuggvenyet.");
    		pushable.switchMe(this);
    	}
    	super.removePushable();
    	
    }
}
