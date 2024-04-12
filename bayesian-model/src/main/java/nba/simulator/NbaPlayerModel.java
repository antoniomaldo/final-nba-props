package nba.simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NbaPlayerModel {
    private static final Random RANDOM = new Random();

    public static ModelOutput runModel(List<PlayerWithCoefs> homePlayers, List<PlayerWithCoefs> awayPlayers, Map<String, Double> minutesExpected, Map<Integer, Double> zeroMinProb, Double totalPoints, Double matchSpread){
        double homeExp = (totalPoints - matchSpread) / 2d;
        double awayExp = totalPoints - homeExp;

        homePlayers.forEach(p->p.setHomePlayer(true));
        awayPlayers.forEach(p->p.setHomePlayer(false));

        ModelOutput homePlayersOutput = TeamSimulator.runTeamBasedModel(homePlayers, minutesExpected, homeExp, awayExp);
        ModelOutput awayPlayersOutput = TeamSimulator.runTeamBasedModel(awayPlayers, minutesExpected, homeExp, awayExp);

        Map<Integer, Map<Integer, Double>> homePlayersFgAttemptedOverProb = homePlayersOutput.getPlayerFgAttemptedOverProb();
        Map<Integer, Map<Integer, Double>> awayPlayersFgAttemptedOverProb = awayPlayersOutput.getPlayerFgAttemptedOverProb();

        Map<Integer, Double> homeFgAvgMap = buildFgAvgMap(homePlayersOutput, zeroMinProb);
        Map<Integer, Double> awayFgAvgMap = buildFgAvgMap(awayPlayersOutput, zeroMinProb);

        double homeTeamSumOfFgPredicted = homeFgAvgMap.keySet().stream().mapToDouble(i -> homeFgAvgMap.get(i)).sum();
        double awayTeamSumOfFgPredicted = awayFgAvgMap.keySet().stream().mapToDouble(i -> awayFgAvgMap.get(i)).sum();

        Map<Integer, Double> homeFgAdjustedGivenPlayedap = adjustFgPred(homeFgAvgMap, homeTeamSumOfFgPredicted, homeExp, awayExp, zeroMinProb);
        Map<Integer, Double> awayFgAdjustedGivenPlayedap = adjustFgPred(awayFgAvgMap, awayTeamSumOfFgPredicted, awayExp, homeExp, zeroMinProb);

        Map<Integer, Double> homePlayerCoefMultiplier = buildPlayerCoefMultiplier(homeFgAdjustedGivenPlayedap, homePlayersFgAttemptedOverProb);
        Map<Integer, Double> awayPlayerCoefMultiplier = buildPlayerCoefMultiplier(awayFgAdjustedGivenPlayedap, awayPlayersFgAttemptedOverProb);

        updatePlayerCoefMultiplier(homePlayers, homePlayerCoefMultiplier);
        updatePlayerCoefMultiplier(awayPlayers, awayPlayerCoefMultiplier);

//        homePlayersOutput = TeamSimulator.runTeamBasedModel(homePlayers, minutesExpected, homeExp, awayExp);
//        awayPlayersOutput = TeamSimulator.runTeamBasedModel(awayPlayers, minutesExpected, homeExp, awayExp);
//
//
//        double homePointsExp = calculateTeamPointsExp(homePlayers, homePlayersOutput.getPlayerOverProb(), zeroMinProb);
//        double awayPointsExp = calculateTeamPointsExp(awayPlayers, awayPlayersOutput.getPlayerOverProb(), zeroMinProb);
//



        List<PlayerWithCoefs> allPlayers = Stream.concat(homePlayers.stream(), awayPlayers.stream()).collect(Collectors.toList());

        return TeamSimulator.runTeamBasedModel(allPlayers, minutesExpected, homeExp, awayExp);
    }

    private static double calculateTeamPointsExp(List<PlayerWithCoefs> players, Map<Integer, Map<Integer, Double>> playerOverProb, Map<Integer, Double> zeroMinProb) {
        double  points=0;
        for(PlayerWithCoefs player : players){
            Double playerPointsExp = playerOverProb.get(player.getPlayerId()).get(-1);
            points+=playerPointsExp * (1 - zeroMinProb.get(player.getPlayerId()));
        }
        return points;
    }

    private static void updatePlayerCoefMultiplier(List<PlayerWithCoefs> players, Map<Integer, Double> teamPlayerCoefMultiplier) {
        for(PlayerWithCoefs player : players){
            player.setFgCoefMultiplier(teamPlayerCoefMultiplier.get(player.getPlayerId()));
        }
    }

    private static Map<Integer, Double> buildPlayerCoefMultiplier(Map<Integer, Double> teamFgAdjustedGivenPlayedap, Map<Integer, Map<Integer, Double>> teamPlayersFgAttemptedOverProb) {
        Map<Integer, Double> map = new HashMap<>();
        for(Integer id : teamFgAdjustedGivenPlayedap.keySet()){
            double multiplier = teamFgAdjustedGivenPlayedap.get(id) / teamPlayersFgAttemptedOverProb.get(id).get(-1);
            map.put(id, multiplier);
        }
        return map;
    }

    private static Map<Integer, Double> adjustFgPred(Map<Integer, Double> teamFgAvgMap, double teamTeamSumOfFgPredicted, double ownExp, double oppExp, Map<Integer, Double> zeroMinProb) {
        Map<Integer, Double> map = new HashMap<>();
        for(Integer id : teamFgAvgMap.keySet()){
            Double zeroProb = zeroMinProb.get(id);
            map.put(id, TeamFgAttemptedNormalizationModel.adjustPrediction(teamFgAvgMap.get(id), ownExp, oppExp, teamTeamSumOfFgPredicted) / (1 - zeroProb));
        }
        return  map;
    }

    private static Map<Integer, Double> buildFgAvgMap(ModelOutput teamPlayersOutput, Map<Integer, Double> zeroMinProb) {
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Map<Integer, Double>> playerFgAttemptedOverProb = teamPlayersOutput.getPlayerFgAttemptedOverProb();
        for(Integer id : playerFgAttemptedOverProb.keySet()){
            Double predGivenPlayed = playerFgAttemptedOverProb.get(id).get(-1);
            map.put(id, predGivenPlayed * (1 - zeroMinProb.get(id)));
        }
        return map;
    }

    private static double getTeamSumOfFgPredicted(List<PlayerWithCoefs> players, Map<Integer, Map<Integer, Double>> playersFgAttemptedOverProb, Map<Integer, Double> zeroMinProb) {
        double sum = 0;
        for(PlayerWithCoefs player : players){
            sum+=(1 - zeroMinProb.get(player.getPlayerId())) * playersFgAttemptedOverProb.get(player.getPlayerId()).get(-1);
        }
        return sum;
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
