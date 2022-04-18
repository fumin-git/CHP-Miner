package util;

/**
 * �˲���
 */
public class Filter
{
	private double frequency; // ��ͨ�˲���������Ƶ�� (ȫ��ͨ��Ϊ1.0)

	public Filter(double frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * �����ݽ����˲�(���ø���Ҷ�任)
	 */
	public void filter(double[] data)
	{
		if (frequency >= 1.0) return;

		int size = 1;
		while (size < data.length)
		{
			size <<= 1;
		}
		FFT fft = new FFT(size);

		// ׼�����ݲ����и���Ҷ���任
		double[] xr = new double[size];
		double[] xi = new double[size];
		for (int i = 0; i < data.length; i++)
		{
			xr[i] = data[i];
		}
		fft.fft(xr, xi);

		// ����Ƶ�����˵�
		int m = (int) (size * frequency);
		for (int i = m; i < size - m; i++)
		{
			xr[i] = 0.0;
			xi[i] = 0.0;
		}

		// ����Ҷ���任����ȡ������
		fft.ifft(xr, xi);
		for (int i = 0; i < data.length; i++)
		{
			data[i] = xr[i];
		}
	}
}
