package nba.bayesianmodel.priors;


import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;
import nba.bayesianmodel.common.PlayersData;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseNewPlayersAveragePrior implements PriorsInterface {

    @Override
    public Map<Integer, Double> getPlayersPriors(PlayersData allPlayers) {
        Map<Integer, Map<PlayerPosition, Double>> priorForNewPlayers = getPriorForNewPlayers(allPlayers);
        return getPriorsForPlayers(allPlayers, priorForNewPlayers);
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

    private Map<Integer, Map<PlayerPosition, Double>> getPriorForNewPlayers(PlayersData playersData) {
        Map<Integer, Integer> playerFirstSeason = new HashMap<>();

        Set<Integer> playerIds = playersData.getPlayersData().keySet();
        for (Integer playerId : playerIds) {
            List<PlayerGameData> playerGameDataForPlayer = playersData.getPlayerGameDataForPlayer(playerId);
            int firstSeason = playerGameDataForPlayer.stream().mapToInt(PlayerGameData::getSeasonYear).min().getAsInt();
            playerFirstSeason.put(playerId, firstSeason);
        }

        List<PlayerGameData> newPlayersData = new ArrayList<>();
        List<PlayerGameData> noNewPlayersData = new ArrayList<>();
        for (int playerId : playerFirstSeason.keySet()) {
            List<PlayerGameData> playerGameDataForPlayer = playersData.getPlayerGameDataForPlayer(playerId);
            Integer firstSeason = playerFirstSeason.get(playerId);
            newPlayersData.addAll(playerGameDataForPlayer.stream().filter(p -> p.getSeasonYear() == firstSeason && p.getSeasonYear() > 2017).collect(Collectors.toList()));
            noNewPlayersData.addAll(playerGameDataForPlayer.stream().filter(p -> p.getSeasonYear() > firstSeason).collect(Collectors.toList()));
        }

        Map<Integer, Map<PlayerPosition, Double>> seasonAveragesForNoNewPlayers = getAveragesForPositionAndSeason(noNewPlayersData);
        Map<PlayerPosition, Double> reductionForNewPlayers = calculateReductionForNewPlayers(newPlayersData, noNewPlayersData);

        Map<Integer, Map<PlayerPosition, Double>> priorForNewPlayersForSeason = new HashMap<>();

        for (Integer season : seasonAveragesForNoNewPlayers.keySet()) {
            Map<PlayerPosition, Double> averagesForSeason = seasonAveragesForNoNewPlayers.get(season);
            Map<PlayerPosition, Double> newPlayersMap = new HashMap<>();
            for (PlayerPosition playerPosition : PlayerPosition.values()) {
                newPlayersMap.put(playerPosition, averagesForSeason.get(playerPosition) * reductionForNewPlayers.get(playerPosition));
            }
            priorForNewPlayersForSeason.put(season + 1, newPlayersMap);
        }

        priorForNewPlayersForSeason.put(2017, seasonAveragesForNoNewPlayers.get(2018));
        priorForNewPlayersForSeason.put(2018, priorForNewPlayersForSeason.get(2019));

        return priorForNewPlayersForSeason;
    }

    private Map<Integer, Map<PlayerPosition, Double>> getAveragesForPositionAndSeason(List<PlayerGameData> noNewPlayersData) {
        int[] seasons = noNewPlayersData.stream().mapToInt(PlayerGameData::getSeasonYear).distinct().toArray();
        Map<Integer, Map<PlayerPosition, Double>> seasonAveragesPerPosition = new HashMap<>();

        for (Integer season : seasons) {
            List<PlayerGameData> seasonNoNewPlayers = noNewPlayersData.stream().filter(p -> p.getSeasonYear() == season).collect(Collectors.toList());
            Map<PlayerPosition, Double> positionMap = new HashMap<>();
            for (PlayerPosition playerPosition : PlayerPosition.values()) {
                double newPlayersValue = getNewPlayersValue(playerPosition, seasonNoNewPlayers);

                positionMap.put(playerPosition, newPlayersValue);
            }
            seasonAveragesPerPosition.put(season, positionMap);
        }
        return seasonAveragesPerPosition;
    }


    private Map<PlayerPosition, Double> calculateReductionForNewPlayers(List<PlayerGameData> newPlayersData, List<PlayerGameData> noNewPlayersData) {

        Map<PlayerPosition, Double> multiplierForNewPlayers = new HashMap<>();
        for (PlayerPosition playerPosition : PlayerPosition.values()) {
            List<PlayerGameData> newPlayersForPosition = newPlayersData.stream().filter(filterByPosition(playerPosition)).collect(Collectors.toList());
            List<PlayerGameData> noNewPlayersForPosition = noNewPlayersData.stream().filter(filterByPosition(playerPosition)).collect(Collectors.toList());


            double newPlayersValue = getNewPlayersValue(playerPosition, newPlayersForPosition);
            double noNewPlayersValue = getNewPlayersValue(playerPosition, noNewPlayersForPosition);

            multiplierForNewPlayers.put(playerPosition, newPlayersValue / noNewPlayersValue);
        }

        return multiplierForNewPlayers;
    }

    protected abstract double getNewPlayersValue(PlayerPosition playerPosition, List<PlayerGameData> seasonNoNewPlayers);

    private static Predicate<PlayerGameData> filterByPosition(PlayerPosition playerPosition) {
        return p -> p.getPosition() == playerPosition && p.getSeasonYear() < 2021;
    }

}
