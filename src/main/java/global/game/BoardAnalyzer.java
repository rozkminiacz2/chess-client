package global.game;

public abstract class BoardAnalyzer {

    protected final Board board;

    protected BoardAnalyzer(Board board) {
        this.board = board;
    }

    public abstract Coord getNextMoveCoord(Faction faction);
}
