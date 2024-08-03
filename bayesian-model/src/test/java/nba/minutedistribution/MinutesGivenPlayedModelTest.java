package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinutesGivenPlayedModelTest {

    public static final double DELTA = 0.0001d;

    @Test
    public void name() {
        Assert.assertEquals(32.8369946158407d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.7073170731707,122.75,118.25,2023,1,0,230.062915018887,34) , DELTA);
        Assert.assertEquals(18.1546180450509d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,15.5833333333333,97.75,109.75,2022,0,0,241.967739233136,19) , DELTA);
        Assert.assertEquals(17.0817458994196d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,14.4411764705882,109.75,102.75,2024,0,0,256.429923723582,35) , DELTA);
        Assert.assertEquals(32.5520460274923d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.625,112.75,111.75,2024,1,0,238.458836098542,37) , DELTA);
        Assert.assertEquals(25.6094433169838d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.6923076923077,115,105.5,2023,1,0,230.018001672241,22) , DELTA);
        Assert.assertEquals(11.2152498980634d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,11.3846153846154,125.5,108.5,2024,0,1,255.052902649713,18) , DELTA);
        Assert.assertEquals(23.4950139836209d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,25.1,105.5,104,2024,0,0,235.381954156954,26) , DELTA);
        Assert.assertEquals(29.5431217590228d, MinutesGivenPlayedModel.getAverageGivenPlayed(30,28.695652173913,112.75,116.75,2023,1,0,248.101279879541,35) , DELTA);
        Assert.assertEquals(20.2272718695091d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,25.7166666666667,114,110.5,2023,0,0,248.306538883897,15) , DELTA);
        Assert.assertEquals(31.7016033040509d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,30.5,108.5,117,2023,1,0,253.812987012987,34) , DELTA);
        Assert.assertEquals(23.4357457706515d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,27.2857142857143,111.5,119.5,2024,0,0,237.543900642544,24) , DELTA);
        Assert.assertEquals(16.2573270925652d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,19.2,115.5,109.5,2024,0,0,246.516666666667,20) , DELTA);
        Assert.assertEquals(23.1832307431067d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23.3428571428571,119.75,122.75,2023,0,0,233.801127033886,26) , DELTA);
        Assert.assertEquals(26.3220678958795d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,26.9636363636364,114,122.5,2023,1,0,244.87432557722,24) , DELTA);
        Assert.assertEquals(19.7311307215761d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,28.4166666666667,115.5,117,2023,0,0,255.650356249278,18) , DELTA);
        Assert.assertEquals(15.5601424105896d, MinutesGivenPlayedModel.getAverageGivenPlayed(10,17.9642857142857,121.75,114.25,2023,0,0,283.889559343024,41) , DELTA);
        Assert.assertEquals(23.8897285808758d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,23.5294117647059,119.25,110.25,2023,0,0,231.4315943091,22) , DELTA);
        Assert.assertEquals(29.1870317232493d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29.6923076923077,106,108.5,2024,1,0,252.289335664336,33) , DELTA);
        Assert.assertEquals(26.601659973542d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,25.7708333333333,120.5,128.5,2024,1,0,231.37911555611,19) , DELTA);
        Assert.assertEquals(35.0590628036333d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,36,113.25,110.25,2024,1,0,243,36) , DELTA);
        Assert.assertEquals(34.6791639779033d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,33.4923076923077,115.25,109.75,2022,1,0,267.310858323453,31) , DELTA);
        Assert.assertEquals(32.8396732155474d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.1346153846154,119,121.5,2023,1,0,254.109841173663,34) , DELTA);
        Assert.assertEquals(12.9572169345977d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,14.9642857142857,118.5,111,2023,0,0,264.293786748934,13) , DELTA);
        Assert.assertEquals(32.4852259613298d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,32.5384615384615,123.25,118.25,2024,1,0,243.312614651493,34) , DELTA);
        Assert.assertEquals(17.0168062236917d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,18.1,113,116.5,2024,0,0,247.024082801257,18) , DELTA);
        Assert.assertEquals(35.1660770382652d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,36.7777777777778,110.5,102,2023,1,0,225.541666666667,38) , DELTA);
        Assert.assertEquals(34.6606308896823d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.5384615384615,113,116.5,2024,1,0,247.024082801257,36) , DELTA);
        Assert.assertEquals(33.2759613548459d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,31.9285714285714,115.75,112.25,2024,1,0,218.202380952381,24) , DELTA);
        Assert.assertEquals(32.5832262076601d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.7777777777778,119.75,124.25,2024,1,0,238.0125,37) , DELTA);
        Assert.assertEquals(19.9489089262381d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,20.0769230769231,119,115,2023,0,0,231.226096850118,19) , DELTA);
        Assert.assertEquals(25.6351289440745d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,20.7777777777778,111.75,114.25,2022,1,0,225.713636363636,25) , DELTA);
        Assert.assertEquals(12.04013120457d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,13.9090909090909,118,113.5,2024,0,0,259.515912949841,6) , DELTA);
        Assert.assertEquals(32.8953567528795d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,34.375,109.5,105.5,2022,1,0,234.864285714286,33) , DELTA);
        Assert.assertEquals(33.9850908671976d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,35,111.5,114,2023,1,0,257,35) , DELTA);
        Assert.assertEquals(15.2035640447212d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,12.6,113.75,109.75,2024,0,0,214.428604655907,13) , DELTA);
        Assert.assertEquals(30.6725158055428d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,31.5,122.5,120.5,2021,1,0,258.112235501023,30) , DELTA);
        Assert.assertEquals(31.94274086711d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.5909090909091,99.25,106.75,2022,1,0,254.629809323355,37) , DELTA);
        Assert.assertEquals(36.1886565307431d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,37.1607142857143,111.75,106.75,2022,1,0,269.328896656257,37) , DELTA);
        Assert.assertEquals(27.7194564211257d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.625,107,110.5,2022,1,0,256.087816089961,32) , DELTA);
        Assert.assertEquals(9.78904384742729d, MinutesGivenPlayedModel.getAverageGivenPlayed(6,9.45,122,114.5,2023,0,0,257.850650719721,13) , DELTA);
        Assert.assertEquals(9.05122430236818d, MinutesGivenPlayedModel.getAverageGivenPlayed(4,10.9230769230769,111.5,114,2023,0,0,223.293132744449,4) , DELTA);
        Assert.assertEquals(34.5314620426943d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.7647058823529,115.25,109.25,2023,1,0,256.154533045102,39) , DELTA);
        Assert.assertEquals(15.6768997981254d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,17.4782608695652,117.5,115,2023,0,0,224.787179487179,13) , DELTA);
        Assert.assertEquals(17.1297870219191d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,15.125,122.5,111,2024,0,0,251.283833144507,23) , DELTA);
        Assert.assertEquals(23.1164632953132d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23,115.75,111.75,2024,0,0,225,23) , DELTA);
        Assert.assertEquals(24.1154092223556d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.8571428571429,118.25,114.75,2024,0,0,242.771428571429,26) , DELTA);
        Assert.assertEquals(14.472605822239d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,17.5,105.75,112.75,2023,0,1,250,11) , DELTA);
        Assert.assertEquals(17.541088270911d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,21.6666666666667,120,127,2024,0,0,242.230108981639,16) , DELTA);
        Assert.assertEquals(19.7274538194321d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,24.5,120,118,2023,0,0,260.741765203853,20) , DELTA);
        Assert.assertEquals(23.1428575437452d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.9230769230769,114.5,125,2024,0,0,209.60425564249,21) , DELTA);
        Assert.assertEquals(34.6154247616776d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,33.0138888888889,111.75,114.25,2023,1,0,257.484908862495,39) , DELTA);
        Assert.assertEquals(26.7750979825585d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,26.6428571428571,108.75,105.25,2022,1,0,243.116758241758,22) , DELTA);
        Assert.assertEquals(26.0693188885393d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,25.1111111111111,122.25,114.25,2024,1,0,225.992740861162,23) , DELTA);
        Assert.assertEquals(14.582710435236d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,14.7777777777778,105.25,111.25,2022,0,0,243.070177104199,17) , DELTA);
        Assert.assertEquals(34.4446542637196d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,35.6842105263158,115.75,110.25,2023,1,0,256.115891202156,28) , DELTA);
        Assert.assertEquals(15.7914491085635d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,21.8,125.5,112.5,2024,0,0,265.518609022556,19) , DELTA);
        Assert.assertEquals(33.7372659842529d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,33.375,115.75,111.75,2023,1,0,247.122879864897,37) , DELTA);
        Assert.assertEquals(32.6687309617646d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,33.7894736842105,104.25,109.25,2022,1,0,256.293259061238,36) , DELTA);
        Assert.assertEquals(26.6554934835497d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,26.3272727272727,111.5,117.5,2023,1,0,263.414421785512,24) , DELTA);
        Assert.assertEquals(22.4509100111929d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,22.2222222222222,108,109.5,2024,0,1,243.387933634993,24) , DELTA);
        Assert.assertEquals(8.6937520432011d, MinutesGivenPlayedModel.getAverageGivenPlayed(5,8.33333333333333,107.25,112.25,2023,0,0,249.351981351981,6) , DELTA);
        Assert.assertEquals(24.9425822745382d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,25.7678571428571,118,108,2022,1,0,247.269268852837,16) , DELTA);
        Assert.assertEquals(33.5267699308754d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.4285714285714,105.75,117.25,2023,1,0,239.066666666667,39) , DELTA);
        Assert.assertEquals(30.231018548517d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,29.6521739130435,116.75,112.75,2023,1,0,257.296996711728,26) , DELTA);
        Assert.assertEquals(17.6846613433219d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,22.0980392156863,118.75,112.25,2023,0,0,242.243215592152,17) , DELTA);
        Assert.assertEquals(28.0805306870229d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,32.1739130434783,121,109,2023,1,0,257.571721149351,19) , DELTA);
        Assert.assertEquals(13.6681309179774d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,14.1363636363636,122.75,110.75,2024,0,0,250.465493278651,13) , DELTA);
        Assert.assertEquals(28.708831610381d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29,114.25,115.25,2024,1,0,240,29) , DELTA);
        Assert.assertEquals(22.0639569544081d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,25.2857142857143,105.25,111.25,2022,0,0,243.070177104199,26) , DELTA);
        Assert.assertEquals(23.3215179758361d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,13.9090909090909,106.5,119.5,2024,1,0,212.042173615858,14) , DELTA);
        Assert.assertEquals(32.5153385737285d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,28.5,108.75,115.75,2024,1,0,212.431143479525,35) , DELTA);
        Assert.assertEquals(32.7183979462208d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,33.2241379310345,111.5,106,2024,1,0,231.544229714589,32) , DELTA);
        Assert.assertEquals(17.4270574040543d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,16.955223880597,116.5,107,2024,0,0,202.719024328924,16) , DELTA);
        Assert.assertEquals(36.5380733894584d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,32,115,111.5,2023,1,1,235.076902483481,37) , DELTA);
        Assert.assertEquals(18.8861699156037d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,21.3809523809524,112.75,107.75,2024,0,0,222.494653299917,19) , DELTA);
        Assert.assertEquals(28.5665217953697d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,13.8947368421053,116.25,119.25,2024,1,0,191.081631161236,35) , DELTA);
        Assert.assertEquals(25.5239186083256d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,24.1666666666667,115.5,122,2023,1,0,250.791262100473,24) , DELTA);
        Assert.assertEquals(13.108911722046d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,9.29411764705882,113.75,108.75,2024,0,0,234.839358713423,8) , DELTA);
        Assert.assertEquals(17.3613508245435d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,16.6818181818182,109.25,117.75,2023,0,0,244.062588858234,16) , DELTA);
        Assert.assertEquals(42.5042046601021d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,36.1923076923077,111.75,115.25,2023,1,1,215.475789023423,35) , DELTA);
        Assert.assertEquals(32.6081402403031d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,30.9166666666667,112.75,116.75,2023,1,0,208.49060625365,36) , DELTA);
        Assert.assertEquals(17.5477421766212d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,12.9,110,114,2023,0,0,214.661496163683,20) , DELTA);
        Assert.assertEquals(19.8281748516452d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,24.7407407407407,108.75,106.75,2023,0,0,275.879929158443,23) , DELTA);
        Assert.assertEquals(24.2901861483439d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,22.0689655172414,123.75,110.25,2023,1,0,229.299880782686,21) , DELTA);
        Assert.assertEquals(17.9193665953885d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,19,113.75,110.25,2022,0,0,240,19) , DELTA);
        Assert.assertEquals(32.699182527911d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.925,114,119,2023,1,0,233.140760773275,37) , DELTA);
        Assert.assertEquals(37.1876540341503d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,38.7692307692308,106.5,109.5,2022,1,0,234.790334665335,40) , DELTA);
        Assert.assertEquals(20.7759434696684d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,25.2,112,107,2024,0,0,252.683200483092,19) , DELTA);
        Assert.assertEquals(15.7678441905644d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,14,122.5,124,2024,0,0,243.060606060606,17) , DELTA);
        Assert.assertEquals(22.1693481869136d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,18.8378378378378,120.75,118.25,2023,0,0,234.597271077937,17) , DELTA);
        Assert.assertEquals(27.573608587261d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,24,111,115,2024,1,0,253.176688311688,30) , DELTA);
        Assert.assertEquals(18.4691272715599d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,21.0357142857143,120.75,104.75,2023,0,0,249.439080968441,14) , DELTA);
        Assert.assertEquals(31.0786693873198d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,28.3714285714286,117.5,110,2023,1,0,214.598745331466,33) , DELTA);
        Assert.assertEquals(31.6113794679603d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.5857142857143,117.5,110,2024,1,0,243.041402942746,28) , DELTA);
        Assert.assertEquals(31.3377936016913d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,31.6666666666667,112.25,110.75,2024,1,0,247,32) , DELTA);
        Assert.assertEquals(36.0867821377078d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,35.6190476190476,119.75,121.25,2024,1,0,196.979390175042,39) , DELTA);
        Assert.assertEquals(14.773277843729d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,11.4642857142857,119.5,117.5,2024,0,0,244.508588043313,16) , DELTA);
        Assert.assertEquals(17.882339986062d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,20.6296296296296,120.25,114.75,2023,0,0,243.611376371308,23) , DELTA);
        Assert.assertEquals(17.9987460063324d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,19.4722222222222,119.25,113.75,2023,0,0,266.117561893566,23) , DELTA);
        Assert.assertEquals(30.1368637213116d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,24.6530612244898,119.5,122,2024,1,0,216.524476833875,33) , DELTA);
          }

    @Test
    public void name3() {
        System.out.println( MinutesGivenPlayedModel.getAverageGivenPlayed(38,36.6530612244898,124.5,124.5,2024,1,0,240,38));
    }
}