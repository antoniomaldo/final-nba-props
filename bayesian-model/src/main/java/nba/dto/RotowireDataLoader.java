package nba.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;

import java.io.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RotowireDataLoader {

    private static final String ROTOWIRE_URL = "https://www.rotowire.com/basketball/tables/projections.php?type=today&pos=ALL&date=";

    public Map<String, List<FullRotowireObject>> loadTodaysPredictions()  {
        String today = new DateTime().toLocalDate().toString();
        Map<String, List<FullRotowireObject>> stringListMap = loadPredictionsForDay(today);

        return stringListMap;
    }

    public Map<String, List<FullRotowireObject>> loadPredictionsForDay(String today) {
        String url = ROTOWIRE_URL + today;

        try {

            Map<String, String> cookies = loadCookiesFromJson("C:\\nba-player-props\\model\\final-nba-props\\bayesian-model\\src\\main\\resources\\roto-cookies.json");

            String doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/x-www-form-urlencoded").
                    ignoreContentType(true).
                            cookies(cookies).
                            get().text();

            doc = doc.replace("MIN", "min");
            doc = doc.replace("AST", "ast");
            doc = doc.replace("BLK", "blk");
            doc = doc.replace("DREB", "dreb");
            doc = doc.replace("FGPCT", "fgpct");
            doc = doc.replace("FTA", "fta");
            doc = doc.replace("FTM", "ftm");
            doc = doc.replace("OREB", "oreb");
            doc = doc.replace("PF", "pf");
            doc = doc.replace("PTS", "pts");
            doc = doc.replace("REB", "reb");
            doc = doc.replace("STL", "stl");
            doc = doc.replace("TO", "to");
            doc = doc.replace("FGA", "fga");
            doc = doc.replace("FGM", "fgm");
            doc = doc.replace("FTPCT", "ftpct");
            doc = doc.replace("THREEPA", "threepa");
            doc = doc.replace("THREEPM", "threepm");
            doc = doc.replace("THREEPPCT", "threeppct");


            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jdk8Module());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            FullRotowireObject[] rotowireObject = objectMapper.readValue(doc, FullRotowireObject[].class);

            List<String> teams = Arrays.stream(rotowireObject).map(r -> r.getTeam()).distinct().collect(Collectors.toList());

            Map<String, List<FullRotowireObject>> teamsMap = new HashMap<>();

            for (String team : teams) {
                List<FullRotowireObject> playersForTeam = Arrays.stream(rotowireObject).filter(r -> r.getTeam().equalsIgnoreCase(team) && Double.parseDouble(r.getMin()) > 0).
                        collect(Collectors.toList());
                teamsMap.put(team, playersForTeam);
            }

            return teamsMap;

        } catch (IOException e) {
            System.out.println("Error in date " + today);
            return new HashMap<>();
        }
    }

    private static Map<String, String> loadCookiesFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file into a list of cookies
            TypeToken<Map<String, Object>[]> token = new TypeToken<Map<String, Object>[]>() {};
            Map<String, Object>[] cookieArray = new Gson().fromJson(reader, token.getType());

            // Convert the array into a simple Map<String, String>
            return cookieArrayToMap(cookieArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Map<String, String> cookieArrayToMap(Map<String, Object>[] cookieArray) {
        Map<String, String> cookieMap = new java.util.HashMap<>();
        for (Map<String, Object> cookie : cookieArray) {
            String name = (String) cookie.get("name");
            String value = (String) cookie.get("value");
            cookieMap.put(name, value);
        }
        return cookieMap;
    }
}
