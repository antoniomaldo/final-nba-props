package nba.bayesianmodel.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoPercModelGivenOwnExpTest {

    private static final double DELTA = 0.000001d;

    @Test
    public void name() {
        Assert.assertEquals(0.542942289725059d, TwoPercModelGivenOwnExp.adjustRate(0.524460014338546,11.5095499999995,16,117.25,31,11) , DELTA);
        Assert.assertEquals(0.481730576267548d, TwoPercModelGivenOwnExp.adjustRate(0.482263148574938,4.32622499999974,1,122,13,0) , DELTA);
        Assert.assertEquals(0.508401969640255d, TwoPercModelGivenOwnExp.adjustRate(0.517735568127143,17.5007999999994,11,115.75,32,4) , DELTA);
        Assert.assertEquals(0.520781241373359d, TwoPercModelGivenOwnExp.adjustRate(0.47433481141148,10.6277249999995,15,122.75,29,6) , DELTA);
        Assert.assertEquals(0.528236115831882d, TwoPercModelGivenOwnExp.adjustRate(0.538994879071815,17.4720249999994,18,113.75,33,5) , DELTA);
        Assert.assertEquals(0.540896912255105d, TwoPercModelGivenOwnExp.adjustRate(0.568130919069187,16.8340999999994,12,112,39,3) , DELTA);
        Assert.assertEquals(0.610935072673295d, TwoPercModelGivenOwnExp.adjustRate(0.561433251946535,5.95532499999969,9,120,21,1) , DELTA);
        Assert.assertEquals(0.588712171073914d, TwoPercModelGivenOwnExp.adjustRate(0.608053426782321,14.2330999999995,18,112.25,31,1) , DELTA);
        Assert.assertEquals(0.453870652792432d, TwoPercModelGivenOwnExp.adjustRate(0.455434937293271,9.30234999999958,15,102.75,37,7) , DELTA);
        Assert.assertEquals(0.569179599159618d, TwoPercModelGivenOwnExp.adjustRate(0.531101131012852,7.48009999999961,5,123,25,1) , DELTA);
        Assert.assertEquals(0.52218393045829d, TwoPercModelGivenOwnExp.adjustRate(0.540783038283534,20.5203249999994,22,112.75,34,1) , DELTA);
        Assert.assertEquals(0.527838042259706d, TwoPercModelGivenOwnExp.adjustRate(0.533970156542587,11.0339999999995,13,110,23,8) , DELTA);
        Assert.assertEquals(0.589482817983938d, TwoPercModelGivenOwnExp.adjustRate(0.522784795530154,4.46947499999973,4,117.25,23,1) , DELTA);
        Assert.assertEquals(0.537075668439565d, TwoPercModelGivenOwnExp.adjustRate(0.537880050759226,3.78444999999978,4,122,13,1) , DELTA);
        Assert.assertEquals(0.424189557978299d, TwoPercModelGivenOwnExp.adjustRate(0.479552704677549,5.37914999999971,2,107.25,9,1) , DELTA);
        Assert.assertEquals(0.690341538079369d, TwoPercModelGivenOwnExp.adjustRate(0.678901811109908,4.7407499999997,8,110.25,29,0) , DELTA);
        Assert.assertEquals(0.548288783241111d, TwoPercModelGivenOwnExp.adjustRate(0.522572360215493,7.61517499999958,8,117.25,34,2) , DELTA);
        Assert.assertEquals(0.675288790535663d, TwoPercModelGivenOwnExp.adjustRate(0.671478008818545,4.57474999999971,7,106,30,0) , DELTA);
        Assert.assertEquals(0.635221472400165d, TwoPercModelGivenOwnExp.adjustRate(0.629151419008211,2.71102499999983,6,109.5,19,0) , DELTA);
        Assert.assertEquals(0.477136894619208d, TwoPercModelGivenOwnExp.adjustRate(0.484236980135779,18.3966999999994,20,111,34,10) , DELTA);
        Assert.assertEquals(0.420825962906373d, TwoPercModelGivenOwnExp.adjustRate(0.459057846027146,6.19832499999971,5,114.75,14,1) , DELTA);
        Assert.assertEquals(0.539024868900658d, TwoPercModelGivenOwnExp.adjustRate(0.560493272025745,14.1266499999994,17,108.25,39,9) , DELTA);
        Assert.assertEquals(0.610807079927168d, TwoPercModelGivenOwnExp.adjustRate(0.573724014572316,7.3417999999996,4,126,24,0) , DELTA);
        Assert.assertEquals(0.529163269542779d, TwoPercModelGivenOwnExp.adjustRate(0.561015523515071,7.97307499999957,8,117.25,18,4) , DELTA);
        Assert.assertEquals(0.5526258404368d, TwoPercModelGivenOwnExp.adjustRate(0.578400206972985,10.8249499999995,7,108.75,22,0) , DELTA);
        Assert.assertEquals(0.515074738109059d, TwoPercModelGivenOwnExp.adjustRate(0.497244502034261,10.1067999999995,19,112,37,13) , DELTA);
        Assert.assertEquals(0.510847491758823d, TwoPercModelGivenOwnExp.adjustRate(0.490637626779657,11.4486749999995,9,118.25,30,6) , DELTA);
        Assert.assertEquals(0.541168442441357d, TwoPercModelGivenOwnExp.adjustRate(0.530152196018295,9.22867499999957,8,116,24,2) , DELTA);
        Assert.assertEquals(0.558760056700818d, TwoPercModelGivenOwnExp.adjustRate(0.556887196127587,3.50149999999981,5,122.5,12,0) , DELTA);
        Assert.assertEquals(0.515066343712875d, TwoPercModelGivenOwnExp.adjustRate(0.508418184851624,13.9300749999995,16,114.5,43,5) , DELTA);
        Assert.assertEquals(0.454083796287822d, TwoPercModelGivenOwnExp.adjustRate(0.530046009984356,8.40052499999959,6,116.5,13,2) , DELTA);
        Assert.assertEquals(0.509008181218594d, TwoPercModelGivenOwnExp.adjustRate(0.475581048142005,11.9790499999995,11,121.5,24,6) , DELTA);
        Assert.assertEquals(0.618489719631597d, TwoPercModelGivenOwnExp.adjustRate(0.642908176471907,17.2825999999994,16,117.25,35,3) , DELTA);
        Assert.assertEquals(0.491323032841849d, TwoPercModelGivenOwnExp.adjustRate(0.514992898138106,16.6764749999994,19,106,38,9) , DELTA);
        Assert.assertEquals(0.562775433501717d, TwoPercModelGivenOwnExp.adjustRate(0.577441279934754,21.5293499999994,21,118.5,32,9) , DELTA);
        Assert.assertEquals(0.497489208878733d, TwoPercModelGivenOwnExp.adjustRate(0.447111928141679,4.89807499999972,2,108.5,20,0) , DELTA);
        Assert.assertEquals(0.618323288417464d, TwoPercModelGivenOwnExp.adjustRate(0.56602380081238,6.80179999999964,10,125.5,23,4) , DELTA);
        Assert.assertEquals(0.552933051049771d, TwoPercModelGivenOwnExp.adjustRate(0.560275945902672,15.7580499999994,15,116,37,6) , DELTA);
        Assert.assertEquals(0.543929768687625d, TwoPercModelGivenOwnExp.adjustRate(0.493976705589377,6.78609999999964,12,117.5,29,11) , DELTA);
        Assert.assertEquals(0.557652458224811d, TwoPercModelGivenOwnExp.adjustRate(0.57046779979743,5.10254999999971,2,112.25,19,0) , DELTA);
        Assert.assertEquals(0.542937682106669d, TwoPercModelGivenOwnExp.adjustRate(0.546795056841416,9.91719999999952,8,112.75,35,7) , DELTA);
        Assert.assertEquals(0.425552615506607d, TwoPercModelGivenOwnExp.adjustRate(0.445888913999833,10.4507499999996,7,115.75,15,5) , DELTA);
        Assert.assertEquals(0.438035626996009d, TwoPercModelGivenOwnExp.adjustRate(0.420991702000881,9.69959999999954,11,109,27,4) , DELTA);
        Assert.assertEquals(0.586298659247411d, TwoPercModelGivenOwnExp.adjustRate(0.641554877344295,7.76097499999963,3,115.5,16,1) , DELTA);
        Assert.assertEquals(0.530843121244149d, TwoPercModelGivenOwnExp.adjustRate(0.540952603311915,17.5784749999994,18,114.25,38,6) , DELTA);
        Assert.assertEquals(0.469819149320032d, TwoPercModelGivenOwnExp.adjustRate(0.453370485823864,8.53642499999956,8,111.25,31,6) , DELTA);
        Assert.assertEquals(0.547261838932649d, TwoPercModelGivenOwnExp.adjustRate(0.547494288969646,1.39139999999992,3,110.75,12,2) , DELTA);
        Assert.assertEquals(0.475494827699197d, TwoPercModelGivenOwnExp.adjustRate(0.487509142732677,15.1795499999995,9,110.75,23,4) , DELTA);
        Assert.assertEquals(0.559857886709867d, TwoPercModelGivenOwnExp.adjustRate(0.555380000801219,17.3838249999994,16,121.25,35,5) , DELTA);
        Assert.assertEquals(0.572125715030362d, TwoPercModelGivenOwnExp.adjustRate(0.534277687360072,13.9014999999995,17,127.25,24,5) , DELTA);
        Assert.assertEquals(0.582823061921167d, TwoPercModelGivenOwnExp.adjustRate(0.574835283241331,7.64304999999958,9,114.75,32,5) , DELTA);
        Assert.assertEquals(0.633659305209702d, TwoPercModelGivenOwnExp.adjustRate(0.596143483585206,4.88837499999971,4,115.25,22,1) , DELTA);
        Assert.assertEquals(0.536073503369207d, TwoPercModelGivenOwnExp.adjustRate(0.534370344726983,15.1410999999995,10,118.75,24,2) , DELTA);
        Assert.assertEquals(0.569388414308642d, TwoPercModelGivenOwnExp.adjustRate(0.555164420188087,3.88637499999977,4,113.5,15,0) , DELTA);
        Assert.assertEquals(0.592321454667651d, TwoPercModelGivenOwnExp.adjustRate(0.601404823568822,18.8973249999994,14,122.5,29,1) , DELTA);
        Assert.assertEquals(0.537576375731773d, TwoPercModelGivenOwnExp.adjustRate(0.524152209465624,9.83777499999951,11,115.75,32,6) , DELTA);
        Assert.assertEquals(0.525058492610246d, TwoPercModelGivenOwnExp.adjustRate(0.539360143099157,19.0704749999994,17,114.75,36,6) , DELTA);
        Assert.assertEquals(0.551091339123049d, TwoPercModelGivenOwnExp.adjustRate(0.550502186942611,9.06809999999954,5,115,31,1) , DELTA);
        Assert.assertEquals(0.59824446820336d, TwoPercModelGivenOwnExp.adjustRate(0.604775730717941,9.18032499999959,13,113.25,24,5) , DELTA);
        Assert.assertEquals(0.510687305971967d, TwoPercModelGivenOwnExp.adjustRate(0.46467226286528,9.04852499999955,5,124.5,24,3) , DELTA);
        Assert.assertEquals(0.510353952648424d, TwoPercModelGivenOwnExp.adjustRate(0.574377657340381,4.11132499999978,5,103.5,13,0) , DELTA);
        Assert.assertEquals(0.544396478699245d, TwoPercModelGivenOwnExp.adjustRate(0.561046536626839,17.9181749999994,21,112.5,39,8) , DELTA);
        Assert.assertEquals(0.562633997666968d, TwoPercModelGivenOwnExp.adjustRate(0.49406808636195,4.21404999999974,6,113.25,22,4) , DELTA);
        Assert.assertEquals(0.665297789816185d, TwoPercModelGivenOwnExp.adjustRate(0.710238097504849,2.72699999999983,1,113.75,6,0) , DELTA);
        Assert.assertEquals(0.488533012255889d, TwoPercModelGivenOwnExp.adjustRate(0.469305902375575,20.2301499999994,24,119.5,41,10) , DELTA);
        Assert.assertEquals(0.499081048469361d, TwoPercModelGivenOwnExp.adjustRate(0.514016801674912,16.6016999999994,20,108.5,40,4) , DELTA);
        Assert.assertEquals(0.541560753764085d, TwoPercModelGivenOwnExp.adjustRate(0.513286248021286,10.4884749999995,8,122.25,31,5) , DELTA);
        Assert.assertEquals(0.55247168659384d, TwoPercModelGivenOwnExp.adjustRate(0.525578461207489,5.03704999999971,3,106.5,21,2) , DELTA);
        Assert.assertEquals(0.600963513155506d, TwoPercModelGivenOwnExp.adjustRate(0.611276959280511,14.6705249999994,17,116.75,36,0) , DELTA);
        Assert.assertEquals(0.562033249890645d, TwoPercModelGivenOwnExp.adjustRate(0.517943905042682,5.87949999999967,7,114.75,27,6) , DELTA);
        Assert.assertEquals(0.518831413773196d, TwoPercModelGivenOwnExp.adjustRate(0.516795752631129,12.7433999999995,13,113.5,26,6) , DELTA);
        Assert.assertEquals(0.548483730115105d, TwoPercModelGivenOwnExp.adjustRate(0.56382577945906,20.8687499999994,17,118.25,38,7) , DELTA);
        Assert.assertEquals(0.593247688678358d, TwoPercModelGivenOwnExp.adjustRate(0.547661033892977,8.75394999999961,18,125,22,6) , DELTA);
        Assert.assertEquals(0.577055782365824d, TwoPercModelGivenOwnExp.adjustRate(0.547479445166554,6.43674999999965,9,114,21,2) , DELTA);
        Assert.assertEquals(0.641146395237552d, TwoPercModelGivenOwnExp.adjustRate(0.61236185610143,4.34264999999975,12,107,22,0) , DELTA);
        Assert.assertEquals(0.492932316304454d, TwoPercModelGivenOwnExp.adjustRate(0.472286648533065,11.4731249999995,17,113.5,35,6) , DELTA);
        Assert.assertEquals(0.485109352997954d, TwoPercModelGivenOwnExp.adjustRate(0.479814129730662,8.09662499999957,7,109.25,31,5) , DELTA);
        Assert.assertEquals(0.570375643663588d, TwoPercModelGivenOwnExp.adjustRate(0.53539532830786,8.03042499999959,9,123.25,27,6) , DELTA);
        Assert.assertEquals(0.556388419571327d, TwoPercModelGivenOwnExp.adjustRate(0.526114422185603,6.53917499999964,4,115.25,24,1) , DELTA);
        Assert.assertEquals(0.575377324236743d, TwoPercModelGivenOwnExp.adjustRate(0.577026933449572,6.06927499999968,8,119.25,18,5) , DELTA);
        Assert.assertEquals(0.497274516924964d, TwoPercModelGivenOwnExp.adjustRate(0.477631966324806,8.79139999999954,12,112.75,31,8) , DELTA);
        Assert.assertEquals(0.56187675061737d, TwoPercModelGivenOwnExp.adjustRate(0.56702748418083,12.4134499999995,14,114,30,0) , DELTA);
        Assert.assertEquals(0.544023463554974d, TwoPercModelGivenOwnExp.adjustRate(0.549978088690146,15.9647999999994,33,108,37,13) , DELTA);
        Assert.assertEquals(0.583363530123753d, TwoPercModelGivenOwnExp.adjustRate(0.600988626015242,17.8719249999994,15,117.75,27,0) , DELTA);
        Assert.assertEquals(0.495706983235156d, TwoPercModelGivenOwnExp.adjustRate(0.604440599766391,10.8211999999995,3,114.25,11,0) , DELTA);
        Assert.assertEquals(0.591441683712149d, TwoPercModelGivenOwnExp.adjustRate(0.577220158526364,7.2801749999996,8,115.75,30,5) , DELTA);
        Assert.assertEquals(0.539865918263296d, TwoPercModelGivenOwnExp.adjustRate(0.542451087676292,13.7843499999994,13,115,36,3) , DELTA);
        Assert.assertEquals(0.530532356185498d, TwoPercModelGivenOwnExp.adjustRate(0.50444939418132,7.6494749999996,10,115.25,28,4) , DELTA);
        Assert.assertEquals(0.494837574026167d, TwoPercModelGivenOwnExp.adjustRate(0.532992628105246,20.1954999999994,16,107.5,36,8) , DELTA);
        Assert.assertEquals(0.479386043251647d, TwoPercModelGivenOwnExp.adjustRate(0.477819971717343,11.4471499999996,15,108,31,6) , DELTA);
        Assert.assertEquals(0.491991108929384d, TwoPercModelGivenOwnExp.adjustRate(0.504862196400219,13.0474999999994,15,106.75,35,10) , DELTA);
        Assert.assertEquals(0.59058586458215d, TwoPercModelGivenOwnExp.adjustRate(0.585330433620904,2.69994999999986,5,121.75,11,2) , DELTA);
        Assert.assertEquals(0.714934372882823d, TwoPercModelGivenOwnExp.adjustRate(0.67669782662398,5.61209999999967,8,126,25,0) , DELTA);
        Assert.assertEquals(0.566853530722519d, TwoPercModelGivenOwnExp.adjustRate(0.526169879811259,11.1850749999995,16,125,33,7) , DELTA);
        Assert.assertEquals(0.661276050157307d, TwoPercModelGivenOwnExp.adjustRate(0.635152073941734,5.22629999999974,3,116.75,21,2) , DELTA);
        Assert.assertEquals(0.580961796017264d, TwoPercModelGivenOwnExp.adjustRate(0.517313122954601,4.33879999999976,13,111,28,6) , DELTA);
        Assert.assertEquals(0.540278264782177d, TwoPercModelGivenOwnExp.adjustRate(0.562907717780566,21.5239999999994,16,116.75,41,6) , DELTA);
        Assert.assertEquals(0.53162551144856d, TwoPercModelGivenOwnExp.adjustRate(0.491737632914852,4.34544999999975,7,118.75,17,4) , DELTA);
        Assert.assertEquals(0.564291327132638d, TwoPercModelGivenOwnExp.adjustRate(0.555310748323702,15.2194999999994,17,120,32,9) , DELTA);
        Assert.assertEquals(0.46829851798379d, TwoPercModelGivenOwnExp.adjustRate(0.535381160185964,9.63572499999954,3,107,18,1) , DELTA);
    }
}