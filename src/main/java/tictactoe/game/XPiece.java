package tictactoe.game;

import global.game.Faction;

public class XPiece extends Faction {

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
        return new OPiece();
    }
}
