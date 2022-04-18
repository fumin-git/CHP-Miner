package gep.num;

import gep.Expression;
import gep.FunctionFactory;

/**
 * ���Һ������ʽ����
 */
public class CosFactory implements FunctionFactory
{

	public char getCode()
	{
		return Cos.code;
	}

	public int getArity()
	{
		return Cos.arity;
	}

	public Expression get(char code)
	{
		return (Expression) new Cos();
	}
}
