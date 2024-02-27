package nba.bayesianmodel.optimizers.threeperc;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PriorsInterface;
import nba.bayesianmodel.priors.ThreePercPrior;

import java.util.Map;

public class ThreePercApplication extends BaseAbstractApplication {
    @Override
    protected PriorsInterface getPriorForParameter() {
        return new ThreePercPrior();  // TODO empty method body
    }

    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsThreePercOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "threePerc";  // TODO empty method body
    }
}
