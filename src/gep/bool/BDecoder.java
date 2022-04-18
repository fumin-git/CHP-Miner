package gep.bool;

import ga.Chromosome;
import ga.Protein;
import gep.Expression;
import gep.GEP;
import gep.GepChromosome;
import gep.GepDecoder;

/**
 * 用于布尔优化的GEP解码器
 */
public class BDecoder extends GepDecoder
{
//	protected BVariable[] variables = new BVariable[343]; // 变量列表
	protected BConstant[] constants = new BConstant[2]; // 变量列表

	//改
	protected BVariable[] variables = new BVariable[343];//四种类型

	
	
	public BDecoder(GEP gep)
	{
		super(gep);
	}

	/**
	 * 重置解码器，准备开始解码下一个个体
	 */
	public void reset()
	{
//		variables = new BVariable[26];
		
		//改
		variables = new BVariable[343];//四种类
//		variables = new BVariable[128];//随机尿
//		variables = new BVariable[58];//生化4
//		variables = new BVariable[178];//生化1+4
//		variables = new BVariable[118];//全血
//		variables = new BVariable[78];//大便
		
	}

	/**
	 * 取得指定代码的变量 在解码一个个体的过程中，对同一代码，始终返回同一个变量
	 * 
	 * @param code
	 * @return
	 */
	public Expression getVariable(char code)
	{
//		int index = code - 'a';
		int index = code - 970;
		if (variables[index] == null)
		{
			variables[index] = new BVariable(code);
		}
		return variables[index];
	}

	public Expression getConstant(char code)
	{
		int index = code - 'a';
		if (constants[index] == null)
		{
			constants[index] = new BConstant(code, index != 0);
		}
		return constants[index];
	}

	public Protein decode(Chromosome chromosome)
	{
		// 先重置解码器
		reset();

		// 分别解码每一个基因
		String genes = ((GepChromosome) chromosome).getGenes();
		char[] p = genes.toCharArray();

		BExpression root = (BExpression) decode0(p);

		return new BFormula(root, variables);
	}

	// duanlei 2011-06-27
	public int getORFSum(Chromosome bestChromosome) {
		// TODO Auto-generated method stub
		return 0;
	}

}
