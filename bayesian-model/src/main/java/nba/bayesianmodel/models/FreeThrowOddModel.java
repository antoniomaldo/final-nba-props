package nba.bayesianmodel.models;

public class FreeThrowOddModel {


    private static final double INTERCEPT = -4.024e-01;
    private static final double MIN = 4.543e-02;
    private static final double MIN_SQUARED = -6.574e-04;
    private static final double FT_PRED = 9.654e-02;
    private static final double OVER_EIGHT_FT_PRED = -1.137e-01;
    private static final double THREE_PROP_COEF = 6.889e-02;
    private static final double TWO_PERC_COEF = 7.670e-01;
    private static final double TWO_PERC_TARGET = -2.380e+00;


    public static double getOddProb(double min, double ftPred, double threePropCoef, double twoPercCoef, double twoPercTarget){
        double body = Math.exp(INTERCEPT +
                min * MIN +
                min * min * MIN_SQUARED +
                ftPred * FT_PRED +
                Math.max(ftPred - 8, 0) * OVER_EIGHT_FT_PRED +
                threePropCoef * THREE_PROP_COEF +
                twoPercCoef * TWO_PERC_COEF +
                twoPercTarget * TWO_PERC_TARGET);

        return body / (1 + body);
    }
}
