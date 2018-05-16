package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A jatekban levo kapcsolokat reprezentalja.
 * Definialja, hogy mi tortenik ha valami ralep, valamint a kapcsolo allapotat valtoztatni tudja.
 */
public class Switch extends Field {
    private static final Logger logger = Logger.getLogger(Switch.class);

    //Kapcsolohoz tartozo csapda

    private Trap trap;
    private boolean trapOpened = false;

    private static BufferedImage switch1Image = null;
    private static BufferedImage switch2Image = null;

    static{
        try{
             switch1Image = ImageIO.read(new File("src/res_small/field.png"));
             switch2Image = ImageIO.read(new File("src/res_small/field.png"));
             BufferedImage switch1Top = ImageIO.read(new File("src/res_small/switch_left.png"));
             BufferedImage switch2Top = ImageIO.read(new File("src/res_small/switch_right.png"));
             Graphics g1 = switch1Image.createGraphics();
             Graphics g2 = switch2Image.createGraphics();
             g1.drawImage(switch1Top, 0, 0, null);
             g2.drawImage(switch2Top, 0, 0, null);
             g1.dispose();
             g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImg() {
        if (trapOpened) {
            return switch1Image;
        } else {
            return switch2Image;
        }
    }

    public void setTrap(Trap trap) {
        this.trap = trap;
    }

    /**
     * meghivja a trap osztaly open vagy close metodusat, ami atvaltja a csapda allapotat.
     */
    public void switch_() {
        logger.debug("A kapcsolo mezo allapotot valtott.");
        if (trapOpened) {
            trap.close();
            trapOpened = false;
        } else {
            trap.open();
            trapOpened = true;
        }
    }

    /*
     * Beallitja a tolhato objektumot es meghivja a switchMe metodusat
     * */
    @Override
    public void setPushable(Pushable p) {
        super.setPushable(p);
        logger.debug("A kapcsolo mezo meghivja a ratolt objektum switchMe fuggvenyet.");
        p.switchMe(this);
    }

    /**
     * meghivja a rajta levo objektum switchMe metodusat, majd torli azt
     */
    @Override
    public void removePushable() {
        if (pushable != null) {
            logger.debug("A kapcsolo mezo meghivja a rajta levo objektum switchMe fuggvenyet.");
            pushable.switchMe(this);
        }
        super.removePushable();
    }

    /*
     * lekeri az allapotat
     * */
    @Override
    public String getState() {
        return trapOpened ? "Flicked On" : "Flicked Off";
    }
}
