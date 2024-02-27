package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;

import java.util.List;

public class ThreePercPrior extends BaseNewPlayersAveragePrior{

    protected double getNewPlayersValue(PlayerPosition playerPosition, List<PlayerGameData> seasonNoNewPlayers) {
        int newPlayersValue = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getThreeMade).sum();
        int newPlayersTotals = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getThreeAttempted).sum();

        return (double) newPlayersValue / newPlayersTotals;
    }
}
