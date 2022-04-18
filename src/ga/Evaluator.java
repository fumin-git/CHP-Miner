package ga;


import funcs.ThreeTuple;

import java.util.List;

/**
 * ������
 */
public interface Evaluator
{
	/**
	 * ����һ�����壬������Ӧ��ֵ
	 * 
	 * @param protein
	 * @return
	 */
	public double evaluate(Protein protein);//ԭʼ������Ϊevaluate
	public ThreeTuple<Double, List<Boolean>, List<Boolean>> evaluate1(Protein protein); //fumin ��
	public ThreeTuple<Double, Double, Double> combineEvaluate(Protein protein);//��ϸ�������

//	public FuncContrastInfo evaluateInfo(Protein protein);
//	public FuncContrastInfo evaluateInfoGetConstant(Protein protein);

	public double getSup0();
	public double getSup1();
	public double getPosSamplesSize();
	public double getNegSamplesSize();
}
