package gep.bool;

import gep.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * ����
 */
public class BVariable implements BExpression
{
	public static final int arity = 0;

	private char code;
	private boolean value;

	public BVariable(char code)
	{
		this.code = code;
	}

	public char getCode()
	{
		return code;
	}

	public int getArity()
	{
		return arity;
	}

	public void addChild(Expression child)
	{
		throw new IllegalStateException("ADD_CHILD_ERROR");
	}

	public void setValue(boolean value)
	{
		this.value = value;
	}

	public boolean evaluate()
	{
		return value;
	}

	public String toString()
	{
//		return "" + code;
		return "a"+String.valueOf(code-970);
	}

	public List<Expression> getChildren()
	{
		List<Expression> children = new ArrayList<Expression>();

		return children;
	}
}
