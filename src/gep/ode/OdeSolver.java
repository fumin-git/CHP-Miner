package gep.ode;

import gep.num.NEvaluable;

import java.text.MessageFormat;

/**
 * ��һ�׳�΢�ַ�����
 */
public class OdeSolver
{
	private int m; // ��������

	private double[] K1, K2, K3, K4; // Runge-Kutta���е�K

	private NEvaluable[] equations; // �����Ҳ�
	private double[] v; // ��ʱ����

	public OdeSolver(int m)
	{
		this.m = m;

		K1 = new double[m];
		K2 = new double[m];
		K3 = new double[m];
		K4 = new double[m];
		v = new double[m + 1];
	}

	public void setEquations(NEvaluable[] equations)
	{
		this.equations = equations;
	}


	public void solve(double x, double[] y, double X, int step)
	{
		double h = (X - x) / step;
		for (int n = 0; n < step; n++, x += h)
		{
			step(x, y, h);
		}
	}

	/**
	 * ����һ��
	 */
	private final void step(double x, double[] y, double h)
	{
		// ����R-K����4��ϵ��
		// K1
		v[0] = x;
		for (int i = 0; i < m; i++)
		{
			v[i + 1] = y[i];
		}
		for (int i = 0; i < m; i++)
		{
			K1[i] = equations[i].evaluate(v);
		}

		// K2
		v[0] = x + h / 2;
		for (int i = 0; i < m; i++)
		{
			v[i + 1] = y[i] + h * K1[i] / 2;
		}
		for (int i = 0; i < m; i++)
		{
			K2[i] = equations[i].evaluate(v);
		}

		// K3
		v[0] = x + h / 2;
		for (int i = 0; i < m; i++)
		{
			v[i + 1] = y[i] + h * K2[i] / 2;
		}
		for (int i = 0; i < m; i++)
		{
			K3[i] = equations[i].evaluate(v);
		}

		// K4
		v[0] = x + h;
		for (int i = 0; i < m; i++)
		{
			v[i + 1] = y[i] + h * K3[i];
		}
		for (int i = 0; i < m; i++)
		{
			K4[i] = equations[i].evaluate(v);
		}

		// ����ϵ������һ��
		for (int i = 0; i < m; i++)
		{
			y[i] = y[i] + h * (K1[i] + 2 * K2[i] + 2 * K3[i] + K4[i]) / 6;
		}
	}

	public static void main(String[] args)
	{
		int m = 2, step = 5;

		OdeSolver ode = new OdeSolver(m);

		NEvaluable[] equations = new NEvaluable[] { new F1(), new F2() };
		double x = 0.0, X = 1.0;
		double[] y = { -0.4, -0.6 };

		long time = System.currentTimeMillis();

		ode.setEquations(equations);

		//		for (int i=0; i<1000000; i++)
		{
			//			if (i%10000==0) System.out.println(i);
			ode.solve(x, y, X, step);
		}

		// ��ȷ��: -0.3533944  2.5787466
		MessageFormat format = new MessageFormat("{0,number,0.0000000}  {1,number,0.0000000}");
		System.out.println(format.format(new Object[] { new Double(y[0]), new Double(y[1]) }));

		System.out.println(System.currentTimeMillis() - time);
	}
}

class F1 implements NEvaluable
{
	public double evaluate(double[] values)
	{
		double y1 = values[2];

		return y1;
	}
}

class F2 implements NEvaluable
{
	public double evaluate(double[] values)
	{
		double x = values[0];
		double y = values[1];
		double z = values[2];

		double r = Math.exp(2 * x) * Math.sin(x) - 2 * y + 2 * z;

		return r;
	}
}
