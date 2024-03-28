package nba.bayesianmodel.optimizers.fgattempted;


import nba.bayesianmodel.common.BaseCoefficientUpdatingFunction;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoefficientUpdatingFgAttemptedFunction extends BaseCoefficientUpdatingFunction {

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
            double lastThreeResid = 0;

            for (int i = 0; i < playerData.size(); i++) {
                PlayerGameData playerGameData = playerData.get(i);

                if(playerData.get(i).getMinPlayed() > 0) {
                    if (playerGameData.getSeasonYear() > lastSeasonYear) {
                        gamesInSeason = 1;
                        lastSeasonYear = playerGameData.getSeasonYear();
                    } else {
                        gamesInSeason++;
                    }
                    targetPerMinPredicted = TargetPredicted.forFgAttempted(playerCoef, priorForPlayer);


                    real = (double) playerGameData.getFgAttempted() / playerGameData.getMinPlayed();
                    resid = real - targetPerMinPredicted;

                    if(i > 3){
                        resid = (4 * resid + 3 * lastResid + 2 * lastTwoResid + lastThreeResid) / 10d;
                    }else if(i > 2){
                        resid = (3 * resid + 2 * lastResid + lastTwoResid) / 6d;
                    }else if (i > 1) {
                        resid = (2 * resid + lastResid) / 3d;
                    }

                    lastThreeResid = lastTwoResid;
                    lastTwoResid = lastResid;
                    lastResid = resid;

                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, targetPerMinPredicted, real, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));
                    playerCoef += Math.exp(weight1) * Math.log(playerGameData.getMinPlayed()) * resid / (Math.log(gamesInSeason * Math.exp(weight2) + 1));
                   // playerCoef = playerCoef > 0.5 ? 0.5:playerCoef;

                    numbGames++;
                }else{
                    predictions.add(new PlayerPredictions(playerGameData.getSeasonYear(), playerGameData.getGameId(), playerGameData.getPlayerId(), numbGames, gamesInSeason, playerGameData.getMinPlayed(), playerCoef, -1, -1, playerGameData.getThreeAttempted(), playerGameData.getFgAttempted(), playerGameData.getThreeMade(), playerGameData.getFgMade(), playerGameData.getOffRebounds(), playerGameData.getDefRbounds(), playerGameData.getFtMade(), playerGameData.getFtAttempted(), priorForPlayer, playerGameData.getAverageMinutesInSeason()));

                }
            }
            return predictions;
        }
    }

}
