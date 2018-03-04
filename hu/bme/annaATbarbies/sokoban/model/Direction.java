package hu.bme.annaATbarbies.sokoban.model;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;
    
    public static Direction oppositeDirection(Direction dir) {
        switch(dir) {
            case UP: return DOWN;
            case RIGHT: return LEFT;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            default: return UP;
        }
    }
}
