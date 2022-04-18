package nominal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import util.Closer;

public class BoolDataSet
{
	private List<boolean[]> data = new ArrayList<boolean[]>();
//	private List<boolean[]> lineData = new ArrayList<boolean[]>();

	public BoolDataSet(List<boolean[]> data){
		this.data = data;

		for(int i = 0; i < this.data.get(0).length; i ++)//列数（属性个数）
		{
			boolean[] linedd = new boolean[this.data.size()];
			for (int j = 0; j < this.data.size(); j ++)//data.size()=6,行数
			{
				linedd[j] = this.data.get(j)[i];
			}
//			lineData.add(linedd);
		}
	}
	public BoolDataSet(String file) throws Exception
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
				boolean[] dd = new boolean[ss.length];
				for (int i = 0; i < ss.length; i++)
				{
					if(Integer.valueOf(ss[i])== 1)//需要改 判断输入是否为1，1则true，反之false
						dd[i] = Boolean.valueOf("true");
					else
						dd[i] = Boolean.valueOf("false");
				}
				
				data.add(dd);
			}
			
//			for(int i = 0; i < data.get(0).length; i ++)//列数（属性个数）
//			{
//				boolean[] linedd = new boolean[data.size()];
//				for (int j = 0; j < data.size(); j ++)//data.size()=6,行数
//				{
//					linedd[j] = data.get(j)[i];
//				}
//				lineData.add(linedd);
//			}
		}
		finally
		{
			Closer.close(reader);
		}
	}
	
	public List<boolean[]> getData()
	{
//		return lineData;
		return data;
	}
}
