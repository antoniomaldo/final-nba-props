package nba.sbr.domain;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MatchWithoutOdds {

    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

    private final DateTime dateTime;
    private final String awayTeamName;
    private final String homeTeamName;

    public MatchWithoutOdds(DateTime dateTime, String awayTeamName, String homeTeamName) {
        this.dateTime = dateTime;
        this.awayTeamName = awayTeamName;
        this.homeTeamName = homeTeamName;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    @Override
    public String toString() {
        return this.dateTime.toString(OUTPUT_DATE_TIME_FORMAT) + "," + this.awayTeamName + "," + this.homeTeamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        MatchWithoutOdds that = (MatchWithoutOdds) o;

        if (this.dateTime != null ? !this.dateTime.equals(that.dateTime) : that.dateTime != null) { return false; }
        if (this.awayTeamName != null ? !this.awayTeamName.equals(that.awayTeamName) : that.awayTeamName != null) {
            return false;
        }
        return this.homeTeamName != null ? this.homeTeamName.equals(that.homeTeamName) : that.homeTeamName == null;
    }

    @Override
    public int hashCode() {
        int result = this.dateTime != null ? this.dateTime.hashCode() : 0;
        result = 31 * result + (this.awayTeamName != null ? this.awayTeamName.hashCode() : 0);
        result = 31 * result + (this.homeTeamName != null ? this.homeTeamName.hashCode() : 0);
        return result;
    }
}
