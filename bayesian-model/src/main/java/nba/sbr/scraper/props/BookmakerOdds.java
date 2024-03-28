package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;
import java.util.Map;

@AutoValue
@JsonDeserialize(builder = BookmakerOdds.Builder.class)
public abstract class BookmakerOdds {
    public static Builder builder() {
        return new AutoValue_BookmakerOdds.Builder();
    }

    public abstract String getSportsbook();
    @Nullable
    public abstract Map<String, Double> getOpeningLine();
    @Nullable
    public abstract Map<String, Double> getCurrentLine();
    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Builder create() {
            return BookmakerOdds.builder();
        }
        public abstract Builder sportsbook(String sportsbook);
        public abstract Builder openingLine(Map<String, Double> openingLine);
        public abstract Builder currentLine(Map<String, Double> currentLine);

        public abstract BookmakerOdds build();
    }
}
