package hu.bme.annaATbarbies.sokoban.model;

import org.apache.log4j.Logger;

/**
 * Elkezd es befejez egy jatekot.
 * --singleton--
 */
public class Game {
    Logger logger = Logger.getLogger(Game.class);

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
     * Elkezdi a jatekot, felepiti a palyat es elhelyezi rajta az mezo elemeket, a jatekosokat es a ladakat.
     */
    public void start(String floorname) {

        Floor.getInstance().Initialize(floorname);
    }

    /**
     * Befejezi a játékot.
     */
    public void finish() {
        logger.debug("A jatek befejezodott");
    }
}
