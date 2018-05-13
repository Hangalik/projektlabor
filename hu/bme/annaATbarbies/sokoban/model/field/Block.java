package hu.bme.annaATbarbies.sokoban.model.field;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Pushable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A jatekban levo fal elemeket reprezentalja.
 * Definialja, hogy mi tortenik, ha valami ralep.
 */
public class Block extends Field {

    private static final Logger logger = Logger.getLogger(Block.class);

    private static BufferedImage blockImg = null;

    static{
        try{
            blockImg = ImageIO.read(new File("src/res/field.png"));
            BufferedImage blockTop = ImageIO.read(new File("src/res/block.png"));
            Graphics g = blockImg.createGraphics();
            g.drawImage(blockTop, 0, 0, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getImg() {
        return blockImg;
    }

    /**
     * nem helyezi magara a tolhato objektumot, mivel a falelemen nem allhat semmi.
     * meghivja az ide lepni akaro objektum crush fuggvenyet
     *
     * @param p
     */
    @Override
    public void accept(Pushable p) {
        logger.debug("A fal elem mezo nem fogadta el a ratolt tolhato objektumot.");
        for (Direction d : neighbor.keySet()) {
            if (p.equals(neighbor.get(d).getObstacle())) {
                logger.debug("A block-ra innen lepnek: " + d.toString());
                p.crush(d);
                break;
            }
        }
    }

    /**
     * Visszaterese null, mivel soha nem allhat rajta tolhato objektum
     */
    @Override
    public Pushable getObstacle() {
        return null;
    }
}
