package gep.num;

import gep.Formula;

/**
 * GEP��ֵ��ʽ
 */
public class NFormula extends Formula implements NEvaluable
{
	private NVariable[] variables; // ������

	public NFormula(NExpression tree, NVariable[] variables)
	{
		super(tree);
		this.variables = variables;
	}

	public double evaluate(double[] values)
	{
		// ���ñ�����ֵ
		for (int index = 0; index < values.length && index < variables.length; index++)
		{
			NVariable variable = variables[index];
			if (variable == null) continue;

			variable.setValue(values[index]);
		}

		// ���㹫ʽ��ֵ
		return ((NExpression) tree).evaluate();
	}
}
