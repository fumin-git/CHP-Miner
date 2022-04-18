package ga;

/**
 * ��������ֹͣ��
 */
public class MaxGenerationStopper implements Stopper
{
	private GA ga;
	private int maxGeneration;

	public MaxGenerationStopper(GA ga, int maxGeneration)
	{
		this.ga = ga;
		this.maxGeneration = maxGeneration;
	}

	public boolean canStop()
	{
		if (ga.generation >= maxGeneration) return true;

		return false;
	}

	public String toString()
	{
		return getClass().getName() + "(maxGeneration:" + maxGeneration + ")";
	}
}
