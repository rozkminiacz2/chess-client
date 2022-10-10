package tictactoe.game;

import global.game.BoardAnalyzer;
import global.game.Coord;
import global.faction.Faction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeBoardAnalyzer extends BoardAnalyzer {

    public TicTacToeBoardAnalyzer(TicTacToeBoard board) {
        super(board);
    }

    @Override
    public Coord getNextMoveCoord(Faction myFaction) {
        validateBoard(myFaction);
        if (isWinningMovePossible(myFaction)) {
            return getWinningMove(myFaction);
        }
        if (isDefensiveMoveNeeded(myFaction)) {
            return getWinningMove(myFaction.getOppositeFaction());
        }
        if (isMidFieldEmpty()) {
            return getMidFieldMove();
        }
        if (isAnyCornerFieldEmpty()) {
            return getRandomEmptyCornerMove();
        }
        return null;
    }

    private Coord getRandomEmptyCornerMove() {
        List<Coord> freeCoords = new ArrayList<>();
        if (isFieldEmpty(new Coord(0, 0))) {
            freeCoords.add(new Coord(0, 0));
        }
        if (isFieldEmpty(new Coord(0, 2))) {
            freeCoords.add(new Coord(0, 2));
        }
        if (isFieldEmpty(new Coord(2, 0))) {
            freeCoords.add(new Coord(2, 0));
        }
        if (isFieldEmpty(new Coord(2, 2))) {
            freeCoords.add(new Coord(2, 2));
        }
        if (freeCoords.isEmpty()) {
            throw new RuntimeException("Expected free coord at any corner.");
        }
        Random rand = new Random();
        return freeCoords.get(rand.nextInt(freeCoords.size()));
    }

    private boolean isAnyCornerFieldEmpty() {
        if (isFieldEmpty(new Coord(0, 0))) {
            return true;
        }
        if (isFieldEmpty(new Coord(0, 2))) {
            return true;
        }
        if (isFieldEmpty(new Coord(2, 0))) {
            return true;
        }
        if (isFieldEmpty(new Coord(2, 2))) {
            return true;
        }
        return false;
    }

    private boolean isMidFieldEmpty() {
        return isFieldEmpty(new Coord(1, 1));
    }

    private void validateBoard(Faction myFaction) {
        int myFactionPiecesCount = getTotalFactionPieces(myFaction);
        int opponentFactionPiecesCount = getTotalFactionPieces(myFaction.getOppositeFaction());
        if (Math.abs(myFactionPiecesCount-opponentFactionPiecesCount) > 1) {
            throw new RuntimeException("Board validation failure. Invalid pieces count.");
        }
    }

    private int getTotalFactionPieces(Faction myFaction) {
        int numberOfMyPiecesForColumn0 = getNumberOfPiecesInColumn(myFaction, 0);
        int numberOfMyPiecesForColumn1 = getNumberOfPiecesInColumn(myFaction, 1);
        int numberOfMyPiecesForColumn2 = getNumberOfPiecesInColumn(myFaction, 2);
        return numberOfMyPiecesForColumn0 + numberOfMyPiecesForColumn1 +numberOfMyPiecesForColumn2;
    }

    private int getNumberOfPiecesInColumn(Faction myFaction, int col) {
        int counter = 0;
        if (checkForFactionPiecePresence(new Coord(0, col), myFaction)) {
            counter++;
        }
        if (checkForFactionPiecePresence(new Coord(1, col), myFaction)) {
            counter++;
        }
        if (checkForFactionPiecePresence(new Coord(2, col), myFaction)) {
            counter++;
        }
        return counter;
    }

    private boolean checkForFactionPiecePresence(Coord coord, Faction myFaction) {
        if (getTicTacToeBoard().getArenaPiece(coord) != null) {
            return getTicTacToeBoard().getArenaPiece(coord).isAllyFaction(myFaction);
        }
        return false;
    }

    private TicTacToeBoard getTicTacToeBoard() {
        return (TicTacToeBoard) this.board;
    }

    private boolean isWinningMovePossible(Faction myFaction) {
        return checkIfFactionIsAboutToWin(myFaction);
    }

    private boolean checkIfFactionIsAboutToWin(Faction myFaction) {
        if (isFactionAboutToWinHorizontally(myFaction)) {
            return true;
        }
        if (isFactionAboutToWinVertically(myFaction)) {
            return true;
        }
        if (isFactionAboutToWinDiagonally1(myFaction)) {
            return true;
        }
        if (isFactionAboutToWinDiagonally2(myFaction)) {
            return true;
        }
        return false;
    }

    private boolean isFactionAboutToWinHorizontally(Faction myFaction) {
        if (isFactionAboutToWinForColumn(myFaction, 0)) {
            return true;
        }
        if (isFactionAboutToWinForColumn(myFaction, 1)) {
            return true;
        }
        if (isFactionAboutToWinForColumn(myFaction, 2)) {
            return true;
        }
        return false;
    }

    private boolean isFactionAboutToWinForColumn(Faction myFaction, int i) {
        int numberOfMyPiecesForRow0 = getNumberOfPiecesInRow(myFaction, i);
        int numberOfOpponentPiecesForRow0 = getNumberOfPiecesInRow(myFaction.getOppositeFaction(), i);
        return numberOfMyPiecesForRow0 == 2 && numberOfOpponentPiecesForRow0 == 0;
    }

    private int getNumberOfPiecesInRow(Faction myFaction, int row) {
        int counter = 0;
        if (checkForFactionPiecePresence(new Coord(row, 0), myFaction)) {
            counter++;
        }
        if (checkForFactionPiecePresence(new Coord(row, 1), myFaction)) {
            counter++;
        }
        if (checkForFactionPiecePresence(new Coord(row, 2), myFaction)) {
            counter++;
        }
        return counter;
    }

    private boolean isFactionAboutToWinVertically(Faction myFaction) {
        if (isFactionAboutToWinForRow(myFaction, 0)) {
            return true;
        }
        if (isFactionAboutToWinForRow(myFaction, 1)) {
            return true;
        }
        if (isFactionAboutToWinForRow(myFaction, 2)) {
            return true;
        }
        return false;
    }

    private boolean isFactionAboutToWinForRow(Faction myFaction, int i) {
        int numberOfMyPiecesForColumn0 = getNumberOfPiecesInColumn(myFaction, i);
        int numberOfOpponentPiecesForColumn0 = getNumberOfPiecesInColumn(myFaction.getOppositeFaction(), i);
        return numberOfMyPiecesForColumn0 == 2 && numberOfOpponentPiecesForColumn0 == 0;
    }

    private boolean isFactionAboutToWinDiagonally1(Faction faction) {
        int numberOfMyPieces = 0;
        int numberOfOpponentPieces = 0;
        if (checkForFactionPiecePresence(new Coord(0, 0), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(0, 0), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(2, 2), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(2, 2), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        return numberOfMyPieces >= 2 && numberOfOpponentPieces == 0;
    }

    private boolean isFactionAboutToWinDiagonally2(Faction faction) {
        int numberOfMyPieces = 0;
        int numberOfOpponentPieces = 0;
        if (checkForFactionPiecePresence(new Coord(2, 0), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(2, 0), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(0, 2), faction)) {
            numberOfMyPieces++;
        }
        if (checkForFactionPiecePresence(new Coord(0, 2), faction.getOppositeFaction())) {
            numberOfOpponentPieces++;
        }
        return numberOfMyPieces >= 2 && numberOfOpponentPieces == 0;
    }


    private Coord getWinningMove(Faction myFaction) {
        if (isFactionAboutToWinHorizontally(myFaction)) {
            return getHorizontalWinningMove(myFaction);
        }
        if (isFactionAboutToWinVertically(myFaction)) {
            return getVerticalWinningMove(myFaction);
        }
        if (isFactionAboutToWinDiagonally1(myFaction)) {
            return getDiagonalWinningMove1(myFaction);
        }
        if (isFactionAboutToWinDiagonally2(myFaction)) {
            return getDiagonalWinningMove2(myFaction);
        }
        return null;
    }

    private Coord getHorizontalWinningMove(Faction myFaction) {
        if (isFactionAboutToWinForColumn(myFaction, 0)) {
            return getFirstFreeCoordForRow(0);
        }
        if (isFactionAboutToWinForColumn(myFaction, 1)) {
            return getFirstFreeCoordForRow(1);
        }
        if (isFactionAboutToWinForColumn(myFaction, 2)) {
            return getFirstFreeCoordForRow(2);
        }
        return null;
    }

    private Coord getFirstFreeCoordForRow(int row) {
        if (isThisCoordEmpty(new Coord(row, 0))) {
            return new Coord(row, 0);
        }
        if (isThisCoordEmpty(new Coord(row, 1))) {
            return new Coord(row, 1);
        }
        if (isThisCoordEmpty(new Coord(row, 2))) {
            return new Coord(row, 2);
        }
        throw new RuntimeException("Expected free coord for row: " + row + ".");
    }

    private Coord getVerticalWinningMove(Faction myFaction) {
        if (isFactionAboutToWinForRow(myFaction, 0)) {
            return getFirstFreeCoordForColumn(0);
        }
        if (isFactionAboutToWinForRow(myFaction, 1)) {
            return getFirstFreeCoordForColumn(1);
        }
        if (isFactionAboutToWinForRow(myFaction, 2)) {
            return getFirstFreeCoordForColumn(2);
        }
        return null;
    }

    private Coord getFirstFreeCoordForColumn(int col) {
        if (isThisCoordEmpty(new Coord(0, col))) {
            return new Coord(0, col);
        }
        if (isThisCoordEmpty(new Coord(1, col))) {
            return new Coord(1, col);
        }
        if (isThisCoordEmpty(new Coord(2, col))) {
            return new Coord(2, col);
        }
        throw new RuntimeException("Expected free coord for column: " + col + ".");
    }

    private boolean isThisCoordEmpty(Coord coord) {
        return getTicTacToeBoard().getArenaPiece(coord) == null;
    }

    private int getBoardSize() {
        return getTicTacToeBoard().BOARD_SIZE;
    }

    private Coord getDiagonalWinningMove1(Faction faction) {
        if (checkForFactionPiecePresence(new Coord(0, 0), faction)) {
            return new Coord(0, 0);
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction)) {
            return new Coord(1, 1);
        }
        if (checkForFactionPiecePresence(new Coord(2, 2), faction)) {
            return new Coord(2, 2);
        }
        return null;
    }

    private Coord getDiagonalWinningMove2(Faction faction) {
        if (checkForFactionPiecePresence(new Coord(2, 0), faction)) {
            return new Coord(2, 0);
        }
        if (checkForFactionPiecePresence(new Coord(1, 1), faction)) {
            return new Coord(1, 1);
        }
        if (checkForFactionPiecePresence(new Coord(0, 2), faction)) {
            return new Coord(0, 2);
        }
        return null;
    }

    private boolean isDefensiveMoveNeeded(Faction myFaction) {
        Faction opponentFaction = myFaction.getOppositeFaction();
        return checkIfFactionIsAboutToWin(opponentFaction);
    }

    private Coord getMidFieldMove() {
        return new Coord(1,1);
    }

    private boolean isFieldEmpty(Coord coord) {
        if (getArenaPiece(coord) == null) {
            return true;
        }
        return false;
    }

    private Faction getArenaPiece(Coord coord) {
        return ((TicTacToeBoard) board).getArenaPiece(coord);
    }

}
