package funcs;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据集
 *
 * @author duanlei
 *
 */
public class CHPCombineDataset
{
    private List<boolean[]> data = new ArrayList<boolean[]>();

    public CHPCombineDataset(List<boolean[]> data){
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

    public List<boolean[]> getData()
    {
//		return lineData;
        return data;
    }
}
