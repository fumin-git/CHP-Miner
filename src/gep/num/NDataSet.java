package gep.num;

/**
 * ��ֵ���ݼ�
 */
public interface NDataSet
{
	/**
	 * ��������Ŀ��ֵ
	 * 
	 * @return
	 */
	public abstract double[] getTargets();

	/**
	 * ������������
	 * 
	 * @return
	 */
	public abstract double[][] getParameters();
}