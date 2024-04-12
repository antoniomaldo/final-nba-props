package nba.bayesianmodel.models;

import com.williamhill.trading.framework.math.common.splines.BoundedRBSpline;
import com.williamhill.trading.framework.math.common.splines.IntegerBoundedRBSpline;
import com.williamhill.trading.framework.math.common.splines.RBSpline;

public class ThreeProportionModelGivenFgAttempted {

    public static double adjustRate(double rawTargetPercentage, double fgAttemptedPred, int realFgAttempted){
        double rawTargetLogit = Math.log(rawTargetPercentage / (1 - rawTargetPercentage));
        double body = Math.exp(
                rawTargetLogit * 0.989212   + //
                (fgAttemptedPred - realFgAttempted) * 0.012695);

        return body / (1 + body);
    }
}
