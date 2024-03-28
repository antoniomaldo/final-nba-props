package nba.sbr.domain;

public class Market {
    private final double line;
    private final double awayOrOver;
    private final double homeOrUnder;

    public Market(double line, double awayOrOver, double homeOrUnder) {
        this.line = line;
        this.awayOrOver = awayOrOver;
        this.homeOrUnder = homeOrUnder;
    }

    public double getLine() {
        return this.line;
    }

    public double getAwayOrOver() {
        return this.awayOrOver;
    }

    public double getHomeOrUnder() {
        return this.homeOrUnder;
    }

    @Override
    public String toString() {
        return this.line + "," + this.awayOrOver + "," + this.homeOrUnder;
    }

    public String toStringHomeFirst() {
        return this.line + "," + this.homeOrUnder + "," + this.awayOrOver;
    }
}
