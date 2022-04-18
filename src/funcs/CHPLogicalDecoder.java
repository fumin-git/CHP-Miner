package funcs;

import numeric.NumChromosome;
import ga.Chromosome;
import ga.Protein;
import gep.Expression;
import gep.bool.BExpression;
import gep.num.NExpression;
import gep.num.NVariable;

public class CHPLogicalDecoder extends CHPLogicalGepDecoder {
    //	protected NVariable[] variables = new NVariable[26]; // �����б�
    protected NVariable[] variables = new NVariable[100]; // �����б�

    public CHPLogicalDecoder(CHPGep CHPGep)
    {
        super(CHPGep);
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
        variables = new NVariable[100];
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

        CHPLogicalChromosome dc = (CHPLogicalChromosome) chromosome;

        // �����ϵ����
        BExpression root = (BExpression) ((CHPGep) CHPGep).getRelation(dc.getRelation());

        // �ֱ𲻵�ʽ��������

        char[][] genes = dc.getGenes();
        for (int i = 0; i < 2; i++)
        {
            NExpression e = (NExpression) decode0(genes[i]);
            root.addChild(e);
        }

        // ���ع�ʽ
        return (Protein) new CHPLogicalFormula(root, variables);
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
            int geneLength = CHPGep.logicalGeneHead + CHPGep.logicalGeneHead * (CHPGep.logicalMaxArity - 1) + 1;

            for(int j = 0; j < CHPGep.logicalGeneNumber; j++)
            {
                int start = j * geneLength;
                int ORFLength = 1;
                for(int k = start; k < (start + geneLength); k++)
                {
                    if(CHPGep.isLogicalFunction(p[k]) == true)
                        ORFLength = ORFLength + CHPGep.getLogicalFunctionArity(p[k]) - 1;
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
