package global.game;

public class Status {
    private final int gameId;
    private final Board board;
    private final boolean yourMove;

    public Status(int gameId, Board board, boolean yourMove) {
        this.gameId = gameId;
        this.board = board;
        this.yourMove = yourMove;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isYourMove() {
        return yourMove;
    }

    public int getGameId() {
        return gameId;
    }
}
