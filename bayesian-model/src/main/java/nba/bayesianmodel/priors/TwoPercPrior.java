package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayerGameData;

import java.util.List;

public class TwoPercPrior extends BaseAbstractPriors {
    @Override
    protected double getPriorForPositionAndSeason(List<PlayerGameData> playerGameDataForSeasonAndPosition) {
        double totalTwoAttempted = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getTwoAttempted).sum();
        double totalTwoMade = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getTwoMade).sum();

        return totalTwoMade / totalTwoAttempted;
    }
}
