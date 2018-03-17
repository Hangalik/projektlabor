package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * A játékban lévő kapcsolókat reprezentálja.
 * Definiálja, hogy mi történik, ha valami rálép, valamint a kapcsoló állapotát változtatni tudja.
 */
public class Switch extends Field {
	
	//Kapcsolóhoz tartozó csapda
	//private Trap trap;

    /**
     * meghívja a pushable switchMe metódusát, hogy váltsa át a csapda állapotát ha láda lépett rá, ha nem, akkor nem történik semmi.
     * @param p
     */
    @Override
    public void accept(Pushable p) {
    	SkeletonHelper.appendIndent();
        SkeletonHelper.write("Switch accept function.");

        p.switchMe(this);

        p.getField().removePushable();
        this.setPushable(p);

        SkeletonHelper.popIndent();
    }

    /**
     * meghívja a Trap osztály open vagy close metódusát, ami átváltja a csapda állapotát.
     */
    public void switch_() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Switch class switch function.");
    	
    	SkeletonHelper.write("Is the switch on? 1: Yes; 2: No");
    	int responseNum = SkeletonHelper.readInt();
    	if(responseNum==1) {
    		new Trap().close();
    	}
    	else {
    		new Trap().open();
    	}
    	
    	SkeletonHelper.popIndent();
    }
    
    /**
     * meghívja a rajta lévő objektum switchMe metódusát, majd törli azt
     */
    @Override
    public void removePushable() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Switch removePushable function.");

        SkeletonHelper.write("What is being removed from the switch? 1: Worker; 2: Box;");
        int responseNum = SkeletonHelper.readInt();
        Pushable p;
        switch (responseNum) {
            default:
            case 1: p = new Worker();
                break;
            case 2: p = new Box();
                break;
        }
        p.switchMe(this);
        SkeletonHelper.popIndent();
    }
}
