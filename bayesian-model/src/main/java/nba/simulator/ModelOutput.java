package nba.simulator;

import java.util.HashMap;
import java.util.Map;

public class ModelOutput {

    private final Map<String, Map<Integer, Double>> playerOutput;
    private final Map<String, Map<Integer, Double>> playerThreePointsMap;

    public ModelOutput(Map<String, Map<Integer, Double>> playerOutput, Map<String, Map<Integer, Double>> playerThreePointsMap) {
        this.playerOutput = playerOutput;
        this.playerThreePointsMap = playerThreePointsMap;
    }

    public Map<String, Map<Integer, Double>> getPlayerOutput() {
        return playerOutput;
    }

    public Map<String, Map<Integer, Double>> getPlayerThreePointsMap() {
        return this.playerThreePointsMap;
    }

    public Map<String, Map<Integer, Double>> getPlayerOverProb() {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (String playerName : this.playerOutput.keySet()) {
            Map<Integer, Double> playerOverProb = new HashMap<>();
            playerOverProb.putAll(this.playerOutput.get(playerName));
            playerOverProb.put(-1, getAverageForPlayer(playerName));
            map.put(playerName, playerOverProb);
        }
        return map;
    }

    public Map<String, Map<Integer, Double>> getPlayerThreePointsOverProb() {
        Map<String, Map<Integer, Double>> map = new HashMap<>();
        for (String playerName : this.playerThreePointsMap.keySet()) {
            Map<Integer, Double> playerOverProb = new HashMap<>();
            playerOverProb.putAll(this.playerThreePointsMap.get(playerName));
            playerOverProb.put(-1, getAverageForPlayer(playerName));
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
