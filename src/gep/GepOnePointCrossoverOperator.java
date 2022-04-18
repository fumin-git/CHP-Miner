package gep;

import ga.Chromosome;
import ga.CrossoverOperator;

/**
 */
public class GepOnePointCrossoverOperator extends CrossoverOperator
{
	public GepOnePointCrossoverOperator(GEP gep, double probability)
	{
		super(probability);
	}

	public Chromosome[] operate(Chromosome[] chromosomes)
	{
		// �����������ʣ�ֱ�ӷ���
		if (!checkProbability()) return chromosomes;

		// ȡ����������
		String genes0 = ((GepChromosome) chromosomes[0]).getGenes();
		String genes1 = ((GepChromosome) chromosomes[1]).getGenes();

		char[] p0 = genes0.toCharArray();
		char[] p1 = genes1.toCharArray();
		char[] s0 = genes0.toCharArray(); // �ȴӸ�������
		char[] s1 = genes1.toCharArray();

		int size = p0.length;

		// ѡ�񽻲�λ��
		int start = (int) (Math.random() * size);

		// ���н��� (�������沿��)
		for (int i = start; i < size; i++)
		{
			s0[i] = p1[i];
			s1[i] = p0[i];
		}

		// ׼���Ӵ�Ⱦɫ��
		Chromosome[] sons = new Chromosome[2];
		sons[0] = new GepChromosome(new String(s0));
		sons[1] = new GepChromosome(new String(s1));

		return sons;
	}

}
