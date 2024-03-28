package nba.dto;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RotowireDataLoaderTest {

    @Test
    @Ignore
    public void name() throws IOException {
        Map<String, List<RotowirePlayer>> stringListMap = RotowireDataLoader.loadTodaysPredictions();

        for(String team : stringListMap.keySet()){
            double sum = 0;
            List<RotowirePlayer> rotowirePlayers = stringListMap.get(team);
            for(RotowirePlayer rotowirePlayer : rotowirePlayers){
                sum += Double.parseDouble(rotowirePlayer.getPmin());
                System.out.println(team + ", " + rotowirePlayer.getName() + ", " + rotowirePlayer.getPmin());
            }
            System.out.println("Team total minutes -> " + team + " : " + sum);
        }
    }
}