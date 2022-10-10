package tictactoe.controller;

import global.controller.GameController;
import global.game.Coord;
import global.faction.Faction;
import global.game.Move;
import tictactoe.game.TicTacToeBoard;
import tictactoe.game.TicTacToeBoardAnalyzer;
import tictactoe.game.TicTacToeMove;

public class TicTacToeController extends GameController {

    public TicTacToeController(int playId, Faction myFaction, Faction startingFaction) {
        super(playId, myFaction);
        board = new TicTacToeBoard(startingFaction);
        analyzer = new TicTacToeBoardAnalyzer((TicTacToeBoard) board);
    }

    @Override
    protected Move calculateNextMove() {
        Coord coord = calculateNewPieceCoord();
        return new TicTacToeMove(board, coord);
    }

    private Coord calculateNewPieceCoord() {
        return analyzer.getNextMoveCoord(myFaction);
    }
}
