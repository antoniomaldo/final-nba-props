package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;
import java.util.Map;

@AutoValue
@JsonDeserialize(builder = ViewData.Builder.class)
public abstract class ViewData {
    public static Builder builder() {
        return new AutoValue_ViewData.Builder();
    }

    @Nullable
    public abstract Map<String, Double> getOpeningLine();
    @Nullable
    public abstract Map<String, Double> getCurrentLine();
    @Nullable
    public abstract Map<String, String> getAwayTeam();
    @Nullable
    public abstract Map<String, String> getHomeTeam();
    @Nullable
    public abstract String getStartDate();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {
        @JsonCreator
        private static Builder create() {
            return ViewData.builder();
        }
        public abstract Builder openingLine(Map<String, Double> openingLine);
        public abstract Builder currentLine(Map<String, Double> currentLine);
        public abstract Builder awayTeam(Map<String, String> awayTeam);
        public abstract Builder homeTeam(Map<String, String> homeTeam);
        public abstract Builder startDate(String startDate);

        public abstract ViewData build();
    }
}
