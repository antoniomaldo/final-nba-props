package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {

    public static double zeroMinutesProb(int pmin, int starter, int hasOt){
        double exp = Math.exp(0.59926 +
                -0.22910  * pmin +
                (starter == 1 ? -15.76094 : 0) +
                pmin * (hasOt == 1 ? 0.05222  : 0d));

        return exp / (1 + exp);
    }
}
