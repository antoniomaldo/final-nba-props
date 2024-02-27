package nba.bayesianmodel.optimizers.threeprop;

import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PriorsInterface;
import nba.bayesianmodel.priors.ThreePropPrior2;

import java.util.Map;

public class ThreePropApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new ThreePropPrior2();
    }

    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsThreePropOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "threeProp";
    }

}
