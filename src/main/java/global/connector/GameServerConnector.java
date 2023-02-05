package global.connector;

import global.faction.Faction;
import global.game.Move;
import global.game.Status;

import java.util.List;
import java.util.Map;

public abstract class GameServerConnector {

    public abstract boolean publish(Move move);

    public abstract List<Status> getHistoricalGameList(int clientId);

    public abstract List<Status> getActiveGameList(int clientId);

    public abstract Status getStatusOfGame(int playId);

    public abstract int hostGame(Faction myFaction);

    public abstract int joinGame(int gameId);

    public abstract Map<Integer, Faction> browseGamesToJoin();
}
