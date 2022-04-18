package ga;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * �ֶ�ֹͣ�� �˹��������ֹͣ�ź�
 */
public class ManualStopper implements Stopper, Runnable
{
	private boolean stopSignal = false;
	private boolean pauseSignal = false;

	private JFrame frame;
	private JButton pauseButton;
	private JButton stopButton;

	public ManualStopper(GA ga)
	{
		new Thread(this).start();
	}

	public boolean canStop()
	{
		synchronized (this)
		{
			try
			{
				while (pauseSignal)
					wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		return stopSignal;
	}

	public void run()
	{
		initializeComponent();
	}

	@SuppressWarnings("deprecation")
	private void initializeComponent()
	{
		frame = new JFrame();
		frame.setTitle("�Ŵ��㷨");
		frame.getContentPane().setLayout(new GridLayout());
		pauseButton = new JButton("��ͣ");
		frame.getContentPane().add(pauseButton);
		stopButton = new JButton("����");
		frame.getContentPane().add(stopButton);

		pauseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pauseButtonOnClick(e);
			}
		});

		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopButtonOnClick(e);
			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.show();
	}

	private void pauseButtonOnClick(ActionEvent e)
	{
		synchronized (this)
		{
			if (pauseSignal)
			{
				// ������ͣ
				//				time = System.currentTimeMillis() - time;
				//				ga.startTime -= time;
				pauseSignal = false;
				pauseButton.setText("��ͣ");
			}
			else
			{
				// ��ʼ��ͣ
				//				time = System.currentTimeMillis();
				pauseSignal = true;
				pauseButton.setText("����");
			}
			notify();
		}
	}

	private void stopButtonOnClick(ActionEvent e)
	{
		synchronized (this)
		{
			pauseSignal = false;
			stopSignal = true;
			notify();
		}

		frame.dispose();
	}

	public String toString()
	{
		return getClass().getName();
	}
}
