package numeric;

import ga.Chromosome;

/**
 * Ⱦɫ��
 * 
 * �����Ӧ�ð��ղ�������÷�ʹ�ã����Ҫ�޸ģ�Ӧ�ò����µ�Ⱦɫ��
 */
public class NumChromosome implements Chromosome
{
	private char relation; // ��ϵ����(ֻ�� == != > < >= <= ��ѡ��)
	private char[][] genes; // ����ʽ���߻���

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
