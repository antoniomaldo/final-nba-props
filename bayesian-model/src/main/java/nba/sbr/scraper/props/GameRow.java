package nba.sbr.scraper.props;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
@JsonDeserialize(builder = GameRow.Builder.class)
public abstract class GameRow {
    public static Builder builder() {
        return new AutoValue_GameRow.Builder();
    }


    public abstract List<BookmakerOdds> getOddsViews();
    public abstract GameView getGameView();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Builder create() {
            return GameRow.builder();
        }
//        public abstract Builder gameView(GameView gameView);
        public abstract Builder oddsViews( List<BookmakerOdds>  oddsViews);
        public abstract Builder gameView(GameView gameView);

        public abstract GameRow build();
    }
}
