package gep.bool;

import funcs.ThreeTuple;
import ga.Evaluator;
import ga.Protein;
import gep.GEP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import duanContrastFunc.FuncContrastInfo;

/**
 * 布尔评价器
 */
public class BEvaluator implements Evaluator
{
	protected GEP gep;
	protected double maxFitness;
	protected List<boolean[]> trainData = new ArrayList<boolean[]>(); // 训练数据

	protected BEvaluator(GEP gep)
	{
		this.gep = gep;
	}

	@Override
	public double evaluate(Protein protein) {
		return 0;
	}

	/**
	 * 评价
	 * 
	 * @param protein
	 * @return
	 */
	public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein)
	{
		BFormula formula = (BFormula) protein;

		double ok = 0; // 模型正确判断的数量
		for (Iterator<boolean[]> iterator = trainData.iterator(); iterator.hasNext();)
		{
			boolean[] sample = (boolean[]) iterator.next();
			boolean target = sample[0];

			boolean model;
			try
			{
				formula.setValues(sample);
				model = formula.evaluate();
			}
			catch (Exception e)
			{
				return new ThreeTuple<>(1.0, null, null);
			}

			if (target == model) ok++;

		}

		return new ThreeTuple<>(1.0, null, null);
	}

	@Override
	public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein) {
		return null;
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
