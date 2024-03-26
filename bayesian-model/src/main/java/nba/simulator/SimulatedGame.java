package nba.simulator;

import java.util.Map;

public class SimulatedGame {

    private final Map<String, SimulatedPlayerScoring> playerScoringMap;

    public SimulatedGame(Map<String, SimulatedPlayerScoring> playerScoringMap) {
        this.playerScoringMap = playerScoringMap;
    }

    public Map<String, SimulatedPlayerScoring> getPlayerScoringMap() {
        return playerScoringMap;
    }
}
