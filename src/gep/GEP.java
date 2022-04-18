package gep;

import ga.GA;
import ga.GepException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Gene Expression Programming
 */
public class GEP extends GA
{
	private char[] functions = new char[0]; // GEP�п���ʹ�õ����к�������
	private char[] terminals = new char[0]; // GEP�п���ʹ�õ������ս������

	private Map<Character, FunctionFactory> functionFactories = new HashMap<Character, FunctionFactory>(); // GEP�п���ʹ�õ����к���(�����ֵ�������ӳ��)

	private char linkFunction = '��'; // ���Ӻ���(����)
	private FunctionFactory linkFunctionFactory; // ���Ӻ������� 

	public int maxArity = 1; // ���������Ŀ��
	public int geneHead = 0; // ����ͷ������
	public int geneTail = 0; // ����β������
	public int geneNumber = 1; // ��������
	 
	public int maxFuncLimit = 0;	// ����ͷ���к��������������

	public void initialize()
	{
		super.initialize();
	}
	
	/**
	 * ��Ӻ�ѡ���ʽ�Ĺ���
	 * 
	 * @param factory
	 */
	public void addFunctionFactory(FunctionFactory factory)
	{
		functionFactories.put(new Character(factory.getCode()), factory);
	}


	public void setFunctionSet(String set) throws GepException
	{
		functions = set.toCharArray();
		for (int i = 0; i < functions.length; i++)
		{
			char function = functions[i];
			FunctionFactory factory = (FunctionFactory) functionFactories.get(new Character(functions[i]));

			if (factory == null) throw new GepException("Unknown function: '" + function + "'");

			int arity = factory.getArity();
			if (arity > maxArity)
			{
				maxArity = arity;
				geneTail = geneHead * (maxArity - 1) + 1;
			}
		}
	}


	public void setVariableSet(String set) throws GepException
	{
		for (int i = 0; i < set.length(); i++)
		{
			char code = set.charAt(i);
/*zuo:		if (!Character.isLowerCase(code)) throw new GepException("Unknown variable: '" + code + "'"); 
/*duan:		if (!Character.isLowerCase(code)&&!Character.isUpperCase(code)) throw new GepException("Unknown variable: '" + code + "'");*/		
///*wang:*/	if (!Character.isLowerCase(code/10));
		    if (code<970) throw new GepException("Unknown variable: '" + code + "'");//fu ��
		}

		terminals = (set + new String(terminals)).toCharArray();
	}


	public void setConstantSet(String set) throws GepException
	{
		for (int i = 0; i < set.length(); i++)
		{
			char code = set.charAt(i);
			if (!Character.isDigit(code)) throw new GepException("Unknown constant: '" + code + "'");
		}

		terminals = (set + new String(terminals)).toCharArray();
	}

	/**
	 * �������Ӻ���
	 * 
	 * @param c
	 * @throws GepException
	 */
	public void setLinkFunction(char c) throws GepException
	{
		linkFunction = c;
		linkFunctionFactory = (FunctionFactory) functionFactories.get(new Character(c));

		if (linkFunctionFactory == null) throw new GepException("Unknown function: '" + c + "'");
	}


	public Expression getFunction(char code)
	{
		FunctionFactory factory = (FunctionFactory) functionFactories.get(new Character(code));
		return factory.get(code);
	}

	/**
	 * �������Ӻ������ʽ
	 * 
	 * @return
	 */
	public Expression getLinkFunction()
	{
		return linkFunctionFactory.get(linkFunction);
	}

	/**
	 * �������Ӻ�����Ŀ��
	 * 
	 * @return
	 */
	public int getLinkFunctionArity()
	{
		return linkFunctionFactory.getArity();
	}

	//----------------------------------
	public int getGeneHead()
	{
		return geneHead;
	}

	public int getGeneTail()
	{
		return geneTail;
	}

	public int getGeneNumber()
	{
		return geneNumber;
	}
	
	public int getMaxFunc()
	{
		return maxFuncLimit;
	}

	public void setGeneHead(int i)
	{
		geneHead = i;
		geneTail = geneHead * (maxArity - 1) + 1;
	}

	public void setGeneTail(int i)
	{
		geneTail = i;
	}

	public void setGeneNumber(int i)
	{
		geneNumber = i;
	}
	
	public void setMaxFunc(int i)
	{
		maxFuncLimit = i;
	}

	/**
	 * ���ػ��򳤶�
	 * 
	 * @return
	 */
	public int getGeneLength()
	{
		return geneHead + geneTail;
	}

	/**
	 * �������������ͷ���Ĵ���
	 * 
	 * @return
	 */
	public char getHeadCode()
	{
		int index = (int) (Math.random() * (functions.length + terminals.length));//����0-functions.length + terminals.length �������
		if (index < functions.length) { return functions[index]; }
		return terminals[index - functions.length];
	}

	/**
	 * ���������ֻ����β������ (�ս������)
	 * 
	 * @return
	 */
	public char getTailCode()
	{
		return terminals[(int) (Math.random() * terminals.length)];
	}

	public boolean isFunction(char code)
	{
		for (int i = 0; i < functions.length; i++)
		{
			if (functions[i] == code) { return true; }
		}

		return false;
	}

	/**
	 * ����GEP������״��
	 */
	public void report(PrintWriter writer) throws GepException
	{
		writer.println("Functions          : " + new String(functions));
		writer.println("Terminals          : " + new String(terminals));
		writer.println("Link Function      : " + linkFunction);

		writer.println("Max Arity          : " + maxArity);
		writer.println("Head Length        : " + geneHead);
		writer.println("Tail Length        : " + geneTail);
		writer.println("Gene Number        : " + geneNumber);

		super.report(writer);
	}
	
	/**����ָ��������Ŀ��  2011-06-27
	 * @return
	 */
	public int getFunctionArity(char code)
	{
		FunctionFactory factory = (FunctionFactory) functionFactories.get(new Character(code));
		return factory.getArity() ;
	}

}
