package ga;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ⱥ (ÿһ����������Ⱦɫ���ʾ)
 */
public class Population
{
	private List<Chromosome> chromosomes = new ArrayList<Chromosome>();


	public void add(Chromosome chromosome)
	{
		chromosomes.add(chromosome);
	}

	/**
	 * ȡ�õ�i��Ⱦɫ��
	 * 
	 * @param i
	 * @return
	 */
	public Chromosome get(int i)
	{
		return (Chromosome) chromosomes.get(i);
	}

	/**
	 * ������Ⱥ��С
	 * 
	 * @return
	 */
	public int size()
	{
		return chromosomes.size();
	}

	/**
	 * ���õ�i��Ⱦɫ��
	 * 
	 * @param i
	 * @param chromosome
	 */
	//	public void set(int i, Chromosome chromosome)
	//	{
	//		chromosomes.set(i, chromosome);
	//	}
}
