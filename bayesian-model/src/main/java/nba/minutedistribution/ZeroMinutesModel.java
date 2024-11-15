package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {
    public static double zeroMinutesProb(double pmin, int starter, int hasOt){
        double exp = Math.exp(0.474362 +
                -0.219168 * pmin +
                (starter == 1 ? -100 : 0) +
                pmin * (hasOt == 1 ? 0.039842 : 0d));
        return exp / (1 + exp);
    }
}
