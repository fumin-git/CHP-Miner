package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * �˷����ʽ����
 */
public class MultiplyFactory implements FunctionFactory
{

	public char getCode()
	{
		return Multiply.code;
	}

	public int getArity()
	{
		return Multiply.arity;
	}

	public Expression get(char code)
	{
		return new Multiply();
	}
}
