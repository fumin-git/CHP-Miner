package ga;

/**
 * ������
 * 
 * ������ռ��еķ���ת��������ռ��е�ʵ�ʵĶ���
 */
public interface Decoder
{
	/**
	 * ���ý�������׼����ʼ������һ��Ⱦɫ��
	 */
	public void reset();

	/**
	 * ����
	 * 
	 * @param chromosome
	 * @return
	 */
	public Protein decode(Chromosome chromosome);

	int getORFSum(Chromosome bestChromosome);	// duanlei 11-06-27
}
