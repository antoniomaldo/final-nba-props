package nba.sbr.scraper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import nba.sbr.domain.Bookmaker;
import nba.sbr.domain.Market;
import nba.sbr.domain.MatchWithoutOdds;
import nba.sbr.scraper.props.ScriptObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UpcomingMatchesScraper extends MatchesScraper<MatchWithoutOdds> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("E, MMMM d, yyyy");

    private final Bookmaker bookmaker;

    public UpcomingMatchesScraper(Bookmaker bookmaker) {
        this.bookmaker = bookmaker;
    }

    @Override
    protected ScriptObject getMarkets(String url, boolean isMoneyLine) throws IOException {
        Document document = getDocument(url);
        if(document == null){
            return null;
        }
        Elements scriptElements = document.select("script");
        Element script = scriptElements.last();

        String jsCode = script.html();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsCode = jsCode.replace("OpeningLine", "openingLine");
        jsCode = jsCode.replace("CurrentLine", "currentLine");
        jsCode = jsCode.replace("AwayTeam", "awayTeam");
        jsCode = jsCode.replace("HomeTeam", "homeTeam");
        jsCode = jsCode.replace("StartDate", "startDate");

        ScriptObject scriptObject = objectMapper.readValue(jsCode, ScriptObject.class);

        return scriptObject;
    }

    private Document getDocument(String url) {
        int tries = 0;
        while (tries < 3) {
            Document document;
            try {
                document = Jsoup.connect(url).userAgent("a").timeout(10000).get();
            } catch (Exception e) {
                document = null;
                e.printStackTrace();
            }
            if (document == null) {
                tries++;
            } else {
                return document;
            }
        }
        return null;
    }
}
