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

    //todo ����������Ҫͬʱ����bool��terminal����ֵ��terminal ��˼·�����벼������ֵ�����������������ǰ����ֵ�ں��γ�����index�ķ�Χ���жϲ�����index�Ƿ񶼰�����������Χ�У�����ڣ��ű�����壬�����������������
    @Override
    public Population generateInitialPopulation() {

        Population population = new Population();

        int geneNumber = CHPGep.getCombineGeneNumber();//�������
        int geneHead = CHPGep.getCombineGeneHead();
        int geneTail = CHPGep.getCombineGeneTail();
        int geneLength = CHPGep.getCombineGeneLength();//����ͷ����β��

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
            population.add(new CHPBoolChromosome(new String(genes)));//�ѻ����ַ�����תΪstring
        }

        return population;
    }
}
