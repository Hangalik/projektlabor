package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;

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
        
        new Switch().removePushable();
        this.setPushable(p);
        
        p.switchMe(this);
        
        SkeletonHelper.popIndent();
    }

    /**
     * meghívja a Trap osztály open vagy close metódusát, ami átváltja a csapda állapotát.
     */
    public void switch() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Switch class switch function.");
    	
    	SkeletonHelper.write("Is the switch on? 1: Yes; 2: No");
    	int responseNum = SkeletonHelper.readInt();
    	if(responseNum==1) {
    		new Trap.close();
    	}
    	else {
    		new Trap.open();
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
        new Pushable.switchMe(this);
        SkeletonHelper.popIndent();
    }
}
