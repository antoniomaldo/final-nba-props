package nba.simulator;

public class TeamFgAttemptedNormalizationModel {
    private static final double teamSumFgPredAvg = -1.475e-03;
    private static final double ONE_OVER_teamPointsResid = 9.743e+00;
    private static final double teamPointsResid_interaction_fgAttemptedPredAvg = -6.142e-04;
    private static final double fgAttemptedPredAvg_OVER_15_NEGATIVE_teamPointsResid = 1.158e-03;




    public static double adjustPrediction(double fgAttemptedPredAvg, double teamPointsResid, double teamFgAttemptedPredAvg) {
        double body =
                Math.log(fgAttemptedPredAvg) + //
                teamSumFgPredAvg * teamFgAttemptedPredAvg + //
                        ONE_OVER_teamPointsResid / teamFgAttemptedPredAvg + //
                        teamPointsResid_interaction_fgAttemptedPredAvg * teamPointsResid * fgAttemptedPredAvg + //
                        fgAttemptedPredAvg_OVER_15_NEGATIVE_teamPointsResid * Math.max(fgAttemptedPredAvg - 15, 0) * Math.min(teamPointsResid, 0);

        return Math.exp(body);
    }
}
