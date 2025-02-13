package nba.bayesianmodel.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThreeProportionModelGivenFgAttemptedTest {

    private static final double DELTA = 0.000001d;

    @Test
    public void name() {
        Assert.assertEquals(0.582477628990978d, ThreeProportionModelGivenFgAttempted.adjustRate(0.544547922883487, 8.35633720325387, 3), DELTA);
        Assert.assertEquals(0.615528189450117d, ThreeProportionModelGivenFgAttempted.adjustRate(0.602422818649948, 5.49150343890185, 4), DELTA);
        Assert.assertEquals(0.615257610036158d, ThreeProportionModelGivenFgAttempted.adjustRate(0.613949668487868, 8.20845035918879, 7), DELTA);
        Assert.assertEquals(0.0183418146908623d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0251038563751991, 3.81640663022894, 2), DELTA);
        Assert.assertEquals(0.259216356925184d, ThreeProportionModelGivenFgAttempted.adjustRate(0.250572735191562, 14.3942455782455, 17), DELTA);
        Assert.assertEquals(0.657247578470487d, ThreeProportionModelGivenFgAttempted.adjustRate(0.703929836553549, 8.40566794047935, 12), DELTA);
        Assert.assertEquals(0.617345098332034d, ThreeProportionModelGivenFgAttempted.adjustRate(0.611960389636834, 7.33617260087853, 6), DELTA);
        Assert.assertEquals(0.454900149951532d, ThreeProportionModelGivenFgAttempted.adjustRate(0.468154431139071, 5.49832387270545, 8), DELTA);
        Assert.assertEquals(0.5394136627998d, ThreeProportionModelGivenFgAttempted.adjustRate(0.544770410224929, 12.2247542069532, 11), DELTA);
        Assert.assertEquals(0.446914554592143d, ThreeProportionModelGivenFgAttempted.adjustRate(0.496509354719971, 16.2267725890675, 24), DELTA);
        Assert.assertEquals(0.489933886665017d, ThreeProportionModelGivenFgAttempted.adjustRate(0.489329566316402, 6.77006614118734, 7), DELTA);
        Assert.assertEquals(0.457246069049239d, ThreeProportionModelGivenFgAttempted.adjustRate(0.446819190681419, 5.41132857281242, 5), DELTA);
        Assert.assertEquals(0.802935168420352d, ThreeProportionModelGivenFgAttempted.adjustRate(0.793253341484295, 4.77105810898129, 3), DELTA);
        Assert.assertEquals(0.485102671394953d, ThreeProportionModelGivenFgAttempted.adjustRate(0.460295654507618, 13.2995694321156, 9), DELTA);
        Assert.assertEquals(0.506882412788133d, ThreeProportionModelGivenFgAttempted.adjustRate(0.533305179690743, 9.86174972499165, 12), DELTA);
        Assert.assertEquals(0.508661669202208d, ThreeProportionModelGivenFgAttempted.adjustRate(0.478156586547764, 6.34378585914193, 3), DELTA);
        Assert.assertEquals(0.78537632545105d, ThreeProportionModelGivenFgAttempted.adjustRate(0.791119160831136, 4.50063265102715, 5), DELTA);
        Assert.assertEquals(0.673259697230145d, ThreeProportionModelGivenFgAttempted.adjustRate(0.702695053266472, 6.48731794851476, 9), DELTA);
        Assert.assertEquals(0.366999857439451d, ThreeProportionModelGivenFgAttempted.adjustRate(0.361226034376024, 2.91179137390273, 4), DELTA);
        Assert.assertEquals(0.358692825904751d, ThreeProportionModelGivenFgAttempted.adjustRate(0.339741361372473, 13.1879044659554, 11), DELTA);
        Assert.assertEquals(0.431100185892437d, ThreeProportionModelGivenFgAttempted.adjustRate(0.427678360941903, 19.1431597141121, 18), DELTA);
        Assert.assertEquals(0.320387387288552d, ThreeProportionModelGivenFgAttempted.adjustRate(0.291963605403739, 4.10173277212181, 1), DELTA);
        Assert.assertEquals(0.287673418125492d, ThreeProportionModelGivenFgAttempted.adjustRate(0.271728633668976, 6.79941109427387, 6), DELTA);
        Assert.assertEquals(0.703544144409677d, ThreeProportionModelGivenFgAttempted.adjustRate(0.749479332744137, 8.54384505603038, 12), DELTA);
        Assert.assertEquals(0.490317353464953d, ThreeProportionModelGivenFgAttempted.adjustRate(0.484782271229333, 16.2246444214286, 14), DELTA);
        Assert.assertEquals(0.29488612511523d, ThreeProportionModelGivenFgAttempted.adjustRate(0.301510558163039, 9.81352728635739, 14), DELTA);
        Assert.assertEquals(0.458438322254427d, ThreeProportionModelGivenFgAttempted.adjustRate(0.484093724586845, 20.7198366745983, 22), DELTA);
        Assert.assertEquals(0.450171700687614d, ThreeProportionModelGivenFgAttempted.adjustRate(0.433433193901982, 2.56760178762393, 2), DELTA);
        Assert.assertEquals(0.384726315353167d, ThreeProportionModelGivenFgAttempted.adjustRate(0.414883718622201, 13.9915793027188, 19), DELTA);
        Assert.assertEquals(0.421397876649822d, ThreeProportionModelGivenFgAttempted.adjustRate(0.400653992950878, 13.1064486694338, 10), DELTA);
        Assert.assertEquals(0.208530147638902d, ThreeProportionModelGivenFgAttempted.adjustRate(0.198886863842287, 11.5886245838597, 14), DELTA);
        Assert.assertEquals(0.556116793340447d, ThreeProportionModelGivenFgAttempted.adjustRate(0.567428537775751, 7.02662201174085, 8), DELTA);
        Assert.assertEquals(0.445806877642411d, ThreeProportionModelGivenFgAttempted.adjustRate(0.441960801815155, 9.43872535811863, 9), DELTA);
        Assert.assertEquals(0.331557325275157d, ThreeProportionModelGivenFgAttempted.adjustRate(0.332849155271842, 18.7929513468604, 21), DELTA);
        Assert.assertEquals(0.407233173790088d, ThreeProportionModelGivenFgAttempted.adjustRate(0.407658134190515, 5.73491244812278, 7), DELTA);
        Assert.assertEquals(0.3682272909401d, ThreeProportionModelGivenFgAttempted.adjustRate(0.364135625318834, 7.28140445428579, 8), DELTA);
        Assert.assertEquals(0.386926287148695d, ThreeProportionModelGivenFgAttempted.adjustRate(0.394027215786825, 9.25953868109052, 11), DELTA);
        Assert.assertEquals(0.505670261950844d, ThreeProportionModelGivenFgAttempted.adjustRate(0.488301213192277, 2.74284187693552, 2), DELTA);
        Assert.assertEquals(0.211089465396991d, ThreeProportionModelGivenFgAttempted.adjustRate(0.186489768519476, 18.7529517994445, 17), DELTA);
        Assert.assertEquals(0.0255226193571939d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0353936587120499, 2.55788352893834, 3), DELTA);
        Assert.assertEquals(0.248318976627767d, ThreeProportionModelGivenFgAttempted.adjustRate(0.248372746697425, 7.06903778037353, 10), DELTA);
        Assert.assertEquals(0.0429734877294209d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0495451800581404, 11.0319571433572, 6), DELTA);
        Assert.assertEquals(0.0789894917295172d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0792854889470889, 21.5822935690882, 19), DELTA);
        Assert.assertEquals(0.758104231603688d, ThreeProportionModelGivenFgAttempted.adjustRate(0.754327498921639, 8.45499626893265, 6), DELTA);
        Assert.assertEquals(0.519617309118156d, ThreeProportionModelGivenFgAttempted.adjustRate(0.511160623308258, 9.76791887314211, 8), DELTA);
        Assert.assertEquals(0.409034172437569d, ThreeProportionModelGivenFgAttempted.adjustRate(0.380369749514163, 8.29072252109062, 5), DELTA);
        Assert.assertEquals(0.401177945628338d, ThreeProportionModelGivenFgAttempted.adjustRate(0.38990200508711, 6.43967852651474, 6), DELTA);
        Assert.assertEquals(0.655747730849249d, ThreeProportionModelGivenFgAttempted.adjustRate(0.654690299734002, 1.93995421194609, 3), DELTA);
        Assert.assertEquals(0.427348267589877d, ThreeProportionModelGivenFgAttempted.adjustRate(0.385599394243347, 7.01603379704643, 2), DELTA);
        Assert.assertEquals(0.0205654595163023d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0257195769689995, 12.3971316521994, 10), DELTA);
        Assert.assertEquals(0.131657953900674d, ThreeProportionModelGivenFgAttempted.adjustRate(0.123435759851548, 20.1547039769153, 17), DELTA);
        Assert.assertEquals(0.847317443542941d, ThreeProportionModelGivenFgAttempted.adjustRate(0.850645365152643, 5.22232606160735, 5), DELTA);
        Assert.assertEquals(0.572534279553107d, ThreeProportionModelGivenFgAttempted.adjustRate(0.567693871807855, 9.85143119661297, 8), DELTA);
        Assert.assertEquals(0.466711683431207d, ThreeProportionModelGivenFgAttempted.adjustRate(0.461272167646862, 11.3104989286049, 10), DELTA);
        Assert.assertEquals(0.653093362590155d, ThreeProportionModelGivenFgAttempted.adjustRate(0.643351647232657, 11.7560780194925, 8), DELTA);
        Assert.assertEquals(0.183898459629352d, ThreeProportionModelGivenFgAttempted.adjustRate(0.175811137403638, 7.47805933212339, 6), DELTA);
        Assert.assertEquals(0.0402774304768071d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0448422115943501, 20.205096099889, 20), DELTA);
        Assert.assertEquals(0.0136106651750786d, ThreeProportionModelGivenFgAttempted.adjustRate(0.018350579078808, 7.64840557290875, 6), DELTA);
        Assert.assertEquals(0.0227194305478434d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0301529752452426, 4.53277104230498, 2), DELTA);
        Assert.assertEquals(0.295116496421468d, ThreeProportionModelGivenFgAttempted.adjustRate(0.286446044368654, 13.1997273389357, 15), DELTA);
        Assert.assertEquals(0.472908270698312d, ThreeProportionModelGivenFgAttempted.adjustRate(0.469051695946605, 5.77172533321736, 6), DELTA);
        Assert.assertEquals(0.506688692432364d, ThreeProportionModelGivenFgAttempted.adjustRate(0.503488321598296, 4.55404255921108, 5), DELTA);
        Assert.assertEquals(0.355295470238238d, ThreeProportionModelGivenFgAttempted.adjustRate(0.346580568246872, 15.5902478128245, 16), DELTA);
        Assert.assertEquals(0.56220319084767d, ThreeProportionModelGivenFgAttempted.adjustRate(0.564224646136648, 10.1172411722379, 9), DELTA);
        Assert.assertEquals(0.51975579268152d, ThreeProportionModelGivenFgAttempted.adjustRate(0.492999936597511, 2.97855819602484, 1), DELTA);
        Assert.assertEquals(0.369162630297372d, ThreeProportionModelGivenFgAttempted.adjustRate(0.339819659755405, 9.48939423542012, 6), DELTA);
        Assert.assertEquals(0.436043779656404d, ThreeProportionModelGivenFgAttempted.adjustRate(0.412671829196769, 8.7226586848263, 6), DELTA);
        Assert.assertEquals(0.521633721316276d, ThreeProportionModelGivenFgAttempted.adjustRate(0.504112888811319, 12.6462382408084, 9), DELTA);
        Assert.assertEquals(0.442665628048408d, ThreeProportionModelGivenFgAttempted.adjustRate(0.443164845317729, 2.18859483986052, 4), DELTA);
        Assert.assertEquals(0.493641616788589d, ThreeProportionModelGivenFgAttempted.adjustRate(0.478449721650935, 0.988997999081007, 1), DELTA);
        Assert.assertEquals(0.412518772794087d, ThreeProportionModelGivenFgAttempted.adjustRate(0.390790930306234, 17.0477613261052, 14), DELTA);
        Assert.assertEquals(0.670880323086244d, ThreeProportionModelGivenFgAttempted.adjustRate(0.674483057781173, 7.89573428866056, 7), DELTA);
        Assert.assertEquals(0.107275724250342d, ThreeProportionModelGivenFgAttempted.adjustRate(0.110620914396002, 13.5827599115155, 11), DELTA);
        Assert.assertEquals(0.364542647429871d, ThreeProportionModelGivenFgAttempted.adjustRate(0.371349963519634, 5.35444688967319, 8), DELTA);
        Assert.assertEquals(0.0324166910891436d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0438341778689301, 5.75427972348252, 8), DELTA);
        Assert.assertEquals(0.504348318908194d, ThreeProportionModelGivenFgAttempted.adjustRate(0.486167012607648, 2.87843634191649, 2), DELTA);
        Assert.assertEquals(0.441829465639842d, ThreeProportionModelGivenFgAttempted.adjustRate(0.418627680515541, 3.68971237557477, 2), DELTA);
        Assert.assertEquals(0.386879958486614d, ThreeProportionModelGivenFgAttempted.adjustRate(0.407962834549462, 15.4031661510819, 19), DELTA);
        Assert.assertEquals(0.211871340228693d, ThreeProportionModelGivenFgAttempted.adjustRate(0.193610446088545, 11.7778889536725, 10), DELTA);
        Assert.assertEquals(0.526387255936541d, ThreeProportionModelGivenFgAttempted.adjustRate(0.567932285816404, 13.955091588284, 17), DELTA);
        Assert.assertEquals(0.461461056598396d, ThreeProportionModelGivenFgAttempted.adjustRate(0.47929430959267, 12.0753395576174, 14), DELTA);
        Assert.assertEquals(0.310403866396686d, ThreeProportionModelGivenFgAttempted.adjustRate(0.299843339978628, 17.9866098304134, 19), DELTA);
        Assert.assertEquals(0.425547884147107d, ThreeProportionModelGivenFgAttempted.adjustRate(0.457094678950493, 17.8146688370108, 21), DELTA);
        Assert.assertEquals(0.317338952775367d, ThreeProportionModelGivenFgAttempted.adjustRate(0.283914895023769, 21.1847036235568, 18), DELTA);
        Assert.assertEquals(0.507439176959039d, ThreeProportionModelGivenFgAttempted.adjustRate(0.505763874474463, 5.71735432844677, 6), DELTA);
        Assert.assertEquals(0.392072548211439d, ThreeProportionModelGivenFgAttempted.adjustRate(0.36937982776008, 7.19405804014222, 5), DELTA);
        Assert.assertEquals(0.24868217594894d, ThreeProportionModelGivenFgAttempted.adjustRate(0.251897697413186, 6.39882286800034, 10), DELTA);
        Assert.assertEquals(0.554393478519516d, ThreeProportionModelGivenFgAttempted.adjustRate(0.560695191068547, 7.82090424127414, 8), DELTA);
        Assert.assertEquals(0.353286838720324d, ThreeProportionModelGivenFgAttempted.adjustRate(0.321136172102539, 7.81365678816659, 4), DELTA);
        Assert.assertEquals(0.771637048414571d, ThreeProportionModelGivenFgAttempted.adjustRate(0.762687733033399, 9.79600739652044, 6), DELTA);
        Assert.assertEquals(0.766801979118517d, ThreeProportionModelGivenFgAttempted.adjustRate(0.77615960887645, 3.60313677088233, 5), DELTA);
        Assert.assertEquals(0.464001816785201d, ThreeProportionModelGivenFgAttempted.adjustRate(0.436252586350399, 3.18355793001082, 1), DELTA);
        Assert.assertEquals(0.00343570405907873d, ThreeProportionModelGivenFgAttempted.adjustRate(0.00524560272899115, 7.44345858940259, 12), DELTA);
        Assert.assertEquals(0.42055874968202d, ThreeProportionModelGivenFgAttempted.adjustRate(0.386543800742669, 10.4567525980225, 6), DELTA);
        Assert.assertEquals(0.352751655993972d, ThreeProportionModelGivenFgAttempted.adjustRate(0.350890667926295, 4.42599844352022, 6), DELTA);
        Assert.assertEquals(0.473008627461107d, ThreeProportionModelGivenFgAttempted.adjustRate(0.483363387326125, 10.3049473900219, 11), DELTA);
        Assert.assertEquals(0.716692686951048d, ThreeProportionModelGivenFgAttempted.adjustRate(0.708664288056592, 5.36409273267455, 4), DELTA);
        Assert.assertEquals(0.515339377202486d, ThreeProportionModelGivenFgAttempted.adjustRate(0.517690307506814, 10.8483643606259, 10), DELTA);
        Assert.assertEquals(0.129693351357591d, ThreeProportionModelGivenFgAttempted.adjustRate(0.139259049264648, 9.0120632574117, 11), DELTA);
        Assert.assertEquals(0.0547710929876188d, ThreeProportionModelGivenFgAttempted.adjustRate(0.0700324005857837, 8.09079367592537, 12), DELTA);
    }
}