package nba.bayesianmodel.models;

import com.williamhill.trading.framework.math.common.splines.BoundedRBSpline;
import com.williamhill.trading.framework.math.common.splines.IntegerBoundedRBSpline;
import com.williamhill.trading.framework.math.common.splines.RBSpline;

public class ThreeProportionModelGivenFgAttempted {

    private static final double[] COEF = new double[] {-5.422434d,3.273733d,4.638681d,5.77008d,6.744387d,8.55717d,0.01339142};
    private static int COUNTER = 0;
    private static final double INTERCEPT = COEF[COUNTER++];
    private static final RBSpline TARGET_PREDICTED_SPINE = new BoundedRBSpline(new double[] {0, 0.2, 0.6, 1}, new double[] { COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++] });
    private static final double FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED = COEF[COUNTER++];

    public static double adjustRate(double rawTargetPercentage, double fgAttemptedPred, int realFgAttempted){
        double body = Math.exp(
                INTERCEPT +
                TARGET_PREDICTED_SPINE.value(rawTargetPercentage) + //
                (fgAttemptedPred - realFgAttempted) * FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED);

        return body / (1 + body);
    }
}
