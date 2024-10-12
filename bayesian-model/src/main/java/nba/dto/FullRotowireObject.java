package nba.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_FullRotowireObject.Builder.class)
public abstract class FullRotowireObject {

    public static FullRotowireObject.Builder builder() {
        return new AutoValue_FullRotowireObject.Builder();
    }

    public abstract Builder toBuilder();
    public abstract String getPlayer();
    public abstract String getTeam();
    public abstract String getPos();
    public abstract String getAst();
    public abstract String getBlk();
    public abstract String getDreb();
    public abstract String getFga();
    public abstract String getFgm();
    public abstract String getFta();
    public abstract String getFtm();
    public abstract String getMin();
    public abstract String getOreb();
    public abstract String getPf();
    public abstract String getPts();
    public abstract String getReb();
    public abstract String getStl();
    public abstract String getTo();
    public abstract String getOpp();
    public abstract String getThreepa();
    public abstract String getThreepm();
    public abstract String getThreeppct();

    {

    }

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public static abstract class Builder {

        public abstract Builder player(final String player);
        public abstract Builder team(final String team);
        public abstract Builder pos(final String pos);
        public abstract Builder ast(final String ast);
        public abstract Builder blk(final String blk);
        public abstract Builder dreb(final String dreb);
        public abstract Builder fga(final String fga);
        public abstract Builder fgm(final String fgm);
        public abstract Builder fta(final String fta);
        public abstract Builder ftm(final String ftm);
        public abstract Builder min(final String min);
        public abstract Builder oreb(final String oreb);
        public abstract Builder pf(final String pf);
        public abstract Builder pts(final String pts);
        public abstract Builder reb(final String reb);
        public abstract Builder stl(final String stl);
        public abstract Builder to(final String to);
        public abstract Builder opp(final String opp);

        public abstract Builder threepa(final String threepa);
        public abstract Builder threepm(final String threepm);
        public abstract Builder threeppct(final String threeppct);
        public abstract FullRotowireObject build();

    }

}
