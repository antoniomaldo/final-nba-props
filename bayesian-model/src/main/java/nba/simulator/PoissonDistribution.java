//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package nba.simulator;

class PoissonDistribution {
    private static final int MAX_TOTAL_GOALS = 15;
    private static final double[] FACTORIAL = new double[16];

    PoissonDistribution() {
    }

    public static double[] calculateProbabilities(double goalExp) {
        double[] probabilities = new double[16];
        probabilities[0] = Math.exp(-goalExp);

        for(int i = 1; i <= 60; ++i) {
            probabilities[i] = probabilities[i - 1] * goalExp / (double)i;
        }

        return probabilities;
    }

    public static double probability(int x, double lambda) {
        return Math.pow(lambda, x) * Math.exp(-lambda) / FACTORIAL[x];
    }

    static {
        FACTORIAL[0] = 1.0;

        for(int i = 1; i <= 15; ++i) {
            FACTORIAL[i] = FACTORIAL[i - 1] * (double)i;
        }

    }
}
