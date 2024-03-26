package nba.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RotowireDataLoader {
    private static final String ROTOWIRE_URL = "https://www.rotowire.com/basketball/tables/projections.php?type=daily&pos=ALL&date=";


    public static void main(String[] args) throws IOException {
        String url =ROTOWIRE_URL + "2024-03-18";

        Connection.Response res = Jsoup
                .connect("https://www.rotowire.com/users/login.php")
                .data("username", "", "password", "")
                .method(Connection.Method.POST)
                .execute();

        //This will get you cookies
        Map<String, String> cookies = res.cookies();

        String doc = Jsoup.connect(url).ignoreContentType(true).cookies(cookies).get().text();

        doc = doc.replace("MIN", "min");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RotowireObject[] rotowireObject = objectMapper.readValue(doc, RotowireObject[].class);

        List<String> teams = Arrays.stream(rotowireObject).map(r -> r.getTeam()).distinct().collect(Collectors.toList());

        Map<String, List<RotowirePlayer>> teamsMap = new HashMap<>();

        for(String team : teams){
            List<RotowirePlayer> playersForTeam = Arrays.stream(rotowireObject).filter(r -> r.getTeam().equalsIgnoreCase(team)).map(r -> new RotowirePlayer(r.getPlayer(), r.getMin())).collect(Collectors.toList());
            teamsMap.put(team, playersForTeam);
            System.out.println(team + " -> " + playersForTeam.stream().mapToDouble(r->Double.parseDouble(r.getPmin())).sum());
        }
        int x = 1;

    }
}
