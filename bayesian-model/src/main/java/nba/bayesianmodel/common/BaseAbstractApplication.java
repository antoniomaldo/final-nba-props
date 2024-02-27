package nba.bayesianmodel.common;

import nba.bayesianmodel.priors.PriorsInterface;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.optimization.OptimizationException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class BaseAbstractApplication {

    public void run(PlayersData playersData) throws OptimizationException, FunctionEvaluationException {
        Map<Integer, Double> playersPriors = getPriorForParameter().getPlayersPriors(playersData);
        BaseAbstractOptimizer coefficientsOptimizer = getCoefOptimizer(playersData, playersPriors);

        double[] optimizedPar = NelderMeadOptim.optimize(coefficientsOptimizer);

        List<PlayerPredictions> playersPredictions = coefficientsOptimizer.getPlayerPredictionsForPar(playersData, playersPriors, optimizedPar);

        System.out.println(Arrays.toString(optimizedPar));

        CsvUtils.savePredictions(playersPredictions, csvName());
    }

    protected abstract PriorsInterface getPriorForParameter();
    protected abstract BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors);

    protected abstract String csvName();

}
