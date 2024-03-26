package nba.ui_application;

import nba.bayesianmodel.common.CsvUtils;
import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import nba.simulator.PlayerWithCoefs;
import org.apache.commons.math3.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class DataServiceController {
    public static String dataLastUpdated = null;

    @ResponseBody
    @GetMapping(value = "/getDayEvents")
    public ResponseEntity<?> getDayEvents() {

        dataLastUpdated = "11/11/23 10:30:05";
        NbaGameEventDto nbaGameEventDto = DataServiceController.loadDataForGameId(401585636);

        Map<String, NbaGameEventDto> map = new HashMap<>();

        map.put(nbaGameEventDto.getEventName(), nbaGameEventDto);

        return new ResponseEntity<>(new Pair<>(dataLastUpdated, map), HttpStatus.OK);
    }

    public static NbaGameEventDto loadDataForGameId(int gameId){
        List<PlayerWithCoefs> playerWithCoefs = CsvUtils.loadPredictionsData();

        List<PlayerWithCoefs> playersForGame = playerWithCoefs.stream().filter(i -> i.getGameId() == gameId).collect(Collectors.toList());

        List<String> teams = playersForGame.stream().map(p->p.getTeam()).distinct().collect(Collectors.toList());

        String homeTeam = teams.get(0);
        String awayTeam = teams.get(1);

        List<PlayerWithCoefs> homePlayers = playersForGame.stream().filter(p->p.getTeam().equalsIgnoreCase(homeTeam)).collect(Collectors.toList());
        List<PlayerWithCoefs> awayPlayers = playersForGame.stream().filter(p->!p.getTeam().equalsIgnoreCase(homeTeam)).collect(Collectors.toList());

        NbaGameEventDto nbaGameEventDto = NbaGameEventDto.builder().
                eventTime("11/11/11 12:30").
                eventName(awayTeam + " @ " + homeTeam).
                homeTeamName(homeTeam).
                awayTeamName(awayTeam).
                totalPoints(220.5).
                matchSpread(-5.5).
                homePlayers(buildPlayerRequest(homePlayers, homeTeam, true)).
                awayPlayers(buildPlayerRequest(awayPlayers, awayTeam,false)).
                build();

        return nbaGameEventDto;
    }

    private static List<PlayerRequest> buildPlayerRequest(List<PlayerWithCoefs> players, String teamName, boolean isHomePlayer) {
        List<PlayerRequest> list = new ArrayList<>();
        for(PlayerWithCoefs player : players){
            PlayerRequest playerRequest = PlayerRequest.builder()
                    .team(teamName)
                    .name(player.getPlayerName())
                    .pmin(player.getMinPlayed())
                    .position("NA")
                    .isHomePlayer(isHomePlayer)
                    .averageMinutes(0d)

                    .fgAttemptedCoef(round(player.getFgAttemptedPlayerCoef()))
                    .fgAttemptedPrior(round(player.getFgAttemptedPrior()))

                    .threePropCoef(round(player.getThreePropCoef()))
                    .threePropPrior(player.getThreePropPrior())

                    .threePercCoef(round(player.getThreePercCoef()))
                    .threePercPrior(player.getThreePercPrior())

                    .twoPercCoef(round(player.getTwoPercCoef()))
                    .twoPercPrior(player.getTwoPercPrior())

                    .ftsAttemptedCoef(round(player.getFtsAttemptedCoef()))
                    .ftsAttemptedPrior(player.getFtsAttemptedPrior())

                    .ftPercCoef(round(player.getFtPercCoef()))
                    .ftPercPrior(player.getFtPercPrior())

                    .reboundsCoef(round(player.getReboundsCoef()))
                    .reboundsPrior(player.getReboundsPrior())

                    .offReboundsPropCoef(round(player.getOffReboundsPropCoef()))
                    .offReboundsPropPrior(player.getOffReboundsPropPrior())

                    .blocksCoef(round(player.getBlocksCoef()))
                    .blocksPrior(player.getBlocksPrior())

                    .stealsCoef(round(player.getStealsCoef()))
                    .stealsPrior(player.getStealsPrior())

                    .turnoversCoef(round(player.getTurnoversCoef()))
                    .turnoversPrior(player.getTurnoversPrior())

                    .foulsCoef(round(player.getFoulsCoef()))
                    .foulsPrior(player.getFoulsPrior())
                    .averageMinutesInSeason(player.getAverageMinutesInSeason())
                    .build();

            list.add(playerRequest);

        }
        return list.stream().sorted((o1, o2) -> Double.compare(o2.getPmin(), o1.getPmin())).collect(Collectors.toList());
    }

    private static double round(double number){
        return Math.round(number * 1000) / 1000d;
    }

}
