package ga;

/**
 * ��������
 * 
 * ���������߶������Ⱦɫ�彻�����ͬ���������Ӵ�Ⱦɫ����Ŵ������� �������㡢��㽻���
 */
abstract public class CrossoverOperator extends GeneticOperator
{
	protected CrossoverOperator(double probability)
	{
		super(probability);
	}

	/**
	 * ʵʩ�Ŵ�����
	 */
	abstract public Chromosome[] operate(Chromosome parents[]);
}
