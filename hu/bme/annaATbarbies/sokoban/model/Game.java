package hu.bme.annaATbarbies.sokoban.model;

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
}
