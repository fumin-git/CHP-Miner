package gep.bool;

import gep.Formula;

/**
 * 布尔公式
 */
public class BFormula extends Formula implements BEvaluable
{
	private BVariable[] variables; // 变量表

	public BFormula(BExpression tree, BVariable[] variables)
	{
		super(tree);
		this.variables = variables;
	}

	public void setValues(boolean[] values)
	{
		for (int index = 0; (index < values.length) && (index < variables.length); index++)
		{
			BVariable variable = variables[index];//基因中的变量
			if (variable == null) continue;

			variable.setValue(values[index]);//values[index]表示实例中对应index下属性的取值（true 或 false）
		}
	}

	public boolean evaluate()
	{
		return ((BExpression) tree).evaluate();
	}
}
