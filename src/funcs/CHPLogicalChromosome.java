package funcs;

import ga.Chromosome;

public class CHPLogicalChromosome implements Chromosome {
    private char relation; // 关系函数(只在 == != > < >= <= 中选择)
    private char[][] genes; // 不等式两边基因

    public CHPLogicalChromosome(char relation, char[][] genes)
    {
        this.relation = relation;
        this.genes = genes;
    }

    public char getRelation()
    {
        return relation;
    }

    public void setRelation(char relation)
    {
        this.relation = relation;
    }

    public char[][] getGenes()
    {
        return genes;
    }

    public void setGenes(char[][] genes)
    {
        this.genes = genes;
    }
}
