package nba.bayesianmodel.optimizers.fgattempted;

import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.FgAttemptedPrior;

import java.util.Map;

public class FgAttemptedApplication extends BaseAbstractApplication {

    @Override
    protected BaseAbstractPriors getPriorForParameter() {
        return new FgAttemptedPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsFgAttemptedOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "fgAttemptedPerMin";
    }
}
