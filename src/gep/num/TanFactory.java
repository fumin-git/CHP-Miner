package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ���к������ʽ����
 */
public class TanFactory implements FunctionFactory
{

	public char getCode()
	{
		return Tan.code;
	}

	public int getArity()
	{
		return Tan.arity;
	}

	public Expression get(char code)
	{
		return new Tan();
	}
}
