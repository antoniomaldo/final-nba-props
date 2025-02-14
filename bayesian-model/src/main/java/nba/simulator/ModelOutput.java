package nba.simulator;

import java.util.HashMap;
import java.util.Map;

public class ModelOutput {

    private final Map<Integer, Map<Integer, Double>> playerOutput;
    private final Map<Integer, Map<Integer, Double>> playerThreePointsMap;
    private final Map<Integer, Map<Integer, Double>> fgAttemptedMap;

    private final Map<Integer, Map<Integer, Double>> playerTwoPointsMap;
    private final Map<Integer, Map<Integer, Double>> playerFtsPointsMap;
    private final Map<Integer, Map<Integer, Double>> playerMinsMap;
    private final Map<Integer, Map<Integer, Double>> playerAssistsMap;
    private final Map<Integer, Double> zeroMinsProb;

    public ModelOutput(Map<Integer, Map<Integer, Double>> playerOutput,
                       Map<Integer, Map<Integer, Double>> playerThreePointsMap,
                       Map<Integer, Map<Integer, Double>> playerFgAttemptedMap,
                       Map<Integer, Map<Integer, Double>> playerTwoPointsMap,
                       Map<Integer, Map<Integer, Double>> playerFtsPointsMap,
                       Map<Integer, Map<Integer, Double>> playerMinMap,
                       Map<Integer, Map<Integer, Double>> playerAssistsMap,
                       Map<Integer, Double> zeroMinsProb
    ) {
        this.playerOutput = playerOutput;
        this.playerThreePointsMap = playerThreePointsMap;
        this.fgAttemptedMap = playerFgAttemptedMap;
        this.playerTwoPointsMap = playerTwoPointsMap;
        this.playerFtsPointsMap = playerFtsPointsMap;
        this.playerMinsMap = playerMinMap;
        this.playerAssistsMap = playerAssistsMap;
        this.zeroMinsProb = zeroMinsProb;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerOutput);
        return map;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerThreePointsOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerThreePointsMap);
        return map;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerFgAttemptedOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.fgAttemptedMap);
        return map;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerTwoPointsOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerTwoPointsMap);
        return map;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerFtsOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerFtsPointsMap);
        return map;
    }

    public Map<Integer, Map<Integer, Double>> getPlayerAssistsOverProb() {
        Map<Integer, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerAssistsMap);
        return map;
    }

    private Map<Integer, Map<Integer, Double>> getPlayerThreePointsOverProb(Map<Integer, Map<Integer, Double>> playerMap) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        for (Integer playerName : playerMap.keySet()) {
            Map<Integer, Double> playerOverProb = new HashMap<>();
            playerOverProb.putAll(playerMap.get(playerName));
            playerOverProb.put(-1, getAverageForPlayer(playerName, playerMap));
            map.put(playerName, playerOverProb);
        }
        return map;
    }

    public double getAverageForPlayer(Integer player, Map<Integer, Map<Integer, Double>> playersMap) {
        Map<Integer, Double> playerMap = playersMap.get(player);
        double noPlayProb = this.zeroMinsProb.get(player);
        return  (1 - noPlayProb) * playerMap.keySet().stream().mapToDouble(k -> k * playerMap.get(k)).sum();
    }

    public Map<Integer, Map<Integer, Double>> getPlayerMinsMap() {
        return playerMinsMap;
    }
}
