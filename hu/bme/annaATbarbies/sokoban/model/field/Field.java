package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Box;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

/**
 * Egy üres mező, amin tolható objektum lehet.
 * Eldönti, hogy mi történjen, ha egy tolható objektum kerülne rá.
 * A többi pályaelem alaposztálya.
 */
public class Field {

    /**
     * törli a mezőn lévő tolható objektumot.
     */
    public void removePushable() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field removePushable function.");
        SkeletonHelper.popIndent();
    }

    /**
     * saját magára teszi a rátolt objektumot, ha rajta nincs másik tolható objektum.
     * @param p
     */
    public void accept(Pushable p) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field accept function.");

        new Field().removePushable();
        this.setPushable(new Worker());

        SkeletonHelper.popIndent();
    }

    /**
     * visszaadja azt a tolható elemet, ami a lépni kívánó elem előtt van, tehát amit el kell tolnia.
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
     * visszaadja a Direction irányban lévő szomszédos mezőt.
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

    public void setPushable(Pushable pushable) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Field setPushable function.");
        SkeletonHelper.popIndent();
    }
}
