package gep;

/**
 * ���ʽ����(��������ģʽ)
 */
public interface FunctionFactory extends ExpressionFactory
{
	/**
	 * ���ر��ʽ�Ĵ���
	 */
	public char getCode();

	/**
	 * ���ر��ʽ��Ŀ��(���м���������)
	 */
	public int getArity();

}
