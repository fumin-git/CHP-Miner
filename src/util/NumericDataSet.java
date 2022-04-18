package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import util.Closer;

/**
 * 数据集
 * 
 * @author duanlei
 *
 */
public class NumericDataSet
{
	private List<double[]> colData = new ArrayList<double[]>();     //	按列存储每个变量的取值
	private List<double[]> lineData = new ArrayList<double[]>();	//	按行存储每个变量的取值
	
	public NumericDataSet(String file) throws Exception
	{
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			
			for (; ; )
			{
				String line = reader.readLine();
				if (line == null) break;
				
				line = line.trim();
				if (line.equals("")) continue;
				
				String[] ss = line.split("[ \t]+");
				double[] dd = new double[ss.length];
				for (int i = 0; i < ss.length; i++)
					dd[i] = Double.valueOf(ss[i]);
				
				colData.add(dd);
			}
			
			for(int i = 0; i < colData.get(0).length; i ++)
			{
				double[] linedd = new double[colData.size()];
				for (int j = 0; j < colData.size(); j ++)
				{
					linedd[j] = colData.get(j)[i];
				}
				lineData.add(linedd);
			}
		}
		finally
		{
			Closer.close(reader);
		}
	}
	
	/*
	 * 每一行是变量的全部取值
	 */
	public List<double[]> getDataLine()
	{
		return lineData;
	}
	
	/*
	 * 每一列是变量的全部取值
	 */
	public List<double[]> getDataCol()
	{
		return colData;
	}
}