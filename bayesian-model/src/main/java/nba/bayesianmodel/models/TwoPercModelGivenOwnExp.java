package nba.bayesianmodel.models;

public class TwoPercModelGivenOwnExp {

    private static final double[] COEF = new double[]{-0.7850695d,0.7833309d,-0.05953706d,-0.05456606d,0.01127225d,-0.1770294d,-0.1780272d,-0.04690009};
    private static int COUNTER = 0;
    private static final double INTERCEPT = COEF[COUNTER++];
    private static final double LOGIT_TARGET_PREDICTED = COEF[COUNTER++];
    private static final double FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED = COEF[COUNTER++];
    private static final double FG_ATTEMPTED = COEF[COUNTER++];
    private static final double OWN_EXP = COEF[COUNTER++];
    private static final double LESS_THAN_TWENTY_MINUTES = COEF[COUNTER++];
    private static final double LESS_THAN_FIFTEEN_MINUTES = COEF[COUNTER++];
    private static final double OVER_EIGHT_FG_ATTEMPTED_PRED = COEF[COUNTER++];

    public static double adjustRate(double rawTargetPercentage, double fgAttemptedPred, int realFgAttempted, double ownExp, int minPlayed, int threeAttempted) {
        double body = Math.exp(
                INTERCEPT +
                        Math.log(rawTargetPercentage / (1 - rawTargetPercentage)) * LOGIT_TARGET_PREDICTED + //
                        (fgAttemptedPred - realFgAttempted) * FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED + //
                        realFgAttempted * FG_ATTEMPTED + //
                        ownExp * OWN_EXP + //
                        (minPlayed < 20 ? LESS_THAN_TWENTY_MINUTES : 0d) + //
                        (minPlayed < 15 ? LESS_THAN_FIFTEEN_MINUTES : 0d) + //
                        Math.min(8 - fgAttemptedPred, 0) * OVER_EIGHT_FG_ATTEMPTED_PRED);

        return body / (1 + body);
    }
}
