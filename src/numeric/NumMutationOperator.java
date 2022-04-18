package numeric;

import ga.Chromosome;
import ga.MutationOperator;

/**
 * 变异算子 只变异GEP基因部分
 */
public class NumMutationOperator extends MutationOperator
{
	private NumGep gep;

	public NumMutationOperator(NumGep gep, double probability)
	{
		super(probability);
		this.gep = gep;
	}

	public Chromosome mutate(Chromosome chromosome)
	{
		NumChromosome c = (NumChromosome) chromosome;

		char[][] ps = c.getGenes();
		char[][] ss = new char[2][];

		int geneHead = gep.getGeneHead();
		int geneTail = gep.getGeneTail();
		int geneNumber = gep.getGeneNumber();

		for (int x = 0; x < 2; x++)
		{
			char[] s = ps[x].clone();
			ss[x] = s;
			
			int index = 0;
			for (int k = 0; k < geneNumber; k++)
			{
				for (int i = 0; i < geneHead; i++, index++)
				{
					if (!checkProbability()) continue;

					s[index] = gep.getHeadCode();
				}
				for (int i = 0; i < geneTail; i++, index++)
				{
					if (!checkProbability()) continue;

					s[index] = gep.getTailCode();
				}
			}
		}

		return new NumChromosome(c.getRelation(), ss);
	}

}
