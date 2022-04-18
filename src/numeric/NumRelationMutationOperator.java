package numeric;

import ga.Chromosome;
import ga.MutationOperator;

/**
 * 变异算子 只变异关系函数部分
 */
public class NumRelationMutationOperator extends MutationOperator
{
	private NumGep gep;

	public NumRelationMutationOperator(NumGep gep, double probability)
	{
		super(probability);
		this.gep = gep;
	}

	public Chromosome mutate(Chromosome chromosome)
	{
		if (!checkProbability()) return chromosome;
		
		NumChromosome c = (NumChromosome) chromosome;
		
		return new NumChromosome(gep.getRelationCode(), c.getGenes());
	}

}
