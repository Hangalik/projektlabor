package hu.bme.annaATbarbies.sokoban.model;

/**
 * Elkezd és befejez egy játékot.
 * --singleton--
 */
public class Game {

    private static Game ourInstance = new Game();

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

    }

    /**
     * Befejezi a játékot.
     */
    public void finish() {

    }
}
