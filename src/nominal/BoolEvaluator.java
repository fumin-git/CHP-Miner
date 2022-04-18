package nominal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import duanContrastFunc.FuncContrastInfo;

import funcs.CHPBoolFormula;
import funcs.ThreeTuple;
import ga.Evaluator;
import ga.Protein;
import funcs.TwoTuple;

/**
 * 评价器
 */
public class BoolEvaluator implements Evaluator
{
	private BoolDataSet train0; // 训练数据0
	private BoolDataSet train1; // 训练数据1
	public static int count0 = 0;
	public static int count1 = 0;
	public static double sup0 = 0;
	public static double sup1 = 0;
	public static List<Boolean> posRecord = null;
	public static  List<Boolean> negRecord = null;
	public static double fit = 0;
	public static int posSamplesSize = 0;
	public static int negSamplesSize = 0;

	public BoolEvaluator(BoolDataSet train0, BoolDataSet train1)
	{
		this.train0 = train0;
		this.train1 = train1;
	}



	/**
	 * 评价
	 * 
	 * @param protein
	 * @return 适应度double，数组boolean[]
	 */
	public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein)
	{
		CHPBoolFormula formula = (CHPBoolFormula) protein;

		TwoTuple<Integer, List<Boolean>> result0 = evaluate0(formula, train0.getData());
		TwoTuple<Integer, List<Boolean>> result1 = evaluate0(formula, train1.getData());

		int count0 = result0.first;//表达式formula在每个正例lineData中出现的次数
		int count1 = result1.first;
		List<Boolean> record0 = result0.second;
		List<Boolean> record1 = result1.second;

		if ((count0==-1)||(count1==-1)) {
			sup0 = 0.0;
			sup1 = 0.0;
			return new ThreeTuple<>(0.0, record0, record1);
		}

		double sup0 = (double)count0/(double)train0.getData().size();
		double sup1 = (double)count1/(double)train1.getData().size();

		BoolEvaluator.count0 = count0;
		BoolEvaluator.count1 = count1;
		BoolEvaluator.sup0 = sup0;
		BoolEvaluator.sup1 = sup1;
		BoolEvaluator.posSamplesSize = train0.getData().size();
		BoolEvaluator.negSamplesSize = train1.getData().size();
		BoolEvaluator.posRecord = record0;
		BoolEvaluator.negRecord = record1;
//		Duan2Evaluator.fit = (count0 * (negSamplesSize - count1));
		BoolEvaluator.fit = (double)(count0 + 1) / (count1 + 1);
//		Duan2Evaluator.fit = (sup0)/(sup1+0.0001);

		return new ThreeTuple<>(fit, posRecord, negRecord);
	}

	@Override
	public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein) {
		return null;
	}
	@Override
	public double evaluate(Protein protein) {
		return 0;
	}

	private TwoTuple<Integer, List<Boolean>> evaluate0(CHPBoolFormula formula, List<boolean[]> train)
	{
		int count = 0; // 模型正确判断的数量
		List<Boolean> record = new ArrayList<Boolean>();
		for (boolean[] sample : train)
		{
			boolean b = false;
			try
			{
				formula.setValues(sample);
				b = formula.evaluate();
				record.add(b);//添加，记录每个样本是否满足该pattern
			}
			catch (Exception e)
			{
				Boolean[] booleans = new Boolean[train.size()];
				Arrays.fill(booleans, Boolean.FALSE);
				return new TwoTuple<Integer, List<Boolean>>(-1,new ArrayList<>(Arrays.asList(booleans)));
 			}

			if (b) count++;
		}

		return new TwoTuple<>(count, record);
	}

//	public FuncContrastInfo evaluateInfo(Protein protein) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public FuncContrastInfo evaluateInfoGetConstant(Protein protein) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public double getSup0(){ return sup0;}
	public double getSup1(){ return sup1;}
	@Override
	public double getPosSamplesSize() {
		return posSamplesSize;
	}

	@Override
	public double getNegSamplesSize() {
		return negSamplesSize;
	}
}

