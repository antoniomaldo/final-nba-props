package nba.bayesianmodel.optimizers.steals;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;

public class CoefficientsStealsOptimizer extends BaseAbstractOptimizer {
    private static final CoefficientUpdatingStealsFunction COEFFICIENT_UPDATING_STEALS_FUNCTION = new CoefficientUpdatingStealsFunction();
    public CoefficientsStealsOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }


    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients){
        return COEFFICIENT_UPDATING_STEALS_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        return Distances.sumSquaresDistance(p);
    }
}
