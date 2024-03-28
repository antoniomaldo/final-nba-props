package nba.sbr.scraper;

import nba.sbr.domain.Bookmaker;
import nba.sbr.domain.MatchWithOdds;
import nba.sbr.domain.MatchWithoutOdds;
import org.joda.time.DateTime;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NbaScraper {

    private static final MatchesScraper<MatchWithoutOdds> MATCH_SCRAPER_BET_365 = new UpcomingMatchesScraper(Bookmaker.BET_365);
    private static final MatchesScraper<MatchWithoutOdds> MATCH_SCRAPER_PINNACLE = new UpcomingMatchesScraper(Bookmaker.PINNACLE);

    public static List<Map<String, MatchWithOdds>> scrapeTodaysMatchesForBet365() {
        try {
            String todayUrl = "https://www.sportsbookreview.com/betting-odds/nba-basketball/";
            System.out.println(todayUrl);
            String todayDate = DateTime.now().toLocalDate().toString().replace("-", "");
            List<Map<String, MatchWithOdds>> matches = MATCH_SCRAPER_BET_365.getMatches(
                    todayUrl, "?date=" + todayDate);
            return matches;
        } catch (IOException e) {
           return new ArrayList<>();
        }
    }
}
