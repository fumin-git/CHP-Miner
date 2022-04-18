package gep.num;

import gep.Expression;
import gep.ExpressionFactory;

/**
 * ��ֵ�������ʽ����
 */
public class NConstantFactory implements ExpressionFactory
{
	private double[] constantValues; // ������ֵ
	private NConstant[] constants; // ����

	public Expression get(char code)
	{
		int index = code - '0';

		if (constants[index] == null)
		{
			constants[index] = new NConstant(code, constantValues[index]);
		}

		return constants[index];
	}
}
