package numeric;

import gep.Formula;
import gep.bool.BEvaluable;
import gep.bool.BExpression;
import gep.num.NVariable;

/**
 * ����ʽ��ʽ
 */
public class NumFormula extends Formula implements BEvaluable
{
	private NVariable[] variables; // ������

	public NumFormula(BExpression tree, NVariable[] variables)
	{
		super(tree);
		this.variables = variables;
	}

	public void setValues(double[] values)
	{
		for (int index = 0; index < values.length && index < variables.length; index++)
		{
			NVariable variable = variables[index];
			if (variable == null) continue;

			variable.setValue(values[index]);
		}
	}
	
	public boolean evaluate()
	{
		return ((BExpression) tree).evaluate();
	}
	
	public String toString()
	{
		return tree.toString();
	}
}
