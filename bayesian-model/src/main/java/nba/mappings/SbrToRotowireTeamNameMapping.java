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
    }

    public static String mapToRotowire(String sbrName){
        return map.get(sbrName);
    }
}
