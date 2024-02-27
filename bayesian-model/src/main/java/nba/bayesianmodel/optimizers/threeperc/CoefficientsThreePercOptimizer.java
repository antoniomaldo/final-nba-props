package nba.bayesianmodel.optimizers.threeperc;


import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.Distances;
import nba.bayesianmodel.common.PlayerPredictions;
import nba.bayesianmodel.common.PlayersData;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CoefficientsThreePercOptimizer extends BaseAbstractOptimizer {

    private static final CoefficientUpdatingThreePercFunction COEFFICIENT_UPDATING_THREE_PERC_FUNCTION = new CoefficientUpdatingThreePercFunction();

    public CoefficientsThreePercOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        super(playersData, priorForPlayers);
    }

    @Override
    protected Predicate<PlayerPredictions> filterGamesToOptimize() {
        return p -> p.getNumbOfGames() > 0 &&
                p.getThreeAttempted() > 0 &&
                p.getSeasonYear() > 2017;
    }


    @Override
    public List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients) {
        return COEFFICIENT_UPDATING_THREE_PERC_FUNCTION.getPlayersPredictions(playersData, priorForPlayers, coefficients);
    }

    @Override
    protected double getDistance(PlayerPredictions p) {
        int total = p.getThreeAttempted();
        int successes = p.getThreeMade();
        double prob = p.getTargetPredicted();


        return Distances.sumSquaresDistance(p);
//        return Distances.binomialLikelihoodDistance(total, successes, prob);
    }
}
