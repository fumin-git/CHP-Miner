package funcs;
import java.util.List;

import ga.Evaluator;
//import duanContrastFunc.FuncContrastInfo;

import ga.Protein;

public class CHPCombineEvaluator implements Evaluator {
    private List<List<Boolean>> train0;
    private List<List<Boolean>> train1;
    public static int count0;
    public static int count1;
    public static double sup0;
    public static double sup1;
    public static double ratios;

    public CHPCombineEvaluator(List<List<Boolean>> train0, List<List<Boolean>> train1){
        this.train0 = train0;
        this.train1 = train1;
    }

    public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein) {
        CHPBoolFormula formula = (CHPBoolFormula) protein;
        int count0 = evaluate0(formula, train0);
        int count1 = evaluate0(formula, train1);

        if ((count0==-1)||(count1==-1)) {
            sup0 = 0.0;
            sup1 = 0.0;
            return new ThreeTuple<>(0.0, 0.0, 0.0);
        }
        double sup0 = (double)count0/(double)train0.size();
        double sup1 = (double)count1/(double)train1.size();
        double ratios;
        if(sup1 == 0){
            ratios = sup0 / (sup1+0.01);
        }
        else{
            ratios = sup0 / sup1;
        }

        CHPCombineEvaluator.count0 = count0;
        CHPCombineEvaluator.count1 = count1;
        CHPCombineEvaluator.sup0 = sup0;
        CHPCombineEvaluator.sup1 = sup1;
        CHPCombineEvaluator.ratios = ratios;
        return new ThreeTuple<>(sup0, sup1, ratios);
    }

    private int evaluate0(CHPBoolFormula formula, List<List<Boolean>> train)
    {
        int count = 0; // 模型正确判断的数量
        for (int i=0; i<train.size(); i++)
        {
            boolean b = false;
            try
            {
                formula.setCombineValues(train.get(i));
                b = formula.evaluate();
            }
            catch (Exception e)
            {

                return -1;
            }

            if (b) count++;
        }

        return count;
    }


    @Override
    public double evaluate(Protein protein) {
        return 0;
    }

    @Override
    public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein) {
        return null;
    }



//    @Override
//    public FuncContrastInfo evaluateInfo(Protein protein) {
//        return null;
//    }
//
//    @Override
//    public FuncContrastInfo evaluateInfoGetConstant(Protein protein) {
//        return null;
//    }

    @Override
    public double getSup0() {
        return 0;
    }

    @Override
    public double getSup1() {
        return 0;
    }

    @Override
    public double getPosSamplesSize() {
        return 0;
    }

    @Override
    public double getNegSamplesSize() {
        return 0;
    }
}
