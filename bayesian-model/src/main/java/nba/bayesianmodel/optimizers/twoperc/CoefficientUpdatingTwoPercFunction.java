package nba.bayesianmodel.optimizers.twoperc;


import nba.bayesianmodel.common.BaseCoefficientUpdatingFunction;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoefficientUpdatingTwoPercFunction extends BaseCoefficientUpdatingFunction {

    @Override
    protected List<PlayerPredictions> getPlayerPredictions(List<PlayerGameData> playerData, Double priorForPlayer, double[] par) {
        if (playerData.size() <= 1) {
            return new ArrayList<>();
        } else {
            List<PlayerPredictions> predictions = new ArrayList<>();

            double weight1 = par[0];
            double weight2 = par[1];

            playerData = playerData.stream().
                    sorted(Comparator.comparingInt(PlayerGameData::getDate)).
                    collect(Collectors.toList());

            double playerCoef = 0;
            double targetPredicted;

            int gamesInSeason = 0;
            int numbGames = 1;
            double real;
            double resid;
            int lastSeasonYear = playerData.get(0).getSeasonYear();

            for (int i = 0; i < playerData.size(); i++) {
                PlayerGameData playerGameData = playerData.get(i);
                if (playerGameData.getSeasonYear() > lastSeasonYear) {
                    gamesInSeason = 1;
                    lastSeasonYear = playerGameData.getSeasonYear();
                } else {
                    gamesInSeason++;
                }
                targetPredicted = TargetPredicted.forTwoPerc(playerCoef, priorForPlayer);


                int twoPointsAttempted = playerGameData.getTwoAttempted();
                if (twoPointsAttempted > 0) {
                    real = (double) playerGameData.getTwoMade() / twoPointsAttempted;
                    resid = real - targetPredicted;
                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPredicted, real, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                    playerCoef += Math.exp(weight1) * Math.log(playerGameData.getFgAttempted()) * resid / (Math.log(gamesInSeason * Math.exp(weight2) + 1));
                } else {
                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPredicted, -1, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                }
                numbGames++;
            }
            return predictions;
        }
    }
}
