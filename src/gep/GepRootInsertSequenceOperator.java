package gep;

import ga.Chromosome;
import ga.MutationOperator;

/**
 * ���崮����
 */
public class GepRootInsertSequenceOperator extends MutationOperator
{
	private GEP gep;
	private int[] lengthes; // ���ĳ��ȿ�ѡֵ

	public GepRootInsertSequenceOperator(GEP gep, double probability, int[] lengthes)
	{
		super(probability);
		this.gep = gep;
		this.lengthes = lengthes;
	}

	public Chromosome mutate(Chromosome chromosome)
	{
		int geneNumber = gep.getGeneNumber();
		int geneHead = gep.getGeneHead();
		int geneLength = gep.getGeneLength();

		String genes = ((GepChromosome) chromosome).getGenes();
		char[] p = genes.toCharArray();
		char[] s = genes.toCharArray(); // �ȴӸ�������һ��

		// ���ÿһ��������в���
		for (int g = 0; g < geneNumber; g++)
		{
			if (!checkProbability()) continue; // ������ʲ����㣬������һ������
			int base = g * geneLength; // ����ʼ��λ��

			// �����崮���ȡ�Դ��λ�ú�Ŀ��λ��
			int length = lengthes[(int) (Math.random() * lengthes.length)];
			int source = (int) (Math.random() * geneHead);
			int target = 0;

			// ��sourceλ�ÿ�ʼ����ң��ҵ���һ������
			int m = base + source;
			int n = base + geneHead;
			for (; m < n; m++)
			{
				if (gep.isFunction(p[m])) break;
			}
			if (m >= n) continue; // ���û���ҵ���������һ������
			source = m - base;

			// Ų������Ϊlength�Ŀռ�
			m = base + target + length;
			n = base + target;
			for (int i = 0; i < geneHead - target - length; i++)
			{
				s[m++] = p[n++];
			}

			// ��ѡ���Դ�����뵽Ŀ��λ��
			m = base + target;
			n = base + source;
			for (int i = 0; i < length; i++)
			{
				s[m++] = p[n++];
			}
		}

		return new GepChromosome(new String(s));
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getName() + "(");
		buffer.append("probability:" + probability);
		buffer.append(";lengthes:" + lengthes[0]);
		for (int i = 1; i < lengthes.length; i++)
		{
			buffer.append(',');
			buffer.append(lengthes[i]);
		}
		buffer.append(")");

		return buffer.toString();
	}
}
