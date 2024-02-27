package nba.simulator;

public class SimulatedPlayerScoring {

    private final int twoPointers;
    private final int threePoints;
    private final int fts;

    public SimulatedPlayerScoring(int twoPointers, int threePoints, int fts) {
        this.twoPointers = twoPointers;
        this.threePoints = threePoints;
        this.fts = fts;
    }

    public int getTwoPointers() {
        return twoPointers;
    }

    public int getThreePoints() {
        return threePoints;
    }

    public int getFts() {
        return fts;
    }
}
