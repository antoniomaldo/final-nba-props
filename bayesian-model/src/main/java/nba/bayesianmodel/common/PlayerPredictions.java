package nba.bayesianmodel.common;

public class PlayerPredictions {

    private final int seasonYear;
    private final int gameId;
    private final int playerId;
    private final int numbOfGames;
    private final int numbOfGamesInSeason;

    private final int minPlayed;
    private final double playerCoef;
    private final double targetPerMinPredicted;
    private final double real;

    private final int threeAttempted;
    private final int fgAttempted;
    private final int threeMade;
    private final int fgMade;
    private final int offRebounds;
    private final int defRebounds;

    private final int ftMade;
    private final int ftAttempted;
    private final double prior;


    public PlayerPredictions(int seasonYear, int gameId, int playerId, int numbOfGames, int numbOfGamesInSeason, int minPlayed, double playerCoef, double targetPerMinPredicted, double real, int threeAttempted, int fgAttempted, int threeMade, int fgMade, int offRebounds, int defRebounds, int ftMade, int ftAttempted, double prior) {
        this.seasonYear = seasonYear;
        this.gameId = gameId;
        this.playerId = playerId;
        this.numbOfGamesInSeason = numbOfGamesInSeason;
        this.numbOfGames = numbOfGames;
        this.minPlayed = minPlayed;
        this.playerCoef = playerCoef;
        this.targetPerMinPredicted = targetPerMinPredicted;
        this.real = real;
        this.threeAttempted = threeAttempted;
        this.fgAttempted = fgAttempted;
        this.threeMade = threeMade;
        this.fgMade = fgMade;
        this.offRebounds = offRebounds;
        this.defRebounds = defRebounds;
        this.ftMade = ftMade;
        this.ftAttempted = ftAttempted;
        this.prior = prior;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public int getGameId() {
        return gameId;
    }

    public int getNumbOfGames() {
        return numbOfGames;
    }
    public int getRebounds() {
        return this.offRebounds + this.defRebounds;
    }

    public int getOffRebounds() {
        return this.offRebounds;
    }
    public int getMinPlayed() {
        return minPlayed;
    }

    public double getPlayerCoef() {
        return playerCoef;
    }

    public double getTargetPredicted() {
        return targetPerMinPredicted;
    }

    public double getReal() {
        return real;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getThreeAttempted() {
        return threeAttempted;
    }

    public int getThreeMade() {
        return threeMade;
    }

    public int getFgAttempted() {
        return fgAttempted;
    }

    public int getFgMade() {
        return fgMade;
    }

    public int getFtMade() {
        return ftMade;
    }

    public int getFtAttempted() {
        return ftAttempted;
    }

    public String[] toRow(){
        String[] row = {
                Integer.toString(this.seasonYear),
                Integer.toString(this.gameId),
                Integer.toString(this.playerId),
                Integer.toString(this.numbOfGames),
                Integer.toString(this.numbOfGamesInSeason),
                Integer.toString(this.minPlayed),
                Double.toString(this.playerCoef),
                Double.toString(this.targetPerMinPredicted),
                Double.toString(this.real),
                Integer.toString(this.threeAttempted),
                Integer.toString(this.fgAttempted),
                Integer.toString(this.threeMade),
                Integer.toString(this.fgMade),
                Double.toString(this.prior)

        };
        return row;
    }
}
