package nba.bayesianmodel.backtest;

import hex.genmodel.easy.exception.PredictException;
import nba.BaseDirectory;
import nba.bayesianmodel.common.CsvUtils;
import nba.bayesianmodel.optimizers.targets.TargetPredicted;
import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import nba.minutedistribution.NormalizedGivenPlayedPredictions;
import nba.minutedistribution.ZeroMinutesModel;
import nba.simulator.*;
import nba.ui_application.ApplicationController;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BacktestApplication {

    private static final PlayerSimulator PLAYER_SIMULATOR = new PlayerSimulator();
    public static final ApplicationController APPLICATION_CONTROLLER = new ApplicationController();

    public static void main(String[] args)  {
        List<BacktestPlayerWithCoefs> playerWithCoefs = CsvUtils.loadPredictionsDataWithProjMinutes(BaseDirectory.baseDirectoryToUse().getBaseDir() + "backtest_analysis\\backtestInputs.csv");

        //playerWithCoefs = playerWithCoefs.stream().filter(p -> p.getSeasonYear() == 2024).collect(Collectors.toList());

        List<Integer> gameIds = playerWithCoefs.stream().map(p -> p.getGameId()).distinct().collect(Collectors.toList());

        List<SimulatorPredictions> list = new ArrayList<>();
        int counter = 1;

        for (Integer gameId : gameIds) {
            System.out.println(gameId);
            Integer finalGameId = gameId;
            List<BacktestPlayerWithCoefs> gamePlayers = playerWithCoefs.stream().filter(p -> p.getGameId() == finalGameId).collect(Collectors.toList());

            List<BacktestPlayerWithCoefs> homePlayers = gamePlayers.stream().filter(p -> p.getTeam().equalsIgnoreCase(p.getHomeTeam())).collect(Collectors.toList());
            List<BacktestPlayerWithCoefs> awayPlayers = gamePlayers.stream().filter(p -> !p.getTeam().equalsIgnoreCase(p.getHomeTeam())).collect(Collectors.toList());

            double homeExp;
            double awayExp;

            if (homePlayers.size() > 0) {
                homeExp = homePlayers.get(0).getOwnExp();
                awayExp = homePlayers.get(0).getOppExp();
            } else {
                homeExp = awayPlayers.get(0).getOppExp();
                awayExp = awayPlayers.get(0).getOwnExp();
            }
            double matchTotalPoints = homeExp + awayExp;
            double matchSpread = awayExp - homeExp;

            NbaGameEventDto nbaGameEventDto = NbaGameEventDto.builder().
                    eventName("").
                    eventTime("").
                    homeTeamName("").
                    awayTeamName("").
                    homePlayers(toPlayerRequest(homePlayers, true)).
                    awayPlayers(toPlayerRequest(awayPlayers, false)).
                    totalPoints(matchTotalPoints).
                    matchSpread(matchSpread).
                    build();


            ResponseEntity<?> modelResponse = APPLICATION_CONTROLLER.getGamePred(nbaGameEventDto);

            Map<String, Map<Integer, Map<Integer, Double>>> modelOutputMap  = (Map<String, Map<Integer, Map<Integer, Double>>>) modelResponse.getBody();

            for(BacktestPlayerWithCoefs player : gamePlayers){
                Double expThrees = modelOutputMap.get("playerThreePoints").get(player.getPlayerId()).get(-1);
                Double expFgPred = modelOutputMap.get("fgAttempted").get(player.getPlayerId()).get(-1);
                Double expTwos = modelOutputMap.get("playerTwoPoints").get(player.getPlayerId()).get(-1);
                Double expFts = modelOutputMap.get("playerFts").get(player.getPlayerId()).get(-1);
                Map<Integer, Double> playerMins = modelOutputMap.get("mins").get(player.getPlayerId());

                double zeroProb = ZeroMinutesModel.zeroMinutesProb(player.getPmin(), player.getStarter(), 0);

                list.add(new SimulatorPredictions(gameId, player.getPlayerId(),
                        expFgPred,
                        expTwos,
                        expThrees,
                        expFts,
                        0,0,0,0, zeroProb,playerMins));
            }
            counter++;
            if (counter % 100 == 0) {
                CsvUtils.savePredictions(list);
            }
        }
        CsvUtils.savePredictions(list);
    }

    private static List<PlayerRequest> toPlayerRequest(List<BacktestPlayerWithCoefs> homePlayersWithCoefs, boolean isHomePlayer) {
        List<PlayerRequest> list = new ArrayList<>();
        for(BacktestPlayerWithCoefs playerWithCoefs : homePlayersWithCoefs) {
            PlayerRequest playerRequest = PlayerRequest.builder()
                    .team("")
                    .name(playerWithCoefs.getPlayerName())
                    .pmin(playerWithCoefs.getPmin())
                    .playerId(playerWithCoefs.getPlayerId())
                    .position("")
                    .starter(playerWithCoefs.getStarter())
                    .isHomePlayer(isHomePlayer)
                    .averageMinutes(playerWithCoefs.getAverageMinutesInSeason())
                    .fgAttemptedCoef((playerWithCoefs.getFgAttemptedPlayerCoef()))
                    .fgAttemptedPrior((playerWithCoefs.getFgAttemptedPrior()))
                    .threePropCoef((playerWithCoefs.getThreePropCoef()))
                    .threePropPrior(playerWithCoefs.getThreePropPrior())
                    .threePercCoef((playerWithCoefs.getThreePercCoef()))
                    .threePercPrior(playerWithCoefs.getThreePercPrior())
                    .twoPercCoef((playerWithCoefs.getTwoPercCoef()))
                    .twoPercPrior(playerWithCoefs.getTwoPercPrior())
                    .ftsAttemptedCoef((playerWithCoefs.getFtsAttemptedCoef()))
                    .ftsAttemptedPrior(playerWithCoefs.getFtsAttemptedPrior())
                    .ftPercCoef((playerWithCoefs.getFtPercCoef()))
                    .ftPercPrior(playerWithCoefs.getFtPercPrior())
                    .reboundsCoef((playerWithCoefs.getReboundsCoef()))
                    .reboundsPrior(playerWithCoefs.getReboundsPrior())
                    .offReboundsPropCoef((playerWithCoefs.getOffReboundsPropCoef()))
                    .offReboundsPropPrior(playerWithCoefs.getOffReboundsPropPrior())
                    .blocksCoef((playerWithCoefs.getBlocksCoef()))
                    .blocksPrior(playerWithCoefs.getBlocksPrior())
                    .stealsCoef((playerWithCoefs.getStealsCoef()))
                    .stealsPrior(playerWithCoefs.getStealsPrior())
                    .turnoversCoef((playerWithCoefs.getTurnoversCoef()))
                    .turnoversPrior(playerWithCoefs.getTurnoversPrior())
                    .foulsCoef((playerWithCoefs.getFoulsCoef()))
                    .foulsPrior(playerWithCoefs.getFoulsPrior())
                    .averageMinutesInSeason(playerWithCoefs.getAverageMinutesInSeason())
                    .lastGameMin(playerWithCoefs.getLastGameMin())
                    .build();

            list.add(playerRequest);
        }
        return list;
    }
}
