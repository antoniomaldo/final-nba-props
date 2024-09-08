package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {
    public static double zeroMinutesProb(double pmin, int starter, int hasOt){
        double exp = Math.exp(.4805316 +
                -0.2118254 * pmin +
                (starter == 1 ? -15.20518 : 0) +
                pmin * (hasOt == 1 ? 0.03426315 : 0d));
        return exp / (1 + exp);
    }
}
