package nominal;

import gep.Formula;
import gep.bool.BEvaluable;
import gep.bool.BExpression;
import gep.bool.BVariable;

/**
 * ����ʽ��������
 */
public class BoolFormula extends Formula implements BEvaluable
{
	private BVariable[] variables; // ������

	public BoolFormula(BExpression tree, BVariable[] variables)
	{
		super(tree);
		this.variables = variables;
	}

	public void setValues(boolean[] values)
	{
		for (int index = 0; index < values.length && index < variables.length; index++)
		{
			BVariable variable = variables[index];
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
