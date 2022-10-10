package tictactoe.game;

import global.game.Board;
import global.game.Coord;
import global.faction.Faction;
import tictactoe.faction.OPiece;
import tictactoe.faction.Piece;
import tictactoe.faction.XPiece;

public class TicTacToeBoard extends Board {
    public final int BOARD_SIZE = 3;
    private final Faction [][] arena = new Faction[BOARD_SIZE][BOARD_SIZE];

    public TicTacToeBoard(Faction startingFaction){
        this.nextMoveFaction=startingFaction;
    }

    public Faction getArenaPiece(Coord coord) {
        return arena[coord.getRow()][coord.getCol()];
    }

    @Override
    protected void insert(Faction faction, Coord coord) {
        if (getArenaPiece(coord) == null) {
            setArenaPiece(coord,faction);
        }
        else {
            throw new IllegalArgumentException("This coord is already in use " + coord);
        }
    }

    private void setArenaPiece(Coord coord, Faction faction) {
        arena[coord.getRow()][coord.getCol()] = faction;
    }

    public String toString() {
        String row0 = getRow(new Coord(0, 0), new Coord(0, 1), new Coord(0, 2)).concat("\n");
        String row1 = getRow(new Coord(1, 0), new Coord(1, 1), new Coord(1, 2)).concat("\n");
        String row2 = getRow(new Coord(2, 0), new Coord(2, 1), new Coord(2, 2));
        return row0.concat(row1).concat(row2);
    }

    private String getRow(Coord coord1, Coord coord2, Coord coord3) {
        return "[" + getDisplay(coord1) + "][" + getDisplay(coord2) + "][" + getDisplay(coord3) + "]";
    }

    private Piece getDisplay(Coord coord) {
        if (getArenaPiece(coord) instanceof XPiece) {
            return Piece.X;
        }
        if (getArenaPiece(coord) instanceof OPiece) {
            return Piece.O;
        }
        return Piece.E;
    }

}
