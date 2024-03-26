package nba.bayesianmodel.coefupdaters;

import nba.bayesianmodel.common.*;
import nba.bayesianmodel.optimizers.assists.CoefficientUpdatingAssistsFunction;
import nba.bayesianmodel.optimizers.blocks.CoefficientUpdatingBlocksFunction;
import nba.bayesianmodel.optimizers.fgattempted.CoefficientUpdatingFgAttemptedFunction;
import nba.bayesianmodel.optimizers.fouls.CoefficientUpdatingFoulsFunction;
import nba.bayesianmodel.optimizers.ftperc.CoefficientUpdatingFtPercFunction;
import nba.bayesianmodel.optimizers.ftsattempted.CoefficientUpdatingFtsAttemptedFunction;
import nba.bayesianmodel.optimizers.offrebprop.CoefficientUpdatingOffRebPropFunction;
import nba.bayesianmodel.optimizers.rebounds.CoefficientUpdatingReboundsFunction;
import nba.bayesianmodel.optimizers.steals.CoefficientUpdatingStealsFunction;
import nba.bayesianmodel.optimizers.threeperc.CoefficientUpdatingThreePercFunction;
import nba.bayesianmodel.optimizers.threeprop.CoefficientUpdatingThreePropFunction;
import nba.bayesianmodel.optimizers.turnovers.CoefficientUpdatingTurnoversFunction;
import nba.bayesianmodel.optimizers.twoperc.CoefficientUpdatingTwoPercFunction;
import nba.bayesianmodel.priors.*;

import java.util.List;
import java.util.Map;

public class CoefUpdaterApplication {

    private static final double[] FG_ATTEMPTED_MODEL_COEFS = new double[]{-1.1466455244675586, 2.4817708354406802};
    private static final double[] THREE_PROP_MODEL_COEFS = new double[]{-1.2775242621304437, -4.106190216427862};
    private static final double[] TWO_PERC_MODEL_COEFS = new double[]{-0.9399967072371814, 9.384618913204807};
    private static final double[] THREE_PERC_MODEL_COEFS = new double[]{-3.6694823610069403, -1.0942849926848508};
    private static final double[] ASSISTS_MODEL_COEFS = new double[]{-4.0681038942967085, 0.39142485063623744};
    private static final double[] STEALS_MODEL_COEFS = new double[]{-1.877701759338379, -5.4388508796691895};
    private static final double[] BLOCKS_MODEL_COEFS = new double[]{-3.2537976615065913, 0.35565815338893114};
    private static final double[] REBOUNDS_MODEL_COEFS = new double[]{-4.812320413726052, 0.4467980300488228};
    private static final double[] TURNOVERS_MODEL_COEFS = new double[]{-3.942611577296182, 0.3018169269290699};
    private static final double[] PERSONAL_FOUL_MODEL_COEFS = new double[]{-4.115418420375875, 0.4110414717291522};
    private static final double[] FTS_ATTEMPTED_MODEL_COEFS = new double[]{-4.281462141342155, 0.4644630044282559};
    private static final double[] OFF_REBOUNDS_MODEL_COEFS = new double[]{-2.211426681950958, 0.3504844407325919};
    private static final double[] FT_PERC_MODEL_COEFS = new double[]{-2.0844366794315787, 0.3381962544817999};


