package tictactoe.controller;

import global.controller.GameController;
import global.game.Coord;
import global.game.Faction;
import global.game.Move;
import tictactoe.game.TicTacToeBoard;
import tictactoe.game.TicTacToeMove;

public class TicTacToeController extends GameController {

    public TicTacToeController(int playId, Faction myFaction) {
        super(playId, myFaction);
        board = new TicTacToeBoard();
    }

    @Override
    protected Move calculateNextMove() {
        Coord coord = calculateNewPieceCoord();
        return new TicTacToeMove(board, coord);
    }

    private Coord calculateNewPieceCoord() {
        return null;
    }
}
