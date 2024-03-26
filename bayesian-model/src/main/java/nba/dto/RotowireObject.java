package nba.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_RotowireObject.Builder.class)
public abstract class RotowireObject {

    public static RotowireObject.Builder builder() {
        return new AutoValue_RotowireObject.Builder();
    }

    public abstract Builder toBuilder();

    public abstract String getTeam();
    public abstract String getOpp();

    public abstract String getPlayer();

    public abstract String getMin();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public static abstract class Builder {
        public abstract Builder team(final String team);
        public abstract Builder opp(final String opp);

        public abstract Builder player(final String player);

        public abstract Builder min(final String min);

        public abstract RotowireObject build();

    }

}
