package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A jatekban levo lyukat reprezentalja. Definialja, hogy mi tortenik, ha a lyukra lep egy tolhato objektum.
 */
public class Hole extends Field {
    private static final Logger logger = Logger.getLogger(Hole.class);

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
        return holeImg;
    }

    /**
     * leveszi a ratolt tolhato objektumot a palyarol.
     *
     * @param p
     */
    @Override
    public void setPushable(Pushable p) {
        if (pushable == null) {
            logger.debug("A mezore tolhato objektum kerult.");
            p.die();
        } else {
            logger.debug("A mezon mar van tolhato objektum.");
        }
    }

    /**
     * A lyuk a lekerdezesre mindig null ertekkel ter vissza
     */
    @Override
    public Pushable getObstacle() {
        return null;
    }
}
