package gep.num;

import ga.Chromosome;
import ga.Protein;
import gep.Expression;
import gep.GEP;
import gep.GepChromosome;
import gep.GepDecoder;

/**
 * ������ֵ�Ż���GEP������
 * 
 * ���ڽ���ÿһ��������Ҫ�����¼������飺 1) ���� reset ���ý�������׼�����뵱ǰ���� 2) ���� setConstantValues
 * ���õ�ǰ����ĳ��� (����ֵ������ÿһ�������) 3) ���� decode ���뵱ǰ����
 */
public class NDecoder extends GepDecoder
{
	protected double[] constantValues = new double[10]; // ����ֵ�б� 
	protected NVariable[] variables = new NVariable[52]; // �����б� a,b,...,z,A,B,...,Z
	protected NConstant[] constants = new NConstant[10]; // �����б�

	public NDecoder(GEP gep)
	{
		super(gep);
	}

	/**
	 * ���ó���ֵ
	 * 
	 * @param values
	 */
	public void setConstantValues(double[] values)
	{
		this.constantValues = values;
	}

	/**
	 * ���ý�������׼����ʼ������һ������
	 */
	public void reset()
	{
		variables = new NVariable[52];
		constants = new NConstant[10];
	}

	/**
	 * ȡ��ָ������ı��� �ڽ���һ������Ĺ����У���ͬһ���룬ʼ�շ���ͬһ������
	 * 
	 * @param code
	 * @return
	 */
	public Expression getVariable(char code)
	{
//		int index = 0;
//		if(Character.isLowerCase(code) == true)
//			index = code - 'a';
//		else if(Character.isUpperCase(code) == true)
//			index = code - 'A'+ 26;
//		else
//			System.out.println("ERROR in Varialbes!");
		
//		int index = code - 970;
		int index = 0;
		
		if (variables[index] == null)
		{
			variables[index] = new NVariable(code);
		}
		return variables[index];
	}

	public Expression getConstant(char code)
	{
		final double[] v = new double[] { 0.1315, 0.2128, 0.3443, 0.5571, 0.9015, 1.4588, 2.3605, 3.8195, 6.1804, 10.0007 };

		int index = code - '0';
		if (constants[index] == null)
		{
			// TODO ����ֱ�������˳���
			//			constants[index] = new NConstant(code, constantValues[index]);
			constants[index] = new NConstant(code, v[index]);

		}
		return constants[index];
	}

	public Protein decode(Chromosome chromosome)
	{
		// �����ý�����
		reset();

		// �ֱ����ÿһ������
		String genes = ((GepChromosome) chromosome).getGenes();
		char[] p = genes.toCharArray();

		NExpression root = (NExpression) decode0(p);

		return (Protein) new NFormula(root, variables);
	}

	// duanlei 2011-06-27
	public int getORFSum(Chromosome bestChromosome) {
		// TODO Auto-generated method stub
		return 0;
	}
}
