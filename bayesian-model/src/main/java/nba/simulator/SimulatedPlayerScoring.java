package nba.simulator;

public class SimulatedPlayerScoring {

    private final int twoPointers;
    private final int threePoints;
    private final int fts;
    private final int assistsAttempted;

    public SimulatedPlayerScoring(int twoPointers, int threePoints, int fts, int assistsAttempted) {
        this.twoPointers = twoPointers;
        this.threePoints = threePoints;
        this.fts = fts;
        this.assistsAttempted = assistsAttempted;
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

    public int getAssistsAttempted() {
        return assistsAttempted;
    }
}
