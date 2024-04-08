package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinutesGivenPlayedModelTest {

    public static final double DELTA = 0.0001d;

    @Test
    public void name() {
        Assert.assertEquals(33.055488137801d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.7073170731707,122.75,118.25,2023,1,0,230.062915018887,32.7073170731707) , DELTA);
        Assert.assertEquals(18.0426207810299d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,15.5833333333333,97.75,109.75,2022,0,0,241.967739233136,15.5833333333333) , DELTA);
        Assert.assertEquals(15.0122595571521d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,14.4411764705882,109.75,102.75,2024,0,0,256.429923723582,14.4411764705882) , DELTA);
        Assert.assertEquals(32.5748517936073d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.625,112.75,111.75,2024,1,0,238.458836098542,32.625) , DELTA);
        Assert.assertEquals(26.1351041750445d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.6923076923077,115,105.5,2023,1,0,230.018001672241,23.6923076923077) , DELTA);
        Assert.assertEquals(10.3896687358314d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,11.3846153846154,125.5,108.5,2024,0,1,255.052902649713,11.3846153846154) , DELTA);
        Assert.assertEquals(23.6589162220395d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,25.1,105.5,104,2024,0,0,235.381954156954,25.1) , DELTA);
        Assert.assertEquals(28.7016360260119d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,28.695652173913,112.75,116.75,2023,1,0,248.101279879541,28.695652173913) , DELTA);
        Assert.assertEquals(20.973438505982d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,25.7166666666667,114,110.5,2023,0,0,248.306538883897,25.7166666666667) , DELTA);
        Assert.assertEquals(32.1507456530076d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,30.5,108.5,117,2023,1,0,253.812987012987,30.5) , DELTA);
        Assert.assertEquals(23.6747745844756d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,27.2857142857143,111.5,119.5,2024,0,0,237.543900642544,27.2857142857143) , DELTA);
        Assert.assertEquals(15.893215989364d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,19.2,115.5,109.5,2024,0,0,246.516666666667,19.2) , DELTA);
        Assert.assertEquals(23.2377187138439d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23.3428571428571,119.75,122.75,2023,0,0,233.801127033886,23.3428571428571) , DELTA);
        Assert.assertEquals(26.4269760497099d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,26.9636363636364,114,122.5,2023,1,0,244.87432557722,26.9636363636364) , DELTA);
        Assert.assertEquals(19.921910042852d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,28.4166666666667,115.5,117,2023,0,0,255.650356249278,28.4166666666667) , DELTA);
        Assert.assertEquals(12.3760723440423d, MinutesGivenPlayedModel.getAverageGivenPlayed(10,17.9642857142857,121.75,114.25,2023,0,0,283.889559343024,17.9642857142857) , DELTA);
        Assert.assertEquals(24.0627436205625d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,23.5294117647059,119.25,110.25,2023,0,0,231.4315943091,23.5294117647059) , DELTA);
        Assert.assertEquals(28.6706481998559d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29.6923076923077,106,108.5,2024,1,0,252.289335664336,29.6923076923077) , DELTA);
        Assert.assertEquals(27.3830912422003d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,25.7708333333333,120.5,128.5,2024,1,0,231.37911555611,25.7708333333333) , DELTA);
        Assert.assertEquals(34.9937010152509d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,36,113.25,110.25,2024,1,0,243,36) , DELTA);
        Assert.assertEquals(35.1611711580159d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,33.4923076923077,115.25,109.75,2022,1,0,267.310858323453,33.4923076923077) , DELTA);
        Assert.assertEquals(33.228048156121d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.1346153846154,119,121.5,2023,1,0,254.109841173663,32.1346153846154) , DELTA);
        Assert.assertEquals(13.0261135984627d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,14.9642857142857,118.5,111,2023,0,0,264.293786748934,14.9642857142857) , DELTA);
        Assert.assertEquals(32.7671875925139d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.5384615384615,123.25,118.25,2024,1,0,243.312614651493,32.5384615384615) , DELTA);
        Assert.assertEquals(17.1413659656765d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,18.1,113,116.5,2024,0,0,247.024082801257,18.1) , DELTA);
        Assert.assertEquals(34.6798046613998d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,36.7777777777778,110.5,102,2023,1,0,225.541666666667,36.7777777777778) , DELTA);
        Assert.assertEquals(34.7910828266117d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.5384615384615,113,116.5,2024,1,0,247.024082801257,34.5384615384615) , DELTA);
        Assert.assertEquals(33.9047700183061d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,31.9285714285714,115.75,112.25,2024,1,0,218.202380952381,31.9285714285714) , DELTA);
        Assert.assertEquals(19.5532197153449d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,13.9636363636364,116,104,2024,0,0,196.704249088417,13.9636363636364) , DELTA);
        Assert.assertEquals(20.2969724131434d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,20.0769230769231,119,115,2023,0,0,231.226096850118,20.0769230769231) , DELTA);
        Assert.assertEquals(25.6258761641392d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,20.7777777777778,111.75,114.25,2022,1,0,225.713636363636,20.7777777777778) , DELTA);
        Assert.assertEquals(12.7341228697709d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,13.9090909090909,118,113.5,2024,0,0,259.515912949841,13.9090909090909) , DELTA);
        Assert.assertEquals(32.7731599968408d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,34.375,109.5,105.5,2022,1,0,234.864285714286,34.375) , DELTA);
        Assert.assertEquals(33.972390912344d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,35,111.5,114,2023,1,0,257,35) , DELTA);
        Assert.assertEquals(15.2241218941802d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,12.6,113.75,109.75,2024,0,0,214.428604655907,12.6) , DELTA);
        Assert.assertEquals(30.5545925692693d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,31.5,122.5,120.5,2021,1,0,258.112235501023,31.5) , DELTA);
        Assert.assertEquals(32.0291284313631d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.5909090909091,99.25,106.75,2022,1,0,254.629809323355,32.5909090909091) , DELTA);
        Assert.assertEquals(35.9393655323193d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,37.1607142857143,111.75,106.75,2022,1,0,269.328896656257,37.1607142857143) , DELTA);
        Assert.assertEquals(27.3749323126444d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.625,107,110.5,2022,1,0,256.087816089961,27.625) , DELTA);
        Assert.assertEquals(9.37263700580672d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,9.45,122,114.5,2023,0,0,257.850650719721,9.45) , DELTA);
        Assert.assertEquals(9.52177941186672d, MinutesGivenPlayedModel.getAverageGivenPlayed(4,10.9230769230769,111.5,114,2023,0,0,223.293132744449,10.9230769230769) , DELTA);
        Assert.assertEquals(34.1995920522291d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.7647058823529,115.25,109.25,2023,1,0,256.154533045102,35.7647058823529) , DELTA);
        Assert.assertEquals(16.0276010265615d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,17.4782608695652,117.5,115,2023,0,0,224.787179487179,17.4782608695652) , DELTA);
        Assert.assertEquals(16.4882053548219d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,15.125,122.5,111,2024,0,0,251.283833144507,15.125) , DELTA);
        Assert.assertEquals(23.3698163668809d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23,115.75,111.75,2024,0,0,225,23) , DELTA);
        Assert.assertEquals(24.2155056675104d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.8571428571429,118.25,114.75,2024,0,0,242.771428571429,27.8571428571429) , DELTA);
        Assert.assertEquals(14.9748195182096d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,17.5,105.75,112.75,2023,0,1,250,17.5) , DELTA);
        Assert.assertEquals(17.8359905786304d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,21.6666666666667,120,127,2024,0,0,242.230108981639,21.6666666666667) , DELTA);
        Assert.assertEquals(19.8739822302555d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,24.5,120,118,2023,0,0,260.741765203853,24.5) , DELTA);
        Assert.assertEquals(23.6803021690074d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.9230769230769,114.5,125,2024,0,0,209.60425564249,23.9230769230769) , DELTA);
        Assert.assertEquals(34.8739226214145d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,33.0138888888889,111.75,114.25,2023,1,0,257.484908862495,33.0138888888889) , DELTA);
        Assert.assertEquals(27.3730693338526d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,26.6428571428571,108.75,105.25,2022,1,0,243.116758241758,26.6428571428571) , DELTA);
        Assert.assertEquals(26.3390429095204d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,25.1111111111111,122.25,114.25,2024,1,0,225.992740861162,25.1111111111111) , DELTA);
        Assert.assertEquals(14.3686631983706d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,14.7777777777778,105.25,111.25,2022,0,0,243.070177104199,14.7777777777778) , DELTA);
        Assert.assertEquals(34.7857002097141d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,35.6842105263158,115.75,110.25,2023,1,0,256.115891202156,35.6842105263158) , DELTA);
        Assert.assertEquals(15.5237796769303d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,21.8,125.5,112.5,2024,0,0,265.518609022556,21.8) , DELTA);
        Assert.assertEquals(33.8171035870023d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,33.375,115.75,111.75,2023,1,0,247.122879864897,33.375) , DELTA);
        Assert.assertEquals(32.6299199194014d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,33.7894736842105,104.25,109.25,2022,1,0,256.293259061238,33.7894736842105) , DELTA);
        Assert.assertEquals(27.1335570865397d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,26.3272727272727,111.5,117.5,2023,1,0,263.414421785512,26.3272727272727) , DELTA);
        Assert.assertEquals(22.5819498936762d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,22.2222222222222,108,109.5,2024,0,1,243.387933634993,22.2222222222222) , DELTA);
        Assert.assertEquals(8.8283072913036d, MinutesGivenPlayedModel.getAverageGivenPlayed(5,8.33333333333333,107.25,112.25,2023,0,0,249.351981351981,8.33333333333333) , DELTA);
        Assert.assertEquals(26.0567777693519d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,25.7678571428571,118,108,2022,1,0,247.269268852837,25.7678571428571) , DELTA);
        Assert.assertEquals(33.4630276712918d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.4285714285714,105.75,117.25,2023,1,0,239.066666666667,34.4285714285714) , DELTA);
        Assert.assertEquals(30.7462677336584d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,29.6521739130435,116.75,112.75,2023,1,0,257.296996711728,29.6521739130435) , DELTA);
        Assert.assertEquals(17.8646875393784d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,22.0980392156863,118.75,112.25,2023,0,0,242.243215592152,22.0980392156863) , DELTA);
        Assert.assertEquals(28.575207238901d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,32.1739130434783,121,109,2023,1,0,257.571721149351,32.1739130434783) , DELTA);
        Assert.assertEquals(13.7976839128566d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,14.1363636363636,122.75,110.75,2024,0,0,250.465493278651,14.1363636363636) , DELTA);
        Assert.assertEquals(28.551458475427d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29,114.25,115.25,2024,1,0,240,29) , DELTA);
        Assert.assertEquals(21.9846295640221d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,25.2857142857143,105.25,111.25,2022,0,0,243.070177104199,25.2857142857143) , DELTA);
        Assert.assertEquals(33.2108562495115d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,34.7727272727273,115.25,101.25,2024,1,0,236.548330504434,34.7727272727273) , DELTA);
        Assert.assertEquals(32.9201739823923d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,28.5,108.75,115.75,2024,1,0,212.431143479525,28.5) , DELTA);
        Assert.assertEquals(33.000686985136d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,33.2241379310345,111.5,106,2024,1,0,231.544229714589,33.2241379310345) , DELTA);
        Assert.assertEquals(17.5761292151786d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,16.955223880597,116.5,107,2024,0,0,202.719024328924,16.955223880597) , DELTA);
        Assert.assertEquals(35.7100595946884d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,32,115,111.5,2023,1,1,235.076902483481,32) , DELTA);
        Assert.assertEquals(31.424064486085d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,33.1451612903226,114.75,108.75,2024,1,0,250.565844741673,33.1451612903226) , DELTA);
        Assert.assertEquals(29.1310696512389d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,13.8947368421053,116.25,119.25,2024,1,0,191.081631161236,13.8947368421053) , DELTA);
        Assert.assertEquals(25.939770292412d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,24.1666666666667,115.5,122,2023,1,0,250.791262100473,24.1666666666667) , DELTA);
        Assert.assertEquals(13.2556092240093d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,9.29411764705882,113.75,108.75,2024,0,0,234.839358713423,9.29411764705882) , DELTA);
        Assert.assertEquals(17.5950640497096d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,16.6818181818182,109.25,117.75,2023,0,0,244.062588858234,16.6818181818182) , DELTA);
        Assert.assertEquals(42.2629979469209d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,36.1923076923077,111.75,115.25,2023,1,1,215.475789023423,36.1923076923077) , DELTA);
        Assert.assertEquals(32.6263011053058d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,30.9166666666667,112.75,116.75,2023,1,0,208.49060625365,30.9166666666667) , DELTA);
        Assert.assertEquals(16.9315755831494d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,12.9,110,114,2023,0,0,214.661496163683,12.9) , DELTA);
        Assert.assertEquals(19.6728539397694d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,24.7407407407407,108.75,106.75,2023,0,0,275.879929158443,24.7407407407407) , DELTA);
        Assert.assertEquals(24.2860171117585d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,22.0689655172414,123.75,110.25,2023,1,0,229.299880782686,22.0689655172414) , DELTA);
        Assert.assertEquals(18.0623129987382d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,19,113.75,110.25,2022,0,0,240,19) , DELTA);
        Assert.assertEquals(32.8417478184894d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.925,114,119,2023,1,0,233.140760773275,31.925) , DELTA);
        Assert.assertEquals(36.0480535858796d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,38.7692307692308,106.5,109.5,2022,1,0,234.790334665335,38.7692307692308) , DELTA);
        Assert.assertEquals(21.2457389824324d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,25.2,112,107,2024,0,0,252.683200483092,25.2) , DELTA);
        Assert.assertEquals(15.5472721301333d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,14,122.5,124,2024,0,0,243.060606060606,14) , DELTA);
        Assert.assertEquals(22.5623321696948d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,18.8378378378378,120.75,118.25,2023,0,0,234.597271077937,18.8378378378378) , DELTA);
        Assert.assertEquals(27.1650546057822d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,24,111,115,2024,1,0,253.176688311688,24) , DELTA);
        Assert.assertEquals(19.283942604566d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,21.0357142857143,120.75,104.75,2023,0,0,249.439080968441,21.0357142857143) , DELTA);
        Assert.assertEquals(31.3357659238268d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,28.3714285714286,117.5,110,2023,1,0,214.598745331466,28.3714285714286) , DELTA);
        Assert.assertEquals(32.2059393766042d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.5857142857143,117.5,110,2024,1,0,243.041402942746,31.5857142857143) , DELTA);
        Assert.assertEquals(31.4663530977611d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,31.6666666666667,112.25,110.75,2024,1,0,247,31.6666666666667) , DELTA);
        Assert.assertEquals(35.5165748639057d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.6190476190476,119.75,121.25,2024,1,0,196.979390175042,35.6190476190476) , DELTA);
        Assert.assertEquals(14.3822195034592d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,11.4642857142857,119.5,117.5,2024,0,0,244.508588043313,11.4642857142857) , DELTA);
        Assert.assertEquals(17.3789502281305d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,20.6296296296296,120.25,114.75,2023,0,0,243.611376371308,20.6296296296296) , DELTA);
        Assert.assertEquals(17.7651574971779d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,19.4722222222222,119.25,113.75,2023,0,0,266.117561893566,19.4722222222222) , DELTA);
        Assert.assertEquals(30.1888833147135d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,24.6530612244898,119.5,122,2024,1,0,216.524476833875,24.6530612244898) , DELTA);
    }

    @Test
    public void name3() {
        System.out.println( MinutesGivenPlayedModel.getAverageGivenPlayed(38,36.6530612244898,124.5,124.5,2024,1,0,240,38));
    }
}