package nba.bayesianmodel.optimizers.ftsattempted;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.FtsAttemptedPerMinPrior;
import nba.bayesianmodel.priors.PriorsInterface;

import java.util.Map;

public class FtsAttemptedApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new FtsAttemptedPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsFtsAttemptedOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "ftsAttemptedPerMin";
    }
}
