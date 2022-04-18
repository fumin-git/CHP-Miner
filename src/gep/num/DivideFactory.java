package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ���������ʽ����
 */
public class DivideFactory implements FunctionFactory
{

	public char getCode()
	{
		return Divide.code;
	}

	public int getArity()
	{
		return Divide.arity;
	}

	public Expression get(char code)
	{
		return new Divide();
	}
}
