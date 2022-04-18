package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ���Һ������ʽ����
 */
public class SinFactory implements FunctionFactory
{

	public char getCode()
	{
		return Sin.code;
	}

	public int getArity()
	{
		return Sin.arity;
	}

	public Expression get(char code)
	{
		return new Sin();
	}
}
