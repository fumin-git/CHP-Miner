package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * �ӷ����ʽ����
 */
public class PlusFactory implements FunctionFactory
{

	public char getCode()
	{
		return Plus.code;
	}

	public int getArity()
	{
		return Plus.arity;
	}

	public Expression get(char code)
	{
		return new Plus();
	}
}
