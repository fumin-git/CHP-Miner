package funcs;

import ga.Initializer;
import ga.Population;

public class CHPBoolInitializer implements Initializer{

    private CHPGep CHPGep;
    private int size;

    public CHPBoolInitializer(CHPGep CHPGep, int size){
        this.CHPGep = CHPGep;
        this.size = size;
    }

    @Override
    public Population generateInitialPopulation() {
        //		int populationSize = gep.getPopulationSize();
        Population population = new Population();

        int geneNumber = CHPGep.getBoolGeneNumber();//基因个数
        int geneHead = CHPGep.getBoolGeneHead();
        int geneTail = CHPGep.getBoolGeneTail();
        int geneLength = CHPGep.getBoolGeneLength();//基因头部加尾部
        for (int k = 0; k < size; k++)
        {
            char[] genes = new char[geneLength * geneNumber];
            int index = 0;
            for (int j = 0; j < geneNumber; j++)
            {
                for (int i = 0; i < geneHead; i++)
                {
                    genes[index++] = CHPGep.getBoolHeadCode();
                }
                for (int i = 0; i < geneTail; i++)
                {
                    genes[index++] = CHPGep.getBoolTailCode();
                }
            }

            population.add(new CHPBoolChromosome(new String(genes)));//把基因字符数组转为string
        }

        return population;
    }
}
