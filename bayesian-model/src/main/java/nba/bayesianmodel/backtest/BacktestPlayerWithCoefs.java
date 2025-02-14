package nba.bayesianmodel.backtest;

import nba.simulator.PlayerWithCoefs;

public class BacktestPlayerWithCoefs extends PlayerWithCoefs {
    private final int pmin;
    private final String homeTeam;
    private final double ownExp;
    private final double oppExp;

    private final int starter;
    public BacktestPlayerWithCoefs(int gameId, int seasonYear, String team, String playerName, int playerId, int minPlayed, double fgAttemptedPlayerCoef, double fgAttemptedPrior, double threePropCoef, double threePropPrior, double threePercCoef, double threePercPrior, double twoPercCoef, double twoPercPrior, double ftsAttemptedCoef, double ftsAttemptedPrior, double ftPercCoef, double ftPercPrior, double reboundsCoef, double reboundsPrior, double offReboundsPropCoef, double offReboundsPropPrior, double blocksCoef, double blocksPrior, double stealsCoef, double stealsPrior, double turnoversCoef, double turnoversPrior, double foulsCoef, double foulsPrior, double averageMinutesInSeason, double lastGameMin, int pmin, double ownExp, double oppExp, String homeTeam, int starter) {
        super(gameId, seasonYear, team, playerName, playerId, minPlayed, fgAttemptedPlayerCoef, fgAttemptedPrior, threePropCoef, threePropPrior, threePercCoef, threePercPrior, twoPercCoef, twoPercPrior, ftsAttemptedCoef, ftsAttemptedPrior, ftPercCoef, ftPercPrior, reboundsCoef, reboundsPrior, offReboundsPropCoef, offReboundsPropPrior, blocksCoef, blocksPrior, stealsCoef, stealsPrior, turnoversCoef, turnoversPrior, foulsCoef, foulsPrior,1,1, averageMinutesInSeason, lastGameMin); //FIXME assists coefs
        this.pmin = pmin;
        this.ownExp = ownExp;
        this.oppExp = oppExp;
        this.homeTeam = homeTeam;
        this.starter = starter;
    }

    public int getPmin() {
        return pmin;
    }

    public double getOwnExp() {
        return ownExp;
    }

    public double getOppExp() {
        return oppExp;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getStarter() {
        return starter;
    }
}
