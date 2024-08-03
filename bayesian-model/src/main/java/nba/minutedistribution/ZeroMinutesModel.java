package nba.minutedistribution;

import org.apache.commons.math3.util.FastMath;

public class ZeroMinutesModel {
    public static double zeroMinutesProb(double pmin, int starter, int hasOt){
        double exp = Math.exp(0.485979d +
                -0.2167899d * pmin +
                (starter == 1 ? -16.08034d : 0) +
                pmin * (hasOt == 1 ? 0.03737547 : 0d));

        return exp / (1 + exp);
    }
}
