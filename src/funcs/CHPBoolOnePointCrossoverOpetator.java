package funcs;

import ga.Chromosome;
import ga.CrossoverOperator;

public class CHPBoolOnePointCrossoverOpetator extends CrossoverOperator {

    public CHPBoolOnePointCrossoverOpetator(CHPGep CHPGep, double probability)
    {
        super(probability);
    }
    @Override
    public Chromosome[] operate(Chromosome[] chromosomes) {
        // 如果不满足概率，直接返回
        if (!checkProbability()) return chromosomes;

        // 取出父代基因
        String genes0 = ((CHPBoolChromosome) chromosomes[0]).getGenes();
        String genes1 = ((CHPBoolChromosome) chromosomes[1]).getGenes();

        char[] p0 = genes0.toCharArray();
        char[] p1 = genes1.toCharArray();
        char[] s0 = genes0.toCharArray(); // 先从父代复制
        char[] s1 = genes1.toCharArray();

        int size = p0.length;

        // 选择交叉位置
        int start = (int) (Math.random() * size);

        // 进行交叉 (交换后面部分)
        for (int i = start; i < size; i++)
        {
            s0[i] = p1[i];
            s1[i] = p0[i];
        }

        // 准备子代染色体
        Chromosome[] sons = new Chromosome[2];
        sons[0] = new CHPBoolChromosome(new String(s0));
        sons[1] = new CHPBoolChromosome(new String(s1));

        return sons;
    }
}
