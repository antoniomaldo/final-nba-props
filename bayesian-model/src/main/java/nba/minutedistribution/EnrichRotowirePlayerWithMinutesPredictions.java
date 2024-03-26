package nba.minutedistribution;

import nba.dto.PlayerRequest;
import nba.dto.RotowirePlayer;
import nba.dto.RotowirePlayerWithPreds;

import java.util.List;
import java.util.stream.Collectors;

public class EnrichRotowirePlayerWithMinutesPredictions {

    public static List<RotowirePlayerWithPreds> normalize(List<PlayerRequest> team, double ownExp, double oppExp){
        int n = 20;

        team = team.stream().filter(t->t.getPmin() > 0).collect(Collectors.toList());

        double teamSumAvgMinutes = 0d;

        for(PlayerRequest player : team){
            double zeroPred = ZeroMinutesModel.zeroMinutesProb(player.getPmin(), player.getStarter(), 0);
            double predGivenPlayed = MinutesGivenPlayedModel.getAverageGivenPlayed((int) player.getPmin(), player.getAverageMinutesInSeason(), ownExp, oppExp, 2024, player.getStarter(), 0, teamSumAvgMinutes);
        }




        return null;
    }
}
