package nba.simulator;

import java.util.Map;

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
    private final double zeroProb;

    private final Map<Integer, Double> playerMins;



    public SimulatorPredictions(int gameId, int playerId, double fgAttemptedPred, double twosAvg, double threesAvg, double ftsAvg, double rebounds, double blocks, double steals, double averageMinutesInSeason, double zeroProb, Map<Integer, Double> playerMins) {
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
        this.zeroProb = zeroProb;
        this.playerMins = playerMins;
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

    public double getProbabilityMinFromTo(int from, int to){
        double sum = 0;
        for (int i = from; i < to; i++) {
            Double sum1 = this.playerMins.get(i);
            sum += sum1 == null ? 0 : sum1;
        }
        return sum;
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
                Double.toString(this.averageMinutesInSeason),
                Double.toString(this.zeroProb),
                Double.toString(getProbabilityMinFromTo(1,5)),
                Double.toString(getProbabilityMinFromTo(6,10)),
                Double.toString(getProbabilityMinFromTo(11, 15)),
                Double.toString(getProbabilityMinFromTo(16, 20)),
                Double.toString(getProbabilityMinFromTo(21, 25)),
                Double.toString(getProbabilityMinFromTo(26, 30)),
                Double.toString(getProbabilityMinFromTo(31, 35)),
                Double.toString(getProbabilityMinFromTo(36, 40)),
                Double.toString(getProbabilityMinFromTo(41, 48)),
                Double.toString(getAverageMinutes())

        };
        return row;
    }

    private double getAverageMinutes() {
        double average = 0;
        for(Integer minutes : this.playerMins.keySet()){
            average += minutes * this.playerMins.get(minutes);
        }
        return average;
    }
}
