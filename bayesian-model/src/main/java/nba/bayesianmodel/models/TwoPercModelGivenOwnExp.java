package nba.bayesianmodel.models;

public class TwoPercModelGivenOwnExp {

    private static final double[] COEF = new double[]{-0.6389552,0.8092617,0.007991094,-0.03552632,-0.009681816,-0.04706234,0.00997772,-0.07226075,-0.01890206,-0.03016938,-0.01995777,0.0239319,0.0005226047};
    private static int COUNTER = 0;
    private static final double INTERCEPT = COEF[COUNTER++];
    private static final double LOGIT_TARGET_PREDICTED = COEF[COUNTER++];
    private static final double TWO_EXPECTED_MINUS_ATTEMPTED = COEF[COUNTER++];
    private static final double FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED = COEF[COUNTER++];
    private static final double OVER_TEN_TWOS = COEF[COUNTER++];
    private static final double FG_ATTEMPTED = COEF[COUNTER++];
    private static final double OWN_EXP = COEF[COUNTER++];
    private static final double LESS_THAN_FIFTEEN_MINUTES = COEF[COUNTER++];
    private static final double OVER_EIGHT_FG_ATTEMPTED_PRED = COEF[COUNTER++];
    private static final double SEASON_YEAR_TWENTY_FIVE = COEF[COUNTER++];
    private static final double LESS_THIRTY_MINUTES_PLAYED = COEF[COUNTER++];
    private static final double OVER_EIGHT_THREE_ATTEMPTED = COEF[COUNTER++];
    private static final double MINUTES_PLAYED_INTERACTION_TWO_ATTEMPTED = COEF[COUNTER++];



    public static double adjustRate(double rawTargetPercentage,
                                    double fgAttemptedPred,
                                    int realFgAttempted,
                                    double ownExp,
                                    int minPlayed,
                                    int threeAttempted,
                                    double threePropPred,
                                    int seasonYear) {
        int twoAttempted = realFgAttempted - threeAttempted;
        double body = Math.exp(
                INTERCEPT +
                        Math.log(rawTargetPercentage / (1 - rawTargetPercentage)) * LOGIT_TARGET_PREDICTED + //
                        Math.min(0, (1 - threePropPred) * realFgAttempted - twoAttempted) * TWO_EXPECTED_MINUS_ATTEMPTED +//
                        (fgAttemptedPred - realFgAttempted) * FG_ATTEMPTED_PRED_DIFF_FG_ATTEMPTED + //
                        Math.max(twoAttempted - 10, 0) * OVER_TEN_TWOS + //
                        realFgAttempted * FG_ATTEMPTED + //
                        ownExp * OWN_EXP + //
                        (minPlayed < 15 ? LESS_THAN_FIFTEEN_MINUTES : 0d) + //
                        Math.min(8 - fgAttemptedPred, 0) * OVER_EIGHT_FG_ATTEMPTED_PRED + //
                        (seasonYear >= 2025 ? SEASON_YEAR_TWENTY_FIVE : 0d) + //
                        Math.max(30 - minPlayed, 0) * LESS_THIRTY_MINUTES_PLAYED + //
                        Math.max(threeAttempted - 8, 0) * OVER_EIGHT_THREE_ATTEMPTED + //
                        minPlayed * twoAttempted * MINUTES_PLAYED_INTERACTION_TWO_ATTEMPTED


                 );

        return body / (1 + body);
    }
}
