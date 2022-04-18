package miner;

/**
 * 所有可能的参数
 */
public class Parameter {
    public static String posDataPath = "data/credit/gep_pos.txt"; //正例数据集的url
    public static String negDataPath = "data/credit/gep_neg.txt"; //负例数据集的url

    public static double ratioThreshold = 1; // 支持度比值阈值

    //allhypo
//    public static int splitIndex = 29;
//    public static int boolIndexCount = 30;
//    public static int logicalIndexCount = 6;

    //band
//    public static int splitIndex = 59;
//    public static int boolIndexCount = 60;
//    public static int logicalIndexCount = 19;

    //credit
    public static int splitIndex = 40;
    public static int boolIndexCount = 41;
    public static int logicalIndexCount = 6;


    //income
//    public static int splitIndex = 98;
//    public static int boolIndexCount = 99;
//    public static int logicalIndexCount = 6;


    public static int boolGeneNumber = 1;
    public static int boolGeneHead = 2;
    public static int boolGepInitializer = 500;
    public static int boolMaxGenerationStopper = 100;

    public static int logicalGeneNumber = 1;
    public static int logicalGeneHead = 1;
    public static int logicalGepInitializer = 500;
    public static int logicalMaxGenerationStopper = 100;

    public static int combineGeneNumber = 1;
    public static int combineGeneHead = 2;
    public static int combineGepSize = 100;

    public static int maxL = 15;
    public static final String dataset = "credit";


}
