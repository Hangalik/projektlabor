package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * Egy Ã¼res mezÅ‘, amin tolhatÃ³ objektum lehet.
 * EldÃ¶nti, hogy mi tÃ¶rtÃ©njen, ha egy tolhatÃ³ objektum kerÃ¼lne rÃ¡.
 * A tÃ¶bbi pÃ¡lyaelem alaposztÃ¡lya.
 */
public class Field {
	
	//Field attribútumok
	//protected Pushable pushable;
	//protected Map<Direction, Field> neighbour;
	
	//Field konstruktor
	//public Field() {
	//	neighbour = new Map<Direction, Field>();
	//}

    /**
     * tÃ¶rli a mezÅ‘n lÃ©vÅ‘ tolhatÃ³ objektumot.
     */
    public void removePushable() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field removePushable function.");
        SkeletonHelper.popIndent();
    }

    /**
     * sajÃ¡t magÃ¡ra teszi a rÃ¡tolt objektumot, ha rajta nincs mÃ¡sik tolhatÃ³ objektum.
     * @param p
     */
    public void accept(Pushable p) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field accept function.");

        p.getField().removePushable();
        this.setPushable(p);

        SkeletonHelper.popIndent();
    }

    /**
     * visszaadja azt a tolhatÃ³ elemet, ami a lÃ©pni kÃ­vÃ¡nÃ³ elem elÅ‘tt van, tehÃ¡t amit el kell tolnia.
     * @return
     */
    public Pushable getObstacle() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field getObstacle function.");
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

    /**
     * visszaadja a Direction irÃ¡nyban lÃ©vÅ‘ szomszÃ©dos mezÅ‘t.
     * @param dir
     * @return
     */
    public Field getNeigbor(Direction dir) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field getNeighbor function.");
        SkeletonHelper.write("What should be the neighboring field? 1: Field; 2: Block; 3: Hole; 4: Trap; 5: Target; 6: Switch");
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
     * Beállítja a megfelelő irányba a szomszédos elemet
     * @param dir
     * @param neig
     */
    public void setNeighbor(Direction dir, Field neig) {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Field setNeighbor function.");
    	SkeletonHelper.popIndent();
    }
    
    /**
     * Ráhelyezi a tolható tárgyat a mezőre
     * @param pushable
     */
    public void setPushable(Pushable pushable) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field setPushable function.");
        SkeletonHelper.popIndent();
    }

    public void pourOil() {

    }

    public void pourHoney() {

    }

    public int getFriction() {
        return 0;
    }
}
