package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinutesGivenPlayedModelTest {

    public static final double DELTA = 0.0001d;

    @Test
    public void name() {
        Assert.assertEquals(28.9930861603554d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,26.28571,115.5,117,2024,1,0,237.58332,39) , DELTA);
        Assert.assertEquals(33.2157778908595d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,34.28,114.75,123.25,2023,1,0,209.289713,33) , DELTA);
        Assert.assertEquals(33.1983674554945d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,33.95349,109.25,107.25,2022,1,0,241.821,34) , DELTA);
        Assert.assertEquals(26.1419976493279d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,20.7551,118.25,107.75,2022,1,0,225.93626,24) , DELTA);
        Assert.assertEquals(14.4076709797645d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,10.33333,116,99.5,2024,0,0,222.35854,12) , DELTA);
        Assert.assertEquals(24.4551481915022d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,23,121,111.5,2025,1,0,237,23) , DELTA);
        Assert.assertEquals(15.6313722959227d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,11.63636,115.75,123.25,2024,0,0,218.94404,18) , DELTA);
        Assert.assertEquals(14.553547860154d, MinutesGivenPlayedModel.getAverageGivenPlayed(11,18.2,112.25,119.25,2024,0,0,242.878794,19) , DELTA);
        Assert.assertEquals(12.0346840209632d, MinutesGivenPlayedModel.getAverageGivenPlayed(10,15.13793,106.75,111.75,2022,0,0,251.17877,9) , DELTA);
        Assert.assertEquals(32.6607640977137d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,30.6,113,117.5,2023,1,1,238.45151,25) , DELTA);
        Assert.assertEquals(36.2886530339229d, MinutesGivenPlayedModel.getAverageGivenPlayed(38,36.45455,110.75,103.75,2023,1,0,199.205563,38) , DELTA);
        Assert.assertEquals(34.6059303283053d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.25,126.5,122.5,2024,1,0,228.30808,37) , DELTA);
        Assert.assertEquals(32.4658217617523d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,29.22951,111,115.5,2023,1,1,262.00606,29) , DELTA);
        Assert.assertEquals(25.7446257845868d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,25.18182,122.75,116.75,2024,1,0,223.54621,19) , DELTA);
        Assert.assertEquals(23.0324669098654d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,14.52778,111,114,2023,1,0,244.91875,25) , DELTA);
        Assert.assertEquals(32.9765544909095d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,28.11111,115,117.5,2023,1,0,230.45799,33) , DELTA);
        Assert.assertEquals(33.0276011067733d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,30.6,119.75,114.25,2023,1,0,210.415013,32) , DELTA);
        Assert.assertEquals(26.2639114916576d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,25.47059,119.25,113.25,2024,1,0,243.491529,26) , DELTA);
        Assert.assertEquals(18.9068123915024d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,22.11765,119.75,114.75,2024,0,0,250.94569,17) , DELTA);
        Assert.assertEquals(14.2249391762376d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,11.35714,114.5,115.5,2023,0,0,250.72801,17) , DELTA);
        Assert.assertEquals(22.5892398100835d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,23.23077,109.5,95.5,2022,1,0,284.40304,22) , DELTA);
        Assert.assertEquals(34.4547551093107d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34.33333,111,118.5,2023,1,0,224.86145,38) , DELTA);
        Assert.assertEquals(39.4993575090882d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,34,111,103.5,2022,1,1,253.981668,33) , DELTA);
        Assert.assertEquals(32.3396541995456d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31,117,112,2023,1,0,240.01,40) , DELTA);
        Assert.assertEquals(12.9045706956799d, MinutesGivenPlayedModel.getAverageGivenPlayed(12,16.3125,120,115,2024,0,0,278.41125,14) , DELTA);
        Assert.assertEquals(42.2249867885284d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,34,111.5,115,2023,1,1,214.27198,44) , DELTA);
        Assert.assertEquals(21.7724401086086d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,30,105.75,111.25,2023,0,0,271.63909,19) , DELTA);
        Assert.assertEquals(24.1195692375266d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,25.36667,117.25,113.25,2024,0,0,236.1543,28) , DELTA);
        Assert.assertEquals(27.8289872669494d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,18.5,105.75,117.25,2022,1,0,222.88108,31) , DELTA);
        Assert.assertEquals(30.0379801240056d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,27.59091,117.75,107.75,2023,1,0,250.58643,44) , DELTA);
        Assert.assertEquals(15.5926047813919d, MinutesGivenPlayedModel.getAverageGivenPlayed(16,15,113.25,118.25,2025,0,0,237.00001,16) , DELTA);
        Assert.assertEquals(25.5061057020765d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,26.5122,114.5,109.5,2024,1,0,237.9602,20) , DELTA);
        Assert.assertEquals(16.2687388061008d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,19,114.75,111.75,2024,0,0,249.90758,14) , DELTA);
        Assert.assertEquals(36.6056021295797d, MinutesGivenPlayedModel.getAverageGivenPlayed(40,31.77778,107.25,101.75,2024,1,0,254.36436,43) , DELTA);
        Assert.assertEquals(34.3339303516588d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,35.09091,109.5,108.5,2023,1,0,309.40478,36) , DELTA);
        Assert.assertEquals(19.9934459911643d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,22.05714,121.25,110.75,2024,0,0,240.60903,25) , DELTA);
        Assert.assertEquals(10.7221863309074d, MinutesGivenPlayedModel.getAverageGivenPlayed(7,13.57143,115.25,118.75,2023,0,0,256.09767,12) , DELTA);
        Assert.assertEquals(31.0724842376045d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,29.09091,111.25,112.25,2023,1,0,244.12516,28) , DELTA);
        Assert.assertEquals(10.3543245211768d, MinutesGivenPlayedModel.getAverageGivenPlayed(8,7.5,122,109,2025,0,0,247.5,9) , DELTA);
        Assert.assertEquals(15.5812608662603d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,19,118.25,119.25,2023,0,0,243.94927,15) , DELTA);
        Assert.assertEquals(19.2876677632842d, MinutesGivenPlayedModel.getAverageGivenPlayed(21,12.5,110.25,123.25,2024,0,0,209.83334,19) , DELTA);
        Assert.assertEquals(33.2177782744809d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,32.81818,123.5,116.5,2024,1,0,247.335111,29) , DELTA);
        Assert.assertEquals(33.3316512884202d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,29.52273,112.75,115.25,2023,1,0,212.848723,35) , DELTA);
        Assert.assertEquals(35.5084819240881d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,38.33333,109.25,116.75,2024,1,0,245.5,40) , DELTA);
        Assert.assertEquals(26.0844744959088d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,22.5,116.5,111,2023,1,0,236.583333,19) , DELTA);
        Assert.assertEquals(31.6861960803231d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,32.75,107.75,111.75,2025,1,0,236.5,32) , DELTA);
        Assert.assertEquals(29.3553322021615d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,30.33333,113.5,105.5,2025,1,0,249.333323,35) , DELTA);
        Assert.assertEquals(26.8825311301368d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,27.66667,116.25,110.75,2024,1,0,253.28012,25) , DELTA);
        Assert.assertEquals(26.1328734823932d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,23.375,121,109,2023,1,0,257.57171,26) , DELTA);
        Assert.assertEquals(18.3019736462265d, MinutesGivenPlayedModel.getAverageGivenPlayed(20,19,117,118.5,2024,0,0,251.42858,19) , DELTA);
        Assert.assertEquals(15.7721123900883d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,16.33333,123.25,110.25,2024,0,0,241.16667,25) , DELTA);
        Assert.assertEquals(29.808319108371d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,31,113,119.5,2023,1,0,252.43133,25) , DELTA);
        Assert.assertEquals(33.1007204232958d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,34.95455,113.75,104.75,2023,1,0,267.73984,37) , DELTA);
        Assert.assertEquals(28.5409712021027d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,26.25,108.5,118.5,2023,1,0,263.00507,27) , DELTA);
        Assert.assertEquals(32.2997792791274d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,33.24359,120,102,2022,1,0,222.18855,36) , DELTA);
        Assert.assertEquals(29.9382087789867d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,30,109.25,117.75,2023,1,0,244.11052,33) , DELTA);
        Assert.assertEquals(31.4108903409649d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,32.6,116,112,2023,1,0,263.377773,33) , DELTA);
        Assert.assertEquals(33.7189058107955d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,34.75,108.5,106,2023,1,0,254.89867,36) , DELTA);
        Assert.assertEquals(25.6071968741007d, MinutesGivenPlayedModel.getAverageGivenPlayed(28,28,111,109.5,2023,0,0,229,28) , DELTA);
        Assert.assertEquals(33.8085477824779d, MinutesGivenPlayedModel.getAverageGivenPlayed(36,29.82927,115,120.5,2023,1,0,209.89447,40) , DELTA);
        Assert.assertEquals(34.1201697649593d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,34.20833,105.25,102.25,2024,1,0,234.55211,37) , DELTA);
        Assert.assertEquals(25.8570956688549d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,23,113.5,119,2023,1,0,268.389843,9) , DELTA);
        Assert.assertEquals(15.981740222718d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,16,115,111,2024,0,0,205.973739,16) , DELTA);
        Assert.assertEquals(22.8764641420005d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,26.35714,110.75,119.25,2023,0,0,240.90861,22) , DELTA);
        Assert.assertEquals(17.7232782763053d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,13.34043,102.25,106.25,2024,0,0,217.88095,22) , DELTA);
        Assert.assertEquals(26.9334504874631d, MinutesGivenPlayedModel.getAverageGivenPlayed(31,30.16667,118.5,111,2024,0,0,244.35871,29) , DELTA);
        Assert.assertEquals(23.8285247604d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,29.37931,106.75,110.25,2024,0,0,246.51759,25) , DELTA);
        Assert.assertEquals(35.3583847467083d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,33.96364,112.75,110.25,2024,1,0,228.66355,38) , DELTA);
        Assert.assertEquals(15.2973698821245d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,19.10526,123.25,117.75,2023,0,1,248.42732,12) , DELTA);
        Assert.assertEquals(14.3139283872842d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,9.173913,114.5,110.5,2023,0,1,205.172495,15) , DELTA);
        Assert.assertEquals(24.5101542990651d, MinutesGivenPlayedModel.getAverageGivenPlayed(27,26.40909,119,122.5,2024,0,0,241.58915,31) , DELTA);
        Assert.assertEquals(21.3674884472331d, MinutesGivenPlayedModel.getAverageGivenPlayed(23,20.5,111.75,108.75,2024,0,0,214.5,22) , DELTA);
        Assert.assertEquals(17.9185916334119d, MinutesGivenPlayedModel.getAverageGivenPlayed(19,20.31579,122.5,106.5,2022,0,0,241.62472,14) , DELTA);
        Assert.assertEquals(15.4091975312572d, MinutesGivenPlayedModel.getAverageGivenPlayed(15,13.75,113.75,106.25,2024,0,0,211.206218,12) , DELTA);
        Assert.assertEquals(25.883826674477d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,23.7619,116,114.5,2023,1,0,279.47901,30) , DELTA);
        Assert.assertEquals(31.8381803260941d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,31.54545,113.75,117.75,2023,1,0,235.408799,21) , DELTA);
        Assert.assertEquals(32.8056836983544d, MinutesGivenPlayedModel.getAverageGivenPlayed(34,30.375,112,109,2024,1,0,212.16865,35) , DELTA);
        Assert.assertEquals(25.0482198180421d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,19.7,117,125,2023,1,0,194.23599,18) , DELTA);
        Assert.assertEquals(31.0281023211813d, MinutesGivenPlayedModel.getAverageGivenPlayed(32,30.8,123,118,2024,1,0,237.56667,37) , DELTA);
        Assert.assertEquals(17.7148592890473d, MinutesGivenPlayedModel.getAverageGivenPlayed(18,21.27778,119,111,2022,0,0,281.71186,23) , DELTA);
        Assert.assertEquals(25.9350388892888d, MinutesGivenPlayedModel.getAverageGivenPlayed(24,23.58065,115,116,2024,1,0,242.0708,32) , DELTA);
        Assert.assertEquals(25.6047467464747d, MinutesGivenPlayedModel.getAverageGivenPlayed(25,23.66667,118.25,115.75,2023,1,0,255.66667,28) , DELTA);
        Assert.assertEquals(20.746495113881d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,17.95122,126,123.5,2024,1,0,243.22897,16) , DELTA);
        Assert.assertEquals(33.3260829960795d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,33.5,108.5,114.5,2025,1,0,232.5,29) , DELTA);
        Assert.assertEquals(33.9450001225077d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,34.57143,104,109,2022,1,0,256.29325,34) , DELTA);
        Assert.assertEquals(13.8884138031573d, MinutesGivenPlayedModel.getAverageGivenPlayed(14,15.56452,118,113.5,2022,0,0,244.331357,10) , DELTA);
        Assert.assertEquals(35.5403309825446d, MinutesGivenPlayedModel.getAverageGivenPlayed(38,33.48837,120.25,117.25,2023,1,0,227.54946,33) , DELTA);
        Assert.assertEquals(14.3794797748195d, MinutesGivenPlayedModel.getAverageGivenPlayed(13,11.48148,108,115.5,2023,0,0,201.940685,10) , DELTA);
        Assert.assertEquals(39.6638508135025d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,33.59524,116.25,109.75,2023,1,1,240.35531,37) , DELTA);
        Assert.assertEquals(21.1443776050627d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,20.20833,117.25,120.25,2023,0,0,251.45808,36) , DELTA);
        Assert.assertEquals(31.2080504605435d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,32.54286,127,120,2024,1,0,244.32664,27) , DELTA);
        Assert.assertEquals(28.5237462728272d, MinutesGivenPlayedModel.getAverageGivenPlayed(29,28.67568,122.75,113.75,2023,1,0,225.79284,26) , DELTA);
        Assert.assertEquals(15.8369155282882d, MinutesGivenPlayedModel.getAverageGivenPlayed(17,18.25,111.75,115.25,2024,0,0,244.740518,10) , DELTA);
        Assert.assertEquals(31.4483010791499d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,31.29688,121.5,118.5,2024,1,0,250.69189,29) , DELTA);
        Assert.assertEquals(37.6596585311822d, MinutesGivenPlayedModel.getAverageGivenPlayed(33,33.84211,105.25,114.75,2024,1,1,239.06481,30) , DELTA);
        Assert.assertEquals(19.1575279452558d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,13.25,121.25,110.75,2024,0,0,240.60903,26) , DELTA);
        Assert.assertEquals(31.7037987471054d, MinutesGivenPlayedModel.getAverageGivenPlayed(35,26,111.75,112.75,2022,1,0,232,26) , DELTA);
        Assert.assertEquals(34.0592231364462d, MinutesGivenPlayedModel.getAverageGivenPlayed(37,35.09091,116.5,107.5,2023,1,0,239.151493,30) , DELTA);
        Assert.assertEquals(19.5312777413608d, MinutesGivenPlayedModel.getAverageGivenPlayed(22,21,108.75,105.75,2022,0,0,243.91974,17) , DELTA);
        Assert.assertEquals(25.8376479317731d, MinutesGivenPlayedModel.getAverageGivenPlayed(26,24.01818,110.75,119.75,2023,1,0,233.38362,20) , DELTA);
           }
    @Test
    public void name3() {
        System.out.println( MinutesGivenPlayedModel.getAverageGivenPlayed(38,36.6530612244898,124.5,124.5,2024,1,0,240,38));
    }
}