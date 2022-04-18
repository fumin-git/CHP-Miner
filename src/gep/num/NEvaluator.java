package gep.num;

//import duanContrastFunc.FuncContrastInfo;
import funcs.ThreeTuple;
import ga.Evaluator;
import ga.Fitness;
import ga.Protein;

import java.util.List;

/**
 * 数值评价器
 */
public class NEvaluator implements Evaluator
{
	private NFitness fitnessFunction; // 适应度函数
	private double[][] parameters; // 训练数据的参数

	public NEvaluator(Fitness fitnessFunction, NDataSet dataSet)
	{
		this.fitnessFunction = (NFitness) fitnessFunction;
		this.parameters = dataSet.getParameters();
	}

	@Override
	public double evaluate(Protein protein) {
		return 0;
	}

	/**
	 * 评价，返回适应度
	 * 
	 * @param protein
	 *            待评价的"蛋白质"
	 * @return 适应度值
	 */
	public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein)
	{
		NEvaluable evaluable = (NEvaluable) protein;

		int size = parameters.length;

		// 计算每一个样本的值
		double[] model = new double[size];
		for (int i = 0; i < size; i++)
		{
			double[] sample = parameters[i];

			// 模型计算值
			try
			{
				model[i] = evaluable.evaluate(sample);
			}
			catch (Exception e)
			{
				return new ThreeTuple<>(fitnessFunction.getMinFitness(), null, null);
			}
		}

		// 计算适应度
		double f = fitnessFunction.calculate(model);

		// TODO 测试"世界简单性原理"
		//		if (false) f += 1/((Formula) protein).getComplex();

		return new ThreeTuple<>(f, null, null);
	}

	@Override
	public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein) {
		return null;
	}

	public String toString()
	{
		return getClass().getName();
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
