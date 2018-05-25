package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;

/**
 * Absztrakt osztaly. Tolhatosagot biztositja a leszarmazottjainak.
 * Rajta keresztul erthetik el a mezo osztalyt.
 * Definialja, hogy mi tortenik, ha egy tolhato objektum meghal.
 */
public abstract class Pushable {

    private static final Logger logger = Logger.getLogger(Pushable.class);

    protected Field field;

    public abstract BufferedImage getImg();

    /**
     * Definialja, hogy mi tortenik, ha egy lada tolta meg.
     *
     * @param dir
     * @param box
     */
    public abstract void push(Direction dir, Box box, int strength);

    /**
     * Definialja, hogy mi tortenik, ha egy munkas tolta meg.
     *
     * @param dir
     * @param worker
     */
    public abstract void push(Direction dir, Worker worker, int strength);

    /**
     * Definialja, hogy mi tortenik, ha ossze akarjak nyomni.
     *
     * @param dir
     * @return
     */
    public boolean crush(Direction dir) {
        logger.debug("Crush visszaad false.");
        return false;    //a worker irja felul, alap esetben semmi sem osszenyomhato
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    /**
     * TODO: No description in the doc...
     */
    public void die() {
        field.removePushable();    //eltavolitja a mezorol
        Floor.getInstance().pushableDied(this);    //szol a palyanak, hogy egy jatekossal kevesebb van
    }

    /**
     * meghivja a Switch tipusu objektum switch() metodusat, ha egy doboz ramegy a kapcsolora.
     * Itt nem csinal semmit. Amelyik leszarmazott akar valamit, az felulirja.
     *
     * @param s
     */
    public void switchMe(Switch s) {
        logger.debug("switchMe");
        //nem csinal semmit, a box fogja megvalositani
    }

    /**
     * meghivja a rewardCurrentPlayer() metodust, amikor egy doboz a helyere kerul.
     * Itt nem csinal semmit. Amelyik leszarmazott akar valamit, az felulirja.
     */
    public void onTarget() {
        logger.debug("onTarget");
        //nem csinal semmit, a box fogja megvalositani
    }
}
