package ga;


import funcs.ThreeTuple;

import java.util.List;

/**
 * 评价器
 */
public interface Evaluator
{
	/**
	 * 评价一个个体，返回适应度值
	 * 
	 * @param protein
	 * @return
	 */
	public double evaluate(Protein protein);//原始的名字为evaluate
	public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein); //fumin 改
	public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein);//混合个体评估

//	public FuncContrastInfo evaluateInfo(Protein protein);
//	public FuncContrastInfo evaluateInfoGetConstant(Protein protein);

	public double getSup0();
	public double getSup1();
	public double getPosSamplesSize();
	public double getNegSamplesSize();
}
