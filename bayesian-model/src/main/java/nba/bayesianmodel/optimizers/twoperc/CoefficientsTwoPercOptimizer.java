package nba.bayesianmodel.optimizers.twoperc;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CoefficientsTwoPercOptimizer extends BaseAbstractOptimizer {

    private static final CoefficientUpdatingTwoPercFunction COEFFICIENT_UPDATING_TWO_PERC_FUNCTION = new CoefficientUpdatingTwoPercFunction();


    public CoefficientsTwoPercOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }

    protected Predicate<PlayerPredictions> filterGamesToOptimize() {
        return p -> p.getNumbOfGames() > 0 &&
                (p.getFgAttempted() - p.getThreeAttempted() > 0) &&
                p.getSeasonYear() > 2017;
    }

    @Override
    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients) {
        return COEFFICIENT_UPDATING_TWO_PERC_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        int total = p.getFgAttempted() - p.getThreeAttempted();
        int successes = p.getFgMade() - p.getThreeMade();
        double prob = p.getTargetPredicted();
        return Distances.binomialLikelihoodDistance(total, successes, prob);
    }
}
