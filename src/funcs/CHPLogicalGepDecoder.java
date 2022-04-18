package funcs;

import ga.Chromosome;
import ga.Decoder;
import ga.Protein;
import gep.Expression;

import java.util.LinkedList;
import java.util.List;

public abstract class CHPLogicalGepDecoder implements Decoder {
    protected CHPGep CHPGep;

    public CHPLogicalGepDecoder(CHPGep CHPGep)
    {
        this.CHPGep = CHPGep;
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
            expression = CHPGep.getLogicalFunction(code);
        }

        return expression;
    }


    protected Expression decode0(char[] genes)
    {
        int geneNumber = CHPGep.getLogicalGeneNumber();
        int geneLength = CHPGep.getLogicalGeneLength();

        Expression[] expressions = new Expression[geneNumber];
        for (int i = 0; i < geneNumber; i++)
        {
            if(CHPGep.getLogicalMaxFunc() == 0)
                expressions[i] = decode1(genes, i * geneLength);
            else
                expressions[i] = decode2(genes, i * geneLength);
        }

        // �����Ӻ�����������������
        int arity = CHPGep.getLogicalLinkFunctionArity();
        Expression root = expressions[0];
        int index = 1;
        while (index < expressions.length)
        {
            Expression expression = CHPGep.getLogicalLinkFunction();
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

    /**
     * ����һ���������ƻ���ͷ���к����������Ŀ��
     *
     * @param genes
     * @param start
     *��ʼλ��
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
                if(numFunctions == CHPGep.getLogicalMaxFunc() && reachMax == false)
                {
                    reachMax = true;
                    index = start + CHPGep.logicalGeneHead;
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
