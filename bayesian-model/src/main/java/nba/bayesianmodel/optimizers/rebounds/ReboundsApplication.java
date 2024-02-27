package nba.bayesianmodel.optimizers.rebounds;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PriorsInterface;
import nba.bayesianmodel.priors.ReboundsPerMinPrior;

import java.util.Map;

public class ReboundsApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new ReboundsPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsReboundsOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "reboundsPerMin";
    }
}
