package nba.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;
import com.sun.istack.Nullable;

@AutoValue
@JsonDeserialize(builder = AutoValue_PlayerRequest.Builder.class)
public abstract class PlayerRequest {
    public static Builder builder() {
        return new AutoValue_PlayerRequest.Builder();
    }

    public abstract Builder toBuilder();

    public abstract String getTeam();

    public abstract String getName();

    public abstract double getPmin();

    public abstract String getPosition();

    @Nullable
    public abstract Boolean getIsHomePlayer();

    public abstract Double getAverageMinutes();

    public abstract Double getFgAttemptedCoef();

    public abstract Double getFgAttemptedPrior();


    public abstract Double getThreePropCoef();

    public abstract Double getThreePropPrior();


    public abstract Double getThreePercCoef();

    public abstract Double getThreePercPrior();


    public abstract Double getTwoPercCoef();

    public abstract Double getTwoPercPrior();


    public abstract Double getFtsAttemptedCoef();

    public abstract Double getFtsAttemptedPrior();


    public abstract Double getFtPercCoef();

    public abstract Double getFtPercPrior();


    public abstract Double getReboundsCoef();

    public abstract Double getReboundsPrior();


    public abstract Double getOffReboundsPropCoef();

    public abstract Double getOffReboundsPropPrior();


    public abstract Double getBlocksCoef();

    public abstract Double getBlocksPrior();


    public abstract Double getStealsCoef();

    public abstract Double getStealsPrior();


    public abstract Double getTurnoversCoef();

    public abstract Double getTurnoversPrior();


    public abstract Double getFoulsCoef();

    public abstract Double getFoulsPrior();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public static abstract class Builder {

        public abstract Builder team(final String team);

        public abstract Builder name(String name);

        public abstract Builder pmin(double pmin);

        public abstract Builder position(String position);

        @Nullable
        public abstract Builder isHomePlayer(Boolean isHomePlayer);

        public abstract Builder averageMinutes(Double averageMinutes);

        public abstract Builder fgAttemptedCoef(Double fgAttemptedCoef);

        public abstract Builder fgAttemptedPrior(Double fgAttemptedPrior);

        public abstract Builder threePropCoef(Double threePropCoef);

        public abstract Builder threePropPrior(Double threePropPrior);

        public abstract Builder threePercCoef(Double threePercCoef);

        public abstract Builder threePercPrior(Double threePercPrior);

        public abstract Builder twoPercCoef(Double twoPercCoef);

        public abstract Builder twoPercPrior(Double twoPercPrior);

        public abstract Builder ftsAttemptedCoef(Double ftsAttemptedCoef);

        public abstract Builder ftsAttemptedPrior(Double ftsAttemptedPrior);

        public abstract Builder ftPercCoef(Double ftPercCoef);

        public abstract Builder ftPercPrior(Double ftPercPrior);

        public abstract Builder reboundsCoef(Double reboundsCoef);

        public abstract Builder reboundsPrior(Double reboundsPrior);

        public abstract Builder offReboundsPropCoef(Double offReboundsPropCoef);

        public abstract Builder offReboundsPropPrior(Double offReboundsPropPrior);

        public abstract Builder blocksCoef(Double blocksCoef);

        public abstract Builder blocksPrior(Double blocksPrior);

        public abstract Builder stealsCoef(Double stealsCoef);

        public abstract Builder stealsPrior(Double stealsPrior);

        public abstract Builder turnoversCoef(Double turnoversCoef);

        public abstract Builder turnoversPrior(Double turnoversPrior);

        public abstract Builder foulsCoef(Double foulsCoef);

        public abstract Builder foulsPrior(Double foulsPrior);

        public abstract PlayerRequest build();
    }

}
