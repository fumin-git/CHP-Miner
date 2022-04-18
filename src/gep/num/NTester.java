package gep.num;

import ga.Fitness;
import ga.Protein;
import ga.Tester;

/**
 * ��ֵ������
 */
public class NTester implements Tester
{
	private NFitness fitnessFunction; // ��Ӧ�Ⱥ���
	private NDataSet dataSet; // ѵ������

	private double[] values; // ���������ֵ

	public NTester(Fitness fitnessFunction, NDataSet dataSet)
	{
		this.fitnessFunction = (NFitness) fitnessFunction;
		this.dataSet = dataSet;
	}

	/**
	 * ���ԣ�������Ӧ��
	 * 
	 * @param protein
	 *            �����Ե�"������"
	 * @return ��Ӧ��ֵ
	 */
	public double test(Protein protein)
	{
		NEvaluable formula = (NEvaluable) protein;

		double[][] parameters = dataSet.getParameters();
		int size = parameters.length;

		// ����ÿһ��������ֵ
		values = new double[size];
		for (int i = 0; i < size; i++)
		{
			double[] sample = parameters[i];

			// ģ��Ԥ��ֵ
			values[i] = formula.evaluate(sample);
			if (Double.isNaN(values[i])) return fitnessFunction.getMinFitness();
			if (Double.isInfinite(values[i])) return fitnessFunction.getMinFitness();
		}

		// ������Ӧ��
		double fitness = fitnessFunction.calculate(values);

		return fitness;
	}

	/**
	 * ���ز��Լ��������ֵ
	 * 
	 * @return
	 */
	public double[] getValues()
	{
		return values;
	}

	public String toString()
	{
		return getClass().getName();
	}
}
