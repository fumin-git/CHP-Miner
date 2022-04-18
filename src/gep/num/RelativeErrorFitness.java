package gep.num;

/**
 * ʹ�����������Ӧ�Ⱥ���
 */
public class RelativeErrorFitness extends GeneralNFitness
{
	private final double MIN_FITNESS = 1.0;
	private double M; // ������������������

	public RelativeErrorFitness(NDataSet dataSet, double M)
	{
		super(dataSet);
		this.M = M;
	}

	public double calculate(double[] values)
	{
		int size = targets.length;
		double fitness = 0.0;
		for (int i = 0; i < size; i++)
		{
			fitness += M - Math.abs((values[i] - targets[i]) / targets[i] * 100);
		}
		if (fitness < MIN_FITNESS) fitness = MIN_FITNESS;

		return fitness;
	}

	public double getMinFitness()
	{
		return MIN_FITNESS;
	}

	public String toString()
	{
		return getClass().getName() + "(M:" + M + ")";
	}
}
