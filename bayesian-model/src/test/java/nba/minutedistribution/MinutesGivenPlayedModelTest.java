package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinutesGivenPlayedModelTest {

    public static final double DELTA = 0.0001d;

    @Test
    public void name() {
        Assert.assertEquals(24.5219132983209d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,19.5555555555556,108,116.5,2024,0,0,213.083333333333,29) , DELTA);
        Assert.assertEquals(32.7027836838133d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.7073170731707,122.75,118.25,2023,1,0,230.062915018887,34) , DELTA);
        Assert.assertEquals(32.1700492073763d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.275,112.5,106.5,2022,1,0,236.004781566043,33) , DELTA);
        Assert.assertEquals(18.295796606793d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,15.5833333333333,97.75,109.75,2022,0,0,241.967739233136,19) , DELTA);
        Assert.assertEquals(32.3564818951224d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,33,110.25,111.25,2024,1,0,240,33) , DELTA);
        Assert.assertEquals(27.2071743333517d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,17.0740740740741,120.5,112.5,2022,1,0,233.179369382027,28) , DELTA);
        Assert.assertEquals(25.6194217962438d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.6923076923077,115,105.5,2023,1,0,230.018001672241,22) , DELTA);
        Assert.assertEquals(25.3472425351295d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,24.4545454545455,109.75,111.75,2023,1,0,240.090909090909,22) , DELTA);
        Assert.assertEquals(29.9964315792064d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,27.9302325581395,117.75,107.75,2023,1,0,250.86187330488,44) , DELTA);
        Assert.assertEquals(23.2047526137038d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,24.9117647058824,108,118.5,2023,0,0,215.695094884463,25) , DELTA);
        Assert.assertEquals(33.758552890576d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,33.5,119.5,114.5,2023,1,0,263.393169286853,36) , DELTA);
        Assert.assertEquals(30.5516514349256d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,31.2121212121212,114.75,109.25,2023,1,0,236.47134803001,35) , DELTA);
        Assert.assertEquals(19.0177725207118d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,19.4285714285714,118.25,111.25,2023,0,0,250.086315430471,20) , DELTA);
        Assert.assertEquals(15.0252816641677d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,14.3125,99.5,112.5,2022,0,0,217.242948717949,9) , DELTA);
        Assert.assertEquals(27.7003998212699d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,29,111.75,112.75,2023,0,0,217.35947391079,33) , DELTA);
        Assert.assertEquals(27.4950802027967d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,29.7777777777778,107.75,101.75,2022,1,0,265.928676470588,23) , DELTA);
        Assert.assertEquals(33.6596419139447d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.5581395348837,114.5,108,2023,1,0,238.376006498838,31) , DELTA);
        Assert.assertEquals(29.5488358896538d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,28.695652173913,112.75,116.75,2023,1,0,248.101279879541,35) , DELTA);
        Assert.assertEquals(31.0998298068706d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,29.3018867924528,109,118,2023,1,0,229.877175578999,38) , DELTA);
        Assert.assertEquals(20.3401871046054d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,25.8846153846154,104.75,102.75,2022,0,0,276.331333200922,21) , DELTA);
        Assert.assertEquals(20.1978899454449d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,25.7166666666667,114,110.5,2023,0,0,248.306538883897,15) , DELTA);
        Assert.assertEquals(35.7584818376412d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,35.7692307692308,113.75,111.25,2023,1,0,209.3624856203,34) , DELTA);
        Assert.assertEquals(19.0323964798697d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,22.3157894736842,113,115.5,2023,0,0,238.945374525201,19) , DELTA);
        Assert.assertEquals(8.43370616441041d, MinutesGivenPlayedModel.getAverageGivenPlayed(3,7.5,112.5,107,2023,0,0,226.853535353535,6) , DELTA);
        Assert.assertEquals(31.6805332833764d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,30.5,108.5,117,2023,1,0,253.812987012987,34) , DELTA);
        Assert.assertEquals(36.0071920616309d, MinutesGivenPlayedModel.getAverageGivenPlayed(39,35,107.25,116.25,2024,1,0,183.785714285714,39) , DELTA);
        Assert.assertEquals(14.1738543476942d, MinutesGivenPlayedModel.getAverageGivenPlayed(10,22.9454545454545,108.5,118.5,2023,0,0,263.005074523564,14) , DELTA);
        Assert.assertEquals(30.479545077716d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,31,109.25,119.75,2024,1,0,238.5,33) , DELTA);
        Assert.assertEquals(19.9995259569202d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,18,117,118.5,2023,0,0,252,18) , DELTA);
        Assert.assertEquals(31.1565665903889d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,32,114.25,109.75,2024,1,0,247.569444444445,34) , DELTA);
        Assert.assertEquals(29.1283003335625d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,27.8529411764706,115.5,111.5,2023,1,0,266.21142347291,34) , DELTA);
        Assert.assertEquals(16.3924414080567d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,19.2,115.5,109.5,2024,0,0,246.516666666667,20) , DELTA);
        Assert.assertEquals(37.8846667603103d, MinutesGivenPlayedModel.getAverageGivenPlayed(38,38.5,107.75,111.25,2024,1,0,218,46) , DELTA);
        Assert.assertEquals(23.0718362185335d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23.3428571428571,119.75,122.75,2023,0,0,233.801127033886,26) , DELTA);
        Assert.assertEquals(31.7944947921147d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,34.0222222222222,121.5,118,2023,1,0,239.354276158624,26) , DELTA);
        Assert.assertEquals(26.3812817312021d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,26.9636363636364,114,122.5,2023,1,0,244.87432557722,24) , DELTA);
        Assert.assertEquals(17.6190462150955d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,13.8955223880597,117.5,104.5,2022,0,0,230.42638691725,13) , DELTA);
        Assert.assertEquals(19.7468183235492d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,28.4166666666667,115.5,117,2023,0,0,255.650356249278,18) , DELTA);
        Assert.assertEquals(29.3031651227984d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,28.4,114.25,117.75,2023,1,0,234.334920634921,27) , DELTA);
        Assert.assertEquals(16.1163504045521d, MinutesGivenPlayedModel.getAverageGivenPlayed(10,17.9642857142857,121.75,114.25,2023,0,0,283.889559343024,41) , DELTA);
        Assert.assertEquals(11.9557442725507d, MinutesGivenPlayedModel.getAverageGivenPlayed(11,11,117.75,114.75,2023,0,0,240,11) , DELTA);
        Assert.assertEquals(23.6612720069064d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,23.5294117647059,119.25,110.25,2023,0,0,231.4315943091,22) , DELTA);
        Assert.assertEquals(9.55513764360918d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,4.6,111.25,107.25,2023,0,0,220.511743158802,8) , DELTA);
        Assert.assertEquals(30.707265399481d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.7826086956522,120.5,109.5,2023,1,0,249.565445488489,26) , DELTA);
        Assert.assertEquals(18.1091631322543d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,22.6363636363636,107,103.5,2023,0,0,248.075926503127,27) , DELTA);
        Assert.assertEquals(29.375749540214d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,20.1111111111111,112.25,111.25,2023,1,0,212.255526171857,21) , DELTA);
        Assert.assertEquals(17.6349422845988d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,17.75,118,123,2024,0,0,239.5,21) , DELTA);
        Assert.assertEquals(35.0989142500071d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.8,111.5,114.5,2022,1,0,224.815098669113,39) , DELTA);
        Assert.assertEquals(33.0116879176012d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,36.4583333333333,113.75,106.25,2023,1,0,248.232434179205,34) , DELTA);
        Assert.assertEquals(31.2147787751412d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,32.5555555555556,108,104,2023,1,0,264.388888888889,28) , DELTA);
        Assert.assertEquals(32.4125666028042d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,30.1153846153846,114,116.5,2023,1,0,218.296499036261,31) , DELTA);
        Assert.assertEquals(17.2450782894305d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,17.8333333333333,120.5,118.5,2024,0,0,217.666666666667,15) , DELTA);
        Assert.assertEquals(34.4298349782158d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,33.4923076923077,115.25,109.75,2022,1,0,267.310858323453,31) , DELTA);
        Assert.assertEquals(19.1453287853771d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,21,109.5,116.5,2023,0,0,227,21) , DELTA);
        Assert.assertEquals(13.3124121927954d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,9.93333333333333,111.75,114.25,2022,0,0,225.713636363636,11) , DELTA);
        Assert.assertEquals(34.5861803764816d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,34.2727272727273,107.75,111.75,2022,1,0,221.438359334469,40) , DELTA);
        Assert.assertEquals(27.233832450177d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,27.6052631578947,108.75,115.75,2023,1,0,207.764097134376,16) , DELTA);
        Assert.assertEquals(32.8009981549358d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.1346153846154,119,121.5,2023,1,0,254.109841173663,34) , DELTA);
        Assert.assertEquals(28.1580084235277d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29.9814814814815,118,107,2023,1,0,258.647843911656,26) , DELTA);
        Assert.assertEquals(33.1414351083314d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,32.3191489361702,107.75,115.25,2023,1,0,204.070140810653,27) , DELTA);
        Assert.assertEquals(12.9504535780157d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,14.9642857142857,118.5,111,2023,0,0,264.293786748934,13) , DELTA);
        Assert.assertEquals(36.8991651568323d, MinutesGivenPlayedModel.getAverageGivenPlayed(39,32.5762711864407,115,116,2021,1,0,208.811950420582,45) , DELTA);
        Assert.assertEquals(34.958444318117d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,36.7777777777778,110.5,102,2023,1,0,225.541666666667,38) , DELTA);
        Assert.assertEquals(34.3720420977642d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.2222222222222,104.5,101.5,2022,1,0,244.637698412698,36) , DELTA);
        Assert.assertEquals(34.435397466068d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,36.1,113.5,120,2023,1,0,264.186359493396,27) , DELTA);
        Assert.assertEquals(15.4877845539033d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,17.5,113.5,107,2024,0,0,236.083333333333,1) , DELTA);
        Assert.assertEquals(17.6296388714197d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,22.1176470588235,104.25,109.25,2022,0,0,256.293259061238,22) , DELTA);
        Assert.assertEquals(35.1820236834706d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,35.6315789473684,103,109,2022,1,0,249.375285253971,37) , DELTA);
        Assert.assertEquals(8.84763968783126d, MinutesGivenPlayedModel.getAverageGivenPlayed(5,14.5833333333333,116.25,113.75,2023,0,0,284.0655730555,9) , DELTA);
        Assert.assertEquals(26.9083800440605d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,25.5142857142857,118.25,111.75,2023,1,0,232.596848094275,19) , DELTA);
        Assert.assertEquals(30.335736764491d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,32.9736842105263,108.25,100.25,2021,1,0,239.964010250852,30) , DELTA);
        Assert.assertEquals(18.4996040656536d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,17.8823529411765,104.25,112.75,2022,0,0,232.284103641457,20) , DELTA);
        Assert.assertEquals(23.5957744403091d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,16.5714285714286,103,112.5,2024,0,0,179.821428571429,15) , DELTA);
        Assert.assertEquals(28.1042579955834d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,25.6666666666667,108.5,112.5,2024,1,0,239.333333333333,13) , DELTA);
        Assert.assertEquals(18.806734582651d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,15.14,122.5,119,2022,0,0,226.972063348281,22) , DELTA);
        Assert.assertEquals(19.9718456893223d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,20.0769230769231,119,115,2023,0,0,231.226096850118,19) , DELTA);
        Assert.assertEquals(25.689146989665d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,20.7777777777778,111.75,114.25,2022,1,0,225.713636363636,25) , DELTA);
        Assert.assertEquals(30.8381153661356d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,27.5121951219512,115.25,121.25,2023,1,0,228.795597499095,27) , DELTA);
        Assert.assertEquals(34.7825739668512d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.9411764705882,118,116,2023,1,0,248.411258139144,33) , DELTA);
        Assert.assertEquals(16.5055582928137d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,18,124.75,112.25,2022,0,0,234.511819815607,3) , DELTA);
        Assert.assertEquals(32.771399442625d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,34.375,109.5,105.5,2022,1,0,234.864285714286,33) , DELTA);
        Assert.assertEquals(34.5365265755115d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.8857142857143,118.25,119.75,2022,1,0,227.091812758891,27) , DELTA);
        Assert.assertEquals(33.9590684060202d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,35,111.5,114,2023,1,0,257,35) , DELTA);
        Assert.assertEquals(26.7058747038692d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,17.7272727272727,110.25,123.75,2023,1,0,190.714965519096,25) , DELTA);
        Assert.assertEquals(19.1399029883553d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,11.9655172413793,118.5,117.5,2023,0,0,226.549333289829,13) , DELTA);
        Assert.assertEquals(29.112634543924d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,29.6615384615385,109.75,115.25,2022,1,0,224.836957548675,29) , DELTA);
        Assert.assertEquals(30.6662074898688d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,31.5,122.5,120.5,2021,1,0,258.112235501023,30) , DELTA);
        Assert.assertEquals(31.9450921634574d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.5909090909091,99.25,106.75,2022,1,0,254.629809323355,37) , DELTA);
        Assert.assertEquals(26.0870132386272d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,29.1,114.5,118.5,2023,0,0,239.754292929293,30) , DELTA);
        Assert.assertEquals(35.8740213351951d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,35.625,114.5,111.5,2023,1,0,226.521679697114,42) , DELTA);
        Assert.assertEquals(36.1446435838723d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,37.1607142857143,111.75,106.75,2022,1,0,269.328896656257,37) , DELTA);
        Assert.assertEquals(25.4446847011408d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,26.4117647058824,112.75,110.25,2023,1,0,255.941494698112,21) , DELTA);
        Assert.assertEquals(29.2963615224922d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,28.3,106.75,108.25,2023,1,0,209.74126984127,25) , DELTA);
        Assert.assertEquals(26.0603690683219d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,18.0238095238095,111.75,109.75,2023,1,0,231.830578579567,33) , DELTA);
        Assert.assertEquals(27.7413786698286d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.625,107,110.5,2022,1,0,256.087816089961,32) , DELTA);
        Assert.assertEquals(30.1448856812354d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,29.4833333333333,120.5,117,2021,1,0,266.123430024999,35) , DELTA);
        Assert.assertEquals(9.8295433124952d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,9.45,122,114.5,2023,0,0,257.850650719721,13) , DELTA);
        Assert.assertEquals(29.1165732619526d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,30.1739130434783,117.5,116,2021,1,0,230.459890833804,26) , DELTA);
        Assert.assertEquals(12.2108781040695d, MinutesGivenPlayedModel.getAverageGivenPlayed(8,16.9166666666667,121,113.5,2023,0,0,263.737795537795,17) , DELTA);
        Assert.assertEquals(9.02459695158835d, MinutesGivenPlayedModel.getAverageGivenPlayed(4,10.9230769230769,111.5,114,2023,0,0,223.293132744449,4) , DELTA);
    }
    @Test
    public void name3() {
        System.out.println( MinutesGivenPlayedModel.getAverageGivenPlayed(38,36.6530612244898,124.5,124.5,2024,1,0,240,38));
    }
}