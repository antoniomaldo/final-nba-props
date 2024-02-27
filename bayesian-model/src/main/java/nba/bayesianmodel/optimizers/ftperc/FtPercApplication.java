package nba.bayesianmodel.optimizers.ftperc;

import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.FtPercPrior;
import nba.bayesianmodel.priors.PriorsInterface;

import java.util.Map;

public class FtPercApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new FtPercPrior();
    }

    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsFtPercOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "ftPerc";
    }

}
