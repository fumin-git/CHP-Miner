package numeric;

import ga.Chromosome;

/**
 * 染色体
 * 
 * 这个类应该按照不变类的用法使用，如果要修改，应该产生新的染色体
 */
public class NumChromosome implements Chromosome
{
	private char relation; // 关系函数(只在 == != > < >= <= 中选择)
	private char[][] genes; // 不等式两边基因

	public NumChromosome(char relation, char[][] genes)
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
