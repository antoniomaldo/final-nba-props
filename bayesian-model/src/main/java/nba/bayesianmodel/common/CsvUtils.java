package nba.bayesianmodel.common;

import com.opencsv.CSVWriter;
import nba.BaseDirectory;
import nba.bayesianmodel.backtest.BacktestPlayerWithCoefs;
import nba.simulator.PlayerWithCoefs;
import nba.simulator.SimulatorPredictions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CsvUtils {

    public static PlayersData loadData(boolean shouldRemoveLastSeason) {
        List<PlayerGameData> playerData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BaseDirectory.baseDirectoryToUse().getBaseDir() + "data\\allPlayers.csv"))) {
            String line;
            String[] colNames = br.readLine().split(",");
            Map<String, Integer> colNamesIndex = createColNamesMap(colNames);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int minPlayed = getAsInteger(values, colNamesIndex, "Min");
                if(minPlayed > 0) {
                    playerData.add(new PlayerGameData(
                            getAsInteger(values, colNamesIndex, "Date"),
                            getAsInteger(values, colNamesIndex, "GameId"),
                            getAsInteger(values, colNamesIndex, "PlayerId"),
                            values[colNamesIndex.get("Team")].replace("\"", ""),
                            PlayerPosition.mapToPlayerPosition(values[colNamesIndex.get("Position")].replace("\"", "")),
                            getAsInteger(values, colNamesIndex, "Starter") == 1,
                            minPlayed,
                            getAsInteger(values, colNamesIndex, "Fg.Made"),
                            getAsInteger(values, colNamesIndex, "Fg.Attempted"),
                            getAsInteger(values, colNamesIndex, "Three.Made"),
                            getAsInteger(values, colNamesIndex, "Three.Attempted"),
                            getAsInteger(values, colNamesIndex, "FT.Made"),
                            getAsInteger(values, colNamesIndex, "FT.Attempted"),
                            getAsInteger(values, colNamesIndex, "Offensive.Rebound"),
                            getAsInteger(values, colNamesIndex, "Defensive.Rebound"),
                            getAsInteger(values, colNamesIndex, "Assists"),
                            getAsInteger(values, colNamesIndex, "Steals"),
                            getAsInteger(values, colNamesIndex, "Blocks"),
                            getAsInteger(values, colNamesIndex, "Turnovers"),
                            getAsInteger(values, colNamesIndex, "Personal.Fouls"),
                            getAsInteger(values, colNamesIndex, "seasonYear"),
                            getAsDouble(values, colNamesIndex, "averageMinutesInSeason")

                            ));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(shouldRemoveLastSeason){
            playerData = playerData.stream().filter(p->p.getSeasonYear() < 2024).collect(Collectors.toList());
        }
        return getPlayersData(playerData);
    }


    public static List<PlayerWithCoefs> loadPredictionsData() {
        return loadPredictionsData(BaseDirectory.baseDirectoryToUse().getBaseDir() + "testing\\allPreds.csv");

    }

    public static List<PlayerWithCoefs> loadLatestPredictionsData() {
        return loadPredictionsData(BaseDirectory.baseDirectoryToUse().getBaseDir() + "testing\\lastUpdate.csv");

    }

    public static List<BacktestPlayerWithCoefs> loadPredictionsDataWithProjMinutes(String csvDir) {
        List<BacktestPlayerWithCoefs> playerData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvDir))) {
            String line;
            String[] colNames = br.readLine().split(",");
            Map<String, Integer> colNamesIndex = createColNamesMap(colNames);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int minPlayed = getAsInteger(values, colNamesIndex, "minPlayed");
                if(minPlayed >= 0) {
                    playerData.add(new BacktestPlayerWithCoefs(
                            getAsInteger(values, colNamesIndex, "GameId"),
                            getAsInteger(values, colNamesIndex, "seasonYear"),
                            values[colNamesIndex.get("Team")].replace("\"", ""),
                            values[colNamesIndex.get("Name")].replace("\"", ""),
                            getAsInteger(values, colNamesIndex, "PlayerId"),
                            getAsInteger(values, colNamesIndex, "pmin"),
                            getAsDouble(values, colNamesIndex, "fgPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "fgPrior"),
                            getAsDouble(values, colNamesIndex, "threePropPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "threePropPrior"),
                            getAsDouble(values, colNamesIndex, "threePercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "threePercPrior"),
                            getAsDouble(values, colNamesIndex, "twoPercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "twoPercPrior"),
                            getAsDouble(values, colNamesIndex, "ftsAttemptedPerMinPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ftsAttemptedPerMinPrior"),
                            getAsDouble(values, colNamesIndex, "ftsPercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ftsPercPrior"),
                            getAsDouble(values, colNamesIndex, "reboundsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "reboundsPrior"),
                            getAsDouble(values, colNamesIndex, "ofReboundsPropPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ofReboundsPropPrior"),
                            getAsDouble(values, colNamesIndex, "blocksPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "blocksPrior"),
                            getAsDouble(values, colNamesIndex, "stealsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "stealsPrior"),
                            getAsDouble(values, colNamesIndex, "turnoversPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "turnoversPrior"),

                            getAsDouble(values, colNamesIndex, "personalFoulsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "personalFoulsPrior"),
                            getAsDouble(values, colNamesIndex, "averageMinutesInSeason"),
                            getAsDouble(values, colNamesIndex, "lastGameMin"),
                            getAsInteger(values, colNamesIndex, "pmin"),
                            getAsDouble(values, colNamesIndex, "ownExp"),
                            getAsDouble(values, colNamesIndex, "oppExp"),
                            values[colNamesIndex.get("HomeTeam")].replace("\"", ""),
                            getAsInteger(values, colNamesIndex, "Starter")

                            ));//getAsDouble(values, colNamesIndex, "oppExp")));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return playerData;
    }

    public static List<PlayerWithCoefs> loadPredictionsData(String csvDir) {
        List<PlayerWithCoefs> playerData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvDir))) {
            String line;
            String[] colNames = br.readLine().split(",");
            Map<String, Integer> colNamesIndex = createColNamesMap(colNames);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int minPlayed = getAsInteger(values, colNamesIndex, "minPlayed");
                if(minPlayed > 0) {

                    playerData.add(new PlayerWithCoefs(
                            getAsInteger(values, colNamesIndex, "GameId"),
                            getAsInteger(values, colNamesIndex, "seasonYear"),
                            values[colNamesIndex.get("Team")].replace("\"", ""),
                            values[colNamesIndex.get("Name")].replace("\"", ""),
                            getAsInteger(values, colNamesIndex, "PlayerId"),
                            minPlayed,
                            getAsDouble(values, colNamesIndex, "fgPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "fgPrior"),
                            getAsDouble(values, colNamesIndex, "threePropPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "threePropPrior"),
                            getAsDouble(values, colNamesIndex, "threePercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "threePercPrior"),
                            getAsDouble(values, colNamesIndex, "twoPercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "twoPercPrior"),
                            getAsDouble(values, colNamesIndex, "ftsAttemptedPerMinPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ftsAttemptedPerMinPrior"),
                            getAsDouble(values, colNamesIndex, "ftsPercPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ftsPercPrior"),
                            getAsDouble(values, colNamesIndex, "reboundsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "reboundsPrior"),
                            getAsDouble(values, colNamesIndex, "ofReboundsPropPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "ofReboundsPropPrior"),
                            getAsDouble(values, colNamesIndex, "blocksPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "blocksPrior"),
                            getAsDouble(values, colNamesIndex, "stealsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "stealsPrior"),
                            getAsDouble(values, colNamesIndex, "turnoversPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "turnoversPrior"),

                            getAsDouble(values, colNamesIndex, "personalFoulsPlayerCoef"),
                            getAsDouble(values, colNamesIndex, "personalFoulsPrior"),
                            getAsDouble(values, colNamesIndex, "averageMinutesInSeason"),
                            getAsDouble(values, colNamesIndex, "lastGameMin")));//getAsDouble(values, colNamesIndex, "oppExp")));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return playerData;
    }

    private static int getAsInteger(String[] values, Map<String, Integer> colNamesIndex, String colName) {
        return Integer.parseInt(values[colNamesIndex.get(colName)].replace("\"", ""));
    }

    private static double getAsDouble(String[] values, Map<String, Integer> colNamesIndex, String colName) {
        return Double.parseDouble(values[colNamesIndex.get(colName)].replace("\"", ""));
    }


    private static Map<String, Integer> createColNamesMap(String[] colNames) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < colNames.length; i++) {
            map.put(colNames[i].replace("\"",""), i);
        }
        return map;
    }

    public static void savePredictions(List<PlayerPredictions> playersPredictions, String csvName) {
        List<String[]> csvData = new ArrayList<>();
        csvData.add(new String[]{"seasonYear", "GameId", "PlayerId", "numbOfGames", "gamesPlayedInSeason", "minPlayed", "playerCoef", "targetPredicted", "target", "threeAttempted", "fgAttempted", "threeMade", "fgMade", "priorForPlayer", "averageMinutesInSeason"});
        for (PlayerPredictions playerPredictions : playersPredictions) {
            csvData.add(playerPredictions.toRow());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(BaseDirectory.baseDirectoryToUse().getBaseDir() + "testing\\" + csvName + ".csv"))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveMinutesPredictions(List<String[]> simulatorPredictions) {
        List<String[]> csvData = new ArrayList<>();
        csvData.add(new String[]{"GameId", "PlayerName", "MinutesPredicted", "PlayerId", "zeroPred", "probTenToTwenty", "probTwentyOneThirty", "probThirtyOneForty", "probOverForty"});

        for (String[] playerPredictions : simulatorPredictions) {
            csvData.add(playerPredictions);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(BaseDirectory.baseDirectoryToUse().getBaseDir() + "backtest_analysis\\minutesPrediction.csv"))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public static void savePredictions(List<SimulatorPredictions> simulatorPredictions) {
        List<String[]> csvData = new ArrayList<>();
        csvData.add(new String[]{"GameId", "PlayerId", "fgAttemptedPred", "twosAvg", "threesAvg", "ftsAvg", "rebounds", "steals", "blocks", "averageMinutesInSeason",
                "zeroProb",
                "minOneToFive",
                "minSixToTen",
                "minElevenToFifteen",
                "minSixteenToTwenty",
                "minTwentyOneToTwentyFive",
                "minTwentySixToThirty",
                "minThirtyOneToThirtyFive",
                "minThirtySixToForty",
                "minOverForty",
                "averageMinutes"
        });
        for (SimulatorPredictions playerPredictions : simulatorPredictions) {
            csvData.add(playerPredictions.toRow());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(BaseDirectory.baseDirectoryToUse().getBaseDir() + "backtest_analysis\\backtestRaw.csv"))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static PlayersData getPlayersData(List<PlayerGameData> playerData) {
        Set<Integer> playerIds = new HashSet<>();
        for (PlayerGameData playerGameData : playerData) {
            playerIds.add(playerGameData.getPlayerId());
        }
        Map<Integer, List<PlayerGameData>> playersDataMap = new HashMap<>();

        for (Integer id : playerIds) {
            playersDataMap.put(id, playerData.stream().filter(p -> p.getPlayerId() == id).collect(Collectors.toList()));
        }
        return new PlayersData(playersDataMap);
    }
}
