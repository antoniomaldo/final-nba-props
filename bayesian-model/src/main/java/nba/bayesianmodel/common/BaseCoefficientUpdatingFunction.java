package nba.bayesianmodel.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseCoefficientUpdatingFunction {

    public List<PlayerPredictions> getPlayersPredictions(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] par) {
        List<PlayerPredictions> playerPredictions = new ArrayList<>();
        for (Integer playerId : playersData) {
            Double priorForPlayer = priorForPlayers.get(playerId);
            List<PlayerGameData> playerGameDataForPlayer = playersData.getPlayerGameDataForPlayer(playerId);
            List<PlayerPredictions> predsForPlayer = getPlayerPredictions(playerGameDataForPlayer, priorForPlayer, par);
            playerPredictions.addAll(predsForPlayer);
        }
        return playerPredictions;
    }

    protected abstract List<PlayerPredictions> getPlayerPredictions(List<PlayerGameData> playerGameDataForPlayer, Double priorForPlayer, double[] par);
}
