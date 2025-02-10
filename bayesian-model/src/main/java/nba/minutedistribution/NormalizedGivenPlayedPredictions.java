package nba.minutedistribution;

import nba.dto.PlayerRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalizedGivenPlayedPredictions {

    private static final Map<Integer, Double> TOTAL_MINS_EXPECTED_GIVEN_PLAYERS = new HashMap<>();

    static {
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(7, 228.4208);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(8, 230.0033);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(9, 231.5858);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(10, 233.1684);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(11, 234.7509);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(12, 236.3334);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(13, 237.9159);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(14, 239.4984);
        TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.put(15, 240d);
    }
    public static List<PlayerRequest> addNormalizedPredictions(List<PlayerRequest> team, double ownExp, double oppExp){

        double teamSumAvgMinutes = team.stream().
                filter(p-> p.getPmin() > 0).
                mapToDouble(PlayerRequest::getAverageMinutesInSeasonOrPmin).sum();

        List<PlayerRequest> teamWithPreds = new ArrayList<>();
        for(PlayerRequest playerRequest : team){
            double zeroProb = ZeroMinutesModel.zeroMinutesProb(playerRequest.getPmin(), playerRequest.getStarter(), 0);
            double averageGivenPlayed = MinutesGivenPlayedModel.getAverageGivenPlayed((int) playerRequest.getPmin(), playerRequest.getAverageMinutesInSeasonOrPmin(), ownExp, oppExp, 2024, playerRequest.getStarter(), 0, teamSumAvgMinutes, playerRequest.getLastGameMinOrPmin());
            teamWithPreds.add(playerRequest.toBuilder().zeroPred(zeroProb).givenPlayedPred(averageGivenPlayed).build());
        }


        double sumRawAvg = teamWithPreds.stream().mapToDouble(p->(1 - p.getZeroPred()) * p.getGivenPlayedPred()).sum();

        int size = getTeamSize(team);

        double predMinsGivenNumbPlayers = TOTAL_MINS_EXPECTED_GIVEN_PLAYERS.get(size);

        boolean atLeast3PlayersWithLessThan20Pred = teamWithPreds.stream().filter(p->(1 - p.getZeroPred())*p.getGivenPlayedPred() < 20).count() > 2;
        List<PlayerRequest> teamWithNormPreds = new ArrayList<>();

        double sumRawAvgNonStarters = teamWithPreds.stream().filter(p->p.getStarter() == 0).mapToDouble(p->(1 - p.getZeroPred())*p.getGivenPlayedPred()).sum();

        if(atLeast3PlayersWithLessThan20Pred){ //Only normalize the bench
            double gamePredMis = predMinsGivenNumbPlayers - teamWithPreds.stream().filter(p->p.getStarter() == 1).mapToDouble(p->(1 - p.getZeroPred())*p.getGivenPlayedPred()).sum();

            for(PlayerRequest playerRequest : teamWithPreds){
                double rawPred = (1 - playerRequest.getZeroPred()) * playerRequest.getGivenPlayedPred();
                if(playerRequest.getStarter() == 1){
                    teamWithNormPreds.add(playerRequest.toBuilder().teamAdjustedMinAvg(rawPred).build());
                }else {
                    teamWithNormPreds.add(playerRequest.toBuilder().teamAdjustedMinAvg(rawPred * gamePredMis / sumRawAvgNonStarters).build());
                }
            }
        }else{
            double gamePredMis = predMinsGivenNumbPlayers;
            for(PlayerRequest playerRequest : teamWithPreds){
                double rawPred = (1 - playerRequest.getZeroPred()) * playerRequest.getGivenPlayedPred();

                teamWithNormPreds.add(playerRequest.toBuilder().teamAdjustedMinAvg(rawPred * gamePredMis / sumRawAvg).build());
            }
        }

//        teamWithNormPreds = new ArrayList<>();
//
//        for(PlayerRequest playerRequest : team){
//            teamWithNormPreds.add(playerRequest.toBuilder().teamAdjustedMinAvg(playerRequest.getPmin()).givenPlayedPred(playerRequest.getPmin()).build());
//        }
        return teamWithNormPreds;
    }

    private static int getTeamSize(List<PlayerRequest> team) {
        int size = team.size();
        size = size < 7 ? 7 : (size > 15 ? 15 : size);
        return size;
    }
}
