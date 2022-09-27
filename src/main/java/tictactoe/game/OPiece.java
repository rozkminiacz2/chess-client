package tictactoe.game;

import global.game.Faction;

public class OPiece extends Faction {

    @Override
    public boolean isEnemyFaction(Faction factionToCompare) {
        if (factionToCompare instanceof XPiece) {
            return true;
        }
        if (factionToCompare instanceof OPiece) {
            return false;
        }
        throw new IllegalArgumentException("Unexpected Faction class: " + factionToCompare.getClass());
    }

    @Override
    public Faction getOppositeFaction() {
        return new XPiece();
    }

    @Override
    public String toString() {
        return Piece.O.toString();
    }
}
