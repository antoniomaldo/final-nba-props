package nba.bayesianmodel.common;

import java.util.HashMap;
import java.util.Map;

public class Distances {
    private static final Map<Integer, long[]> PASCAL_TRIANGLE = populateTriangle();

    public static double binomialLikelihoodDistance(int total, int successes, double prob) {
        double likelihood = PASCAL_TRIANGLE.get(total)[successes] * Math.pow(prob, successes) * Math.pow(1 - prob, total - successes);
        return -1 * Math.log(likelihood);
    }

    public static double sumSquaresDistance(PlayerPredictions p) {
        return Math.pow(p.getReal() - p.getTargetPredicted(), 2);
    }




    private static Map<Integer, long[]> populateTriangle() {
        Map<Integer, long[]> pascalTriangle = new HashMap<>();
        for (int n = 1; n < 50; n++) {
            long[] combs = new long[n + 1];
            long nCk = 1;
            for (int k = 0; k <= n; k++) {
                combs[k] = nCk;

                nCk = nCk * (n - k) / (k + 1);
            }
            pascalTriangle.put(n, combs);
        }
        return pascalTriangle;
    }

}
