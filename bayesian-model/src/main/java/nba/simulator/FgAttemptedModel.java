package nba.simulator;

public class FgAttemptedModel {

    private final static double INTERCEPT = 0.0070540;
    private final static double LOG_RAW_PREDICTION = 1.0047485;
    private final static double TARGET_PREDICTED = -0.1742961;
    private final static double TARGET_PREDICTED_OVER_POINT_FIVE_FIVE = -0.1167798;
    private final static double TARGET_PREDICTED_OVER_POINT_FOUR_FIVE = -0.1702361;
    private final static double TARGET_PREDICTED_OVER_POINT_THREE_FIVE = 0.1050210;
    private final static double MIN_PLAYED = 0.0016115;

    public static double getFgAttemptedPrediction(double fgPerMinTarget, int minPlayed) {

        if(false){
            return fgPerMinTarget * minPlayed;
        }

        double body = INTERCEPT +
                Math.log(fgPerMinTarget * minPlayed) * LOG_RAW_PREDICTION +
                fgPerMinTarget * TARGET_PREDICTED +
                Math.max(fgPerMinTarget - 0.55, 0) * TARGET_PREDICTED_OVER_POINT_FIVE_FIVE + //
                Math.max(fgPerMinTarget - 0.45, 0) * TARGET_PREDICTED_OVER_POINT_FOUR_FIVE + //
                Math.max(fgPerMinTarget - 0.35, 0) * TARGET_PREDICTED_OVER_POINT_THREE_FIVE + //
                minPlayed * MIN_PLAYED;

        return Math.exp(body);
    }
}
