package nba.minutedistribution;

import nba.bayesianmodel.common.PlayerGameData;
import nba.bayesianmodel.common.PlayerPosition;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import sun.misc.ClassLoaderUtil;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SimulateMinutesForPlayer {
    private final static Map<Integer, double[]> MINUTES_DISTRIBUTION = getScaleAndShapeMap();

    public static double[] getMinutesDistributionForPrediction(int minutesPredicted){
        return MINUTES_DISTRIBUTION.get(minutesPredicted);
    }

    public static Map<Integer, double[]>  getScaleAndShapeMap(){
        File file = new File(SimulateMinutesForPlayer.class.getResource("/minutesDistribution.csv").getFile());

        List<Triple<Integer, Integer, Double>> entries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int minExpected = Integer.parseInt(values[1]);
                int minPlayed = Integer.parseInt(values[2].replace("\"", ""));
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

    private static int getAsInteger(String[] values, Map<String, Integer> colNamesIndex, String colName) {
        return Integer.parseInt(values[colNamesIndex.get(colName)].replace("\"", ""));
    }

    private static double getAsDouble(String[] values, Map<String, Integer> colNamesIndex, String colName) {
        return Double.parseDouble(values[colNamesIndex.get(colName)].replace("\"", ""));
    }

}
