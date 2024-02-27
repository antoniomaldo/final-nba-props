package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayerGameData;

import java.util.List;

public class ThreeAttemptedPrior extends BaseAbstractPriors {
    @Override
    protected double getPriorForPositionAndSeason(List<PlayerGameData> playerGameDataForSeasonAndPosition) {
        double totalAttempted = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getThreeAttempted).sum();
        double totalMins = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getMinPlayed).sum();

        return totalAttempted / totalMins;
    }
}
