package tictactoe.game;

import global.game.Coord;

public class TestBoardCreator {

    private TicTacToeBoard board = new TicTacToeBoard();

    public TestBoardCreator withRow1(Piece p1, Piece p2, Piece p3) {
        createRow(0, p1, p2, p3);
        return this;
    }

    public TestBoardCreator withRow2(Piece p1, Piece p2, Piece p3) {
        createRow(1, p1, p2, p3);
        return this;
    }

    public TestBoardCreator withRow3(Piece p1, Piece p2, Piece p3) {
        createRow(2, p1, p2, p3);
        return this;
    }

    public TicTacToeBoard build() {
        return board;
    }

    private void createRow(int row, Piece p1, Piece p2, Piece p3) {
        insertRow(row, p1, 0);
        insertRow(row, p2, 1);
        insertRow(row, p3, 2);
    }

    private void insertRow(int rowNumber, Piece piece, int column) {
        if (piece.equals(Piece.X)) {
            board.insert(new XPiece(), new Coord(rowNumber, column));
        }
        if (piece.equals(Piece.O)) {
            board.insert(new OPiece(), new Coord(rowNumber, column));
        }
    }
}
