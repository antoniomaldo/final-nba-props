package nba.bayesianmodel.models;

public class ThreeProportionModelGivenFgAttempted {

    private final static double[] COEF = {0.2293627d, 1.008567d, 0.02328505d, -0.02527005d, -0.02249732d, -2.408107d, 0.01115994d, -0.01060715d, 0.01600597d, -0.03981176};
    private static int COUNTER = 0;

    private final static double INTERCEPT = COEF[COUNTER++];
    private final static double TARGET_PREDICTED_LOGIT = COEF[COUNTER++];
    private final static double FG_ATTEMPTED_PRED_MINUS_REAL = COEF[COUNTER++];
    private final static double FG_ATTEMPTED_PRED_MINUS_REAL_OVER_FIVE = COEF[COUNTER++];
    private final static double FG_ATTEMPTED_PRED_MINUS_REAL_LESS_MINUS_FIVE = COEF[COUNTER++];
    private final static double TARGET_PREDICTED_LESS_POINT_TWO = COEF[COUNTER++];
    private final static double FG_ATTEMPTED_OVER_TWELVE = COEF[COUNTER++];
    private final static double FG_ATTEMPTED_LESS_FIFTEEN = COEF[COUNTER++];
    private final static double RAW_THREE_EXP_OVER_TEN = COEF[COUNTER++];
    private final static double RAW_THREE_EXP = COEF[COUNTER++];


    public static double adjustRate(double rawTargetPercentage, double fgAttemptedPred, int realFgAttempted) {
        double rawTargetLogit = Math.log(rawTargetPercentage / (1 - rawTargetPercentage));
        double body = Math.exp(INTERCEPT + //
                rawTargetLogit * TARGET_PREDICTED_LOGIT + //
                (fgAttemptedPred - realFgAttempted) * FG_ATTEMPTED_PRED_MINUS_REAL +//
                Math.max(fgAttemptedPred - realFgAttempted - 5, 0) * FG_ATTEMPTED_PRED_MINUS_REAL_OVER_FIVE + //
                Math.min(fgAttemptedPred - realFgAttempted + 5, 0) * FG_ATTEMPTED_PRED_MINUS_REAL_LESS_MINUS_FIVE + //
                Math.max(0.2 - rawTargetPercentage, 0) * TARGET_PREDICTED_LESS_POINT_TWO + //
                Math.max(realFgAttempted - 12, 0) * FG_ATTEMPTED_OVER_TWELVE + //
                Math.max(15 - realFgAttempted, 0) * FG_ATTEMPTED_LESS_FIFTEEN + //
                Math.max(realFgAttempted * rawTargetPercentage - 10, 0) * RAW_THREE_EXP_OVER_TEN + //
                realFgAttempted * rawTargetPercentage  * RAW_THREE_EXP
        );

        return body / (1 + body);
    }
}
