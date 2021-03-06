package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Worker extends Pushable implements Controller {

    private static final Logger logger = Logger.getLogger(Worker.class);
    private static final int initialStrength = 10;
    private static BufferedImage player1Img = null;
    private static BufferedImage player2Img = null;
    private static BufferedImage player3Img = null;
    private static BufferedImage player4Img = null;

    static {
        try {
            player1Img = ImageIO.read(new File("src/res_small/worker1.png"));
            player2Img = ImageIO.read(new File("src/res_small/worker2.png"));
            player3Img = ImageIO.read(new File("src/res_small/worker3.png"));
            player4Img = ImageIO.read(new File("src/res_small/worker4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImg() {
        switch(ID % 4) {
            default:
            case 0:
                return player1Img;
            case 1:
                return player2Img;
            case 2:
                return player3Img;
            case 3:
                return player4Img;
        }
    }

    private enum WorkerState {
        STEPPING, PUSHED_BY_BOX, PUSHED_BY_WORKER;
    }

    private WorkerState workerState;
    private int ID;
    private int point;

    public Worker(int id) {
        ID = id;
        this.point = 0;
    }

    // Rendezeshez.
    @Override
    public int compareTo(Controller o) {
        return o.getPoint() - this.getPoint();
    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public int getID(){
        return ID;
    }

    //a jatekos lep
    @Override
    public void step(Direction dir) {
        logger.debug("step");

        workerState = WorkerState.STEPPING;

        Field neighbor = field.getNeighbor(dir);
        Pushable obstacle = neighbor.getObstacle();    //lekeri a szomszedos mezo tolhato objektumat

        if (obstacle == null) {
            logger.debug("Senki nincs elotte, lepunk.");

            neighbor.accept(this);            //ha nincs, akkor a szomszedos mezore lep
        } else {
            logger.debug("Akadaly, eltoljuk.");

            obstacle.push(dir, this, initialStrength);    //ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);            //ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }                                            //ha nem, akkor nem lep sehova
        }
    }

    @Override
    public void gainPoint() {
        point++;
        logger.debug("gainPoint");
    }

    @Override
    public void lubricateOil() {
        field.pourOil();
    }

    @Override
    public void lubricateHoney() {
        field.pourHoney();
    }

    /**
     * Definialja, hogy mi tortenik, ha egy lada tolta meg. Ekkor az allapota lada altal toltta valtozik.
     *
     * @param dir
     * @param box
     */
    @Override
    public void push(Direction dir, Box box, int strength) {
        logger.debug("push, strength valtozatlan");

        workerState = WorkerState.PUSHED_BY_BOX;

        Field neighbor = field.getNeighbor(dir);
        Pushable obstacle = neighbor.getObstacle();    //lekeri a szomszedos mezo tolhato objektumat

        if (obstacle == null) {
            logger.debug("Senki nincs elotte, lepunk.");

            neighbor.accept(this);            //ha nincs, akkor a szomszedos mezore lep
        } else {
            logger.debug("Akadaly, eltoljuk.");

            obstacle.push(dir, this, strength);    //ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);            //ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }                                            //ha nem, akkor nem lep sehova
        }
    }
    
    /**
     * Definialja, hogy mi tortenik, ha egy munkas tolta meg.
     *
     * @param dir
     * @param worker
     */
    @Override
    public void push(Direction dir, Worker worker, int strength) {
        logger.debug("push, strength valtozatlan");

        workerState = WorkerState.PUSHED_BY_WORKER;

        Field neighbor = field.getNeighbor(dir);
        Pushable obstacle = neighbor.getObstacle();    //lekeri a szomszedos mezo tolhato objektumat

        if (obstacle == null) {
            logger.debug("Senki nincs elotte, lepunk.");

            neighbor.accept(this);            //ha nincs, akkor a szomszedos mezore lep
        } else {
            logger.debug("Akadaly, eltoljuk.");

            obstacle.push(dir, this, strength);    //ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                logger.debug("Most mar ures, lepunk.");

                neighbor.accept(this);            //ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }                                            //ha nem, akkor nem lep sehova
        }
    }

    /**
     * Definialja, hogy mi tortenik, ha ossze akarjak nyomni.
     * Ha az allapota az, hogy lada tolta, akkor megoli magat.
     *
     * @param dir
     * @return
     */
    public boolean crush(Direction dir) {
        logger.debug("crush");

        switch (workerState) {
            default:
            case STEPPING:
                logger.debug("Nem hal meg.");

                return false;

            case PUSHED_BY_BOX:
                logger.debug("Meghal, mert osszenyomjak.");

                this.die();
                return true;

            case PUSHED_BY_WORKER:
                logger.debug("Visszafele hivja a crusht.");

                Field crushNeighbor = field.getNeighbor(dir);
                return crushNeighbor.getObstacle().crush(dir);  // Elvileg itt nem adhat vissza nullt a getObstacle()
        }
    }
}
