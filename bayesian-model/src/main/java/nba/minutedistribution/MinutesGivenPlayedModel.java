package nba.minutedistribution;

import com.williamhill.trading.framework.math.common.splines.IntegerBoundedSpline;
import com.williamhill.trading.framework.math.common.splines.IntegerSpline;
import org.apache.commons.math3.util.FastMath;

public class MinutesGivenPlayedModel {
    private static final double[] COEF = {1.912834d,0.3414087d,0.7235398d,1.262182d,1.352581d,1.672959d,1.282233d,0.01081333d,-0.0003189751d,0.008385791d,0.001023392d,0.01942846d,-0.008347188d,0d,0.06393102d,-0.003387699d,0.0005614642d,-0.0003613362d,-0.0007119915d,0.0004255616d,9.573341e-05};
    private static int COUNTER = 0;

    private static double INTERCEPT = COEF[COUNTER++];
    private static final IntegerSpline PMIN_SPLINE = new IntegerBoundedSpline(new double[]{0, 20, 37, 40, 45}, new double[]{COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++], COEF[COUNTER++]});
    private static double AVERAGE_MINUTES_IN_SEASON = COEF[COUNTER++];
    private static double EXP_POINTS_DIFF = COEF[COUNTER++];
    private static double ABS_EXP_POINTS_DIFF = COEF[COUNTER++];
    private static double PMIN_MINUS_AVERAGE_MINUTES_POSITIVE = COEF[COUNTER++];
    private static double LESS_THAN_THIRTY_STARTER = COEF[COUNTER++];
    private static double MORE_THAN_TWENTY_NON_STARTER = COEF[COUNTER++];
    private static double LAST_GAME_MIN_DIFFERENCE_EXPECTED = COEF[COUNTER++];
    private static double MORE_THAN_TWENTY_OT = COEF[COUNTER++];
    private static double TEAM_SUM_AVG_MINUTES = COEF[COUNTER++];
    private static double ABS_TEAM_SUM_AVG_MINUTES = COEF[COUNTER++];
    private static double ABS_EXP_DIFF_INTERACTION_PMIN = COEF[COUNTER++];
    private static double OVER_THIRTY_EXPECTED_INTERACTION_LAST_GAME_MIN = COEF[COUNTER++];
    private static double LESS_THAN_THIRTY_EXPECTED_INTERACTION_LAST_GAME_MIN = COEF[COUNTER++];
    private static double PMIN_INTERACTION_TEAM_SUM_AVG_MINUTES = COEF[COUNTER++];

    public static double getAverageGivenPlayed(int pmin, double averageMinutesInSeason, double ownExp, double oppExp, int seasonYear, int Starter, int hasOt, double teamSumAvgMinutes, double lastGameMin) {
        return Math.exp(INTERCEPT +
                PMIN_SPLINE.value(pmin) +
                AVERAGE_MINUTES_IN_SEASON * averageMinutesInSeason +
                EXP_POINTS_DIFF * (ownExp - oppExp) +
                Math.abs(ownExp - oppExp) * ABS_EXP_POINTS_DIFF +
                FastMath.max(pmin - averageMinutesInSeason, 0) * PMIN_MINUS_AVERAGE_MINUTES_POSITIVE + //
                (Starter == 1 ? FastMath.max(30 - pmin, 0) * LESS_THAN_THIRTY_STARTER : 0d) + //
                (Starter == 0 ? FastMath.max(pmin - 20, 0) * MORE_THAN_TWENTY_NON_STARTER : 0d) + //

                (lastGameMin - pmin) * LAST_GAME_MIN_DIFFERENCE_EXPECTED + //

                (hasOt == 1 ? Math.log(FastMath.max(pmin - 20, 1)) * MORE_THAN_TWENTY_OT : 0d) + //

                (teamSumAvgMinutes - 240) * TEAM_SUM_AVG_MINUTES + //
                Math.abs(teamSumAvgMinutes - 240) * ABS_TEAM_SUM_AVG_MINUTES + //

               Math.abs(ownExp - oppExp) * pmin * ABS_EXP_DIFF_INTERACTION_PMIN + //
                FastMath.max(pmin - 30, 0) * (lastGameMin - 30)  * OVER_THIRTY_EXPECTED_INTERACTION_LAST_GAME_MIN +//
                FastMath.max(30 - pmin, 0) * (lastGameMin - 15) * LESS_THAN_THIRTY_EXPECTED_INTERACTION_LAST_GAME_MIN + //
                pmin * (teamSumAvgMinutes - 240)  * PMIN_INTERACTION_TEAM_SUM_AVG_MINUTES

        );
    }
}
