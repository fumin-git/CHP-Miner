package gep;

import ga.Chromosome;
import ga.CrossoverOperator;

/**
 * ��������(����)
 */
public class GepGeneCrossoverOperator extends CrossoverOperator
{
	private GEP gep;

	public GepGeneCrossoverOperator(GEP gep, double probability)
	{
		super(probability);
		this.gep = gep;
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

		int geneNumber = gep.getGeneNumber();
		int geneLength = gep.getGeneLength();

		// ѡ�񽻲����
		int g = (int) (Math.random() * geneNumber);

		// ���н��� (����ѡ�еĻ���)
		int m = g * geneLength;
		for (int i = 0; i < geneLength; i++)
		{
			s0[m] = p1[m];
			s1[m] = p0[m];
			m++;
		}

		// ׼���Ӵ�Ⱦɫ��
		Chromosome[] sons = new Chromosome[2];
		sons[0] = new GepChromosome(new String(s0));
		sons[1] = new GepChromosome(new String(s1));

		return sons;
	}

}
