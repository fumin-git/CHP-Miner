package gep.num;

/**
 * ��ֵ���ݼ�
 */
public class GeneralNDataSet implements NDataSet
{
	protected double[] targets; // Ŀ��ֵ
	protected double[][] parameters; // ����

	/**
	 * ����ѵ��������Ŀ��ֵ
	 * 
	 * @return
	 */
	public double[] getTargets()
	{
		return targets;
	}

	/**
	 * ����ѵ�������Ĳ���
	 * 
	 * @return
	 */
	public double[][] getParameters()
	{
		return parameters;
	}
}