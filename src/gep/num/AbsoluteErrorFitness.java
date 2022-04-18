package gep.num;

/**
 * ʹ�þ�����������Ӧ�Ⱥ���
 */
public class AbsoluteErrorFitness extends GeneralNFitness
{
	private final double MIN_FITNESS = 1.0;
	private double[] targets; // Ŀ��ֵ
	private double M; // ����ľ������ֵ����

	public AbsoluteErrorFitness(NDataSet dataSet, double M)
	{
		super(dataSet);
		this.targets = dataSet.getTargets();
		this.M = M;
	}

	public double calculate(double[] values)
	{
		double sum = 0.0;
		for (int i = 0; i < values.length; i++)
		{
			sum += M - Math.abs(values[i] - targets[i]);
		}
		if (sum < 0) sum = 0;

		return sum;
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
