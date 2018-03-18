package hu.bme.annaATbarbies.sokoban.model;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;

/**
 * Elkezd és befejez egy játékot.
 * --singleton--
 */
public class Game {

    private static Game ourInstance;

    public static Game getInstance() {
        if (ourInstance == null) {
            ourInstance = new Game();
        }
        return ourInstance;
    }

    private Game() {
    }
    
    /**
     * Elkezdi a játékot, felépíti a pályát és elhelyezi rajta az mező elemeket, a játékosokat és a ládákat.
     */
    public void start() {
    	SkeletonHelper.appendIndent();
    	SkeletonHelper.write("Game start function.");
    	Floor.getInstance().Initialize();
    	SkeletonHelper.popIndent();
    }

    /**
     * Befejezi a játékot.
     */
    public void finish() {

    }
}
