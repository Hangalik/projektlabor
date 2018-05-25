package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A jatekban levo csapdakat reprezentalja.
 * Definialja, hogy mi tortenik, ha egy tolhato objektum csapdara lep.
 */
public class Trap extends Field {
    private static final Logger logger = Logger.getLogger(Trap.class);
    private boolean isOpened = false;

    private static BufferedImage holeImg = null;

    static {
        try {
            holeImg = ImageIO.read(new File("src/res_small/field.png"));
            BufferedImage holeTop = ImageIO.read(new File("src/res_small/hole.png"));
            Graphics g = holeImg.createGraphics();
            g.drawImage(holeTop, 0, 0, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImg() {
        if (!isOpened) {
            return super.getImg();
        }

        return holeImg;
    }

    /**
     * Ha nyitva van megoli a ratett objektumokat
     * Ha zarva van elfogadja a ratett objektumokat
     *
     * @param p
     */
    @Override
    public void setPushable(Pushable p) {
        if (isOpened) {
            logger.debug("A csapda nyitva, ezert a ratett objektum meghal.");
            p.die();
        } else {
            logger.debug("A csapda zarva, ratett objektum rajta marad.");
            if (pushable == null) {
                logger.debug("A mezore tolhato objektum kerult.");
                pushable = p;
                p.setField(this);
            } else {
                logger.debug("A mezon mar van tolhato objektum.");
            }
        }
    }

    /**
     * kinyitja a csapdat, megoli aki rajta van
     */
    public void open() {
        logger.debug("A csapda kinyilt.");
        isOpened = true;
        if (pushable != null) {
            logger.debug("A csapdan volt tolhato objektum.");
            pushable.die();
        } else {
            logger.debug("A csapdan nem volt tolhato objektum.");
        }
    }

    /**
     * bezarja a csapdat
     */
    public void close() {
        logger.debug("A csapda bezarult.");
        isOpened = false;
    }

    /*
     * lekeri az allapotat
     * */
    @Override
    public String getState() {
        return isOpened ? "Opened" : "Closed";
    }
}
