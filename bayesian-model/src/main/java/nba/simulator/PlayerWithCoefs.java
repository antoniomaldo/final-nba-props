package nba.simulator;

public class PlayerWithCoefs {

    private final int gameId;
    private final int seasonYear;

    private final String team;
    private final String playerName;
    private final int playerId;

    private final int minPlayed;

    private final double fgAttemptedPlayerCoef;
    private final double fgAttemptedPrior;

    private final double threePropCoef;
    private final double threePropPrior;

    private final double threePercCoef;
    private final double threePercPrior;
    private final double twoPercCoef;
    private final double twoPercPrior;

    private final double ftsAttemptedCoef;
    private final double ftsAttemptedPrior;

    private final double ftPercCoef;
    private final double ftPercPrior;

    private final double reboundsCoef;
    private final double reboundsPrior;

    private final double offReboundsPropCoef;
    private final double offReboundsPropPrior;

    private final double blocksCoef;
    private final double blocksPrior;

    private final double stealsCoef;
    private final double stealsPrior;

    private final double turnoversCoef;
    private final double turnoversPrior;

    private final double foulsCoef;
    private final double foulsPrior;

    private final double averageMinutesInSeason;


    public PlayerWithCoefs(int gameId, int seasonYear, String team, String playerName, int playerId, int minPlayed, double fgAttemptedPlayerCoef, double fgAttemptedPrior, double threePropCoef, double threePropPrior, double threePercCoef, double threePercPrior, double twoPercCoef, double twoPercPrior, double ftsAttemptedCoef, double ftsAttemptedPrior, double ftPercCoef, double ftPercPrior, double reboundsCoef, double reboundsPrior, double offReboundsPropCoef, double offReboundsPropPrior, double blocksCoef, double blocksPrior, double stealsCoef, double stealsPrior, double turnoversCoef, double turnoversPrior, double foulsCoef, double foulsPrior, double averageMinutesInSeason) {
        this.gameId = gameId;
        this.seasonYear = seasonYear;
        this.team = team;
        this.playerName = playerName;
        this.playerId = playerId;
        this.minPlayed = minPlayed;
        this.fgAttemptedPlayerCoef = fgAttemptedPlayerCoef;
        this.fgAttemptedPrior = fgAttemptedPrior;
        this.threePropCoef = threePropCoef;
        this.threePropPrior = threePropPrior;
        this.threePercCoef = threePercCoef;
        this.threePercPrior = threePercPrior;
        this.twoPercCoef = twoPercCoef;
        this.twoPercPrior = twoPercPrior;
        this.ftsAttemptedCoef = ftsAttemptedCoef;
        this.ftsAttemptedPrior = ftsAttemptedPrior;
        this.ftPercCoef = ftPercCoef;
        this.ftPercPrior = ftPercPrior;
        this.reboundsCoef = reboundsCoef;
        this.reboundsPrior = reboundsPrior;
        this.offReboundsPropCoef = offReboundsPropCoef;
        this.offReboundsPropPrior = offReboundsPropPrior;
        this.blocksCoef = blocksCoef;
        this.blocksPrior = blocksPrior;
        this.stealsCoef = stealsCoef;
        this.stealsPrior = stealsPrior;
        this.turnoversCoef = turnoversCoef;
        this.turnoversPrior = turnoversPrior;
        this.foulsCoef = foulsCoef;
        this.foulsPrior = foulsPrior;
        this.averageMinutesInSeason = averageMinutesInSeason;
    }

    public int getGameId() {
        return gameId;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public String getTeam() {
        return team;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getMinPlayed() {
        return minPlayed;
    }

    public double getFgAttemptedPlayerCoef() {
        return fgAttemptedPlayerCoef;
    }

    public double getFgAttemptedPrior() {
        return fgAttemptedPrior;
    }

    public double getThreePropCoef() {
        return threePropCoef;
    }

    public double getThreePropPrior() {
        return threePropPrior;
    }

    public double getThreePercCoef() {
        return threePercCoef;
    }

    public double getThreePercPrior() {
        return threePercPrior;
    }

    public double getTwoPercCoef() {
        return twoPercCoef;
    }

    public double getTwoPercPrior() {
        return twoPercPrior;
    }

    public double getFtsAttemptedCoef() {
        return ftsAttemptedCoef;
    }

    public double getFtsAttemptedPrior() {
        return ftsAttemptedPrior;
    }

    public double getFtPercCoef() {
        return ftPercCoef;
    }

    public double getFtPercPrior() {
        return ftPercPrior;
    }

    public double getReboundsCoef() {
        return reboundsCoef;
    }

    public double getReboundsPrior() {
        return reboundsPrior;
    }

    public double getOffReboundsPropCoef() {
        return offReboundsPropCoef;
    }

    public double getOffReboundsPropPrior() {
        return offReboundsPropPrior;
    }

    public double getBlocksCoef() {
        return blocksCoef;
    }

    public double getBlocksPrior() {
        return blocksPrior;
    }

    public double getStealsCoef() {
        return stealsCoef;
    }

    public double getStealsPrior() {
        return stealsPrior;
    }

    public double getTurnoversCoef() {
        return turnoversCoef;
    }

    public double getTurnoversPrior() {
        return turnoversPrior;
    }

    public double getFoulsCoef() {
        return foulsCoef;
    }

    public double getFoulsPrior() {
        return foulsPrior;
    }

    public double getAverageMinutesInSeason(){
        return averageMinutesInSeason;
    }
}
