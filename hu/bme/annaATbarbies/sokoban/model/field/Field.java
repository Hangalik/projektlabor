package hu.bme.annaATbarbies.sokoban.model.field;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.SurfaceContamination;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * Egy altalanos mezo, amin tolhato objektum lehet.
 * Eldonti, hogy mi tonjen, ha egy tolhato objektum kerulne ra.
 * A tobbi palyaelem alaposzta.
 */
public class Field {
	Logger logger = Logger.getLogger(Field.class);
	
	//Field attributumok
	protected Pushable pushable;													//A mezon tartozkodo tolhato objektum
	protected Map<Direction, Field> neighbor =  new HashMap<Direction, Field>();	//mezo szomszedjai "Direction" iranyokban

	protected SurfaceContamination contamination = SurfaceContamination.NONE;												//a mezo surlodasi tenyezoje
    /**
     * torli a mezon levo tolhato objektumot.
     */
    public void removePushable() {
        if(pushable != null) {
        	pushable = null;
        	logger.debug("Tolhato objektum torolve.");
        }
        else {
        	logger.debug("Nincs tolhato objektum, amit torolni lehetne.");
        }
    }

    /**
     * sajat magara teszi a ratolt objektumot, ha rajta nincs masik tolhato objektum.
     * @param p
     */
    public void accept(Pushable p) {
        if(pushable == null) {
        	logger.debug("A mezo elfogadta a tolhato objektumot.");
        	p.getField().removePushable();
        	this.setPushable(p);
        }
        else {
        	logger.debug("A mezo nem fogadta el a tolhato objektumot.");
        }
    }

    /**
     * visszaadja azt a tolhato elemet, ami a lepni kivano elem elott van, tehat amit el kell tolnia.
     * @return
     */
    public Pushable getObstacle() {
        logger.debug("Lekertek a mezotol a tolhato objektumat.");
        return pushable;
    }

    /**
     * visszaadja a Direction iranyban levo szomszedos mezot.
     * @param dir
     * @return
     */
    public Field getNeigbor(Direction dir) {
        logger.debug("Lekertek a mezotol a szomszedjat.");
        return neighbor.get(dir);
    }
    
    /**
     * Beallitja a megfelelo iranyba a szomszedos elemet
     * @param dir
     * @param neig
     */
    public void setNeighbor(Direction dir, Field neig) {
    	logger.debug("Mezonek szomszed lett beallitva.");
    	neighbor.put(dir, neig);
    }
    
    /**
     * Rahelyezi a tolhato targyat a mezore
     * Csak palyaepitesnel hasznalhato
     * @param pushable
     */
    public void setPushable(Pushable p) {
    	if(pushable == null) {
    		logger.debug("A mezore tolhato objektum kerult.");
        	pushable = p;
        	p.setField(this);
        }
        else {
        	logger.debug("A mezon mar van tolhato objektum.");
        }
    }
    
    public void pourOil() {
    	logger.debug("Olajjal lett osszekenve a mezo.");
    	contamination = SurfaceContamination.OIL;
    }
    
    public void pourHoney() {
    	logger.debug("Mezzel lett osszekenve a mezo.");
    	contamination = SurfaceContamination.HONEY;
    }
        
    public int getFriction() {
    	logger.debug("A mezotol le lett kerdezve a surlodasi tenyezoje.");
    	return contamination.getValue();
    }
}
