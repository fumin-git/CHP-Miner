package gep.export;

import ga.GepException;

/**
 * �����
 */
public interface Outputter
{
	/** �������־�ļ� */
	public void output(Node root, int id) throws GepException;

	public void output(Node root, String file) throws GepException;
}
