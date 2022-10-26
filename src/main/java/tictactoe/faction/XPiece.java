package tictactoe.faction;

import global.faction.Faction;

public class XPiece extends Faction {

    XPiece() {}

    @Override
    public boolean isEnemyFaction(Faction factionToCompare) {
        if (factionToCompare instanceof OPiece) {
            return true;
        }
        if (factionToCompare instanceof XPiece) {
            return false;
        }
        throw new IllegalArgumentException("Unexpected Faction class: " + factionToCompare.getClass());
    }

    @Override
    public Faction getOppositeFaction() {
        return FactionManager.OPiece();
    }

    @Override
    public String toString() {
        return Piece.X.toString();
    }
}
