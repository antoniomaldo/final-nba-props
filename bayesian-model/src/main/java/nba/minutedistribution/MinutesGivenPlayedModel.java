package nba.minutedistribution;

import com.williamhill.trading.framework.math.common.splines.IntegerBoundedSpline;
import com.williamhill.trading.framework.math.common.splines.IntegerSpline;
import org.apache.commons.math3.util.FastMath;

public class MinutesGivenPlayedModel {
    private static final double[] COEF = {1.841775d,0.243216d,0.6939453d,1.0578d,1.050282d,1.334289d,0.9999551d,0.01928704d,-0.0007203485d,0.01160776d,0.01212878d,0.01956566d,-0.00810572d,0.05841004d,-0.0009053845d,-0.0004721798};
    private static int COUNTER = 0;

    private static double INTERCEPT = COEF[COUNTER++];
    private static final IntegerSpline PMIN_SPLINE = new IntegerBoundedSpline(new double[]{0, 20, 37, 40, 45}, new double[]{COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++]});
    private static double AVERAGE_MINUTES_IN_SEASON = COEF[COUNTER++];
    private static double EXP_POINTS_DIFF = COEF[COUNTER++];
    private static double ABS_EXP_POINTS_DIFF = COEF[COUNTER++];
    private static double PMIN_MINUS_AVERAGE_MINUTES_POSITIVE = COEF[COUNTER++];
    private static double LESS_THAN_THIRTY_STARTER = COEF[COUNTER++];
    private static double MORE_THAN_TWENTY_NON_STARTER = COEF[COUNTER++];
    private static double MORE_THAN_TWENTY_OT = COEF[COUNTER++];
    private static double TEAM_SUM_AVG_MINUTES = COEF[COUNTER++];
    private static double ABS_EXP_DIFF_INTERACTION_PMIN = COEF[COUNTER++];

    public static double getAverageGivenPlayed(int pmin, double averageMinutesInSeason, double ownExp, double oppExp, int seasonYear, int Starter, int hasOt, double teamSumAvgMinutes) {
        return Math.exp(INTERCEPT +
                PMIN_SPLINE.value(pmin) +
                AVERAGE_MINUTES_IN_SEASON * averageMinutesInSeason +
                EXP_POINTS_DIFF * (ownExp - oppExp) +
                Math.abs(ownExp - oppExp) * ABS_EXP_POINTS_DIFF +
                FastMath.max(pmin - averageMinutesInSeason, 0) * PMIN_MINUS_AVERAGE_MINUTES_POSITIVE + //
                (Starter == 1 ? FastMath.max(30 - pmin, 0) * LESS_THAN_THIRTY_STARTER : 0d) + //
                (Starter == 0 ? FastMath.max(pmin - 20, 0) * MORE_THAN_TWENTY_NON_STARTER : 0d) + //
                (hasOt == 1 ? Math.log(FastMath.max(pmin - 20, 1)) * MORE_THAN_TWENTY_OT : 0d) + //

                (teamSumAvgMinutes - 240) * TEAM_SUM_AVG_MINUTES + //
                Math.abs(ownExp - oppExp) * pmin * ABS_EXP_DIFF_INTERACTION_PMIN
        );
    }
}
