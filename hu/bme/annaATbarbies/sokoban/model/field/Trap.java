package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * A játékban lévő csapdákat reprezentálja.
 * Definiálja, hogy mi történik, ha valami a csapdára lép, valamint változtatni tudja a csapda állapotát.
 */
public class Trap extends Field {

    /**
     * Leveszi a rá lépő/tolódó dolgot a pályáról, ha nyitva van.
     * Saját magára teszi a rátolt objektumot, ha zárva van.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Trap accept function.");
    	SkeletonHelper.write("Is the trap active? 1: Yes; 2: No");
    	int responseNum = SkeletonHelper.readInt();
    	
    	if(responseNum==1) {
    		p.die();
    	}
    	else {
    		new Trap().removePushable();
        	this.setPushable(p);
    	}
    	
    	SkeletonHelper.popIndent();
    }
    
    /**
     * Lekérdezi a csapda állapotát, ha nem aktív, akkor
     * visszaadja azt a tolható elemet, ami a lépni kívánó elem előtt van, tehát amit el kell tolnia.
     */
    @Override
    public Pushable getObstacle() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Trap getObstacle function.");
    	SkeletonHelper.write("Is the trap active? 1: Yes; 2: No");
    	int trapActive = SkeletonHelper.readInt();
    	
    	if(trapActive==2) {
	    	SkeletonHelper.write("What should be on the field? 1: Empty; 2: Worker; 3: Box;");
	    	int responseNum = SkeletonHelper.readInt();
	    	
	    	Pushable ret;
	    	switch (responseNum) {
	    		default:
	    	    case 1: ret = null;
	    	    	break;
	    	    case 2: ret = new Worker();
	    	    	break;
	    	    case 3: ret = new Box();
	    	    	break;
	    	}
	
	    	SkeletonHelper.popIndent();
	    	return ret;
    	}
    	else {
    		SkeletonHelper.popIndent();
    		return null;
    	}
    }

    /**
     * kinyitja a csapdát, megöli aki rajta áll.
     */
    public void open() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Trap open function.");
    	new Worker().die();
    	SkeletonHelper.popIndent();
    }

    /**
     * bezárja a csapdát.
     */
    public void close() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Trap close function.");
    	SkeletonHelper.popIndent();
    }
}
