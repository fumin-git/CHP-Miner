package numeric;

import ga.Initializer;
import ga.Population;

/**
 * ������ֵ�Ż��ĳ�ʼ��Ⱥ������
 */
public class NumInitializer implements Initializer
{
	private NumGep gep;
	private int size; // ��Ⱥ��С

	public NumInitializer(NumGep gep, int size)
	{
		this.gep = gep;
		this.size = size;
	}

	public Population generateInitialPopulation()
	{
		Population population = new Population();

		int geneNumber = gep.getGeneNumber();
		int geneHead = gep.getGeneHead();
		int geneTail = gep.getGeneTail();
		int geneLength = gep.getGeneLength();
		for (int k = 0; k < size; k++)
		{
			char[][] genes = new char[2][];
			for (int x = 0; x < 2; x++)
			{
				char[] g = new char[geneLength * geneNumber];
				genes[x] = g;

				int index = 0;
				for (int j = 0; j < geneNumber; j++)
				{
					for (int i = 0; i < geneHead; i++)
					{
						g[index++] = gep.getHeadCode();
					}
					for (int i = 0; i < geneTail; i++)
					{
						g[index++] = gep.getTailCode();
					}
				}
			}

			char relation = gep.getRelationCode();
			population.add(new NumChromosome(relation, genes));
		}

		return population;
	}

}
