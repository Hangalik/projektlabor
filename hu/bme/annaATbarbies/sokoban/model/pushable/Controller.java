package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;

/**
 * Interface a jatekos altal iranyithato dolgoknak.
 */
public interface Controller {

    /**
     * Definialja, hogy mi tortenik, ha a jatekos lep egy megadott iranyba.
     * A metodus meghivja annak a mezonek, a getNeighbor(Direction) metodusat, amelyen a jatekos epp all.
     * Ez a metodus visszater azzal a szomszedjaval, amire a jatekos lepni akar.
     * Ekkor a jatekos meghivja annak a mezonek az accept(Worker) metodusat.
     *
     * @param dir
     */
    void step(Direction dir);

    /**
     * Ha a jatekos jo helyre tolt egy ladat, pontot ad neki.
     */
    void gainPoint();
    
    /**
     * Vissza adja a j�t�kos ID-�t
     * @return ID
     */
    int GetID();

    /**
     * Osszekeni a mezot olajjal, ezzel csuszosabba teve a mezot.
     */
    void lubricateOil();

    /**
     * Osszekeni a mezot mezzel, ezzel ragadosabba teve a mezot.
     */
    void lubricateHoney();
}
