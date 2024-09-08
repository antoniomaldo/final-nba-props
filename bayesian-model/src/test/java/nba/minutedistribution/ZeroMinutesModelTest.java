package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroMinutesModelTest {

    public static final double DELTA = 0.00001;

    @Test
    public void name() {

        Assert.assertEquals(0.0933670829846123d, ZeroMinutesModel.zeroMinutesProb(13,0,0) , DELTA);
        Assert.assertEquals(3.70976134174789e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.00280307625084918d, ZeroMinutesModel.zeroMinutesProb(30,0,0) , DELTA);
        Assert.assertEquals(0.022843578915811d, ZeroMinutesModel.zeroMinutesProb(20,0,0) , DELTA);
        Assert.assertEquals(3.00158995896278e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(1.06984043454919e-09d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(0.162775192686446d, ZeroMinutesModel.zeroMinutesProb(10,0,0) , DELTA);
        Assert.assertEquals(0.0517263877500605d, ZeroMinutesModel.zeroMinutesProb(16,0,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.566775238251119d, ZeroMinutesModel.zeroMinutesProb(1,0,0) , DELTA);
        Assert.assertEquals(0.00991955317593107d, ZeroMinutesModel.zeroMinutesProb(24,0,0) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(1.32225012059279e-09d, ZeroMinutesModel.zeroMinutesProb(27,1,0) , DELTA);
        Assert.assertEquals(0.162775192686446d, ZeroMinutesModel.zeroMinutesProb(10,0,0) , DELTA);
        Assert.assertEquals(0.0280817645580807d, ZeroMinutesModel.zeroMinutesProb(19,0,0) , DELTA);
        Assert.assertEquals(0.0122312687196598d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(0.228981397353562d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(1.06984043454919e-09d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(2.42860428250068e-10d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(1.58989169851577e-10d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.00527884799927765d, ZeroMinutesModel.zeroMinutesProb(27,0,0) , DELTA);
        Assert.assertEquals(3.00158995896278e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(8.65614256724838e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(1.58989169851577e-10d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.0422695079848443d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(8.65614256724838e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(0.0631595091040247d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(0.0185638268647484d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.013207830969377d, ZeroMinutesModel.zeroMinutesProb(27,0,1) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.268499996649052d, ZeroMinutesModel.zeroMinutesProb(7,0,0) , DELTA);
        Assert.assertEquals(0.0769147302112503d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.00804119613147503d, ZeroMinutesModel.zeroMinutesProb(25,0,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(1.58989169851577e-10d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.0769147302112503d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.514216350896212d, ZeroMinutesModel.zeroMinutesProb(2,0,0) , DELTA);
        Assert.assertEquals(0.0344787189381256d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(2.0197742440167e-09d, ZeroMinutesModel.zeroMinutesProb(25,1,0) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(7.00373641917747e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0344787189381256d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(0.0933670829846123d, ZeroMinutesModel.zeroMinutesProb(13,0,0) , DELTA);
        Assert.assertEquals(0.461341142845552d, ZeroMinutesModel.zeroMinutesProb(3,0,0) , DELTA);
        Assert.assertEquals(0.112908199085293d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.112908199085293d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.0631595091040247d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(0.112908199085293d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.0185638268647484d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(0.0422695079848443d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(1.95766639594475e-09d, ZeroMinutesModel.zeroMinutesProb(30,1,1) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.0769147302112503d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.0422695079848443d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(0.0631595091040247d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(3.70976134174789e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(1.32225012059279e-09d, ZeroMinutesModel.zeroMinutesProb(27,1,0) , DELTA);
        Assert.assertEquals(0.0185638268647484d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(0.00427545568235137d, ZeroMinutesModel.zeroMinutesProb(28,0,0) , DELTA);
        Assert.assertEquals(1.32225012059279e-09d, ZeroMinutesModel.zeroMinutesProb(27,1,0) , DELTA);
        Assert.assertEquals(0.00183681878886229d, ZeroMinutesModel.zeroMinutesProb(32,0,0) , DELTA);
        Assert.assertEquals(0.0517263877500605d, ZeroMinutesModel.zeroMinutesProb(16,0,0) , DELTA);
        Assert.assertEquals(0.135925979221643d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(0.228981397353562d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(0.0631595091040247d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(0.00280307625084918d, ZeroMinutesModel.zeroMinutesProb(30,0,0) , DELTA);
        Assert.assertEquals(0.0933670829846123d, ZeroMinutesModel.zeroMinutesProb(13,0,0) , DELTA);
        Assert.assertEquals(1.06984043454919e-09d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(2.42860428250068e-10d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(1.06984043454919e-09d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(0.312078298612626d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(1.63917142747212e-09d, ZeroMinutesModel.zeroMinutesProb(31,1,1) , DELTA);
        Assert.assertEquals(3.70976134174789e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0422695079848443d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(5.66676477965616e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.35925583302648d, ZeroMinutesModel.zeroMinutesProb(5,0,0) , DELTA);
        Assert.assertEquals(0.0185638268647484d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(0.193738761338306d, ZeroMinutesModel.zeroMinutesProb(9,0,0) , DELTA);
        Assert.assertEquals(0.268499996649052d, ZeroMinutesModel.zeroMinutesProb(7,0,0) , DELTA);
        Assert.assertEquals(2.0197742440167e-09d, ZeroMinutesModel.zeroMinutesProb(25,1,0) , DELTA);
        Assert.assertEquals(0.0185638268647484d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(0.0150735190852449d, ZeroMinutesModel.zeroMinutesProb(22,0,0) , DELTA);
        Assert.assertEquals(0.268499996649052d, ZeroMinutesModel.zeroMinutesProb(7,0,0) , DELTA);
        Assert.assertEquals(7.00373641917747e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(5.66676477965616e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(0.312078298612626d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(3.00158995896278e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(1.96499816482404e-10d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(3.00158995896278e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(4.58501307662051e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0422695079848443d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(0.0344787189381256d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);                 }
}