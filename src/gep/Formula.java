package gep;

import ga.Protein;

/**
 * ��ʽ
 * 
 * Ӧ�ð���һ��Expression��һ���������Լ�һ�������б�
 */
public class Formula implements Protein
{
	protected Expression tree; // ���ʽ

	public Formula(Expression tree)
	{
		this.tree = tree;
	}

	/**
	 * ȡ�ñ��ʽ
	 */
	public Expression getExpression()
	{
		return tree;
	}

	public String toString()
	{
		return tree.toString();
	}
}
