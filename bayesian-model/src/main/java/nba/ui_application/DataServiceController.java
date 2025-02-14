package nba.ui_application;

import nba.bayesianmodel.common.CsvUtils;
import nba.dto.*;
import nba.mappings.SbrToRotowireTeamNameMapping;
import nba.minutedistribution.NormalizedGivenPlayedPredictions;
import nba.sbr.domain.MatchWithOdds;
import nba.sbr.scraper.NbaScraper;
import nba.simulator.PlayerWithCoefs;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;
import static nba.mappings.RotowireNameToEspnPlayerIdMapping.getPlayerId;


@Controller
public class DataServiceController {
    public static String dataLastUpdated = null;

    public static Map<String, List<FullRotowireObject>> ROTOWIRE_PREDICTIONS = new RotowireDataLoader().loadTodaysPredictions();
    public static List<PlayerWithCoefs> LATEST_PLAYER_COEFICIENTS = CsvUtils.loadLatestPredictionsData();
    private static List<MatchWithOdds> TODAYS_EVENTS_WITH_ODDS = scrapeSbrOdds();
    public static final Map<String, NbaGameEventDto> EVENTS_CACHE = new HashMap<>();
    public static String linesLastUpdated = null;
    @ResponseBody
    @GetMapping(value = "/getDayEvents")
    public ResponseEntity<?> getDayEvents() {
        dataLastUpdated = now().toString();

        Map<String, NbaGameEventDto> map = new HashMap<>();

        for(MatchWithOdds matchWithOdds : TODAYS_EVENTS_WITH_ODDS){
            String awayTeamName = matchWithOdds.getMatchWithoutOdds().getAwayTeamName();
            String homeTeamName = matchWithOdds.getMatchWithoutOdds().getHomeTeamName();

            String eventName = awayTeamName + " @ " + homeTeamName;

            List<PlayerRequest> homePlayers = buildPlayers(homeTeamName, true);
            List<PlayerRequest> awayPlayers = buildPlayers(awayTeamName, false);

            double matchTotalPoints = matchWithOdds.getMatchTotal().getLine();
            double matchSpread = matchWithOdds.getMatchSpread().getLine();

            double homeExp = (matchTotalPoints - matchSpread) / 2d;
            double awayExp = matchTotalPoints - homeExp;

            homePlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(homePlayers, homeExp, awayExp);
            awayPlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(awayPlayers, awayExp,homeExp);

            NbaGameEventDto nbaGameEventDto = NbaGameEventDto.builder().
                    eventName(eventName).
                    eventTime(matchWithOdds.getMatchWithoutOdds().getDateTime().toLocalDate().toString()).
                    homeTeamName(homeTeamName).
                    awayTeamName(awayTeamName).
                    totalPoints(matchTotalPoints).
                    matchSpread(matchSpread).
                    homePlayers(homePlayers).
                    awayPlayers(awayPlayers).
                    build();

            map.put(nbaGameEventDto.getEventName(), nbaGameEventDto);

        }

        return new ResponseEntity<>(new Pair<>(dataLastUpdated, map), HttpStatus.OK);
    }

    private List<PlayerRequest> buildPlayers(String sbrTeamName, boolean isHomePlayer) {
        String rotowireTeam = SbrToRotowireTeamNameMapping.mapToRotowire(sbrTeamName);
        List<FullRotowireObject> rotowirePlayers;
        if(rotowireTeam == null){
            System.out.println("Mapping needed for team: " + sbrTeamName);
            rotowirePlayers = new ArrayList<>();

        }else {
            rotowirePlayers = ROTOWIRE_PREDICTIONS.get(rotowireTeam);
            rotowirePlayers = rotowirePlayers == null ? new ArrayList<>() : rotowirePlayers;

        }

        double sumMin = rotowirePlayers.stream().mapToDouble(p->Double.parseDouble(p.getMin())).sum();
        System.out.println(rotowireTeam + " -> " + sumMin);
        List<PlayerRequest> playerRequests = new ArrayList<>();
        for(FullRotowireObject rotowirePlayer : rotowirePlayers) {
            PlayerWithCoefs playerWithCoefs = getPlayerFromRotoName(rotowirePlayer.getPlayer());
            if(playerWithCoefs != null) {
                PlayerRequest playerRequest = PlayerRequest.builder()
                        .team(rotowireTeam)
                        .name(rotowirePlayer.getPlayer())
                        .playerId(playerWithCoefs.getPlayerId())
                        .pmin(Double.parseDouble(rotowirePlayer.getMin()))
                        .position(rotowirePlayer.getPos())
                        .isHomePlayer(isHomePlayer)
                        .averageMinutes(Double.parseDouble(rotowirePlayer.getMin()))
                        .fgAttemptedCoef(round(playerWithCoefs.getFgAttemptedPlayerCoef()))
                        .fgAttemptedPrior(round(playerWithCoefs.getFgAttemptedPrior()))
                        .threePropCoef(round(playerWithCoefs.getThreePropCoef()))
                        .threePropPrior(playerWithCoefs.getThreePropPrior())
                        .threePercCoef(round(playerWithCoefs.getThreePercCoef()))
                        .threePercPrior(playerWithCoefs.getThreePercPrior())
                        .twoPercCoef(round(playerWithCoefs.getTwoPercCoef()))
                        .twoPercPrior(playerWithCoefs.getTwoPercPrior())
                        .ftsAttemptedCoef(round(playerWithCoefs.getFtsAttemptedCoef()))
                        .ftsAttemptedPrior(playerWithCoefs.getFtsAttemptedPrior())
                        .ftPercCoef(round(playerWithCoefs.getFtPercCoef()))
                        .ftPercPrior(playerWithCoefs.getFtPercPrior())
                        .reboundsCoef(round(playerWithCoefs.getReboundsCoef()))
                        .reboundsPrior(playerWithCoefs.getReboundsPrior())
                        .offReboundsPropCoef(round(playerWithCoefs.getOffReboundsPropCoef()))
                        .offReboundsPropPrior(playerWithCoefs.getOffReboundsPropPrior())
                        .blocksCoef(round(playerWithCoefs.getBlocksCoef()))
                        .blocksPrior(playerWithCoefs.getBlocksPrior())
                        .stealsCoef(round(playerWithCoefs.getStealsCoef()))
                        .stealsPrior(playerWithCoefs.getStealsPrior())
                        .turnoversCoef(round(playerWithCoefs.getTurnoversCoef()))
                        .turnoversPrior(playerWithCoefs.getTurnoversPrior())
                        .foulsCoef(round(playerWithCoefs.getFoulsCoef()))
                        .foulsPrior(playerWithCoefs.getFoulsPrior())
                        .assistsPrior(playerWithCoefs.getAssistsPrior())
                        .assistsCoef(playerWithCoefs.getAssistsCoef())
                        .averageMinutesInSeason(playerWithCoefs.getAverageMinutesInSeason())
                        .lastGameMin(playerWithCoefs.getLastGameMin())
                        .build();
                playerRequests.add(playerRequest);
            }else{
                System.out.println("No mappings found for " + rotowirePlayer.getPlayer());
            }
        }

        List<PlayerRequest> orderedPlayers = playerRequests.stream().sorted((o1, o2) -> Double.compare(o2.getPmin(), o1.getPmin())).collect(Collectors.toList());
        List<PlayerRequest> list = new ArrayList<>();
        for (int i = 0; i < orderedPlayers.size(); i++) {
            if(i < 5){
                list.add(orderedPlayers.get(i).toBuilder().starter(1).build());
            }else{
                list.add(orderedPlayers.get(i));
            }
        }
        return list;
    }

