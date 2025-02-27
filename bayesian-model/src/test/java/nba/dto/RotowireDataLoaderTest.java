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
        Map<String, List<FullRotowireObject>> stringListMap = new RotowireDataLoader().loadTodaysPredictions();

        for(String team : stringListMap.keySet()){
            double sum = 0;
            List<FullRotowireObject> rotowirePlayers = stringListMap.get(team);
            for(FullRotowireObject rotowirePlayer : rotowirePlayers){
                sum += Double.parseDouble(rotowirePlayer.getMin());
                System.out.println(team + ", " + rotowirePlayer.getPlayer() + ", " + rotowirePlayer.getMin());
            }
            System.out.println("Team total minutes -> " + team + " : " + sum);
        }
    }
}