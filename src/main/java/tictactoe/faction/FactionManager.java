package tictactoe.faction;

public class FactionManager {

    private static final XPiece xpiece = new XPiece();
    private static final OPiece opiece = new OPiece();

    public static XPiece XPiece() {
        return xpiece;
    }

    public static OPiece OPiece() {
        return opiece;
    }
}
