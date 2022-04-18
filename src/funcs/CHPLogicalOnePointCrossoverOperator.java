package funcs;

import ga.Chromosome;
import ga.CrossoverOperator;

public class CHPLogicalOnePointCrossoverOperator extends CrossoverOperator {
    public CHPLogicalOnePointCrossoverOperator(CHPGep CHPGep, double probability)
    {
        super(probability);
    }
    public Chromosome[] operate(Chromosome[] chromosomes)
    {
        // 如果不满足概率，直接返回
        if (!checkProbability()) return chromosomes;

        CHPLogicalChromosome c0 = (CHPLogicalChromosome) chromosomes[0];
        CHPLogicalChromosome c1 = (CHPLogicalChromosome) chromosomes[1];

        // 取出父代基因
        char[][] pg0 = c0.getGenes();
        char[][] pg1 = c1.getGenes();

        char[][] sg0 = new char[2][];
        char[][] sg1 = new char[2][];

        // 分别交叉两条基因
        for (int i = 0; i < 2; i++)
        {
            char[] p0 = pg0[i];
            char[] p1 = pg1[i];
            char[] s0 = p0.clone(); // 先从父代复制
            char[] s1 = p1.clone();

            crossover(p0, p1, s0, s1);

            sg0[i] = s0;
            sg1[i] = s1;
        }

        // 准备子代染色体
        Chromosome[] sons = new Chromosome[2];
        sons[0] = new CHPLogicalChromosome(c0.getRelation(), sg0);
        sons[1] = new CHPLogicalChromosome(c1.getRelation(), sg1);

        return sons;
    }

    private void crossover(char[] p0, char[] p1, char[] s0, char[] s1)
    {
        int size = p0.length;

        // 选择交叉位置
        int start = (int) (Math.random() * size);

        // 进行交叉 (交换后面部分)
        for (int i = start; i < size; i++)
        {
            s0[i] = p1[i];
            s1[i] = p0[i];
        }
    }
}
