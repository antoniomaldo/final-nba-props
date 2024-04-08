package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {

    public static double zeroMinutesProb(double pmin, int starter, int hasOt){
        double exp = Math.exp(0.473489 +
                -0.216238 * pmin +
                (starter == 1 ? -16.074187 : 0) +
                pmin * (hasOt == 1 ? 0.036677 : 0d));

        return exp / (1 + exp);
    }
}
