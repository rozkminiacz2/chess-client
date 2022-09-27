package tictactoe.game;

import global.game.Coord;

public class TestBoardCreator {

    private TicTacToeBoard board = new TicTacToeBoard(new XPiece());

    public TestBoardCreator withRow0(Piece p1, Piece p2, Piece p3) {
        createRow(0, p1, p2, p3);
        return this;
    }

    public TestBoardCreator withRow1(Piece p1, Piece p2, Piece p3) {
        createRow(1, p1, p2, p3);
        return this;
    }

    public TestBoardCreator withRow2(Piece p1, Piece p2, Piece p3) {
        createRow(2, p1, p2, p3);
        return this;
    }

    public TicTacToeBoard build() {
        return board;
    }

    private void createRow(int row, Piece p1, Piece p2, Piece p3) {
        insertPiece(row,0, p1);
        insertPiece(row,1, p2);
        insertPiece(row,2, p3);
    }

    private void insertPiece(int row, int column, Piece piece) {
        if (piece.equals(Piece.X)) {
            board.insert(new XPiece(), new Coord(row, column));
        }
        if (piece.equals(Piece.O)) {
            board.insert(new OPiece(), new Coord(row, column));
        }
    }
}
