package nba.bayesianmodel.common;

import nba.bayesianmodel.priors.PriorsInterface;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseAbstractPriors implements PriorsInterface {
    @Override
    public Map<Integer, Double> getPlayersPriors(PlayersData allPlayers) {
        Map<Integer, Map<PlayerPosition, Double>> priorBySeasonAndPosition = getPriorBySeasonAndPosition(allPlayers);
        return getPriorsForPlayers(allPlayers, priorBySeasonAndPosition);
    }

    private Map<Integer, Map<PlayerPosition, Double>> getPriorBySeasonAndPosition(PlayersData allPlayers) {
        Map<Integer, Integer> newPlayersPerSeason = getNewPlayersPerSeason(allPlayers);

        Set<Integer> seasons = new HashSet<>(newPlayersPerSeason.values());

        Map<Integer, Map<PlayerPosition, Double>> averagesByYearAndPosition = new HashMap<>();
        for (int seasonYear : seasons) {
            List<Integer> playersForSeason = newPlayersPerSeason.keySet().stream().filter(p -> newPlayersPerSeason.get(p) == seasonYear).collect(Collectors.toList());

            Map<PlayerPosition, List<PlayerGameData>> dataForNewPlayersOnSeason = initializeMap();
            for (Integer id : playersForSeason) {
                List<PlayerGameData> playerGameDataForPlayer = allPlayers.getPlayerGameDataForPlayer(id);
                dataForNewPlayersOnSeason.get(playerGameDataForPlayer.get(0).getPosition()).addAll(playerGameDataForPlayer.stream().filter(p -> p.getSeasonYear() == seasonYear).collect(Collectors.toList()));
            }
            Map<PlayerPosition, Double> averageByPosition = new HashMap<>();
            for (PlayerPosition playerPosition : PlayerPosition.values()) {
                List<PlayerGameData> playerGameData = dataForNewPlayersOnSeason.get(playerPosition);
                double priorForPositionAndSeason = getPriorForPositionAndSeason(playerGameData);
                averageByPosition.put(playerPosition, priorForPositionAndSeason);
            }
            averagesByYearAndPosition.put(seasonYear, averageByPosition);
        }

        return averagesByYearAndPosition;
    }

    private Map<Integer, Double> getPriorsForPlayers(PlayersData playersData, Map<Integer, Map<PlayerPosition, Double>> priorsBySeasonAndPosition) {
        Map<Integer, Double> priorsForPlayers = new HashMap<>();
        Set<Integer> playerIds = playersData.getPlayersData().keySet();
        for (Integer playerId : playerIds) {
            int playerFirstSeason = playersData.getPlayerGameDataForPlayer(playerId).stream().mapToInt(PlayerGameData::getSeasonYear).min().getAsInt();
            PlayerPosition playerPosition = playersData.getPlayerGameDataForPlayer(playerId).get(0).getPosition();
            priorsForPlayers.put(playerId, priorsBySeasonAndPosition.get(Math.max(2017, playerFirstSeason - 1)).get(playerPosition));
        }

        return priorsForPlayers;
    }

    protected abstract double getPriorForPositionAndSeason(List<PlayerGameData> playerGameDataForSeasonAndPosition);

    private static Map<PlayerPosition, List<PlayerGameData>> initializeMap() {
        Map<PlayerPosition, List<PlayerGameData>> map = new HashMap<>();
        for (PlayerPosition position : PlayerPosition.values()) {
            map.put(position, new ArrayList<>());
        }
        return map;
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
}
