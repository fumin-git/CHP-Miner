package miner;

import util.Closer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DatasetProcessor
 * @Description 处理数据集
 * @Author Xu Yifan
 * @Date 2021/10/30 13:24
 **/
class DatasetProcessor {
    /**
     * 处理原始数据集
     * @param datasetPath
     * @return
     */
    static List<String[]> getDataset(String datasetPath){
        BufferedReader reader = null;
        List<String[]> result = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(datasetPath));
            for (; ;){
                String line = reader.readLine();
                if(line == null)break;

                line = line.trim();
                if("".equals(line)) continue;
                String[] ss = line.split("[ \t]+");
                result.add(ss);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Closer.close(reader);
        }
        return result;
    }

    /**
     * 从原始数据集中得到枚举型数据
     */
    static List<boolean[]> getBoolDataset(List<String[]> dataset){
        if(dataset.size() == 0){
            return new ArrayList<>();
        }
        List<boolean[]> result = new ArrayList<>();
        for(String[] ss : dataset){
            boolean[] dd = new boolean[Parameter.splitIndex + 1];
            for (int i = 0; i <= Parameter.splitIndex; i++) {
                if(Integer.parseInt(ss[i])== 1)
                    dd[i] = true;
                else
                    dd[i] = false;
            }
            result.add(dd);
        }
        return result;
    }

    /**
     * 从原始数据中得到数值型数据
     */
    static List<double[]> getLogicalDataset(List<String[]> dataset){
        if(dataset.size() == 0){
            return new ArrayList<>();
        }
        List<double[]> result = new ArrayList<>();
        for (String[] ss : dataset){
            double[] dd = new double[ss.length - Parameter.splitIndex - 1];
            for (int i = Parameter.splitIndex + 1; i < ss.length; i++) {
                dd[i - Parameter.splitIndex - 1] = Double.parseDouble(ss[i]);
            }

            result.add(dd);
        }
        return result;
    }
}
