package global.game;

public class CoordManager {
    public static final int BOARD_SIZE = 3;
    private static final Coord[][] arena = new Coord[BOARD_SIZE][BOARD_SIZE];
    static {
        arena[0][0] = new Coord(0, 0);
        arena[0][1] = new Coord(0, 1);
        arena[0][2] = new Coord(0, 2);
        arena[1][0] = new Coord(1, 0);
        arena[1][1] = new Coord(1, 1);
        arena[1][2] = new Coord(1, 2);
        arena[2][0] = new Coord(2, 0);
        arena[2][1] = new Coord(2, 1);
        arena[2][2] = new Coord(2, 2);
    }
    public static Coord coord(int x, int y) {
        return arena[x][y];
    }
}
