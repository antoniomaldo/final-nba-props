package nba.simulator;

import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.minutedistribution.SimulateMinutesForPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static nba.simulator.PlayerSimulator.simulateFgAttemped;

public class NbaPlayerModel {
    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();
    private static final Random RANDOM = new Random();

    public static ModelOutput runModel(List<PlayerWithCoefs> players, Map<String, Double> minutesExpected){

        Map<String, Map<Integer, Double>> playerPointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerThreePointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerTwoPointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerFtsPointsMap = initializePlayerMap(players);
        Map<String, Map<Integer, Double>> playerFgAttemptedMap = initializePlayerMap(players);

        for (PlayerWithCoefs player : players) {
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior());

            double minutesPredicted = minutesExpected.get(player.getPlayerName());
            minutesPredicted = minutesPredicted < 7 ? 7 : minutesPredicted;
            minutesPredicted = minutesPredicted > 40 ? 40 : minutesPredicted;
            double[] minutesDistributionForPrediction = SimulateMinutesForPlayer.getMinutesDistributionForPrediction((int) Math.round(minutesPredicted));

            for (int i = 0; i < 40000; i++) {
                int simulateMinutesPlayed = simulateMinutesPlayed(minutesDistributionForPrediction);

                double fgAttemptedPrediction = simulateMinutesPlayed * fgAttemptedPerMin;// FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, simulateMinutesPlayed);
                int fgAttempted = simulateFgAttemped(fgAttemptedPrediction);

                SimulatedPlayerScoring simulatedPlayerScoring = PLAYER_SIMULATOR.simulatePoints(player, simulateMinutesPlayed, fgAttempted);

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

                if(playerTwoPointsMap.get(player.getPlayerName()).get(twoPoints) == null){
                    playerTwoPointsMap.get(player.getPlayerName()).put(twoPoints, 1d / 40000d);
                }else {
                    playerTwoPointsMap.get(player.getPlayerName()).put(twoPoints, playerTwoPointsMap.get(player.getPlayerName()).get(twoPoints) + 1d / 40000d);
                }

                if(playerFtsPointsMap.get(player.getPlayerName()).get(fts) == null){
                    playerFtsPointsMap.get(player.getPlayerName()).put(fts, 1d / 40000d);
                }else {
                    playerFtsPointsMap.get(player.getPlayerName()).put(fts, playerFtsPointsMap.get(player.getPlayerName()).get(fts) + 1d / 40000d);
                }

                if(playerFgAttemptedMap.get(player.getPlayerName()).get(fgAttempted) == null){
                    playerFgAttemptedMap.get(player.getPlayerName()).put(fgAttempted, 1d / 40000d);
                }else {
                    playerFgAttemptedMap.get(player.getPlayerName()).put(fgAttempted, playerFgAttemptedMap.get(player.getPlayerName()).get(fgAttempted) + 1d / 40000d);
                }

            }
        }
        return new ModelOutput(playerPointsMap, playerThreePointsMap, playerFgAttemptedMap, playerTwoPointsMap, playerFtsPointsMap);
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
