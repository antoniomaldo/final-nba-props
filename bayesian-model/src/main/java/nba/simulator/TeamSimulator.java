package nba.simulator;

import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.minutedistribution.MinutesExpectedModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static nba.minutedistribution.MinutesExpectedModel.getMinutesDistributionForPlayer;
import static nba.minutedistribution.MinutesExpectedModel.getMinutesDistributionForPrediction;
import static nba.simulator.PlayerSimulator.simulateFgAttemped;

public class TeamSimulator {
    private static final Random RANDOM = new Random();
    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();

    public static ModelOutput runTeamBasedModel(List<PlayerWithCoefs> team, Map<Integer, Double> minutesExpected, double homeExp, double awayExp, Map<Integer, Double> zeroMinsProb) {

        Map<Integer, Map<Integer, Double>> playerPointsMap = initializePlayerMap(team);
        Map<Integer, Map<Integer, Double>> playerThreePointsMap = initializePlayerMap(team);
        Map<Integer, Map<Integer, Double>> playerTwoPointsMap = initializePlayerMap(team);
        Map<Integer, Map<Integer, Double>> playerFtsPointsMap = initializePlayerMap(team);
        Map<Integer, Map<Integer, Double>> playerFgAttemptedMap = initializePlayerMap(team);
        Map<Integer, Map<Integer, Double>> playerMinMap = initializePlayerMap(team);

        for (PlayerWithCoefs player : team) {
            double ownExp = player.isHomePlayer() ? homeExp : awayExp;
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior()) * player.getFgCoefMultiplier();

            double minutesPredicted = minutesExpected.get(player.getPlayerId());
            double[] minutesDistributionForPrediction = getMinutesDistributionForPlayer((int) Math.round(minutesPredicted));


            for (int i = 0; i < 40000; i++) {
                int simulateMinutesPlayed = simulateMinutesPlayed(minutesDistributionForPrediction);

                double fgAttemptedPrediction = FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, simulateMinutesPlayed);
                int fgAttempted = simulateFgAttemped(fgAttemptedPrediction);


                SimulatedPlayerScoring simulatedPlayerScoring = PLAYER_SIMULATOR.simulatePoints(player, simulateMinutesPlayed, fgAttempted, fgAttemptedPrediction, ownExp);

                int twoPoints = simulatedPlayerScoring.getTwoPointers();
                int threePoints = simulatedPlayerScoring.getThreePoints();
                int fts = simulatedPlayerScoring.getFts();
                int points = 3 * threePoints + 2 * twoPoints + fts;

                if(playerPointsMap.get(player.getPlayerId()).get(points) == null){
                    playerPointsMap.get(player.getPlayerId()).put(points, 1d / 40000d);
                }else {
                    playerPointsMap.get(player.getPlayerId()).put(points, playerPointsMap.get(player.getPlayerId()).get(points) + 1d / 40000d);
                }

                if(playerThreePointsMap.get(player.getPlayerId()).get(threePoints) == null){
                    playerThreePointsMap.get(player.getPlayerId()).put(threePoints, 1d / 40000d);
                }else {
                    playerThreePointsMap.get(player.getPlayerId()).put(threePoints, playerThreePointsMap.get(player.getPlayerId()).get(threePoints) + 1d / 40000d);
                }

                if(playerTwoPointsMap.get(player.getPlayerId()).get(twoPoints) == null){
                    playerTwoPointsMap.get(player.getPlayerId()).put(twoPoints, 1d / 40000d);
                }else {
                    playerTwoPointsMap.get(player.getPlayerId()).put(twoPoints, playerTwoPointsMap.get(player.getPlayerId()).get(twoPoints) + 1d / 40000d);
                }

                if(playerFtsPointsMap.get(player.getPlayerId()).get(fts) == null){
                    playerFtsPointsMap.get(player.getPlayerId()).put(fts, 1d / 40000d);
                }else {
                    playerFtsPointsMap.get(player.getPlayerId()).put(fts, playerFtsPointsMap.get(player.getPlayerId()).get(fts) + 1d / 40000d);
                }

                if(playerFgAttemptedMap.get(player.getPlayerId()).get(fgAttempted) == null){
                    playerFgAttemptedMap.get(player.getPlayerId()).put(fgAttempted, 1d / 40000d);
                }else {
                    playerFgAttemptedMap.get(player.getPlayerId()).put(fgAttempted, playerFgAttemptedMap.get(player.getPlayerId()).get(fgAttempted) + 1d / 40000d);
                }

                if(playerMinMap.get(player.getPlayerId()).get(simulateMinutesPlayed) == null){
                    playerMinMap.get(player.getPlayerId()).put(simulateMinutesPlayed, 1d / 40000d);
                }else {
                    playerMinMap.get(player.getPlayerId()).put(simulateMinutesPlayed, playerMinMap.get(player.getPlayerId()).get(simulateMinutesPlayed) + 1d / 40000d);
                }
            }
        }
        return new ModelOutput(playerPointsMap, playerThreePointsMap, playerFgAttemptedMap, playerTwoPointsMap, playerFtsPointsMap, playerMinMap, zeroMinsProb);
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

    private static Map<Integer, Map<Integer, Double>> initializePlayerMap(List<PlayerWithCoefs> players) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();

        for (PlayerWithCoefs player : players) {
            map.put(player.getPlayerId(), new HashMap<>());
        }
        return map;
    }
}
