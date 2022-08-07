package global.controller;

import global.connector.GameServerConnector;
import global.game.Status;

import java.util.List;

public class ClientController {

    GameController gameController;
    GameServerConnector gameServerConnector;

    public ClientController(GameController gameController, GameServerConnector gameServerConnector) {
        this.gameController = gameController;
        this.gameServerConnector = gameServerConnector;
    }

    public Status getStatusOfPlay(int playId) {
        return gameServerConnector.getStatusOfPlay(playId);
    }

    public boolean publishNextMove() {
        return gameServerConnector.publish(gameController.getNextMove());
    }

    public List<Status> getHistoricalPlayList(int clientId) {
        return gameServerConnector.getHistoricalPlayList(clientId);
    }

    public List<Status> getActivePlayList(int clientId) {
        return gameServerConnector.getActivePlayList(clientId);
    }

    public boolean setPlayAsCurrent(int playId) {
        Status status = getStatusOfPlay(playId);
        if (!status.isYourMove()) {
            return false;
        }
        gameController.updateBoard(status.getBoard());
        return true;
    }
}
