package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {

    public static double zeroMinutesProb(double pmin, int starter, int hasOt){
        double exp = Math.exp(0.485979    +
                -0.216790     * pmin +
                (starter == 1 ? -16.080344 : 0) +
                pmin * (hasOt == 1 ? 0.037375  : 0d));

        return exp / (1 + exp);
    }
}
