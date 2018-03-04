package hu.bme.annaATbarbies.sokoban.model;

public class Floor {
    private static Floor ourInstance;

    public static Floor getInstance() {
        if (ourInstance == null) {
            ourInstance = new Floor();
        }
        return ourInstance;
    }

    private Floor() {
    }
}
