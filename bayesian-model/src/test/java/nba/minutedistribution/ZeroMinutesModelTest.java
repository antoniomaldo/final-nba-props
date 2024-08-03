package nba.minutedistribution;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroMinutesModelTest {

    public static final double DELTA = 0.00001;

    @Test
    public void name() {
        Assert.assertEquals(1.31971821007183e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0208400280855461d, ZeroMinutesModel.zeroMinutesProb(20,0,0) , DELTA);
        Assert.assertEquals(0.0037429216370525d, ZeroMinutesModel.zeroMinutesProb(28,0,0) , DELTA);
        Assert.assertEquals(3.14111528615185e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(0.156841363504441d, ZeroMinutesModel.zeroMinutesProb(10,0,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(0.222987554583493d, ZeroMinutesModel.zeroMinutesProb(8,0,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(2.03602235823449e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.0724883203115118d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0724883203115118d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.0317917731419067d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(2.03602235823449e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(3.90152633778221e-10d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(0.130254527092933d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(7.76054045026686e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,1) , DELTA);
        Assert.assertEquals(1.31971821007183e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0037429216370525d, ZeroMinutesModel.zeroMinutesProb(28,0,0) , DELTA);
        Assert.assertEquals(5.71083817418066e-08d, ZeroMinutesModel.zeroMinutesProb(5,1,0) , DELTA);
        Assert.assertEquals(1.15342105917379e-09d, ZeroMinutesModel.zeroMinutesProb(23,1,0) , DELTA);
        Assert.assertEquals(0.00157598350174106d, ZeroMinutesModel.zeroMinutesProb(32,0,0) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(0.0150858555805773d, ZeroMinutesModel.zeroMinutesProb(26,0,1) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(1.31971821007183e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0391864367112756d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.262783620772905d, ZeroMinutesModel.zeroMinutesProb(7,0,0) , DELTA);
        Assert.assertEquals(0.0168467145330973d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(3.59399285024138e-11d, ZeroMinutesModel.zeroMinutesProb(39,1,0) , DELTA);
        Assert.assertEquals(2.03602235823449e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(0.306876977388094d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0317917731419067d, ZeroMinutesModel.zeroMinutesProb(18,0,0) , DELTA);
        Assert.assertEquals(3.90152633778221e-10d, ZeroMinutesModel.zeroMinutesProb(28,1,0) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(1.31971821007183e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.45899460429126d, ZeroMinutesModel.zeroMinutesProb(3,0,0) , DELTA);
        Assert.assertEquals(0.0208400280855461d, ZeroMinutesModel.zeroMinutesProb(20,0,0) , DELTA);
        Assert.assertEquals(2.03602235823449e-10d, ZeroMinutesModel.zeroMinutesProb(31,1,0) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.00464481120496579d, ZeroMinutesModel.zeroMinutesProb(27,0,0) , DELTA);
        Assert.assertEquals(0.0304383993794972d, ZeroMinutesModel.zeroMinutesProb(22,0,1) , DELTA);
        Assert.assertEquals(1.06250392384059e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(0.107599397083968d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.0136079553672465d, ZeroMinutesModel.zeroMinutesProb(22,0,0) , DELTA);
        Assert.assertEquals(0.0150858555805773d, ZeroMinutesModel.zeroMinutesProb(26,0,1) , DELTA);
        Assert.assertEquals(0.0391864367112756d, ZeroMinutesModel.zeroMinutesProb(17,0,0) , DELTA);
        Assert.assertEquals(1.06250392384059e-10d, ZeroMinutesModel.zeroMinutesProb(34,1,0) , DELTA);
        Assert.assertEquals(0.0208400280855461d, ZeroMinutesModel.zeroMinutesProb(20,0,0) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(0.0168467145330973d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(2.52890904393053e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.513096784527009d, ZeroMinutesModel.zeroMinutesProb(2,0,0) , DELTA);
        Assert.assertEquals(0.00242930287809301d, ZeroMinutesModel.zeroMinutesProb(30,0,0) , DELTA);
        Assert.assertEquals(0.130254527092933d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(2.52890904393053e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0843505452539699d, ZeroMinutesModel.zeroMinutesProb(16,0,1) , DELTA);
        Assert.assertEquals(0.306876977388094d, ZeroMinutesModel.zeroMinutesProb(6,0,0) , DELTA);
        Assert.assertEquals(0.0724883203115118d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.0037429216370525d, ZeroMinutesModel.zeroMinutesProb(28,0,0) , DELTA);
        Assert.assertEquals(1.31971821007183e-10d, ZeroMinutesModel.zeroMinutesProb(33,1,0) , DELTA);
        Assert.assertEquals(0.0724883203115118d, ZeroMinutesModel.zeroMinutesProb(14,0,0) , DELTA);
        Assert.assertEquals(0.0037429216370525d, ZeroMinutesModel.zeroMinutesProb(28,0,0) , DELTA);
        Assert.assertEquals(4.46403792493086e-11d, ZeroMinutesModel.zeroMinutesProb(38,1,0) , DELTA);
        Assert.assertEquals(6.01915984278173e-10d, ZeroMinutesModel.zeroMinutesProb(26,1,0) , DELTA);
        Assert.assertEquals(0.0304383993794972d, ZeroMinutesModel.zeroMinutesProb(22,0,1) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.00886289727603837d, ZeroMinutesModel.zeroMinutesProb(24,0,0) , DELTA);
        Assert.assertEquals(6.8869861416113e-11d, ZeroMinutesModel.zeroMinutesProb(36,1,0) , DELTA);
        Assert.assertEquals(0.398649994452264d, ZeroMinutesModel.zeroMinutesProb(5,0,1) , DELTA);
        Assert.assertEquals(1.63919973837731e-10d, ZeroMinutesModel.zeroMinutesProb(32,1,0) , DELTA);
        Assert.assertEquals(3.14111528615185e-10d, ZeroMinutesModel.zeroMinutesProb(29,1,0) , DELTA);
        Assert.assertEquals(2.52890904393053e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.45899460429126d, ZeroMinutesModel.zeroMinutesProb(3,0,0) , DELTA);
        Assert.assertEquals(0.0591966124844413d, ZeroMinutesModel.zeroMinutesProb(15,0,0) , DELTA);
        Assert.assertEquals(0.00576276186159755d, ZeroMinutesModel.zeroMinutesProb(26,0,0) , DELTA);
        Assert.assertEquals(2.52890904393053e-10d, ZeroMinutesModel.zeroMinutesProb(30,1,0) , DELTA);
        Assert.assertEquals(0.0109848865678278d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(0.130254527092933d, ZeroMinutesModel.zeroMinutesProb(11,0,0) , DELTA);
        Assert.assertEquals(0.0257551124814061d, ZeroMinutesModel.zeroMinutesProb(19,0,0) , DELTA);
        Assert.assertEquals(0.0482154227479048d, ZeroMinutesModel.zeroMinutesProb(16,0,0) , DELTA);
        Assert.assertEquals(0.0168467145330973d, ZeroMinutesModel.zeroMinutesProb(21,0,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.00576276186159755d, ZeroMinutesModel.zeroMinutesProb(26,0,0) , DELTA);
        Assert.assertEquals(0.107599397083968d, ZeroMinutesModel.zeroMinutesProb(12,0,0) , DELTA);
        Assert.assertEquals(0.0109848865678278d, ZeroMinutesModel.zeroMinutesProb(23,0,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
        Assert.assertEquals(5.54470624331954e-11d, ZeroMinutesModel.zeroMinutesProb(37,1,0) , DELTA);
        Assert.assertEquals(0.00886289727603837d, ZeroMinutesModel.zeroMinutesProb(24,0,0) , DELTA);
        Assert.assertEquals(8.55420937254127e-11d, ZeroMinutesModel.zeroMinutesProb(35,1,0) , DELTA);
                     }
}