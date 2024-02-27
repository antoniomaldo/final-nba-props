package nba.bayesianmodel.priors;

import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;

import java.util.List;

public class ReboundsPerMinPrior extends BaseNewPlayersAveragePrior {

    protected double getNewPlayersValue(PlayerPosition playerPosition, List<PlayerGameData> seasonNoNewPlayers) {
        int newPlayersValue = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getRebounds).sum();
        int newPlayersMin = seasonNoNewPlayers.stream().filter(p -> p.getPosition() == playerPosition).mapToInt(PlayerGameData::getMinPlayed).sum();

        return (double) newPlayersValue / newPlayersMin;
    }
}
