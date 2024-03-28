package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = ScriptObject.Builder.class)
public abstract class ScriptObject {

    public static Builder builder() {
        return new AutoValue_ScriptObject.Builder();
    }

    public abstract Props getProps();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")

    public abstract static class Builder {
        @JsonCreator
        private static Builder create() {
            return ScriptObject.builder();
        }
        public abstract Builder props(Props props);


        public abstract ScriptObject build();

    }
}
