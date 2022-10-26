package global.game;

public class Coord {
    private final int row;
    private final int col;

    Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare instanceof Coord coord) {
            return this.row == coord.row && this.col == coord.col;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coord{" + "row=" + row + ", col=" + col + '}';
    }
}
