package nba.minutedistribution;

import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MinutesExpectedModel {
    private final static Map<Integer, double[]> MINUTES_DISTRIBUTION = getMinDistributionMap();

    public static double[] getMinutesDistributionForPrediction(int minutesPredicted){
        return MINUTES_DISTRIBUTION.get(minutesPredicted);
    }


    public static Pair<Map<Integer, Double>, Map<Integer, Double>> buildMinutesExpectedMap(NbaGameEventDto gameRequest) {
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Double> zeroPredMap = new HashMap<>();

        double matchTotalPoints = gameRequest.getTotalPoints();
        double matchSpread = gameRequest.getMatchSpread();

        double homeExp = (matchTotalPoints - matchSpread) / 2d;
        double awayExp = matchTotalPoints - homeExp;

        List<PlayerRequest> homePlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(gameRequest.getHomePlayers(), homeExp, awayExp);
        List<PlayerRequest> awayPlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(gameRequest.getAwayPlayers(), awayExp,homeExp);

        for(PlayerRequest playerRequest : homePlayers){
            map.put(playerRequest.getPlayerId(), playerRequest.getTeamAdjustedMinAvg());
            zeroPredMap.put(playerRequest.getPlayerId(), playerRequest.getZeroPred());
        }

        for(PlayerRequest playerRequest : awayPlayers){
            map.put(playerRequest.getPlayerId(), playerRequest.getTeamAdjustedMinAvg() );
            zeroPredMap.put(playerRequest.getPlayerId(), playerRequest.getZeroPred());

        }

        return Pair.of(map, zeroPredMap);
    }

    public static double[] getMinutesDistributionForPlayer(double minutesPredicted){
//        double minutesPredicted = minutesExpected.get(player.getPlayerId());
        minutesPredicted = minutesPredicted < 4 ? 4 : minutesPredicted;
        minutesPredicted = minutesPredicted > 36 ? 36 : minutesPredicted;

        return getMinutesDistributionForPrediction((int) Math.round(minutesPredicted));

    }

    public static Map<Integer, double[]> getMinDistributionMap(){
        File file = new File(MinutesExpectedModel.class.getResource("/minutesDistribution.csv").getFile());

        List<Triple<Integer, Integer, Double>> entries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int minExpected = Integer.parseInt(values[2]);
                int minPlayed = Integer.parseInt(values[1].replace("\"", ""));
                double minPlayedProb = Double.parseDouble(values[3]);

                entries.add(Triple.of(minExpected, minPlayed, minPlayedProb));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<Integer, double[]> minProbsMap = convertToMap(entries);

        return minProbsMap;
    }

    private static Map<Integer, double[]> convertToMap(List<Triple<Integer, Integer, Double>> entries) {
        Set<Integer> minPredSet = entries.stream().map(i -> i.getLeft()).distinct().collect(Collectors.toSet());
        Map<Integer, double[]> map = new HashMap<>();
        for(Integer minPred : minPredSet){
            List<Triple<Integer, Integer, Double>> entriesForMinPred = entries.stream().filter(e -> e.getLeft() == minPred).collect(Collectors.toList());
            double[] probs = new double[49];
            for (int i = 1; i <= 48; i++) {
                int finalI = i;
                Optional<Triple<Integer, Integer, Double>> first = entriesForMinPred.stream().filter(e -> e.getMiddle() == finalI).findFirst();
                if(first.isPresent()) {
                    probs[i] = first.get().getRight();
                }
            }
            map.put(minPred, probs);
        }
        return map;
    }

}
