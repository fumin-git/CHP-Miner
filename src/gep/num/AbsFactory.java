package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ����ֵ�������ʽ����
 */
public class AbsFactory implements FunctionFactory
{

	public char getCode()
	{
		return Abs.code;
	}

	public int getArity()
	{
		return Abs.arity;
	}

	public Expression get(char code)
	{
		return new Abs();
	}
}
