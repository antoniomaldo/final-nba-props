package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class MinutesGivenPlayedModel {
    private static final double[] COEF = {1.903789,-0.02364771,-0.007086019,0.04451934,-0.000799556,1.424164e-05,0.01628107,-0.0004840618,0.007078444,0.008072843,0.01839287,-0.008556499,0.002015425,0.06407151,-0.003221458,0.0004196618,-0.0003073462,-9.441688e-05,0.0003388732,8.604374e-05};
    private static int COUNTER = 0;

    private static double INTERCEPT = COEF[COUNTER++];
    private static double OVER_THIRTY_PMIN = COEF[COUNTER++];
    private static double OVER_THIRTY_FIVE_PMIN = COEF[COUNTER++];
    private static double PMIN = COEF[COUNTER++];
    private static double PMIN_SQUARED = COEF[COUNTER++];
    private static double PMIN_CUBIC = COEF[COUNTER++];
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
                (pmin > 30 ? (pmin - 30) * OVER_THIRTY_PMIN : 0d) +
                (pmin > 35 ? (pmin - 35) * OVER_THIRTY_FIVE_PMIN : 0d) +
                pmin * PMIN +
                pmin * pmin * PMIN_SQUARED +
                pmin * pmin * pmin * PMIN_CUBIC +

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
