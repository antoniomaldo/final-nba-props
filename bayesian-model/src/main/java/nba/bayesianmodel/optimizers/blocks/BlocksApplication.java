package nba.bayesianmodel.optimizers.blocks;


import nba.bayesianmodel.common.BaseAbstractApplication;
import nba.bayesianmodel.common.BaseAbstractOptimizer;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.priors.BlocksPerMinPrior;
import nba.bayesianmodel.priors.PriorsInterface;

import java.util.Map;

public class BlocksApplication extends BaseAbstractApplication {

    @Override
    protected PriorsInterface getPriorForParameter() {
        return new BlocksPerMinPrior();
    }
    @Override
    protected BaseAbstractOptimizer getCoefOptimizer(PlayersData playersData, Map<Integer, Double> playersPriors) {
        return new CoefficientsBlocksOptimizer(playersData, playersPriors);
    }

    @Override
    protected String csvName() {
        return "blocksPerMin";
    }
}
