package global.controller;

import global.game.Board;
import global.game.BoardAnalyzer;
import global.faction.Faction;
import global.game.Move;

public abstract class GameController {
    protected final int playId;
    protected final Faction myFaction;
    protected Board board;
    protected BoardAnalyzer analyzer;
    private Move move;

    public GameController(int playId, Faction myFaction) {
        this.playId = playId;
        this.myFaction = myFaction;
        move = null;
    }

    public Move getNextMove() {
        if (move == null) {
            return calculateNextMove();
        }
        return move;
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    protected abstract Move calculateNextMove();
}
