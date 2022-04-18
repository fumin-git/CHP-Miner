package gep;

import ga.Initializer;
import ga.Population;

/**
 * ������ֵ�Ż��ĳ�ʼ��Ⱥ������
 */
public class GepInitializer implements Initializer
{
	private GEP gep;
	private int size; // ��Ⱥ��С

	public GepInitializer(GEP gep, int size)
	{
		this.gep = gep;
		this.size = size;
	}

	public Population generateInitialPopulation()
	{
		//		int populationSize = gep.getPopulationSize();
		Population population = new Population();

		int geneNumber = gep.getGeneNumber();//�������
		int geneHead = gep.getGeneHead();
		int geneTail = gep.getGeneTail();
		int geneLength = gep.getGeneLength();//����ͷ����β��
		for (int k = 0; k < size; k++)
		{
			char[] genes = new char[geneLength * geneNumber];
			int index = 0;
			for (int j = 0; j < geneNumber; j++)
			{
				for (int i = 0; i < geneHead; i++)
				{
					genes[index++] = gep.getHeadCode();
				}
				for (int i = 0; i < geneTail; i++)
				{
					genes[index++] = gep.getTailCode();
				}
			}

			population.add(new GepChromosome(new String(genes)));//�ѻ����ַ�����תΪstring
		}

		return population;
	}

}
