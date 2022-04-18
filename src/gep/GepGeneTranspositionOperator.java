package gep;

import ga.Chromosome;
import ga.MutationOperator;

/**
 * ����Ǩ������
 */
public class GepGeneTranspositionOperator extends MutationOperator
{
	private GEP gep;

	public GepGeneTranspositionOperator(GEP gep, double probability)
	{
		super(probability);
		this.gep = gep;
	}

	public Chromosome mutate(Chromosome chromosome)
	{
		if (!checkProbability()) return chromosome;

		String genes = ((GepChromosome) chromosome).getGenes();
		char[] p = genes.toCharArray();
		char[] s = genes.toCharArray(); // �ȴӸ�������һ��

		int geneNumber = gep.getGeneNumber();
		int geneLength = gep.getGeneLength();

		// ���ѡ��Ǩ�ƵĻ���
		int g = (int) (Math.random() * geneNumber);

		// Ų����λ
		int q = g * geneLength;
		int m = geneLength;
		int n = 0;
		for (int i = 0; i < q; i++)
		{
			s[m++] = p[n++];
		}

		// ǰ�����Ǩ�ƵĻ���
		m = 0;
		n = g * geneLength;
		for (int i = 0; i < geneLength; i++)
		{
			s[m++] = p[n++];
		}

		return new GepChromosome(new String(s));
	}

}
