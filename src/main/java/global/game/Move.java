package global.game;

public abstract class Move {
    private final Board board;

    public Move(Board board) {
        this.board = board;
    }
}
