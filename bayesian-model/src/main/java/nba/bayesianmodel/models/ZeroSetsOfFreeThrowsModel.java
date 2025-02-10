package nba.bayesianmodel.models;

public class ZeroSetsOfFreeThrowsModel {

    private static final double[] COEF = new double[]{2.836933d,-2.115266d,-0.0231398d,-0.0003454061d,0.03780718d,0.1262304d,0.1686905d,0.06339013d,-0.001715799d,0.04157868d,0.02868511d,4.025969};

    private static int COUNTER = 0;
    private static final double INTERCEPT = COEF[COUNTER++];
    private static final double SQRT_FT_PRED = COEF[COUNTER++];
    private static final double FG_ATTEMPTED = COEF[COUNTER++];
    private static final double FG_ATTEMPTED_SQUARED = COEF[COUNTER++];
    private static final double THREE_ATTEMPTED = COEF[COUNTER++];
    private static final double FG_MADE_ONE = COEF[COUNTER++];
    private static final double FG_MADE_ZERO = COEF[COUNTER++];
    private static final double FG_MADE_TWO = COEF[COUNTER++];
    private static final double FG_MADE_SQUARE = COEF[COUNTER++];
    private static final double THREE_MADE = COEF[COUNTER++];
    private static final double OVER_NINE_FG_MADE = COEF[COUNTER++];
    private static final double LESS_POINT_THREE_FT_PRED = COEF[COUNTER++];


    public static double getZeroFtsProb(double ftPred, int FgAttempted, int threeAttempted, int fgMade, int threeMade){
        double body = Math.exp(
                    INTERCEPT +
                            Math.sqrt(ftPred) * SQRT_FT_PRED +
                            FgAttempted * FG_ATTEMPTED +
                            FgAttempted * FgAttempted * FG_ATTEMPTED_SQUARED +
                            threeAttempted * THREE_ATTEMPTED +
                            (fgMade == 0 ? FG_MADE_ZERO : 0) +
                            (fgMade == 1 ? FG_MADE_ONE : 0) +
                            (fgMade == 2 ? FG_MADE_TWO : 0) +
                            fgMade * fgMade * FG_MADE_SQUARE +
                            threeMade * THREE_MADE +
                            Math.max(fgMade - 9, 0) * OVER_NINE_FG_MADE +
                            Math.max(0.3 - ftPred, 0) * LESS_POINT_THREE_FT_PRED);

        return body / (1 + body);
    }
}
