package nba.dto;

public class RotowirePlayerWithPreds extends RotowirePlayer{

    private final double zeroPred;
    private final double givenPlayedPred;

    public RotowirePlayerWithPreds(String name, String pmin, String pos, double zeroPred, double givenPlayedPred) {
        super(name, pmin, pos);
        this.zeroPred = zeroPred;
        this.givenPlayedPred = givenPlayedPred;
    }

    public double getZeroPred() {
        return zeroPred;
    }

    public double getGivenPlayedPred() {
        return givenPlayedPred;
    }
}
