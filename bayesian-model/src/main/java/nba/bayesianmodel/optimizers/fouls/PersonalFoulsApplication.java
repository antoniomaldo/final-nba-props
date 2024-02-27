package nba.bayesianmodel.optimizers.fouls;

import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.PersonalFoulsPerMinPrior;
import nba.bayesianmodel.priors.PriorsInterface;

import java.util.Map;

public class PersonalFoulsApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new PersonalFoulsPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsFoulsOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "personalFoulsPerMin";
    }
}
