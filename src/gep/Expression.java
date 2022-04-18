package gep;

import java.util.List;

/**
 * ���ʽ
 */
public interface Expression
{
	/**
	 * ���ش���
	 * 
	 * @return
	 */
	public char getCode();

	/**
	 * ���ر��ʽ��Ŀ��(���м���������)
	 */
	public int getArity();

	/**
	 * ����ӱ��ʽ(������)���������ҵ�˳��
	 */
	public void addChild(Expression child);

	/**
	 * ���ظýڵ�������ӽڵ�
	 * 
	 * @return
	 */
	public List<Expression> getChildren();
}