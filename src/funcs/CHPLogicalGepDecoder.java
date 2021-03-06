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
     * 返回当前解码过程中 code 表示的变量
     *
     * @param code
     * @return
     */
    protected abstract Expression getVariable(char code);

    /**
     * 返回当前解码过程中 code 表示的常量
     *
     * @param code
     * @return
     */
    protected abstract Expression getConstant(char code);

    /**
     * 返回指定代码对应的表达式
     *
     * @param code
     * @return
     */
    protected Expression getExpression(char code)
    {
        Expression expression;

        if (code>=970) // 改 是变量
        {
            expression = getVariable(code);
        }

        else if (Character.isDigit(code)) // 是常量
        {
            expression = getConstant(code);
        }
        else // 是普通函数
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

        // 用连接函数将它们连接起来
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
     * 解码一个基因
     *
     * @param genes
     * @param start
     *             开始位置
     * @return
     */
    protected Expression decode1(char[] genes, int start)
    {
        // 采用队列来解码
        List<Expression> queue = new LinkedList<Expression>();

        // 将初始节点推入队列尾部
        int index = start;
        char code = genes[index++];
        Expression root = getExpression(code);
        queue.add(root);

        // 依次弹出队列头部的元素进行处理
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
     * 解码一个基因（限制基因头部中函数的最大数目）
     *
     * @param genes
     * @param start
     *开始位置
     * @return
     */
    protected Expression decode2(char[] genes, int start)
    {
        int numFunctions = 0;	// 记录gene中function的总数

        // 采用队列来解码
        List<Expression> queue = new LinkedList<Expression>();

        // 将初始节点推入队列尾部
        int index = start;
        char code = genes[index++];
        if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false && code<970)// 改 是函数
            numFunctions ++;

        Expression root = getExpression(code);
        queue.add(root);

        boolean reachMax = false;

        // 依次弹出队列头部的元素进行处理
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
//				if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false) // 是函数
                if (Character.isLowerCase(code)==false && Character.isUpperCase(code)==false && Character.isDigit(code)==false && code<970) //改  是函数
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
