package nba.simulator;

public class TeamFgAttemptedNormalizationModel {
    private static final double INTERCEPT = 3.7941977;
    private static final double LOG_FG_ATTEMPTED_PRED_AVG = 1.0409870;
    private static final double LOG_TEAM_FG_ATTEMPTED_PRED_AVG = -0.9978556;
    private static final double OWN_EXP =  0.0049590;
    private static final double EXP_DIFF = -0.0033049;
    private static final double FG_ATTEMPTED_PRED_AVG_OVER_19 = -0.0199095;

    public static double adjustPrediction(double fgAttemptedPredAvg, double ownExp, double oppExp, double teamFgAttemptedPredAvg) {
        double body = INTERCEPT +
                Math.log(fgAttemptedPredAvg) * LOG_FG_ATTEMPTED_PRED_AVG + //
                Math.log(teamFgAttemptedPredAvg) * LOG_TEAM_FG_ATTEMPTED_PRED_AVG +//
                ownExp * OWN_EXP + //
                (ownExp - oppExp) * EXP_DIFF + //
                Math.max(fgAttemptedPredAvg - 19, 0) * FG_ATTEMPTED_PRED_AVG_OVER_19;
        return Math.exp(body);
    }
}
