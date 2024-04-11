package nba.bayesianmodel.optimizers;

import nba.bayesianmodel.common.CsvUtils;
import nba.bayesianmodel.common.PlayersData;
import nba.bayesianmodel.optimizers.assists.AssistsApplication;
import nba.bayesianmodel.optimizers.blocks.BlocksApplication;
import nba.bayesianmodel.optimizers.fgattempted.FgAttemptedApplication;
import nba.bayesianmodel.optimizers.fouls.PersonalFoulsApplication;
import nba.bayesianmodel.optimizers.ftperc.FtPercApplication;
import nba.bayesianmodel.optimizers.ftsattempted.FtsAttemptedApplication;
import nba.bayesianmodel.optimizers.offrebprop.OfRebApplication;
import nba.bayesianmodel.optimizers.rebounds.ReboundsApplication;
import nba.bayesianmodel.optimizers.steals.StealsApplication;
import nba.bayesianmodel.optimizers.threeperc.ThreePercApplication;
import nba.bayesianmodel.optimizers.threeprop.ThreePropApplication;
import nba.bayesianmodel.optimizers.turnovers.TurnoversApplication;
import nba.bayesianmodel.optimizers.twoperc.TwoPercApplication;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.optimization.OptimizationException;

public class CoefOptimizerApplication {

    public static void main(String[] args) throws OptimizationException, FunctionEvaluationException {
        //Get data from google big query

        PlayersData playersData = CsvUtils.loadData(false);

//        System.out.println("Running FG attempted per minute application");
//        new FgAttemptedApplication().run(playersData); //Fg Attempted per minute

        System.out.println("Running Three proportion application");
        new ThreePropApplication().run(playersData); //Three proportion of shots
//
//        System.out.println("Running Two Perc proportion application");
//        new TwoPercApplication().run(playersData); //Two perc
////
//        System.out.println("Running Three Perc proportion application");
//        new ThreePercApplication().run(playersData); //Three perc
////
//        System.out.println("Running Assists per min application");
//        new AssistsApplication().run(playersData); //Assist per min
////
//        System.out.println("Running Steals per min application");
//        new StealsApplication().run(playersData); //Steals per min
////
//        System.out.println("Running Blocks per min application");
//        new BlocksApplication().run(playersData); //Blocks per min
////
//        System.out.println("Running Rebounds per min application");
//        new ReboundsApplication().run(playersData); //Rebounds per min
////
//        System.out.println("Running Turnovers per min application");
//        new TurnoversApplication().run(playersData); //Turnovers per min
////
//        System.out.println("Running Personal fouls per min application");
//        new PersonalFoulsApplication().run(playersData); //Pf per min
////
//        System.out.println("Running FTs Attempted per min application");
//        new FtsAttemptedApplication().run(playersData); //Fts Attempted per min
//
//        System.out.println("Running Off Reb prop application");
//        new OfRebApplication().run(playersData); //Of Rebounds per min
////
//        System.out.println("Running Ft Perc application");
//        new FtPercApplication().run(playersData); //Ft Perc per min
    }
}
