package nba.simulator;

import nba.bayesianmodel.models.*;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;

import java.util.Random;

public class PlayerSimulator {
    public static final Random RANDOM = new Random();
    private static final double THREE_PROP_WEIGHT = -4.1182636747493655;
    private static final double THREE_PERC_WEIGHT = -1.0895411400443664;
    public SimulatedPlayerScoring simulatePoints(PlayerWithCoefs playerWithCoefs, int minutesPlayed, int fgAttempted, double fgAttemptedPrediction, double ownExp) {

        double threeProp = TargetPredicted.forThreeProp(playerWithCoefs.getThreePropCoef(), playerWithCoefs.getThreePropPrior());

        double threePropAdjusted = ThreeProportionModelGivenFgAttempted.adjustRate(threeProp,fgAttemptedPrediction, fgAttempted);

        int threeAttempted = simulateThreeAttempted(fgAttempted, threePropAdjusted);
        int twoAttempted = fgAttempted - threeAttempted;

        double threePerc = TargetPredicted.forThreePerc(playerWithCoefs.getThreePercCoef(), threeAttempted, playerWithCoefs.getThreePercPrior(), THREE_PERC_WEIGHT);

        double threePercAdjusted = threeAttempted == 0 ? 0 : ThreePercModelGivenOwnExp.adjustRate(threePerc, fgAttemptedPrediction, fgAttempted, ownExp, minutesPlayed, threeAttempted, threePropAdjusted);

        double twoPerc = TargetPredicted.forTwoPerc(playerWithCoefs.getTwoPercCoef(), playerWithCoefs.getTwoPercPrior());
        double twoPercAdjusted = TwoPercModelGivenOwnExp.adjustRate(twoPerc, fgAttemptedPrediction,fgAttempted, ownExp, minutesPlayed, threeAttempted, threePropAdjusted, playerWithCoefs.getSeasonYear());






        int threePointers = 0;
        for (int i = 0; i < threeAttempted; i++) {
            if(Math.random() < threePercAdjusted){
                threePointers ++;
            }
        }

        int twoPointers = 0;
        for (int i = 0; i < twoAttempted; i++) {
            if(Math.random() < twoPercAdjusted){
                twoPointers ++;
            }
        }

        double ftsPercPred = TargetPredicted.forFtPerc(playerWithCoefs.getFtPercCoef(), playerWithCoefs.getFtPercPrior());

        double ftsAttemptedPred = minutesPlayed * TargetPredicted.forFtsAttempted(playerWithCoefs.getFtsAttemptedCoef(), playerWithCoefs.getFtsAttemptedPrior());

        double zeroProb = ZeroSetsOfFreeThrowsModel.getZeroFtsProb(ftsAttemptedPred, fgAttempted, threeAttempted, twoPointers + threePointers, threePointers);
        double oddProb = FreeThrowOddModel.getOddProb(minutesPlayed, ftsAttemptedPred, playerWithCoefs.getThreePropCoef(), playerWithCoefs.getTwoPercCoef(), twoPerc);
        double setOfFtPred = ftsAttemptedPred / 2 - oddProb;

        int ftsAttempted = simulateFtsAttempted(ftsAttemptedPred);

        int fts=0;
        for (int i = 0; i < ftsAttempted; i++) {
            if(Math.random() < ftsPercPred){
                fts ++;
            }
        }

        return new SimulatedPlayerScoring(twoPointers, threePointers, fts);
    }

    private int simulateFtsAttempted(double ftsAttemptedPred) {
        return simulateFgAttemped(ftsAttemptedPred);
    }
    private int simulateThreeAttempted(int fgAttempted, double threeProp) {
        int threes = 0;
        for (int i = 0; i < fgAttempted; i++) {
            if(RANDOM.nextDouble() < threeProp){
                threes++;
            }
        }
        return threes;
    }
    public static int simulateFgAttemped(double mean) {
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * RANDOM.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}