package nba.simulator;

import java.util.HashMap;
import java.util.Map;

public class ModelOutput {

    private final Map<String, Map<Integer, Double>> playerOutput;
    private final Map<String, Map<Integer, Double>> playerThreePointsMap;
    private final Map<String, Map<Integer, Double>> fgAttemptedMap;

    private final Map<String, Map<Integer, Double>> playerTwoPointsMap;
    private final Map<String, Map<Integer, Double>> playerFtsPointsMap;
    public ModelOutput(Map<String, Map<Integer, Double>> playerOutput, Map<String, Map<Integer, Double>> playerThreePointsMap, Map<String, Map<Integer, Double>> playerFgAttemptedMap, Map<String, Map<Integer, Double>> playerTwoPointsMap, Map<String, Map<Integer, Double>> playerFtsPointsMap) {
        this.playerOutput = playerOutput;
        this.playerThreePointsMap = playerThreePointsMap;
        this.fgAttemptedMap = playerFgAttemptedMap;
        this.playerTwoPointsMap = playerTwoPointsMap;
        this.playerFtsPointsMap = playerFtsPointsMap;
    }

    public Map<String, Map<Integer, Double>> getPlayerOutput() {
        return playerOutput;
    }

    public Map<String, Map<Integer, Double>> getPlayerThreePointsMap() {
        return this.playerThreePointsMap;
    }

    public Map<String, Map<Integer, Double>> getPlayerOverProb() {
        Map<String, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerOutput);
        return map;
    }

    public Map<String, Map<Integer, Double>> getPlayerThreePointsOverProb() {
        Map<String, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerThreePointsMap);
        return map;
    }

    public Map<String, Map<Integer, Double>> getPlayerFgAttemptedOverProb() {
        Map<String, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.fgAttemptedMap);
        return map;
    }

    public Map<String, Map<Integer, Double>> getPlayerTwoPointsOverProb() {
        Map<String, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerTwoPointsMap);
        return map;
    }

    public Map<String, Map<Integer, Double>> getPlayerFtsOverProb() {
        Map<String, Map<Integer, Double>> map = getPlayerThreePointsOverProb(this.playerFtsPointsMap);
        return map;
    }

    private Map<String, Map<Integer, Double>> getPlayerThreePointsOverProb(Map<String, Map<Integer, Double>> playerMap) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (String playerName : playerMap.keySet()) {
            Map<Integer, Double> playerOverProb = new HashMap<>();
            playerOverProb.putAll(playerMap.get(playerName));
            playerOverProb.put(-1, getAverageForPlayer(playerName, playerMap));
            map.put(playerName, playerOverProb);
        }
        return map;
    }


    public double getProbabilityForOverForPlayer(String player, double overLine) {
        Map<Integer, Double> playerPointsMap = this.playerOutput.get(player);
        return playerPointsMap.keySet().stream().filter(k -> k > overLine).mapToDouble(playerPointsMap::get).sum();
    }

    public double getAverageForPlayer(String player) {
        Map<Integer, Double> playerPointsMap = this.playerOutput.get(player);
        return playerPointsMap.keySet().stream().mapToDouble(k -> k * playerPointsMap.get(k)).sum();
    }

    public double getAverageForPlayer(String player, Map<String, Map<Integer, Double>> playersMap) {
        Map<Integer, Double> playerMap = playersMap.get(player);
        return playerMap.keySet().stream().mapToDouble(k -> k * playerMap.get(k)).sum();
    }

    public double getProbabilityOfEvenScoreForPlayer(String player) {
        Map<Integer, Double> integerDoubleMap = this.playerOutput.get(player);
        return integerDoubleMap.keySet().stream().filter(k -> k % 2 == 0).mapToDouble(k -> integerDoubleMap.get(k)).sum();
    }

    public Map<String, Double> getEvenProb() {
        Map<String, Double> map = new HashMap<>();
        for (String player : this.playerOutput.keySet()) {
            map.put(player, getProbabilityOfEvenScoreForPlayer(player));
        }
        return map;
    }
}
