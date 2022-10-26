package tictactoe.game;

import global.game.Board;
import global.game.BoardVisualizer;
import global.game.Coord;
import global.faction.Faction;

public class TicTacToeBoard extends Board {
    public final int BOARD_SIZE = 3;
    private final Faction [][] arena = new Faction[BOARD_SIZE][BOARD_SIZE];
    private final BoardVisualizer<String> textVisualizer = new TicTacToeBoardSimpleTextVisualizer(this);

    public TicTacToeBoard(Faction startingFaction){
        this.nextMoveFaction = startingFaction;
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
        return textVisualizer.getVisualization();
    }

}
