package nba.bayesianmodel.optimizers.turnovers;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PriorsInterface;
import nba.bayesianmodel.priors.TurnoversPerMinPrior;

import java.util.Map;

public class TurnoversApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new TurnoversPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsTurnoversOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "turnoversPerMin";
    }
}
