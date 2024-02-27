package nba.bayesianmodel.optimizers.steals;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PriorsInterface;
import nba.bayesianmodel.priors.StealsPerMinPrior;

import java.util.Map;

public class StealsApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new StealsPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsStealsOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "stealsPerMin";
    }
}
