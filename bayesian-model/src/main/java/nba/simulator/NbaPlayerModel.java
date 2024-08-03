package nba.simulator;

import nba.bayesianmodel.models.TeamBasedFgAdjuster;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.minutedistribution.SimulateMinutesForPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NbaPlayerModel {
    private static final Random RANDOM = new Random();

    public static ModelOutput runModel(List<PlayerWithCoefs> homePlayers, List<PlayerWithCoefs> awayPlayers, Map<String, Double> minutesExpected, Map<Integer, Double> zeroMinProb, Double totalPoints, Double matchSpread, boolean shouldAdjust){
        double homeExp = (totalPoints - matchSpread) / 2d;
        double awayExp = totalPoints - homeExp;

        homePlayers.forEach(p -> p.setHomePlayer(true));
        awayPlayers.forEach(p -> p.setHomePlayer(false));
        if(shouldAdjust) {
            List<PlayerWithCoefs> allPlayers = Stream.concat(homePlayers.stream(), awayPlayers.stream()).collect(Collectors.toList());
            ModelOutput rawModelOutput = TeamSimulator.runTeamBasedModel(allPlayers, minutesExpected, homeExp, awayExp);

            //we need "fgAttemptedPredAvg" = merged$fgAttemptedPred * (1 - merged$zeroProb)

            //Calculate fgPredicted without having to run the model
            Map<Integer, Double> homeFgAvgMap = getTeamFgAvg(homePlayers, minutesExpected, zeroMinProb);
            Map<Integer, Double> awayFgAvgMap = getTeamFgAvg(awayPlayers, minutesExpected, zeroMinProb);

            //we need teamSumFgPredAvg

            double homeTeamSumOfFgPredicted = homeFgAvgMap.keySet().stream().mapToDouble(i -> homeFgAvgMap.get(i)).sum();
            double awayTeamSumOfFgPredicted = awayFgAvgMap.keySet().stream().mapToDouble(i -> awayFgAvgMap.get(i)).sum();

            //we need teamPointsResid -> agg$pointsAvgNorm - agg$ownExp
            //agg$pointsAvgNorm -> merged$pointsAvg * (1 - merged$zeroProb)

            double homeTeamExpPoints = calculateTeamPointsExp(homePlayers, rawModelOutput.getPlayerOverProb(), zeroMinProb);
            double awayTeamExpPoints = calculateTeamPointsExp(awayPlayers, rawModelOutput.getPlayerOverProb(), zeroMinProb);

            double homeTeamResid = homeTeamExpPoints - homeExp;
            double awayTeamResid = awayTeamExpPoints - awayExp;



            for (PlayerWithCoefs player : homePlayers) {
                Double fdPredictedGivenPlayed = rawModelOutput.getPlayerFgAttemptedOverProb().get(player.getPlayerId()).get(-1);
                double fgAttemptedPredAvg = fdPredictedGivenPlayed * (1 - zeroMinProb.get(player.getPlayerId()));
                double fgAdjusted =  TeamBasedFgAdjuster.adjust(fgAttemptedPredAvg, homeTeamResid, homeTeamSumOfFgPredicted);

                double multiplier = fgAdjusted / fgAttemptedPredAvg;
                player.setFgCoefMultiplier(multiplier);
            }

            for (PlayerWithCoefs player : awayPlayers) {
                Double fdPredictedGivenPlayed = rawModelOutput.getPlayerFgAttemptedOverProb().get(player.getPlayerId()).get(-1);
                double fgAttemptedPredAvg = fdPredictedGivenPlayed * (1 - zeroMinProb.get(player.getPlayerId()));
                double fgAdjusted = TeamBasedFgAdjuster.adjust(fgAttemptedPredAvg, awayTeamResid, awayTeamSumOfFgPredicted);

                double multiplier = fgAdjusted / fgAttemptedPredAvg;
                player.setFgCoefMultiplier(multiplier);
            }

            allPlayers = Stream.concat(homePlayers.stream(), awayPlayers.stream()).collect(Collectors.toList());
            return TeamSimulator.runTeamBasedModel(allPlayers, minutesExpected, homeExp, awayExp);

        }else{
            List<PlayerWithCoefs> allPlayers = Stream.concat(homePlayers.stream(), awayPlayers.stream()).collect(Collectors.toList());
            return TeamSimulator.runTeamBasedModel(allPlayers, minutesExpected, homeExp, awayExp);

        }

    }

    private static Map<Integer, Double> getTeamFgAvg(List<PlayerWithCoefs> players, Map<String, Double> minutesExpected, Map<Integer, Double> zeroMinProb) {
        Map<Integer, Double> fgAttemptedMap = new HashMap<>();
        for(PlayerWithCoefs player : players){
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior()) * player.getFgCoefMultiplier();
            double minutesPredicted = minutesExpected.get(player.getPlayerName());
            minutesPredicted = minutesPredicted < 7 ? 7 : minutesPredicted;
            minutesPredicted = minutesPredicted > 40 ? 40 : minutesPredicted;
            double[] minutesDistributionForPrediction = SimulateMinutesForPlayer.getMinutesDistributionForPrediction((int) Math.round(minutesPredicted));
            double fgAttemptedPrediction = 0;
            for (int i = 0; i < minutesDistributionForPrediction.length; i++) {
                fgAttemptedPrediction += fgAttemptedPerMin * i * minutesDistributionForPrediction[i];
            }
            fgAttemptedMap.put(player.getPlayerId(), fgAttemptedPrediction * (1 - zeroMinProb.get(player.getPlayerId())));
        }
        return fgAttemptedMap;
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

    private static Map<Integer, Double> buildPlayerCoefMultiplier(Map<Integer, Double> teamFgAdjustedGivenPlayedap, Map<Integer, Double> teamPlayersFgAttempted, Map<Integer, Double> zeroMinProb) {
        Map<Integer, Double> map = new HashMap<>();
        for(Integer id : teamFgAdjustedGivenPlayedap.keySet()){
            double multiplier = teamFgAdjustedGivenPlayedap.get(id) / (teamPlayersFgAttempted.get(id) / (1 - zeroMinProb.get(id)));
            map.put(id, multiplier);
        }
        return map;
    }

    private static Map<Integer, Double> adjustFgPred(Map<Integer, Double> teamFgAvgMap, double teamTeamSumOfFgPredicted, double ownExp, double oppExp, Map<Integer, Double> zeroMinProb) {
        Map<Integer, Double> map = new HashMap<>();
        for(Integer id : teamFgAvgMap.keySet()){
            Double zeroProb = zeroMinProb.get(id);
//            map.put(id, TeamFgAttemptedNormalizationModel.adjustPrediction(teamFgAvgMap.get(id), ownExp, oppExp, teamTeamSumOfFgPredicted) / (1 - zeroProb));
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
