package nba.simulator;

import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.minutedistribution.SimulateMinutesForPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NbaPlayerModel {
    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();
    private static final Random RANDOM = new Random();

    public static ModelOutput runModel(List<PlayerWithCoefs> players){

        Map<String, Map<Integer, Double>> playerPointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerThreePointsMap = initializePlayerMap(players);

        for (PlayerWithCoefs player : players) {
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior());


            double[] minutesDistributionForPrediction = SimulateMinutesForPlayer.getMinutesDistributionForPrediction(player.getMinPlayed());

            for (int i = 0; i < 40000; i++) {
                int simulateMinutesPlayed = simulateMinutesPlayed(minutesDistributionForPrediction);

                double fgAttemptedPrediction = FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, simulateMinutesPlayed);
                SimulatedPlayerScoring simulatedPlayerScoring = PLAYER_SIMULATOR.simulatePoints(player, simulateMinutesPlayed, fgAttemptedPrediction);

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

    private static int simulateMinutesPlayed(double[] minutesDistributionForPrediction) {
        double random = RANDOM.nextDouble();
        for (int i = 0; i <= 48; i++) {
            if((random-=minutesDistributionForPrediction[i]) < 0){
                return i;
            }
        }
        return 0;
    }

    private static Map<String, Map<Integer, Double>> initializePlayerMap(List<PlayerWithCoefs> players) {
        Map<String, Map<Integer, Double>> map = new HashMap<>();

        for (PlayerWithCoefs player : players) {
            map.put(player.getPlayerName(), new HashMap<>());
        }
        return map;
    }
}
