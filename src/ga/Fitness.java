package ga;

/**
 * ��Ӧ�Ⱥ��� ����ѵ�����ݵ�����ֵ, ������Ӧ�Ⱥ���
 */
public interface Fitness
{
	/**
	 * ���ظ���Ӧ�Ⱥ������ص���С��Ӧ��ֵ (��Ϊ�������Ⱦɫ����Ӧ��ֵ)
	 * 
	 * @return
	 */
	public abstract double getMinFitness();
}
