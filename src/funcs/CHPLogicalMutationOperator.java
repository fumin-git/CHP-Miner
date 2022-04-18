package funcs;

import ga.Chromosome;
import ga.MutationOperator;

public class CHPLogicalMutationOperator extends MutationOperator {
    private CHPGep CHPGep;

    public CHPLogicalMutationOperator(CHPGep CHPGep, double probability)
    {
        super(probability);
        this.CHPGep = CHPGep;
    }

    public Chromosome mutate(Chromosome chromosome)
    {
        CHPLogicalChromosome c = (CHPLogicalChromosome) chromosome;

        char[][] ps = c.getGenes();
        char[][] ss = new char[2][];

        int geneHead = CHPGep.getLogicalGeneHead();
        int geneTail = CHPGep.getLogicalGeneTail();
        int geneNumber = CHPGep.getLogicalGeneNumber();

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

                    s[index] = CHPGep.getLogicalHeadCode();
                }
                for (int i = 0; i < geneTail; i++, index++)
                {
                    if (!checkProbability()) continue;

                    s[index] = CHPGep.getLogicalTailCode();
                }
            }
        }

        return new CHPLogicalChromosome(c.getRelation(), ss);
    }
}
