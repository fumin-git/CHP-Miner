package funcs;

import gep.Formula;
import gep.bool.BEvaluable;
import gep.bool.BExpression;
import gep.bool.BVariable;

import java.util.List;

public class CHPBoolFormula extends Formula implements BEvaluable {
    private BVariable[] variables; // 变量表

    public CHPBoolFormula(BExpression tree, BVariable[] variables)
    {
        super(tree);
        this.variables = variables;
    }

    public void setValues(boolean[] values)
    {
        for (int index = 0; (index < values.length) && (index < variables.length); index++)
        {
            BVariable variable = variables[index];//基因中的变量
            if (variable == null) continue;

            variable.setValue(values[index]);//values[index]表示实例中对应index下属性的取值
        }
    }
    public void setCombineValues(List<Boolean> values)
    {
        for (int index = 0; (index < values.size()) && (index < variables.length); index++)
        {
            BVariable variable = variables[index];//基因中的变量
            if (variable == null) continue;

            variable.setValue(values.get(index));//values[index]表示实例中对应index下属性的取值
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
