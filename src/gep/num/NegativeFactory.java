package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ���ű��ʽ����
 */
public class NegativeFactory implements FunctionFactory
{
	public char getCode()
	{
		return Negative.code;
	}

	public int getArity()
	{
		return Negative.arity;
	}

	public Expression get(char code)
	{
		return new Negative();
	}
}
