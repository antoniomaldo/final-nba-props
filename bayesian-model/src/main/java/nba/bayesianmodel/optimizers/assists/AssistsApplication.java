package nba.bayesianmodel.optimizers.assists;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.AssistsPerMinPrior;
import nba.bayesianmodel.priors.PriorsInterface;

import java.util.Map;

public class AssistsApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new AssistsPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsAssistsOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "assistsPerMin";
    }
}
