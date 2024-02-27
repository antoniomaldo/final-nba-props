package nba.bayesianmodel.optimizers.offrebprop;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CoefficientsOffRebPropOptimizer extends BaseAbstractOptimizer {
    private static final CoefficientUpdatingOffRebPropFunction COEFFICIENT_UPDATING_OF_REB_PROP_FUNCTION = new CoefficientUpdatingOffRebPropFunction();

    public CoefficientsOffRebPropOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }

    @Override
    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients) {
        return COEFFICIENT_UPDATING_OF_REB_PROP_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    protected Predicate<PlayerPredictions> filterGamesToOptimize() {
        return p -> p.getNumbOfGames() > 0 &&
                p.getSeasonYear() > 2017 &&
                p.getRebounds() > 0;
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        return Distances.binomialLikelihoodDistance(p.getRebounds(), p.getOffRebounds(), p.getTargetPredicted());
    }
}
