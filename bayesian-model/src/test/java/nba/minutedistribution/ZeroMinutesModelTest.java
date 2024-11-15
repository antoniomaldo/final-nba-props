package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroMinutesModelTest {

    public static final double DELTA = 0.00001;

    @Test
    public void name() {
        Assert.assertEquals(0.301398387809229d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(0.0566170760390828d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(3.62752970487097e-11d, ZeroMinutesModel.zeroMinutesProb(39,1,0) , DELTA);
        Assert.assertEquals(5.03284472204416e-10d, ZeroMinutesModel.zeroMinutesProb(27,1,0) , DELTA);
        Assert.assertEquals(0.0158568322054002d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(5.62312929819461e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(2.09449509081068e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(0.00666072603215688d, ZeroMinutesModel.zeroMinutesProb(25,0,0) , DELTA);
        Assert.assertEquals(0.00535682826363778d, ZeroMinutesModel.zeroMinutesProb(26,0,0) , DELTA);
        Assert.assertEquals(0.103803780763037d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(1.35117703259548e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.563454350668388d, ZeroMinutesModel.zeroMinutesProb(1,0,0) , DELTA);
        Assert.assertEquals(0.00430707360394395d, ZeroMinutesModel.zeroMinutesProb(27,0,0) , DELTA);
        Assert.assertEquals(0.00666072603215688d, ZeroMinutesModel.zeroMinutesProb(25,0,0) , DELTA);
        Assert.assertEquals(0.0459865043419067d, ZeroMinutesModel.zeroMinutesProb(16,0,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(1.35117703259548e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0158568322054002d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(0.217723196403705d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(1.08524728567433e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(0.0243675287580286d, ZeroMinutesModel.zeroMinutesProb(19,0,0) , DELTA);
        Assert.assertEquals(2.6077316192124e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0127758665300873d, ZeroMinutesModel.zeroMinutesProb(22,0,0) , DELTA);
        Assert.assertEquals(1.08524728567433e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(0.509005314873716d, ZeroMinutesModel.zeroMinutesProb(2,0,0) , DELTA);
        Assert.assertEquals(4.04231343669515e-10d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(1.6822703889715e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0196659878022271d, ZeroMinutesModel.zeroMinutesProb(20,0,0) , DELTA);
        Assert.assertEquals(1.35117703259548e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0243675287580286d, ZeroMinutesModel.zeroMinutesProb(19,0,0) , DELTA);
        Assert.assertEquals(2.6077316192124e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(3.24673198213251e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(0.301398387809229d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(8.86751080125582e-09d, ZeroMinutesModel.zeroMinutesProb(17,1,1) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.0695259955536944d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.0695259955536944d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(4.51642209758739e-11d, ZeroMinutesModel.zeroMinutesProb(38,1,0) , DELTA);
        Assert.assertEquals(0.00430707360394395d, ZeroMinutesModel.zeroMinutesProb(27,0,0) , DELTA);
        Assert.assertEquals(5.62312929819461e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(3.24673198213251e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(0.00430707360394395d, ZeroMinutesModel.zeroMinutesProb(27,0,0) , DELTA);
        Assert.assertEquals(0.115453492333223d, ZeroMinutesModel.zeroMinutesProb(14,0,1) , DELTA);
        Assert.assertEquals(1.87462674096394e-09d, ZeroMinutesModel.zeroMinutesProb(21,1,0) , DELTA);
        Assert.assertEquals(1.08524728567433e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(2.09449509081068e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(0.0566170760390828d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(3.24673198213251e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(5.62312929819461e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.217723196403705d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(1.35117703259548e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(2.6077316192124e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0695259955536944d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.217723196403705d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(0.00223631987827688d, ZeroMinutesModel.zeroMinutesProb(30,0,0) , DELTA);
        Assert.assertEquals(0.103803780763037d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.0708183334473812d, ZeroMinutesModel.zeroMinutesProb(17,0,1) , DELTA);
        Assert.assertEquals(1.6822703889715e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0102872717405663d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(0.0301584874731373d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(1.08524728567433e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(0.45434178383577d, ZeroMinutesModel.zeroMinutesProb(3,0,0) , DELTA);
        Assert.assertEquals(0.349445039710596d, ZeroMinutesModel.zeroMinutesProb(5,0,0) , DELTA);
        Assert.assertEquals(0.00179697307325333d, ZeroMinutesModel.zeroMinutesProb(31,0,0) , DELTA);
        Assert.assertEquals(0.0459865043419067d, ZeroMinutesModel.zeroMinutesProb(16,0,0) , DELTA);
        Assert.assertEquals(0.0102872717405663d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(4.04231343669515e-10d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.00535682826363778d, ZeroMinutesModel.zeroMinutesProb(26,0,0) , DELTA);
        Assert.assertEquals(1.50567507728591e-09d, ZeroMinutesModel.zeroMinutesProb(22,1,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.45434178383577d, ZeroMinutesModel.zeroMinutesProb(3,0,0) , DELTA);
        Assert.assertEquals(0.0301584874731373d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(2.09449509081068e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(5.62312929819461e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(4.51642209758739e-11d, ZeroMinutesModel.zeroMinutesProb(38,1,0) , DELTA);
        Assert.assertEquals(5.62312929819461e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.0851126550739253d, ZeroMinutesModel.zeroMinutesProb(13,0,0) , DELTA);
        Assert.assertEquals(1.6822703889715e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0851126550739253d, ZeroMinutesModel.zeroMinutesProb(13,0,0) , DELTA);
        Assert.assertEquals(7.80154510230993e-10d, ZeroMinutesModel.zeroMinutesProb(25,1,0) , DELTA);
        Assert.assertEquals(2.6077316192124e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0243675287580286d, ZeroMinutesModel.zeroMinutesProb(19,0,0) , DELTA);
        Assert.assertEquals(0.126034108598564d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(0.126034108598564d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(2.09449509081068e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(0.0158568322054002d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(5.03284472204416e-10d, ZeroMinutesModel.zeroMinutesProb(27,1,0) , DELTA);
        Assert.assertEquals(0.0566170760390828d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(8.69359948003948e-09d, ZeroMinutesModel.zeroMinutesProb(14,1,0) , DELTA);
        Assert.assertEquals(0.00278278431478648d, ZeroMinutesModel.zeroMinutesProb(29,0,0) , DELTA);
        Assert.assertEquals(0.0301584874731373d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.0695259955536944d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(1.35117703259548e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(7.00102479815154e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.0102872717405663d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(4.04231343669515e-10d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(1.6822703889715e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);   }}