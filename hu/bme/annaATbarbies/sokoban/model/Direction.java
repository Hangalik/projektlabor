package hu.bme.annaATbarbies.sokoban.model;

/**
 * Egy enumeráció, ami egy mező (Field) lehetséges szomszédos irányait reprezentálja: fel(Up), le(Down), jobbra(Rigth), balra(Left)
 */
public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * visszaadja az ellenkező irányt.
     * @param dir
     * @return
     */
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
