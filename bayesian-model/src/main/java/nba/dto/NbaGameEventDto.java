package nba.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;
import com.sun.istack.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoValue
@JsonDeserialize(builder = AutoValue_NbaGameEventDto.Builder.class)
public abstract class NbaGameEventDto {

    public static Builder builder() {
        return new AutoValue_NbaGameEventDto.Builder();
    }

    public abstract Builder toBuilder();

    @Nullable
    public abstract String getEventTime();

    public abstract String getEventName();

    public abstract String getHomeTeamName();

    public abstract String getAwayTeamName();

    @Nullable
    public abstract Double getTotalPoints();

    @Nullable
    public abstract Double getMatchSpread();

    public abstract List<PlayerRequest> getHomePlayers();

    public abstract List<PlayerRequest> getAwayPlayers();

    public List<PlayerRequest> getAllPlayers() {
        return Stream.concat(getHomePlayers().stream(), getAwayPlayers().stream()).collect(Collectors.toList());
    }

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public static abstract class Builder {

        public abstract Builder eventTime(final String eventTime);

        public abstract Builder eventName(final String eventName);

        public abstract Builder homeTeamName(final String homeTeamName);

        public abstract Builder awayTeamName(final String awayTeamName);

        public abstract Builder totalPoints(final Double totalPoints);

        public abstract Builder matchSpread(final Double matchSpread);

        public abstract Builder homePlayers(List<PlayerRequest> homePlayers);

        public abstract Builder awayPlayers(List<PlayerRequest> awayPlayers);

        public abstract NbaGameEventDto build();
    }

}

