package ga;

import java.util.ArrayList;
import java.util.List;

/**
 * 种群 (每一个个体用其染色体表示)
 */
public class Population
{
	private List<Chromosome> chromosomes = new ArrayList<Chromosome>();


	public void add(Chromosome chromosome)
	{
		chromosomes.add(chromosome);
	}

	/**
	 * 取得第i个染色体
	 * 
	 * @param i
	 * @return
	 */
	public Chromosome get(int i)
	{
		return (Chromosome) chromosomes.get(i);
	}

	/**
	 * 返回种群大小
	 * 
	 * @return
	 */
	public int size()
	{
		return chromosomes.size();
	}

	/**
	 * 设置第i个染色体
	 * 
	 * @param i
	 * @param chromosome
	 */
	//	public void set(int i, Chromosome chromosome)
	//	{
	//		chromosomes.set(i, chromosome);
	//	}
}
