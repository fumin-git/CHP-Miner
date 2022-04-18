package gep;

import ga.GA;
import ga.GepException;
import ga.Reporter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GEP�ı���������
 */
public class GepReporter implements Reporter
{
	private final String LOG_FILE = "d:/work/zGEP/data/gep.log";

	public GepReporter()
	{
	}

	public void report(GA ga, Date now) throws GepException
	{
		//		GEP gep = (GEP) ga;

		// ���ļ�
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(new FileWriter(LOG_FILE, true));
			DateFormat format1 = new SimpleDateFormat("yyyyMMddkkmmss");
			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			writer.println();
			writer.println("=======================================================");
			writer.println(format1.format(now));
			writer.println("------------------------------------------");
			writer.println("Date               : " + format2.format(now));

			ga.report(writer);
		}
		catch (Exception e)
		{
			throw new GepException("���ɱ������", e);
		}
		finally
		{
			if (writer != null) try
			{
				writer.close();
			}
			catch (Exception e)
			{
			}
		}

	}

}
