package nba.bayesianmodel.backtest;

import nba.bayesianmodel.common.CsvUtils;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.simulator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BacktestApplication {

    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();

    public static void main(String[] args) {
        List<PlayerWithCoefs> playerWithCoefs = CsvUtils.loadPredictionsData();

        playerWithCoefs = playerWithCoefs.stream().filter(p->p.getSeasonYear() == 2024).collect(Collectors.toList());

        List<Integer> gameIds = playerWithCoefs.stream().map(p -> p.getGameId()).distinct().collect(Collectors.toList());

        List<SimulatorPredictions> list = new ArrayList<>();
        int counter = 1;

        for (Integer gameId : gameIds) {
            System.out.println(gameId);
            List<PlayerWithCoefs> gamePlayers = playerWithCoefs.stream().filter(p -> p.getGameId() == gameId).collect(Collectors.toList());
            List<String> teams = gamePlayers.stream().map(p -> p.getTeam()).distinct().collect(Collectors.toList());

            List<PlayerWithCoefs> teamAPlayers = playerWithCoefs.stream().filter(p-> p.getTeam() == teams.get(0)).collect(Collectors.toList());
            List<PlayerWithCoefs> teamBPlayers = playerWithCoefs.stream().filter(p-> p.getTeam() == teams.get(1)).collect(Collectors.toList());

            double teamAFgPred = calculateTeamTotalFgPred(teamAPlayers);
            double teamBFgPred = calculateTeamTotalFgPred(teamBPlayers);



            for (PlayerWithCoefs player : gamePlayers) {
                double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior());
                double fgAttemptedPrediction = FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, player.getMinPlayed());

                int twoPoints = 0;
                int threePoints = 0;
                int fts = 0;
                for (int i = 0; i < 40000; i++) {
                    SimulatedPlayerScoring simulatedPlayerScoring = PLAYER_SIMULATOR.simulatePoints(player, player.getMinPlayed(), fgAttemptedPrediction);
                    twoPoints += simulatedPlayerScoring.getTwoPointers();
                    threePoints += simulatedPlayerScoring.getThreePoints();
                    fts += simulatedPlayerScoring.getFts();
                }

                double rebounds = player.getMinPlayed() * TargetPredicted.forRebounds(player.getReboundsCoef(), player.getReboundsPrior());
                double blocks = player.getMinPlayed() * TargetPredicted.forBlocks(player.getBlocksCoef(), player.getBlocksPrior());
                double steals = player.getMinPlayed() * TargetPredicted.forAssists(player.getStealsCoef(), player.getStealsPrior());
                list.add(new SimulatorPredictions(player.getGameId(),
                        player.getPlayerId(),
                        fgAttemptedPrediction,
                        twoPoints / 40000d,
                        threePoints / 40000d,
                        fts / 40000d,
                        rebounds,
                        blocks,
                        steals

                ));
            }
            counter++;
            if (counter % 100 == 0) {
                CsvUtils.savePredictions(list);
            }
        }
        CsvUtils.savePredictions(list);


    }

    private static double calculateTeamTotalFgPred(List<PlayerWithCoefs> teamPlayers) {
        double totalFgPred = 0d;
        for(PlayerWithCoefs player : teamPlayers){
            double fgAttemptedPerMin = TargetPredicted.forFgAttempted(player.getFgAttemptedPlayerCoef(), player.getFgAttemptedPrior());
            double fgAttemptedPrediction = FgAttemptedModel.getFgAttemptedPrediction(fgAttemptedPerMin, player.getMinPlayed());
            totalFgPred += fgAttemptedPrediction;
        }
        return totalFgPred;
    }
}
