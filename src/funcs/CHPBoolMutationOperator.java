package funcs;

import ga.Chromosome;
import ga.MutationOperator;

public class CHPBoolMutationOperator extends MutationOperator {
    private CHPGep CHPGep;

    public CHPBoolMutationOperator(CHPGep CHPGep, double probability)
    {
        super(probability);
        this.CHPGep = CHPGep;
    }
    public Chromosome mutate(Chromosome chromosome) {
        String genes = ((CHPBoolChromosome) chromosome).getGenes();

        char[] s = genes.toCharArray();

        int geneHead = CHPGep.getBoolGeneHead();
        int geneTail = CHPGep.getBoolGeneTail();
        int geneNumber = CHPGep.getBoolGeneNumber();
        int index = 0;
        for (int k = 0; k < geneNumber; k++)
        {
            for (int i = 0; i < geneHead; i++, index++)
            {
                if (!checkProbability()) continue;

                s[index] = CHPGep.getBoolHeadCode();
            }
            for (int i = 0; i < geneTail; i++, index++)
            {
                if (!checkProbability()) continue;

                s[index] = CHPGep.getBoolTailCode();
            }
        }

        return new CHPBoolChromosome(new String(s));
    }
}
