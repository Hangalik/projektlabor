package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.*;

/**
 * Absztrakt osztály. Tolhatóságot biztosítja a leszármazottjainak.
 * Rajta keresztül érthetik el a mező osztályt.
 * Definiálja, hogy mi történik, ha egy tolható objektum meghal.
 */
public abstract class Pushable {

    /**
     * Definiálja, hogy mi történik, ha egy láda tolta meg.
     * @param dir
     * @param box
     */
    public abstract void push(Direction dir, Box box);

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg.
     * @param dir
     * @param worker
     */
    public abstract void push(Direction dir, Worker worker);

    /**
     * Definiálja, hogy mi történik, ha össze akarják nyomni.
     * @param dir
     * @return
     */
    public boolean crush(Direction dir) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Pushable crush function.");
    	SkeletonHelper.popIndent();
    	return false;	//a worker irja felul, alap esetben semmi sem osszenyomhato
    }

    public Field getField() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Pushable getField function.");

        SkeletonHelper.write("What kind of field is this object on? 1: Field; 2: Block; 3: Hole; 4: Trap; 5: Target; 6: Switch");
        int responseNum = SkeletonHelper.readInt();

        Field ret;
        switch (responseNum) {
            default:
            case 1: ret = new Field();
                break;
            case 2: ret = new Block();
                break;
            case 3: ret = new Hole();
                break;
            case 4: ret = new Trap();
                break;
            case 5: ret = new Target();
                break;
            case 6: ret = new Switch();
                break;
        }

        SkeletonHelper.popIndent();
        return ret;
    }

    /**
     * TODO: No description in the doc...
     */
    public void die() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Pushable die function.");

        new Field().removePushable();	//eltavolitja a mezororol
        Floor.getInstance().pushableDied(this);	//szol a palyanak, hogy egy jatekossal kevesebb van

        SkeletonHelper.popIndent();
    }

    /**
     * meghívja a Switch típusú objektum switch() metódusát, ha egy doboz rámegy a kapcsolóra.
     * Itt nem csinál semmit. Amelyik leszármazott akar valamit, az felülírja.
     * @param s
     */
    public void switchMe(Switch s) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Pushable switchMe function.");
    	
    	//nem csinal semmit, a box fogja megvalositani
    	
    	SkeletonHelper.popIndent();
    }

    /**
     * meghívja a rewardCurrentPlayer() metódust, amikor egy doboz a helyére kerül.
     * Itt nem csinál semmit. Amelyik leszármazott akar valamit, az felülírja.
     */
    public void onTarget() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Pushable onTarget function.");
    	
    	//nem csinal semmit, a box fogja megvalositani
    	
    	SkeletonHelper.popIndent();
    }
}
