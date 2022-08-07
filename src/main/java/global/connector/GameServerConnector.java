package global.connector;

import global.game.Faction;
import global.game.Move;
import global.game.Status;

import java.util.List;
import java.util.Map;

public abstract class GameServerConnector {

    public abstract boolean publish(Move move);

    public abstract List<Status> getHistoricalPlayList(int clientId);

    public abstract List<Status> getActivePlayList(int clientId);

    public abstract Status getStatusOfPlay(int playId);

    public abstract int hostGame(Faction myFaction);

    public abstract int joinGame(int playId);

    public abstract Map<Integer, Faction> browseGamesToJoin();
}
