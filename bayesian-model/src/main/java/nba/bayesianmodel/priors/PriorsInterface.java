package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.PlayersData;

import java.util.Map;

public interface PriorsInterface {

    public Map<Integer, Double> getPlayersPriors(PlayersData allPlayers);
}
