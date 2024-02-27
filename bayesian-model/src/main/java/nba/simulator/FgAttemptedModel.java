package nba.simulator;

public class FgAttemptedModel {

    private final static double INTERCEPT = -0.0002146;
    private final static double LOG_RAW_PREDICTION = 1.0055727;
    private final static double RAW_PREDICTION = -0.1722040;
    private final static double MIN_PLAYED = 0.0018540;
    private final static double MIN_PLAYED_INTERACT_RAW_PREDICTION = -0.0001337;
    public static double getFgAttemptedPrediction(double fgPerMinTarget, int minPlayed) {
        double body = INTERCEPT +
                Math.log(fgPerMinTarget * minPlayed) * LOG_RAW_PREDICTION +
                fgPerMinTarget * RAW_PREDICTION + //
                minPlayed * MIN_PLAYED + //
                minPlayed * fgPerMinTarget * MIN_PLAYED_INTERACT_RAW_PREDICTION;

        return Math.exp(body);
    }
}
