package gep.num;

import ga.Fitness;

/**
 * ��ֵ����Ӧ�Ⱥ���
 */
public interface NFitness extends Fitness
{

	public abstract double calculate(double[] values);
}
