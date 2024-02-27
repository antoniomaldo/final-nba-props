package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.BaseAbstractPriors;
import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;
import nba.bayesianmodel.common.PlayersData;

import java.util.*;
import java.util.stream.Collectors;

public class ThreePropPrior extends BaseAbstractPriors {
    public Map<Integer, Double> getPlayersPriors(PlayersData allPlayers) {
        Map<Integer, Map<PlayerPosition, Double>> priorBySeasonAndPosition = getPriorBySeasonAndPosition(allPlayers);
        return getPriorsForPlayers(allPlayers, priorBySeasonAndPosition);
    }

    private Map<Integer, Double> getPriorsForPlayers(PlayersData playersData, Map<Integer, Map<PlayerPosition, Double>> priorsBySeasonAndPosition) {
        Map<Integer, Double> priorsForPlayers = new HashMap<>();
        Set<Integer> playerIds = playersData.getPlayersData().keySet();
        for (Integer playerId : playerIds) {
            int playerFirstSeason = playersData.getPlayerGameDataForPlayer(playerId).stream().mapToInt(PlayerGameData::getSeasonYear).min().getAsInt();
            PlayerPosition playerPosition = playersData.getPlayerGameDataForPlayer(playerId).get(0).getPosition();
            priorsForPlayers.put(playerId, priorsBySeasonAndPosition.get(Math.max(2018, playerFirstSeason - 1)).get(playerPosition));
        }

        return priorsForPlayers;
    }


    private Map<Integer, Map<PlayerPosition, Double>> getPriorBySeasonAndPosition(PlayersData allPlayers) {
        List<PlayerGameData> allYearsData = getAllYearsData(allPlayers);
        Map<Integer, Integer> newPlayersPerSeason = getNewPlayersPerSeason(allPlayers);

        int minSeason = newPlayersPerSeason.values().stream().mapToInt(o -> o).min().getAsInt();
        int maxSeason = newPlayersPerSeason.values().stream().mapToInt(o -> o).max().getAsInt();

        Map<Integer, Map<PlayerPosition, Double>> averagesByYearAndPosition = new HashMap<>();
        for (int seasonYear = minSeason + 1; seasonYear <= maxSeason; seasonYear++) {
            int finalSeasonYear = seasonYear;

            List<PlayerGameData> lastThreeSeasonsData = allYearsData.stream().
                    filter(p -> p.getSeasonYear() < finalSeasonYear && (finalSeasonYear - p.getSeasonYear() <= 3)).
                    collect(Collectors.toList());

            Map<PlayerPosition, Double> priorsForPosition = calculatePriorForPosition(lastThreeSeasonsData);


            Map<PlayerPosition, Double> averageByPosition = new HashMap<>();
            for (PlayerPosition playerPosition : PlayerPosition.values()) {
                averageByPosition.put(playerPosition, priorsForPosition.get(playerPosition));
            }
            averagesByYearAndPosition.put(seasonYear, averageByPosition);
        }

        return averagesByYearAndPosition;
    }

    private Map<PlayerPosition, Double> calculatePriorForPosition(List<PlayerGameData> lastThreeSeasonsData) {
        Map<PlayerPosition, Double> totalsByPosition = new HashMap<>();

        for (PlayerPosition playerPosition : PlayerPosition.values()) {
            int positionTotalThreeAttempted = lastThreeSeasonsData.stream().
                    filter(p -> p.getPosition() == playerPosition).
                    mapToInt(PlayerGameData::getThreeAttempted).
                    sum();

            int positionTotalFgAttempted = lastThreeSeasonsData.stream().
                    filter(p -> p.getPosition() == playerPosition).
                    mapToInt(PlayerGameData::getFgAttempted).
                    sum();


            totalsByPosition.put(playerPosition, (double) positionTotalThreeAttempted / positionTotalFgAttempted);
        }

        return totalsByPosition;
    }

    private List<PlayerGameData> getAllYearsData(PlayersData allPlayers) {
        Set<Integer> playerIds = allPlayers.getPlayersData().keySet();
        List<PlayerGameData> allYearsData = new ArrayList<>();
        for (Integer playerId : playerIds) {
            List<PlayerGameData> playerGameDataForPlayer = allPlayers.getPlayerGameDataForPlayer(playerId);
            allYearsData.addAll(playerGameDataForPlayer);
        }

        return allYearsData;
    }

    private static Map<Integer, Integer> getNewPlayersPerSeason(PlayersData allPlayers) {
        Set<Integer> playerIds = allPlayers.getPlayersData().keySet();
        //Get first season per players
        Map<Integer, Integer> playerFirstSeason = new HashMap<>();

        for (Integer playerId : playerIds) {
            List<PlayerGameData> playerGameDataForPlayer = allPlayers.getPlayerGameDataForPlayer(playerId);
            int firstSeason = playerGameDataForPlayer.stream().
                    mapToInt(PlayerGameData::getSeasonYear).
                    min().getAsInt();

            playerFirstSeason.put(playerId, firstSeason);
        }

        return playerFirstSeason;
    }

    @Override
    protected double getPriorForPositionAndSeason(List<PlayerGameData> playerGameDataForSeasonAndPosition) {
        double totalThreeAttempted = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getThreeAttempted).sum();
        double totalThreeMade = playerGameDataForSeasonAndPosition.stream().mapToDouble(PlayerGameData::getThreeMade).sum();

        return totalThreeMade / totalThreeAttempted;
    }

    private static Map<PlayerPosition, List<PlayerGameData>> initializeMap() {
        Map<PlayerPosition, List<PlayerGameData>> map = new HashMap<>();
        for (PlayerPosition position : PlayerPosition.values()) {
            map.put(position, new ArrayList<>());
        }
        return map;
    }
}
