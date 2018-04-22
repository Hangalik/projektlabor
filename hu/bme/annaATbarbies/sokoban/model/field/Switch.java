package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

/**
 * A jatekban levo kapcsolokat reprezentalja.
 * Definialja, hogy mi tortenik ha valami ralep, valamint a kapcsolo allapotat valtoztatni tudja.
 */
public class Switch extends Field {
	Logger logger = Logger.getLogger(Switch.class);

	//Kapcsolohoz tartozo csapda

	private Trap trap;
	private boolean trapOpened = false;

	public void setTrap(Trap trap) {
		this.trap = trap;
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
    
    /*
     * Beallitja a tolhato objektumot es meghivja a switchMe metodusat
     * */
    @Override
    public void setPushable(Pushable p) {
    	super.setPushable(p);
    	logger.debug("A kapcsolo mezo meghivja a ratolt objektum switchMe fuggvenyet.");
    	p.switchMe(this);
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
    /*
     * lekeri az allapotat
     * */
    @Override
    public String getState() {
    	return trapOpened? "Flicked On":"Flicked Off";
    }
}
