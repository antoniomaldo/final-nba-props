package nba.bayesianmodel.optimizers.targets;

public class TargetPredicted {

    public static double forAssists(double playerCoef, double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forBlocks(double playerCoef, double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forFgAttempted(double playerCoef, double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forFouls(double playerCoef, Double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forFtPerc(double playerCoef, Double priorForPlayer) {
        double priorLogit = probToLogit(Math.max(0.01, priorForPlayer));
        return logitToProb(playerCoef + priorLogit);
    }
    public static double forFtsAttempted(double playerCoef, Double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forOffRebProp(double playerCoef, Double priorForPlayer) {
        double priorLogit = probToLogit(Math.max(0.01, priorForPlayer));
        return logitToProb(playerCoef + priorLogit);
    }

    public static double forRebounds(double playerCoef, Double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forSteals(double playerCoef, Double priorForPlayer) {
        return Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forThreePerc(double playerCoef, int threeAttempted, Double priorForPlayer, double weight2) {

        double priorLogit = probToLogit(priorForPlayer * 0.7);

        return  logitToProb(playerCoef +
                Math.exp(weight2) * Math.log(threeAttempted) +
                priorLogit);
    }

    public static double forThreeProp(double playerCoef, int fgAttempted, Double priorForPlayer, double weight2) {
        double priorLogit = probToLogit(priorForPlayer * 1.3);

        return logitToProb(playerCoef +
                priorLogit);
    }

    public static double forTurnovers(double playerCoef, Double priorForPlayer) {
        return  Math.exp(playerCoef) * priorForPlayer;
    }

    public static double forTwoPerc(double playerCoef, Double priorForPlayer) {
        double priorLogit = probToLogit(Math.min(0.7, Math.max(0.3, priorForPlayer)));
        return  Math.max(0.35, Math.min(0.85, logitToProb(playerCoef + priorLogit)));
    }


    private static double logitToProb(double x) {
        return Math.exp(x) / (1 + Math.exp(x));
    }

    private static double probToLogit(double x) {
        return Math.log(x / (1 - x));
    }
}
