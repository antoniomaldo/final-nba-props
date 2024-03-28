package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = Props.Builder.class)
public abstract class Props {
    public static Builder builder() {
        return new AutoValue_Props.Builder();
    }

    public abstract PageProps getPageProps();


    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {
        @JsonCreator
        private static Builder create() {
            return Props.builder();
        }

        public abstract Builder pageProps(PageProps pageProps);

        public abstract Props build();
    }
}
