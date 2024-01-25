package logic;

public enum CellType {
    EMPTY(0),
    PLAYER(1),
    GOAL(2),
    WALL(3),
    ENEMY(4);

    private int value;

    CellType(int t) {
        value = t;
    }

    int getValue() {
        return value;
    }
}
