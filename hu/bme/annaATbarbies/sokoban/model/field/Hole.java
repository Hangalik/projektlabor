package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő lyukat reprezentálja. Definiálja, hogy mit történik, ha a lyukra lép egy tolható objektum.
 */
public class Hole extends Field {

    /**
     * leveszi a rátolódó tolható objektumot a pályáról.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Hole accept function.");
    	p.die();
    	SkeletonHelper.popIndent();
    }
    
    /**
     * A lyuk a lekérdezésre mindig null értékkel tér vissza
     */
    @Override
    public Pushable getObstacle() {
		SkeletonHelper.appendIndent();
		SkeletonHelper.write("Hole getObstacle function.");
		SkeletonHelper.popIndent();
		return null;
	}
}
