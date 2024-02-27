package nba.simulator;

import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NbaPlayerModel {
    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();

    public static ModelOutput runModel(List<PlayerWithCoefs> players){

        Map<String, Map<Integer, Double>> playerPointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerThreePointsMap = initializePlayerMap(players);

        for (PlayerWithCoefs player : players) {
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior());
            double fgAttemptedPrediction = FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, player.getMinPlayed());


            for (int i = 0; i < 40000; i++) {
                SimulatedPlayerScoring simulatedPlayerScoring = PLAYER_SIMULATOR.simulatePoints(player, player.getMinPlayed(), fgAttemptedPrediction);

                int twoPoints = simulatedPlayerScoring.getTwoPointers();
                int threePoints = simulatedPlayerScoring.getThreePoints();
                int fts = simulatedPlayerScoring.getFts();
                int points = 3 * threePoints + 2 * twoPoints + fts;

                if(playerPointsMap.get(player.getPlayerName()).get(points) == null){
                    playerPointsMap.get(player.getPlayerName()).put(points, 1d / 40000d);
                }else {
                    playerPointsMap.get(player.getPlayerName()).put(points, playerPointsMap.get(player.getPlayerName()).get(points) + 1d / 40000d);
                }

                if(playerThreePointsMap.get(player.getPlayerName()).get(threePoints) == null){
                    playerThreePointsMap.get(player.getPlayerName()).put(threePoints, 1d / 40000d);
                }else {
                    playerThreePointsMap.get(player.getPlayerName()).put(threePoints, playerThreePointsMap.get(player.getPlayerName()).get(threePoints) + 1d / 40000d);
                }
            }
        }
        return new ModelOutput(playerPointsMap, playerThreePointsMap);
    }

    private static Map<String, Map<Integer, Double>> initializePlayerMap(List<PlayerWithCoefs> players) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();

        for (PlayerWithCoefs player : players) {
            map.put(player.getPlayerName(), new HashMap<>());
        }
        return map;
    }
}
