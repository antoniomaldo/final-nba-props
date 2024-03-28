package nba.simulator;

public class SimulatorPredictions {

    private final int gameId;
    private final int playerId;

    private final double fgAttemptedPred;
    private final double twosAvg;
    private final double threesAvg;
    private final double ftsAvg;
    private final double rebounds;
    private final double blocks;
    private final double steals;
    private final double averageMinutesInSeason;



    public SimulatorPredictions(int gameId, int playerId, double fgAttemptedPred, double twosAvg, double threesAvg, double ftsAvg, double rebounds, double blocks, double steals, double averageMinutesInSeason) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.fgAttemptedPred = fgAttemptedPred;
        this.twosAvg = twosAvg;
        this.threesAvg = threesAvg;
        this.ftsAvg = ftsAvg;
        this.rebounds = rebounds;
        this.blocks = blocks;
        this.steals = steals;
        this.averageMinutesInSeason = averageMinutesInSeason;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public double getFgAttemptedPred() {
        return fgAttemptedPred;
    }

    public double getTwosAvg() {
        return twosAvg;
    }

    public double getThreesAvg() {
        return threesAvg;
    }

    public double getFtsAvg() {
        return ftsAvg;
    }

    public double getAverageMinutesInSeason() {
        return averageMinutesInSeason;
    }

    public String[] toRow(){
        String[] row = {
                Integer.toString(this.gameId),
                Integer.toString(this.playerId),
                Double.toString(this.fgAttemptedPred),
                Double.toString(this.twosAvg),
                Double.toString(this.threesAvg),
                Double.toString(this.ftsAvg),
                Double.toString(this.rebounds),
                Double.toString(this.steals),
                Double.toString(this.blocks),
                Double.toString(this.averageMinutesInSeason)
        };
        return row;
    }
}
