package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A jatekban levo celpont elemeket reprezentalja. Definialja, ho mi tortenjen, ha valami ralep.
 */
public class Target extends Field {
    private static final Logger logger = Logger.getLogger(Target.class);

    private static BufferedImage targetImg = null;

    static{
        try{
            targetImg = ImageIO.read(new File("src/res_small/field.png"));
            BufferedImage targetTop = ImageIO.read(new File("src/res_small/target.png"));
            Graphics g = targetImg.createGraphics();
            g.drawImage(targetTop, 0, 0, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImg() {
        return targetImg;
    }

    /**
     * meghivja a raltett tolhato objektum onTarget() metodusat,
     * ami ha ladan hivodott meg, eltunteti a ladat es pontot ad a jelenlegi jatekosnak
     * es innentol a Target mezo Fieldkent viselkedik
     *
     * @param p
     */
    @Override
    public void setPushable(Pushable p) {
        if (pushable == null) {
            logger.debug("A mezore tolhato objektum kerult.");
            pushable = p;
            p.setField(this);
            logger.debug("A celpont mezo meghivja a ratolt objektum onTarget metodusat");
            p.onTarget();
        } else {
            logger.debug("A mezon mar van tolhato objektum.");
        }
    }
}
