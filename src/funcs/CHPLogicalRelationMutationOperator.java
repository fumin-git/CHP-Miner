package funcs;

import ga.Chromosome;
import ga.MutationOperator;

public class CHPLogicalRelationMutationOperator extends MutationOperator {
    private CHPGep CHPGep;

    public CHPLogicalRelationMutationOperator(CHPGep CHPGep, double probability)
    {
        super(probability);
        this.CHPGep = CHPGep;
    }

    public Chromosome mutate(Chromosome chromosome)
    {
        if (!checkProbability()) return chromosome;

        CHPLogicalChromosome c = (CHPLogicalChromosome) chromosome;

        return new CHPLogicalChromosome(CHPGep.getLogicalRelationCode(), c.getGenes());
    }
}
