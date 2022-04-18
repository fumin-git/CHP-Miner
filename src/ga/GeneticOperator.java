package ga;

import java.util.Random;

/**
 * �Ŵ������
 */
public abstract class GeneticOperator
{
	private Random random = new Random();
	protected double probability; // ����

	protected GeneticOperator(double probability)
	{
		this.probability = probability;
	}

	protected boolean checkProbability()
	{
		if (random.nextDouble() < probability) return true;

		return false;
	}

	public String toString()
	{
		return getClass().getName() + "(probability:" + probability + ")";
	}
}