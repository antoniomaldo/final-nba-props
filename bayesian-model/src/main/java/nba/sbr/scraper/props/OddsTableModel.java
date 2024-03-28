package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
@JsonDeserialize(builder = OddsTableModel.Builder.class)
public abstract class OddsTableModel {
    public static Builder builder() {
        return new AutoValue_OddsTableModel.Builder();
    }

    public abstract List<GameRow> getGameRows();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Builder create() {
            return OddsTableModel.builder();
        }
        public abstract Builder gameRows(List<GameRow>  gameRows);

        public abstract OddsTableModel build();
    }
}
