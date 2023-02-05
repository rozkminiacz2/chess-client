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

    public Status getStatusOfGame(int gameId) {
        return gameServerConnector.getStatusOfGame(gameId);
    }

    public boolean publishNextMove() {
        return gameServerConnector.publish(gameController.getNextMove());
    }

    public List<Status> getHistoricalGameList(int clientId) {
        return gameServerConnector.getHistoricalGameList(clientId);
    }

    public List<Status> getActiveGameList(int clientId) {
        return gameServerConnector.getActiveGameList(clientId);
    }

    public boolean setGameAsCurrent(int gameId) {
        Status status = getStatusOfGame(gameId);
        if (!status.isYourMove()) {
            return false;
        }
        gameController.updateBoard(status.getBoard());
        return true;
    }
}
