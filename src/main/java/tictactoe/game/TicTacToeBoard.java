package tictactoe.game;

import global.game.Board;
import global.game.Coord;
import global.game.Faction;

public class TicTacToeBoard extends Board {
    private final int BOARD_SIZE = 3;
    private final Faction [][] arena = new Faction[BOARD_SIZE][BOARD_SIZE];

    public TicTacToeBoard(){}

    @Override
    protected void insert(Faction faction, Coord coord) {
        if (arena[coord.getX()][coord.getY()] == null) {
            arena[coord.getX()][coord.getY()] = faction;
        }
        else {
            throw new IllegalArgumentException("This coord is already in use " + coord);
        }
    }

    public boolean isWinningMovePossible(Faction myFaction) {
        return checkIfFactionIsAboutToWin(myFaction);
    }

    private boolean checkIfFactionIsAboutToWin(Faction myFaction) {
        if (isFactionAboutToWinHorizontallyAndVertically(myFaction)) return true;
        if (isFactionAboutToWinDiagonally1(myFaction)) return true;
        if (isFactionAboutToWinDiagonally2(myFaction)) return true;
        return false;
    }

    public boolean isDefensiveMoveNeeded(Faction myFaction) {
        Faction opponentFaction = myFaction.getOppositeFaction();
        return checkIfFactionIsAboutToWin(opponentFaction);
    }

    private boolean isFactionAboutToWinHorizontallyAndVertically(Faction faction) {
        for (int i = 0; i<BOARD_SIZE;i++) {
            int opponentsRowPieceCounter = 0;
            int opponentsColumnPieceCounter = 0;
            for (int j = 0; j<BOARD_SIZE;j++) {
                if (checkForFactionPiecePresence(new Coord(i,j), faction)) {
                    opponentsRowPieceCounter++;
                }
                if (checkForFactionPiecePresence(new Coord(j,i), faction)) {
                    opponentsColumnPieceCounter++;
                }
                if (opponentsRowPieceCounter >= 2 || opponentsColumnPieceCounter >=2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFactionAboutToWinDiagonally1(Faction faction) {
        int opponentsPieceCounterDiagonal1 = 0;
        if (checkForFactionPiecePresence(new Coord(0,0), faction)) {
            opponentsPieceCounterDiagonal1++;
        }
        if (checkForFactionPiecePresence(new Coord(1,1), faction)) {
            opponentsPieceCounterDiagonal1++;
        }
        if (checkForFactionPiecePresence(new Coord(2,2), faction)) {
            opponentsPieceCounterDiagonal1++;
        }
        if (opponentsPieceCounterDiagonal1 >= 2) {
            return true;
        }
        return false;
    }

    private boolean isFactionAboutToWinDiagonally2(Faction faction) {
        int opponentsPieceCounterDiagonal2 = 0;
        if (checkForFactionPiecePresence(new Coord(2,0), faction)) {
            opponentsPieceCounterDiagonal2++;
        }
        if (checkForFactionPiecePresence(new Coord(1,1), faction)) {
            opponentsPieceCounterDiagonal2++;
        }
        if (checkForFactionPiecePresence(new Coord(0,2), faction)) {
            opponentsPieceCounterDiagonal2++;
        }
        if (opponentsPieceCounterDiagonal2 >= 2) {
            return true;
        }
        return false;
    }

    private boolean checkForFactionPiecePresence(Coord coord, Faction myFaction) {
        if (arena[coord.getX()][coord.getY()] != null) {
            return arena[coord.getX()][coord.getY()].isAllyFaction(myFaction);
        }
        return false;
    }

    public String toString() {
        String row1 = "[" + getDisplay(new Coord(0, 0)) + "][" + getDisplay(new Coord(0, 1)) + "][" + getDisplay(new Coord(0, 2)) + "]\n";
        String row2 = "[" + getDisplay(new Coord(1, 0)) + "][" + getDisplay(new Coord(1, 1)) + "][" + getDisplay(new Coord(1, 2)) + "]\n";
        String row3 = "[" + getDisplay(new Coord(2, 0)) + "][" + getDisplay(new Coord(2, 1)) + "][" + getDisplay(new Coord(2, 2)) + "]";
        return row1.concat(row2).concat(row3);
    }

    private Piece getDisplay(Coord coord) {
        if (arena[coord.getX()][coord.getY()] instanceof XPiece) {
            return Piece.X;
        }
        if (arena[coord.getX()][coord.getY()] instanceof OPiece) {
            return Piece.O;
        }
        return Piece.E;
    }

}
