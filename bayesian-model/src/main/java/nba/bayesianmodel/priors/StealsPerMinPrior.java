package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;

import java.util.List;

public class StealsPerMinPrior extends BaseNewPlayersAveragePrior {

    protected double getNewPlayersValue(PlayerPosition playerPosition, List<PlayerGameData> seasonNoNewPlayers) {
        int newPlayersAssists = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getSteals).sum();
        int newPlayersMin = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getMinPlayed).sum();

        return (double) newPlayersAssists / newPlayersMin;
    }
}
