package gep;

import ga.Chromosome;
import ga.Decoder;
import ga.Protein;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ֵ�Ż���GEP������
 */
public abstract class GepDecoder implements Decoder
{
	protected GEP gep;

	public GepDecoder(GEP gep)
	{
		this.gep = gep;
	}

	/**
	 * ���ص�ǰ��������� code ��ʾ�ı���
	 * 
	 * @param code
	 * @return
	 */
	protected abstract Expression getVariable(char code);

	/**
	 * ���ص�ǰ��������� code ��ʾ�ĳ���
	 * 
	 * @param code
	 * @return
	 */
	protected abstract Expression getConstant(char code);

	/**
	 * ����ָ�������Ӧ�ı��ʽ
	 * 
	 * @param code
	 * @return
	 */
	protected Expression getExpression(char code)
	{
		Expression expression;
//		if (Character.isLowerCase(code)||Character.isUpperCase(code)) // �Ǳ���
//		{
//			expression = getVariable(code);
//		}
		
//		if (Character.isLowerCase(code/10)) // �Ǳ���
//		if (Character.isLowerCase(code)) // �Ǳ���
//		  {
//		   expression = getVariable(code);
//		  }
		
		if (code>=970) // �� �Ǳ���
		  {
		   expression = getVariable(code);
		  }
		
		else if (Character.isDigit(code)) // �ǳ���
		{
			expression = getConstant(code);
		}
		else // ����ͨ����
		{
			expression = gep.getFunction(code);
		}

		return expression;
	}


	protected Expression decode0(char[] genes)
	{
		int geneNumber = gep.getGeneNumber();
		int geneLength = gep.getGeneLength();
		
		Expression[] expressions = new Expression[geneNumber];
		for (int i = 0; i < geneNumber; i++)
		{
			if(gep.getMaxFunc() == 0)
				expressions[i] = decode1(genes, i * geneLength);
			else
				expressions[i] = decode2(genes, i * geneLength);
		}

		// �����Ӻ�����������������
		int arity = gep.getLinkFunctionArity();
		Expression root = expressions[0];
		int index = 1;
		while (index < expressions.length)
		{
			Expression expression = gep.getLinkFunction();
			expression.addChild(root);
			for (int i = 1; i < arity; i++)
			{
				expression.addChild(expressions[index++]);
			}
			root = expression;
		}

		return root;
	}

	/**
	 * ����һ������
	 * 
	 * @param genes
	 * @param start
	 *             ��ʼλ��
	 * @return
	 */
	protected Expression decode1(char[] genes, int start)
	{
		// ���ö��������� 
		List<Expression> queue = new LinkedList<Expression>();

		// ����ʼ�ڵ��������β��
		int index = start;
		char code = genes[index++];
		Expression root = getExpression(code);
		queue.add(root);

		// ���ε�������ͷ����Ԫ�ؽ��д���
		while (!queue.isEmpty())
		{
			Expression expression = (Expression) queue.remove(0);
			int arity = expression.getArity();

			for (int i = 0; i < arity; i++)
			{
				code = genes[index++];
				Expression child = getExpression(code);

				expression.addChild(child);
				queue.add(child);
			}
		}

		return root;
	}
	
	

	
	public static boolean isSpecialChar(char code) {
		
//        String regEx = "[ _@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]|\n|\r|\t";
		String str = Character.toString(code);
		String regEx = "[!&|^]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

	
	/**
	 * ����һ���������ƻ���ͷ���к����������Ŀ��
	 * 
	 * @param genes
	 * @param start
	 *            ��ʼλ��
	 * @return
	 */
	protected Expression decode2(char[] genes, int start)
	{
		int numFunctions = 0;	// ��¼gene��function������
		
		// ���ö��������� 
		List<Expression> queue = new LinkedList<Expression>();

		// ����ʼ�ڵ��������β��
		int index = start;
		char code = genes[index++];
//		if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false)// �Ǻ���
		if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false && code<970)// �� �Ǻ���
			numFunctions ++;
		
		Expression root = getExpression(code);
		queue.add(root);
		
		boolean reachMax = false;

		// ���ε�������ͷ����Ԫ�ؽ��д���
		while (!queue.isEmpty())
		{
			Expression expression = (Expression) queue.remove(0);
			int arity = expression.getArity();

			for (int i = 0; i < arity; i++)
			{
				if(numFunctions == gep.getMaxFunc() && reachMax == false)
				{
					reachMax = true;
					index = start + gep.geneHead;
				}
				code = genes[index++];
//				if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false) // �Ǻ���
				if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false && code<970) //��  �Ǻ���
					numFunctions ++;
				Expression child = getExpression(code);

				expression.addChild(child);
				queue.add(child);
			}
		}

		return root;
	}
	

	public abstract void reset();

	public abstract Protein decode(Chromosome chromosome);

	public String toString()
	{
		return getClass().getName();
	}
}
