package ga;

import java.util.Random;

/**
 * ����ѡ������
 */
public class TournamentSelectionOperator implements SelectionOperator
{
	private Random random = new Random();
	private int size; // ������ģ

	public TournamentSelectionOperator(GA ga, int size)
	{
		this.size = size;
	}

	public int[] select(double[] fitnesses)
	{
		int populationSize = fitnesses.length;
		int[] index = new int[populationSize];

		for (int i = 0; i < populationSize; i++)
		{
			int winner = random.nextInt(populationSize);
			double fitness = fitnesses[winner];
			for (int j = 1; j < size; j++)
			{
				int k = random.nextInt(populationSize);
				double f = fitnesses[k];

				if (f > fitness)
				{
					winner = k;
					fitness = f;
				}
			}

			index[i] = winner;
		}

		return index;
	}

	public String toString()
	{
		return getClass().getName() + "(size:" + size + ")";
	}
}
