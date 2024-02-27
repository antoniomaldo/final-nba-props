package nba.bayesianmodel.common;

import org.apache.commons.math.analysis.MultivariateRealFunction;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseAbstractOptimizer implements MultivariateRealFunction {
    private final PlayersData playersData;
    private final Map<Integer, Double> priorForPlayers;

    public BaseAbstractOptimizer(PlayersData playersData, Map<Integer, Double> priorForPlayers) {
        this.playersData = playersData;
        this.priorForPlayers = priorForPlayers;
    }

    @Override
    public double value(double[] par) throws IllegalArgumentException {
        List<PlayerPredictions> playerPredictions = getPlayerPredictionsForPar(this.playersData, this.priorForPlayers, par);
        return playerPredictions.stream().
                filter(filterGamesToOptimize()).
                mapToDouble(this::getDistance).sum();
    }

    protected Predicate<PlayerPredictions> filterGamesToOptimize() {
        return p -> p.getNumbOfGames() > 0 && p.getSeasonYear() > 2017;
    }

    protected abstract double getDistance(PlayerPredictions p);

    public abstract List<PlayerPredictions> getPlayerPredictionsForPar(PlayersData playersData, Map<Integer, Double> priorForPlayers, double[] coefficients);
}
