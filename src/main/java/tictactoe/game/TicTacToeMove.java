package tictactoe.game;

import global.game.Board;
import global.game.Coord;
import global.game.Move;

public class TicTacToeMove extends Move {
    private final Coord coord;

    public TicTacToeMove(Board board, Coord coord) {
        super(board);
        this.coord = coord;
    }
}
