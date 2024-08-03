package nba.simulator;

public class FgAttemptedModel {

    private final static double INTERCEPT = -0.0187298;
    private final static double LOG_RAW_PREDICTION = 0.9664248;
    private final static double RAW_PREDICTION_OVER_20 = -0.0062892;
    private final static double RAW_PREDICTION_OVER_10 = -0.0016209;
    private final static double MIN_PLAYED = 0.0035312;

    public static double getFgAttemptedPrediction(double fgPerMinTarget, int minPlayed) {

        if(true){
            return fgPerMinTarget * minPlayed;
        }

        double fgExp = fgPerMinTarget * minPlayed;
        double body = INTERCEPT +
                Math.log(fgPerMinTarget * minPlayed) * LOG_RAW_PREDICTION +
                Math.max(fgExp - 20, 0) * RAW_PREDICTION_OVER_20 + //
                Math.max(fgExp - 10, 0) * RAW_PREDICTION_OVER_10 + //
                minPlayed * MIN_PLAYED;

        return Math.exp(body);
    }
}
