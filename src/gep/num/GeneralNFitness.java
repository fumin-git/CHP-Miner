package gep.num;

/**
 * ͨ����Ӧ�Ⱥ���
 */
public abstract class GeneralNFitness implements NFitness
{
	protected double[] targets; // ������Ŀ��ֵ

	protected GeneralNFitness(NDataSet dataSet)
	{
		this.targets = dataSet.getTargets();
	}
}
