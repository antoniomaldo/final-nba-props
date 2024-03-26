package nba.bayesianmodel.optimizers.ftperc;


import nba.bayesianmodel.common.BaseCoefficientUpdatingFunction;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoefficientUpdatingFtPercFunction extends BaseCoefficientUpdatingFunction {

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
            int lastSeasonYear = playerData.get(0).getSeasonYear();
            double lastResid = 0;
            double lastTwoResid = 0;
            double resid = 0;

            for (int i = 0; i < playerData.size(); i++) {
                PlayerGameData playerGameData = playerData.get(i);
                if (playerGameData.getSeasonYear() > lastSeasonYear) {
                    gamesInSeason = 1;
                    lastSeasonYear = playerGameData.getSeasonYear();
                } else {
                    gamesInSeason++;
                }
                targetPredicted = TargetPredicted.forFtPerc(playerCoef, priorForPlayer);

                if (i > 1) {
                    resid = (resid + lastResid) / 2d;
                }
                if (i > 2) {
                    resid = (resid +  lastResid + lastTwoResid) / 3d;
                }
                lastTwoResid = lastResid;
                lastResid = resid;

                int totals = playerGameData.getFtAttempted();
                if (totals > 0) {
                    real = (double) playerGameData.getFtMade() / totals;
                    resid = real - targetPredicted;

                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPredicted, real, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                    playerCoef += Math.exp(weight2 * Math.max(0, 5 - numbGames)) * Math.exp(weight1) * Math.log(totals) * resid;
                } else{
                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPredicted, -1, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                }
                numbGames++;
            }
            return predictions;
        }
    }


}
