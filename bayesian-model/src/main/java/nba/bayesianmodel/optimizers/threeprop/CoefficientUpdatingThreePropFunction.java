package nba.bayesianmodel.optimizers.threeprop;


import nba.bayesianmodel.common.BaseCoefficientUpdatingFunction;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoefficientUpdatingThreePropFunction extends BaseCoefficientUpdatingFunction {


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

                if (i > 1) {
                    resid = (2 * resid + lastResid) / 3d;
                }
                if (i > 2) {
                    resid = (3 * resid + 2 * lastResid +  lastTwoResid) / 6d;
                }
                lastTwoResid = lastResid;
                lastResid = resid;

                int fgAttempted = playerGameData.getFgAttempted();
                if (fgAttempted > 0) {
                    targetPredicted = TargetPredicted.forThreeProp(playerCoef, playerGameData.getFgAttempted(), priorForPlayer, weight2);

                    real = (double) playerGameData.getThreeAttempted() / fgAttempted;
                    resid = real - targetPredicted;

                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPredicted, real, playerGameData.getThreeAttempted(), fgAttempted, playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                    playerCoef += Math.exp(weight1) * Math.log(fgAttempted) * resid;
                } else{
                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, -1, -1, playerGameData.getThreeAttempted(), fgAttempted, playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                }
                numbGames++;
            }
            return predictions;
        }
    }
}
