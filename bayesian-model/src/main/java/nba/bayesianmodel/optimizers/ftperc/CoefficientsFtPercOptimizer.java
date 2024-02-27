package nba.bayesianmodel.optimizers.ftperc;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CoefficientsFtPercOptimizer extends BaseAbstractOptimizer {
    private static final CoefficientUpdatingFtPercFunction COEFFICIENT_UPDATING_FT_PERC_FUNCTION = new CoefficientUpdatingFtPercFunction();

    public CoefficientsFtPercOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }

    @Override
    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients) {
        return COEFFICIENT_UPDATING_FT_PERC_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    protected Predicate<PlayerPredictions> filterGamesToOptimize() {
        return p -> p.getNumbOfGames() > 0 &&
                p.getSeasonYear() > 2017 &&
                p.getFtAttempted() > 0;
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        return Distances.binomialLikelihoodDistance(p.getFtAttempted(), p.getFtMade(), p.getTargetPredicted());
    }
}