    private PlayerWithCoefs getPlayerFromRotoName(String rotowireName) {
        Integer playerId = getPlayerId(rotowireName);
        if(playerId == null){
            return null;
        }
        Optional<PlayerWithCoefs> player = LATEST_PLAYER_COEFICIENTS.stream().filter(p -> p.getPlayerId() == playerId).findFirst();
        return player.orElse(null);
    }

//    @ResponseBody
//    @GetMapping(value = "/scrapeLines")
//    public ResponseEntity<?> scrapeLines() {
//        List<MatchWithOdds> matchWithOdds = NbaScraper.scrapeTodaysMatchesForBet365();
//        if (matchWithOdds.size() == 0) {
//            matchWithOdds = NbaScraper.scrapeTodaysMatchesForPinnacle();
//        }
//
//        matchWithOdds.sort(Comparator.comparing(MatchWithOdds::getDateTime));
//
//        for (MatchWithOdds match : matchWithOdds) {
//            String awayTeamName = match.getMatchWithoutOdds().getAwayTeamName();
//            String homeTeamName = match.getMatchWithoutOdds().getHomeTeamName();
//
//            String eventName = MAPPINGS.get(awayTeamName) + " @ " + MAPPINGS.get(homeTeamName);
//            for (String event : EVENTS_CACHE.keySet()) {
//                if (event.equalsIgnoreCase(eventName)) {
//                    NbaGameEventDto nbaGameEventDto = EVENTS_CACHE.get(event);
//                    Double totalMatch = match.getMatchTotal() != null ? match.getMatchTotal().getLine() : null;
//                    Double totalSpread = match.getMatchSpread() != null ? match.getMatchSpread().getLine() : null;
//                    EVENTS_CACHE.put(event, nbaGameEventDto.toBuilder().eventTime(match.getDateTime().toLocalDateTime().toString()).matchSpread(totalSpread).totalPoints(totalMatch).build());
//                }
//            }
//        }
//
//        linesLastUpdated = new DateTime().toLocalDateTime().toString();
//
//        return new ResponseEntity<>(new Pair<>(EVENTS_CACHE, linesLastUpdated), HttpStatus.OK);
//    }
//
//

    public static List<MatchWithOdds> scrapeSbrOdds(){
        List<Map<String, MatchWithOdds>> matchWithOddsMap = NbaScraper.scrapeTodaysMatchesForBet365();
        List<MatchWithOdds> matchWithOdds = new ArrayList<>();

        for(Map<String, MatchWithOdds> gameOdds : matchWithOddsMap){
            if(gameOdds.get("bet365") != null){
                matchWithOdds.add(gameOdds.get("bet365"));
            }else if(gameOdds.get("williamhill") != null){
                matchWithOdds.add(gameOdds.get("williamhill"));
            }else if(gameOdds.get("888sport") != null){
                matchWithOdds.add(gameOdds.get("888sport"));
            }else if(gameOdds.get("betway") != null){
                matchWithOdds.add(gameOdds.get("betway"));
            }
        }

        matchWithOdds.sort(Comparator.comparing(MatchWithOdds::getDateTime));
        return matchWithOdds;
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
                    .playerId(player.getPlayerId())
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
