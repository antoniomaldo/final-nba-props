package nba.bayesianmodel.common;


import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.direct.NelderMead;

public class NelderMeadOptim {

    public static double[] optimize(BaseAbstractOptimizer coefficientsOptimizer) throws OptimizationException, FunctionEvaluationException {
        NelderMead nelderMead = new NelderMead();
        nelderMead.setMaxIterations(20000); //FIXME

        RealPointValuePair optimize = nelderMead.optimize(coefficientsOptimizer, GoalType.MINIMIZE, new double[]{0, 0});
        return optimize.getPoint();
    }
}
