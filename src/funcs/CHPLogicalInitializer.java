package funcs;

import ga.Initializer;
import ga.Population;

public class CHPLogicalInitializer implements Initializer {
    private CHPGep CHPGep;
    private int size;

    public CHPLogicalInitializer(CHPGep CHPGep, int size){
        this.CHPGep = CHPGep;
        this.size = size;
    }

    @Override
    public Population generateInitialPopulation() {
        Population population = new Population();

        int geneNumber = CHPGep.getLogicalGeneNumber();
        int geneHead = CHPGep.getLogicalGeneHead();
        int geneTail = CHPGep.getLogicalGeneTail();
        int geneLength = CHPGep.getLogicalGeneLength();
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
                        g[index++] = CHPGep.getLogicalHeadCode();
                    }
                    for (int i = 0; i < geneTail; i++)
                    {
                        g[index++] = CHPGep.getLogicalTailCode();
                    }
                }
            }

            char relation = CHPGep.getLogicalRelationCode();
            population.add(new CHPLogicalChromosome(relation, genes));
        }

        return population;
    }
}
