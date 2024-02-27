package nba.bayesianmodel.common;

public class PlayerGameData {

    private final int date;
    private final int gameId;
    private final int playerId;

    private final String team;

    private final PlayerPosition position;
    private final boolean starter;
    private final int minPlayed;
    private final int fgMade;
    private final int fgAttempted;
    private final int threeMade;
    private final int threeAttempted;
    private final int ftMade;

    private final int ftAttempted;

    private final int offRebounds;
    private final int defRbounds;
    private final int assists;
    private final int steals;
    private final int blocks;
    private final int turnovers;
    private final int personalFouls;

    private final int seasonYear;

    private final int twoAttempted;
    private final int twoMade;

    public PlayerGameData(int date, int gameId, int playerId, String team, PlayerPosition position, boolean starter, int minPlayed, int fgMade, int fgAttempted, int threeMade, int threeAttempted, int ftMade, int ftAttempted, int offRebounds, int defRbounds, int assists, int steals, int blocks, int turnovers, int personalFouls, int seasonYear) {
        this.date = date;
        this.gameId = gameId;
        this.playerId = playerId;
        this.team = team;
        this.position = position;
        this.starter = starter;
        this.minPlayed = minPlayed;
        this.fgMade = fgMade;
        this.fgAttempted = fgAttempted;
        this.threeMade = threeMade;
        this.threeAttempted = threeAttempted;
        this.ftMade = ftMade;
        this.ftAttempted = ftAttempted;
        this.offRebounds = offRebounds;
        this.defRbounds = defRbounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personalFouls = personalFouls;
        this.seasonYear = seasonYear;
        this.twoAttempted = this.fgAttempted - this.threeAttempted;
        this.twoMade = this.fgMade - this.threeMade;
    }

    public int getDate() {
        return date;
    }

    public int getPlayerId() {
        return playerId;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public int getGameId() {
        return gameId;
    }


    public int getMinPlayed() {
        return minPlayed;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public int getFgAttempted() {
        return fgAttempted;
    }

    public int getThreeAttempted() {
        return threeAttempted;
    }

    public int getTwoMade() {
        return twoMade;
    }

    public int getThreeMade() {
        return threeMade;
    }

    public int getFgMade() {
        return fgMade;
    }

    public int getTwoAttempted() {
        return twoAttempted;
    }

    public String getTeam() {
        return team;
    }

    public boolean isStarter() {
        return starter;
    }

    public int getFtMade() {
        return ftMade;
    }

    public int getFtAttempted() {
        return ftAttempted;
    }

    public int getOffRebounds() {
        return offRebounds;
    }

    public int getDefRbounds() {
        return defRbounds;
    }

    public int getAssists() {
        return assists;
    }

    public int getRebounds(){
        return this.offRebounds + this.defRbounds;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }
}
