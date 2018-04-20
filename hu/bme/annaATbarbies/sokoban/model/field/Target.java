package hu.bme.annaATbarbies.sokoban.model.field;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A jatekban levo celpont elemeket reprezentalja. Definialja, ho mi tortenjen, ha valami ralep.
 */
public class Target extends Field {
	Logger logger = Logger.getLogger(Target.class);

    /**
     * meghivja a raleptetett tolhato objektum onTarget() metodusat,
     * ami ha ladan hivodott meg, eltunteti a ladat es pontot ad a jelenlegi jatekosnak
     * es innentol a Target mezo Fieldkent viselkedik
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	super.accept(p);
    	logger.info("A celpont mezo meghivja a ratolt objektum onTarget metodusat");
    	p.onTarget();
    }
}
