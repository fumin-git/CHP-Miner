package funcs;

import ga.Chromosome;
import ga.Protein;
import gep.Expression;
import gep.bool.BConstant;
import gep.bool.BExpression;
import gep.bool.BVariable;

public class CHPBoolDecoder extends CHPBoolGepDecoder {
    //	protected BVariable[] variables = new BVariable[343]; // 变量列表
    protected BConstant[] constants = new BConstant[2]; // 变量列表

    //改
    protected BVariable[] variables = new BVariable[343];//四种类型



    public CHPBoolDecoder(CHPGep CHPGep)
    {
        super(CHPGep);
    }

    /**
     * 重置解码器，准备开始解码下一个个体
     */
    public void reset()
    {
//		variables = new BVariable[26];

        //改
        variables = new BVariable[343];//四种类

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
        String genes = ((CHPBoolChromosome) chromosome).getGenes();
        char[] p = genes.toCharArray();

        BExpression root = (BExpression) decode0(p);

        return new CHPBoolFormula(root, variables);
    }

    // duanlei 2011-06-27
    public int getORFSum(Chromosome bestChromosome) {
        // TODO Auto-generated method stub
        return 0;
    }
}
