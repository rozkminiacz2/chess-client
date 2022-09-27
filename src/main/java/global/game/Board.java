package global.game;

public abstract class Board {
    protected Faction nextMoveFaction;
    protected abstract void insert(Faction faction, Coord coord);
    public Faction getNextMoveFaction() {
        return nextMoveFaction;
    }
    public void switchNextMoveFaction() {
        nextMoveFaction = nextMoveFaction.getOppositeFaction();
    }
}
