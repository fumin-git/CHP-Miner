package gep.export;

import ga.GepException;
import gep.Formula;

/**
 * ͼ������� ���ҵ��Ĺ�ʽ��ͼ�εķ�ʽ���
 */
public interface GraphExporter
{
	/**
	 * ���������ʽ��ͼ�α�ʾ����־�ļ�
	 * 
	 * @param formula
	 * @throws GepException
	 */
	public abstract void export(Formula formula, int id) throws GepException;

	/**
	 * ���������ʽ��ͼ�α�ʾ��ָ���ļ�
	 * 
	 * @param formula
	 * @param file
	 * @throws GepException
	 */
	public abstract void export(Formula formula, String file) throws GepException;
}
