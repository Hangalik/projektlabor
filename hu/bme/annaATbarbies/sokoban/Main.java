package hu.bme.annaATbarbies.sokoban;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Game;
import hu.bme.annaATbarbies.sokoban.model.field.Block;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.field.Hole;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;
import hu.bme.annaATbarbies.sokoban.model.field.Target;
import hu.bme.annaATbarbies.sokoban.model.field.Trap;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        int menu;
        //BasicConfigurator.configure();
        while (true) {
            logger.debug("ujrakezdtünk");
            SkeletonHelper.write("What do you want to do?");
            SkeletonHelper.appendIndent();
            SkeletonHelper.write("Press 1 to start initialize.");
            SkeletonHelper.write("Press 2 to try Worker Step function.");
            SkeletonHelper.write("Press 3 to exit.");
            menu = SkeletonHelper.readInt();
            switch (menu) {
                default:
                    break;
                case 1:
                    Game.getInstance().start();
                    break;
                case 2:
                    new Worker().step(Direction.UP);
                    break;
                case 3:
                    return;
            }
            SkeletonHelper.popIndent();
        }
    }
}
