package nba.minutedistribution;

import nba.BaseDirectory;
import nba.bayesianmodel.backtest.BacktestPlayerWithCoefs;
import nba.bayesianmodel.common.CsvUtils;
import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nba.minutedistribution.MinutesExpectedModel.buildMinutesExpectedMap;
import static nba.minutedistribution.MinutesExpectedModel.getMinutesDistributionForPrediction;

public class MinutesDistributionTestApplication {

    public static void main(String[] args) {
        List<BacktestPlayerWithCoefs> playerWithCoefs = CsvUtils.loadPredictionsDataWithProjMinutesOnly(BaseDirectory.baseDirectoryToUse().getBaseDir() + "backtest_analysis\\backtestInputsMinutes.csv");

        List<Integer> gameIds = playerWithCoefs.stream().map(p -> p.getGameId()).distinct().collect(Collectors.toList());

        List<String[]> list = new ArrayList<>();
        int counter = 0;
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

            Pair<Map<Integer, Double>, Map<Integer, Double>> mapMapPair = buildMinutesExpectedMap(nbaGameEventDto);

            Map<Integer, Double> minutesExpected =  mapMapPair.getLeft();
            Map<Integer, Double> zeroMinProb = mapMapPair.getRight();


            for(PlayerRequest player : nbaGameEventDto.getAwayPlayers()) {
                addToList(list, gameId, minutesExpected, zeroMinProb, player);
            }

            for(PlayerRequest player : nbaGameEventDto.getHomePlayers()) {
                addToList(list, gameId, minutesExpected, zeroMinProb, player);
            }

            counter++;
            if (counter % 100 == 0) {
                CsvUtils.saveMinutesPredictions(list);
            }
        }
    }

    private static void addToList(List<String[]> list, Integer gameId, Map<Integer, Double> minutesExpected, Map<Integer, Double> zeroMinProb, PlayerRequest player) {
        double adjMinutes = minutesExpected.get(player.getPlayerId());
        double minutesPredicted = minutesExpected.get(player.getPlayerId());
//        minutesPredicted = minutesPredicted < 4 ? 4 : minutesPredicted;
//        minutesPredicted = minutesPredicted > 36 ? 36 : minutesPredicted;

        double[] minutesDistributionForPrediction = MinutesExpectedModel.getMinutesDistributionForPlayer((int) Math.round(minutesPredicted));

        double probTenToTwenty = getDistributionProb(minutesDistributionForPrediction, 10, 20);
        double probTwentyOneThirty = getDistributionProb(minutesDistributionForPrediction, 21, 30);
        double probThirtyOneForty = getDistributionProb(minutesDistributionForPrediction, 31, 40);
        double probOverForty = getDistributionProb(minutesDistributionForPrediction, 41, 48);
        list.add(new String[] {Integer.toString(gameId),
                player.getName(),
                Double.toString(adjMinutes),
                Integer.toString(player.getPlayerId()),
                Double.toString(zeroMinProb.get(player.getPlayerId())),
              Double.toString(probTenToTwenty),
              Double.toString(probTwentyOneThirty),
              Double.toString(probThirtyOneForty),
              Double.toString(probOverForty)

        });
    }

    private static double getDistributionProb(double[] minutesDistributionForPrediction, int lower, int upper) {
        double sum = 0;
        for (int i = lower; i <= upper; i++) {
            sum+=minutesDistributionForPrediction[i];
        }
        return sum;
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
