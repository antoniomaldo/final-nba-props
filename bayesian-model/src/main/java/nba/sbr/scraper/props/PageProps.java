package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
@JsonDeserialize(builder = PageProps.Builder.class)

public abstract class PageProps {
    public static Builder builder() {
        return new AutoValue_PageProps.Builder();
    }

    public abstract List<OddsTables> getOddsTables();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {
        @JsonCreator
        private static Builder create() {
            return PageProps.builder();
        }

        public abstract Builder oddsTables(List<OddsTables> oddsTables);

        public abstract PageProps build();
    }
}
