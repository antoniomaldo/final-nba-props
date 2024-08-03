package nba.minutedistribution;

import nba.BaseDirectory;
import nba.bayesianmodel.backtest.BacktestPlayerWithCoefs;
import nba.bayesianmodel.common.CsvUtils;
import nba.dto.PlayerRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class NormalizedGivenPlayedPredictionsTest {


    @Test
    public void name() {
        List<BacktestPlayerWithCoefs> playerWithCoefs = CsvUtils.loadPredictionsDataWithProjMinutes(BaseDirectory.baseDirectoryToUse().getBaseDir() + "backtest_analysis\\backtestInputs.csv");

        int finalGameId = 401360602;

        List<BacktestPlayerWithCoefs> gamePlayers = playerWithCoefs.stream().filter(p -> p.getGameId() == finalGameId).collect(Collectors.toList());

        List<BacktestPlayerWithCoefs> homePlayers = gamePlayers.stream().filter(p -> p.getTeam().equalsIgnoreCase(p.getHomeTeam())).collect(Collectors.toList());


        List<PlayerRequest> normalizedPredictions = NormalizedGivenPlayedPredictions.addNormalizedPredictions(toPlayerRequest(homePlayers, true), 113, 116);

        int x = 1;

        for(PlayerRequest playerRequest : normalizedPredictions){
            System.out.println(playerRequest.getName() + " -> " + playerRequest.getTeamAdjustedMinAvg());
        }

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