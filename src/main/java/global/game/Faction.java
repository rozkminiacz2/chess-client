package global.game;

public abstract class Faction {
    public abstract boolean isEnemyFaction(Faction factionToCompare);
    public boolean isAllyFaction(Faction factionToCompare) {
        return !isEnemyFaction(factionToCompare);
    }
    public abstract Faction getOppositeFaction();

    @Override
    public String toString() {
        return super.toString();
    }
}
