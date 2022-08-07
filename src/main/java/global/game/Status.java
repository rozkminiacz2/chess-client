package global.game;

public class Status {
    private final int playId;
    private final Board board;
    private final boolean yourMove;

    public Status(int playId, Board board, boolean yourMove) {
        this.playId = playId;
        this.board = board;
        this.yourMove = yourMove;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isYourMove() {
        return yourMove;
    }

    public int getPlayId() {
        return playId;
    }
}
