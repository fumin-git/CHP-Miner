package funcs;

import gep.Formula;
import gep.bool.BEvaluable;
import gep.bool.BExpression;
import gep.bool.BVariable;

import java.util.List;

public class CHPBoolFormula extends Formula implements BEvaluable {
    private BVariable[] variables; // ������

    public CHPBoolFormula(BExpression tree, BVariable[] variables)
    {
        super(tree);
        this.variables = variables;
    }

    public void setValues(boolean[] values)
    {
        for (int index = 0; (index < values.length) && (index < variables.length); index++)
        {
            BVariable variable = variables[index];//�����еı���
            if (variable == null) continue;

            variable.setValue(values[index]);//values[index]��ʾʵ���ж�Ӧindex�����Ե�ȡֵ
        }
    }
    public void setCombineValues(List<Boolean> values)
    {
        for (int index = 0; (index < values.size()) && (index < variables.length); index++)
        {
            BVariable variable = variables[index];//�����еı���
            if (variable == null) continue;

            variable.setValue(values.get(index));//values[index]��ʾʵ���ж�Ӧindex�����Ե�ȡֵ
        }
    }


    public boolean evaluate()
    {
        return ((BExpression) tree).evaluate();
    }
    public String toString()
    {
        return tree.toString();
    }
}
