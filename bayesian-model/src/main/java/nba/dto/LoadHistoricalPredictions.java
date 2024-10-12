package nba.dto;

import com.opencsv.CSVWriter;
import nba.bayesianmodel.common.CsvUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadHistoricalPredictions {

    public static void main(String[] args) throws IOException {
//        String date = "2023-11-11";

        LocalDate start = LocalDate.of(2024,04,01);
        LocalDate end = LocalDate.of(2024,04,01);

        for (LocalDate localDate = start; localDate.isBefore(end); localDate = localDate.plusDays(1)) {
            String date = localDate.toString();
            System.out.println(date);
            Map<String, List<FullRotowireObject>> stringListMap = RotowireDataLoader.loadPredictionsForDay(date);

            List<String[]> csv = new ArrayList<>();

            csv.add(new String[] {",",",",",",",",",", "Popular Stats",",", ",", ",", ",", ",", ",","Field Goals",",",",",",", "Three Pointers",",","Free Throws", ",","More Stats", ","});
            csv.add(new String[] {"NAME","Team","OPP","Pos","MIN","PTS","REB","AST","STL","BLK","TO","FGM","FGA","FG%","3PM","3PA","3P%","FTM","FTA","FT%","OREB","DREB"});

            for(String team : stringListMap.keySet()){

                List<FullRotowireObject> fullRotowireObjects = stringListMap.get(team);
                for(FullRotowireObject fullRotowireObject : fullRotowireObjects){
                    csv.add(new String[] {
                            fullRotowireObject.getPlayer(),
                            fullRotowireObject.getTeam() ,
                            fullRotowireObject.getOpp() ,
                            fullRotowireObject.getPos() ,
                            fullRotowireObject.getMin() ,
                            fullRotowireObject.getPts() ,
                            fullRotowireObject.getReb() ,
                            fullRotowireObject.getAst() ,
                            fullRotowireObject.getStl() ,
                            fullRotowireObject.getBlk() ,
                            fullRotowireObject.getTo() ,
                            fullRotowireObject.getFgm() ,
                            fullRotowireObject.getFga() ,
                            "FG%" ,
                            fullRotowireObject.getThreepm() ,
                            fullRotowireObject.getThreepa() ,
                            fullRotowireObject.getThreeppct() ,
                            fullRotowireObject.getFtm() ,
                            fullRotowireObject.getFta() ,
                            "FT%" ,
                            fullRotowireObject.getOreb() ,
                            fullRotowireObject.getDreb()});

                }

            }
            CsvUtils.saveRotowirePreds(csv, date);
        }


        int x = 1;
    }
}
