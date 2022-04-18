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
 * 用于数值优化的GEP解码器
 * 
 * 对于解码每一个个体需要做以下几件事情： 1) 调用 reset 重置解码器，准备解码当前个体 2) 调用 setConstantValues
 * 设置当前个体的常量 (常量值是属于每一个个体的) 3) 调用 decode 解码当前个体
 */
public class NumDecoder extends GepDecoder
{
//	protected NVariable[] variables = new NVariable[26]; // 变量列表
	protected NVariable[] variables = new NVariable[23]; // 变量列表

	public NumDecoder(GEP gep)
	{
		super(gep);
	}

	/**
	 * 设置常量值
	 * 
	 * @param values
	 */
	public void setConstantValues(double[] values)
	{
	}

	/**
	 * 重置解码器，准备开始解码下一个个体
	 */
	public void reset()
	{
//		variables = new NVariable[26];
		variables = new NVariable[23];
	}

	/**
	 * 取得指定代码的变量 在解码一个个体的过程中，对同一代码，始终返回同一个变量
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
		// 先重置解码器
		reset();

		NumChromosome dc = (NumChromosome) chromosome;

		// 解码关系函数
		BExpression root = (BExpression) ((NumGep) gep).getRelation(dc.getRelation());

		// 分别不等式左右两边
		
		char[][] genes = dc.getGenes();
		for (int i = 0; i < 2; i++)
		{
			NExpression e = (NExpression) decode0(genes[i]);
			root.addChild(e);
		}

		// 返回公式
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
