package numeric;

import ga.Chromosome;
import ga.Protein;
import gep.Expression;
import gep.GEP;
import gep.GepDecoder;
import gep.bool.BExpression;
import gep.num.NExpression;
import gep.num.NVariable;

/**
 * ������ֵ�Ż���GEP������
 * 
 * ���ڽ���ÿһ��������Ҫ�����¼������飺 1) ���� reset ���ý�������׼�����뵱ǰ���� 2) ���� setConstantValues
 * ���õ�ǰ����ĳ��� (����ֵ������ÿһ�������) 3) ���� decode ���뵱ǰ����
 */
public class NumDecoder extends GepDecoder
{
//	protected NVariable[] variables = new NVariable[26]; // �����б�
	protected NVariable[] variables = new NVariable[23]; // �����б�

	public NumDecoder(GEP gep)
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
	}

	/**
	 * ���ý�������׼����ʼ������һ������
	 */
	public void reset()
	{
//		variables = new NVariable[26];
		variables = new NVariable[23];
	}

	/**
	 * ȡ��ָ������ı��� �ڽ���һ������Ĺ����У���ͬһ���룬ʼ�շ���ͬһ������
	 * 
	 * @param code
	 * @return
	 */
	public Expression getVariable(char code)
	{
		int index = code - 970;
		if (variables[index] == null)
		{
			variables[index] = new NVariable(code);
		}
		return variables[index];
	}

	public Protein decode(Chromosome chromosome)
	{
		// �����ý�����
		reset();

		NumChromosome dc = (NumChromosome) chromosome;

		// �����ϵ����
		BExpression root = (BExpression) ((NumGep) gep).getRelation(dc.getRelation());

		// �ֱ𲻵�ʽ��������
		
		char[][] genes = dc.getGenes();
		for (int i = 0; i < 2; i++)
		{
			NExpression e = (NExpression) decode0(genes[i]);
			root.addChild(e);
		}

		// ���ع�ʽ
		return (Protein) new NumFormula(root, variables);
	}

	protected Expression getConstant(char code)
	{
		return null;
	}

// duanlei  11-06-27
	public int getORFSum(Chromosome chromosome) {
		int ORFSum = 0;
		
		NumChromosome dc = (NumChromosome) chromosome;
		char[][] genes = dc.getGenes();
		for (int i = 0; i < 2; i++)
		{
			char [] p = genes[i];
			int geneLength = gep.geneHead + gep.geneHead * (gep.maxArity - 1) + 1;
		
			for(int j = 0; j < gep.geneNumber; j++)
			{
				int start = j * geneLength;
				int ORFLength = 1; 
				for(int k = start; k < (start + geneLength); k++)
				{
					if(gep.isFunction(p[k]) == true)
						ORFLength = ORFLength + gep.getFunctionArity(p[k]) - 1;
					else
						ORFLength -= 1;
					ORFSum++;
					if(ORFLength == 0)
						break;
				}
			}
		}
		
		return ORFSum;
	}

}
