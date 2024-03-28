package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = OddsTables.Builder.class)
public abstract class OddsTables {
    public static Builder builder() {
        return new AutoValue_OddsTables.Builder();
    }

    public abstract OddsTableModel getOddsTableModel();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Builder create() {
            return OddsTables.builder();
        }

        public abstract Builder oddsTableModel(OddsTableModel oddsTableModel);

        public abstract OddsTables build();
    }
}
