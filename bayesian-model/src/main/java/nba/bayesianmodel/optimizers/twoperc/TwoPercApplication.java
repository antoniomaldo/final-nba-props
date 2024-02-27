package nba.bayesianmodel.optimizers.twoperc;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.TwoPercPrior;

import java.util.Map;

public class TwoPercApplication extends BaseAbstractApplication {
    @Override
    protected BaseAbstractPriors getPriorForParameter() {
        return new TwoPercPrior();
    }

    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsTwoPercOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "twoPerc";
    }
}
