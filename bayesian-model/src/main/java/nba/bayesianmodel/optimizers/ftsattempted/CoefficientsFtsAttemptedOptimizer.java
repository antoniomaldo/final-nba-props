package nba.bayesianmodel.optimizers.ftsattempted;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;

public class CoefficientsFtsAttemptedOptimizer extends BaseAbstractOptimizer {
    private static final CoefficientUpdatingFtsAttemptedFunction COEFFICIENT_UPDATING_FTS_ATTEMPTED_FUNCTION = new CoefficientUpdatingFtsAttemptedFunction();
    public CoefficientsFtsAttemptedOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }


    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients){
        return COEFFICIENT_UPDATING_FTS_ATTEMPTED_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        return Distances.sumSquaresDistance(p);
    }
}
