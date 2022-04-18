package gep.num;

import ga.Fitness;

/**
 * 数值的适应度函数
 */
public interface NFitness extends Fitness
{

	public abstract double calculate(double[] values);
}
