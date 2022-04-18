package funcs;

import ga.Initializer;
import ga.Population;

public class CHPCombineInitializer implements Initializer{
    private CHPGep CHPGep;
    private int size;
    private char[] comTerminals;
    private int boolSize;
    private int logicalSize;

    public CHPCombineInitializer(CHPGep CHPGep, int size){
        this.CHPGep = CHPGep;
        this.size = size;
//        this.comTerminals = comTerminals;
    }

    //todo 个体生成需要同时包含bool的terminal和数值的terminal （思路：传入布尔和数值个体的数量，布尔在前，数值在后，形成两个index的范围，判断产生的index是否都包含在两个范围中，如果在，才保存个体，否则，重新随机产生）
    @Override
    public Population generateInitialPopulation() {

        Population population = new Population();

        int geneNumber = CHPGep.getCombineGeneNumber();//基因个数
        int geneHead = CHPGep.getCombineGeneHead();
        int geneTail = CHPGep.getCombineGeneTail();
        int geneLength = CHPGep.getCombineGeneLength();//基因头部加尾部

        for (int k = 0; k < size; k++)
        {
            char[] genes = new char[geneLength * geneNumber];
            int index = 0;
            for (int j = 0; j < geneNumber; j++)
            {
                for (int i = 0; i < geneHead; i++)
                {
                    genes[index++] = CHPGep.getCombineHeadCode();
                }
                for (int i = 0; i < geneTail; i++)
                {
                    genes[index++] = CHPGep.getCombineTailCode();
                }
            }
            population.add(new CHPBoolChromosome(new String(genes)));//把基因字符数组转为string
        }

        return population;
    }
}
