package gep.export;

import gep.Formula;

/**
 * ���ı��ķ�ʽ�����ʽ
 */
public interface TextExporter
{
	/**
	 * �����ʽ���ı����
	 * 
	 * @param formula
	 * @return
	 */
	public abstract String export(Formula formula);
}
