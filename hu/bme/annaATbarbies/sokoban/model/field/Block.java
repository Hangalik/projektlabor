package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

/**
 * A játékban lévő fal elemeket reprezentálja.
 * Definiálja, hogy mi történik, ha valami rálép.
 */
public class Block extends Field {

    /**
     * nem helyezi magára a tolható objektumot, mivel a falelemen nem állhat semmi.
     * meghívja az ide lépni akaró objektum crush
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Block accept function.");
    	p.crush(Direction.DOWN);
    	SkeletonHelper.popIndent();
    }
    
    /**
     * Visszatérése null, mivel soha nem állhat rajta tolható objektum
     */
    @Override
    public Pushable getObstacle() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Block getObstacle function.");
        SkeletonHelper.popIndent();
        return null;
    }
}
