package ga;

/**
 * ��������
 * 
 * ����Ⱦɫ���ڲ����е��Ŵ������� �������顢�����
 */
public abstract class MutationOperator extends GeneticOperator
{
	public MutationOperator(double probability)
	{
		super(probability);
	}

	/**
	 * ʵʩ�Ŵ�����
	 */
	abstract public Chromosome mutate(Chromosome chromosome);
}
