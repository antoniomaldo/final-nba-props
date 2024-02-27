package nba.bayesianmodel.optimizers.ftsattempted;


import nba.bayesianmodel.common.BaseCoefficientUpdatingFunction;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoefficientUpdatingFtsAttemptedFunction extends BaseCoefficientUpdatingFunction {

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
            double targetPerMinPredicted;

            int gamesInSeason = 0;
            int numbGames = 1;
            double real;
            double resid;
            int lastSeasonYear = playerData.get(0).getSeasonYear();

            double lastResid = 0;
            double lastTwoResid = 0;

            for (int i = 0; i < playerData.size(); i++) {
                PlayerGameData playerGameData = playerData.get(i);
                if (playerGameData.getSeasonYear() > lastSeasonYear) {
                    gamesInSeason = 1;
                    lastSeasonYear = playerGameData.getSeasonYear();
                } else {
                    gamesInSeason++;
                }

                targetPerMinPredicted = TargetPredicted.forFtsAttempted(playerCoef, priorForPlayer);

                real = (double) (playerGameData.getFtAttempted()) / playerGameData.getMinPlayed();
                resid = real - targetPerMinPredicted;

                if (i > 1) {
                    resid = (2 * resid + lastResid) / 3d;
                }
                if (i > 2) {
                    resid = (3 * resid + 2 * lastResid + lastTwoResid) / 6d;
                }
                lastTwoResid = lastResid;
                lastResid = resid;

                predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPerMinPredicted, real, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer));
                playerCoef += Math.exp(weight2 * Math.max(0, 5 - numbGames)) * Math.exp(weight1) * (playerGameData.getMinPlayed()) * resid;
                numbGames++;
            }
            return predictions;
        }
    }

}
