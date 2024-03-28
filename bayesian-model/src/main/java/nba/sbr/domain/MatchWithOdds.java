package nba.sbr.domain;


import org.joda.time.DateTime;

public class MatchWithOdds {

    //    private static final TeamMappings MAPPINGS = new TeamMappings();

    private final MatchWithoutOdds matchWithoutOdds;
    private final Market matchSpread;
    private final Market matchTotal;

    public MatchWithOdds(MatchWithoutOdds matchWithoutOdds, Market matchSpread, Market matchTotal) {
        this.matchWithoutOdds = matchWithoutOdds;
        this.matchSpread = matchSpread;
        this.matchTotal = matchTotal;
    }

    public DateTime getDateTime() {
        return this.matchWithoutOdds.getDateTime();
    }

    public MatchWithoutOdds getMatchWithoutOdds() {
        return this.matchWithoutOdds;
    }

    public Market getMatchSpread() {
        return matchSpread;
    }

    public Market getMatchTotal() {
        return matchTotal;
    }

    @Override
    public String toString() {
        return this.matchSpread == null ? ",," : this.matchSpread.toString();
    }

    public String toStringHomeFirst() {
        return
                (this.matchSpread == null ? ",," : this.matchSpread.toStringHomeFirst()) + "," + //
                (this.matchTotal == null ? ",," : this.matchTotal.toString());
    }

}
