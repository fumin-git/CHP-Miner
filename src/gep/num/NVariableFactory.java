package gep.num;

import gep.Expression;
import gep.ExpressionFactory;

/**
 * ��ֵ��������
 */
public class NVariableFactory implements ExpressionFactory
{
	private NVariable[] variables; // �����б�

	public Expression get(char code)
	{
		int index = code - '0';

		if (variables[index] == null)
		{
			variables[index] = new NVariable(code);
		}

		return variables[index];
	}
}
