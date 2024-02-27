package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayerGameData;

import java.util.List;

public class FgAttemptedPrior extends BaseAbstractPriors {
    @Override
    protected double getPriorForPositionAndSeason(List<PlayerGameData> playerGameDataForSeasonAndPosition) {
        double totalFgAttempted = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getFgAttempted).sum();
        double totalMins = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getMinPlayed).sum();

        return totalFgAttempted / totalMins;
    }
}
