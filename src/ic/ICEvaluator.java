package ic;

import gep.GEP;
import gep.bool.BEvaluator;

/**
 * ���Żع��������
 */
public class ICEvaluator extends BEvaluator
{

	public ICEvaluator(GEP gep)
	{
		super(gep);
	}

	/**
	 * װ��ѵ������
	 */
	public void loadTrainData(String file)
	{
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				int a = i * j;

				boolean[] sample = new boolean[1 + 3 + 3];

				int k = 0;
				//			sample[k++] = (a & 32) != 0;
				sample[k++] = (a & 16) != 0;
				//			sample[k++] = (a & 8) != 0;
				//			sample[k++] = (a & 4) != 0;
				//			sample[k++] = (a & 2) != 0;
				//			sample[k++] = (a & 1) != 0;

				sample[k++] = (i & 4) != 0;
				sample[k++] = (i & 2) != 0;
				sample[k++] = (i & 1) != 0;

				sample[k++] = (j & 4) != 0;
				sample[k++] = (j & 2) != 0;
				sample[k++] = (j & 1) != 0;

				trainData.add(sample);
			}
	}
}
