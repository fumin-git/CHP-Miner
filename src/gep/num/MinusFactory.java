package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * �������ʽ����
 */
public class MinusFactory implements FunctionFactory
{

	public char getCode()
	{
		return Minus.code;
	}

	public int getArity()
	{
		return Minus.arity;
	}

	public Expression get(char code)
	{
		return new Minus();
	}
}