    public static void main(String[] args) {
        PlayersData allPlayers = CsvUtils.loadData(false);

        PlayersData playersForSeason = allPlayers.getPlayersDataForSeason(2024);

        addDummyGameForAllPlayers(playersForSeason);

        List<PlayerPredictions> fgAttemptedPredictions = getPlayersPredictions(new CoefficientUpdatingFgAttemptedFunction(), new FgAttemptedPrior(), playersForSeason, FG_ATTEMPTED_MODEL_COEFS);
        List<PlayerPredictions> threePropPredictions = getPlayersPredictions(new CoefficientUpdatingThreePropFunction(), new ThreePropPrior2(), playersForSeason, THREE_PROP_MODEL_COEFS);
        List<PlayerPredictions> twoPredictions = getPlayersPredictions(new CoefficientUpdatingTwoPercFunction(), new TwoPercPrior(), playersForSeason, TWO_PERC_MODEL_COEFS);
        List<PlayerPredictions> threePredictions = getPlayersPredictions(new CoefficientUpdatingThreePercFunction(), new ThreePercPrior(), playersForSeason, THREE_PERC_MODEL_COEFS);
        List<PlayerPredictions> assistsPredictions = getPlayersPredictions(new CoefficientUpdatingAssistsFunction(), new AssistsPerMinPrior(), playersForSeason, ASSISTS_MODEL_COEFS);
        List<PlayerPredictions> stealsPredictions = getPlayersPredictions(new CoefficientUpdatingStealsFunction(), new StealsPerMinPrior(), playersForSeason, STEALS_MODEL_COEFS);
        List<PlayerPredictions> blocksPredictions = getPlayersPredictions(new CoefficientUpdatingBlocksFunction(), new BlocksPerMinPrior(), playersForSeason, BLOCKS_MODEL_COEFS);
        List<PlayerPredictions> reboundsPredictions = getPlayersPredictions(new CoefficientUpdatingReboundsFunction(), new ReboundsPerMinPrior(), playersForSeason, REBOUNDS_MODEL_COEFS);
        List<PlayerPredictions> turnoversPredictions = getPlayersPredictions(new CoefficientUpdatingTurnoversFunction(), new TurnoversPerMinPrior(), playersForSeason, TURNOVERS_MODEL_COEFS);
        List<PlayerPredictions> personalFoulsPredictions = getPlayersPredictions(new CoefficientUpdatingFoulsFunction(), new PersonalFoulsPerMinPrior(), playersForSeason, PERSONAL_FOUL_MODEL_COEFS);
        List<PlayerPredictions> ftsAttemptedPredictions = getPlayersPredictions(new CoefficientUpdatingFtsAttemptedFunction(), new FtsAttemptedPerMinPrior(), playersForSeason, FTS_ATTEMPTED_MODEL_COEFS);
        List<PlayerPredictions> offReboundsPredictions = getPlayersPredictions(new CoefficientUpdatingOffRebPropFunction(), new OffReboundsPropPrior(), playersForSeason, OFF_REBOUNDS_MODEL_COEFS);
        List<PlayerPredictions> ftPercPredictions = getPlayersPredictions(new CoefficientUpdatingFtPercFunction(), new OffReboundsPropPrior(), playersForSeason, FT_PERC_MODEL_COEFS);


        CsvUtils.savePredictions(fgAttemptedPredictions, "fgAttemptedPerMin");
        CsvUtils.savePredictions(threePropPredictions, "threeProp");
        CsvUtils.savePredictions(twoPredictions, "twoPerc");
        CsvUtils.savePredictions(threePredictions, "threePerc");
        CsvUtils.savePredictions(assistsPredictions, "assistsPerMin");
        CsvUtils.savePredictions(stealsPredictions, "stealsPerMin");
        CsvUtils.savePredictions(blocksPredictions, "blocksPerMin");
        CsvUtils.savePredictions(reboundsPredictions, "reboundsPerMin");
        CsvUtils.savePredictions(turnoversPredictions, "turnoversPerMin");
        CsvUtils.savePredictions(personalFoulsPredictions, "personalFoulsPerMin");
        CsvUtils.savePredictions(ftsAttemptedPredictions, "ftsAttemptedPerMin");
        CsvUtils.savePredictions(offReboundsPredictions, "ofRebProp");
        CsvUtils.savePredictions(ftPercPredictions, "ftPerc");
    }

    private static List<PlayerPredictions> getPlayersPredictions(BaseCoefficientUpdatingFunction coefficientUpdatingFunction, BaseAbstractPriors priorsFunction, PlayersData playersData, double[] coeff) {
        Map<Integer, Double> priors = priorsFunction.getPlayersPriors(playersData);
        List<PlayerPredictions> playersPredictions = coefficientUpdatingFunction.getPlayersPredictions(playersData, priors, coeff);
        return playersPredictions;
    }

    private static void addDummyGameForAllPlayers(PlayersData playersData) {
        for(Integer playerId : playersData){
            List<PlayerGameData> playerGameDataForPlayer = playersData.getPlayerGameDataForPlayer(playerId);
            int latestSeason = playerGameDataForPlayer.stream().mapToInt(i -> i.getSeasonYear()).max().getAsInt();
            playerGameDataForPlayer.add(addDummyGame(playerId, latestSeason));
            int x = 1;
        }
    }

    private static PlayerGameData addDummyGame(Integer playerId, int latestSeason) {
        return new PlayerGameData(20990101, -1, playerId, "", null, false,1,1,1,1,1,1,1,1,1,1,1,1,1,1,latestSeason, 1);
    }


    private static List<PlayerPredictions> getPlayersPredictions(BaseCoefficientUpdatingFunction coefficientUpdatingFunction, BaseNewPlayersAveragePrior priorsFunction, PlayersData playersData, double[] coeff) {
        Map<Integer, Double> priors = priorsFunction.getPlayersPriors(playersData);
        List<PlayerPredictions> playersPredictions = coefficientUpdatingFunction.getPlayersPredictions(playersData, priors, coeff);
        return playersPredictions;
    }
}
