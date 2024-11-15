package nba.mappings;

import java.util.HashMap;
import java.util.Map;

public class SbrToRotowireTeamNameMapping {

    private static Map<String, String> map = new HashMap<>();

    static{
        map.put("Miami", "MIA");
        map.put("Milwaukee", "MIL");
        map.put("Sacramento", "SAC");
        map.put("Oklahoma City", "OKC");
        map.put("L.A. Lakers", "LAL");
        map.put("Golden State", "GSW");
        map.put("New Orleans", "NOP");
        map.put("Dallas", "DAL");
        map.put("Atlanta", "ATL");
        map.put("Boston", "BOS");
        map.put("Toronto", "TOR");
        map.put("Washington", "WAS");
        map.put("Philadelphia", "PHI");
        map.put("New York", "NYK");
        map.put("Minnesota", "min");
        map.put("Houston", "HOU");
        map.put("Utah", "UTA");
        map.put("Cleveland", "CLE");
        map.put("Denver", "DEN");
        map.put("San Antonio", "SAS");
        map.put("L.A. Clippers", "LAC");
        map.put("Orlando", "ORL");
        map.put("Indiana", "IND");
        map.put("Charlotte", "CHA");
        map.put("Detroit", "DET");
        map.put("Brooklyn", "BKN");
        map.put("Phoenix", "PHX");
        map.put("Memphis", "MEM");
        map.put("Portland", "POR");
        map.put("Chicago", "CHI");
    }

    public static String mapToRotowire(String sbrName){
        return map.get(sbrName);
    }
}
