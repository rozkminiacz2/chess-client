package tictactoe.game;

import global.game.BoardVisualizer;
import global.game.Coord;
import tictactoe.faction.OPiece;
import tictactoe.faction.Piece;
import tictactoe.faction.XPiece;

import static global.game.CoordManager.coord;

public class TicTacToeBoardSimpleTextVisualizer implements BoardVisualizer<String> {
    private final TicTacToeBoard ticTacToeBoard;

    public TicTacToeBoardSimpleTextVisualizer(TicTacToeBoard ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;
    }

    public String getVisualization() {
        String row0 = getRow0AsString();
        String row1 = getRow1AsString();
        String row2 = getRow2AsString();
        return row0.concat(row1).concat(row2);
    }

    private String getRow2AsString() {
        return getRowAsString(coord(2, 0), coord(2, 1), coord(2, 2));
    }

    private String getRow1AsString() {
        return getRowAsString(coord(1, 0), coord(1, 1), coord(1, 2)).concat("\n");
    }

    private String getRow0AsString() {
        return getRowAsString(coord(0, 0), coord(0, 1), coord(0, 2)).concat("\n");
    }

    private String getRowAsString(Coord coord1, Coord coord2, Coord coord3) {
        return "[" + getPieceSymbol(coord1) + "][" + getPieceSymbol(coord2) + "][" + getPieceSymbol(coord3) + "]";
    }

    private Piece getPieceSymbol(Coord coord) {
        if (ticTacToeBoard.getArenaPiece(coord) instanceof XPiece) {
            return Piece.X;
        }
        if (ticTacToeBoard.getArenaPiece(coord) instanceof OPiece) {
            return Piece.O;
        }
        return Piece.E;
    }

}