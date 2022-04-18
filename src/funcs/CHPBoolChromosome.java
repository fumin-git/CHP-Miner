package funcs;

import ga.Chromosome;

public class CHPBoolChromosome implements Chromosome {
    private String genes; // ���ݻ���
    private String structureGene; // �ṹ���� (��ʾǰ��������ռ�ṹ�Ļ���)

    public CHPBoolChromosome(String genes)
    {
        this.genes = genes;
    }

    public String getGenes()
    {
        return genes;
    }

    public String getStructureGene()
    {
        return structureGene;
    }

    public String toString()
    {
        // TODO û�п��ǽṹ�������ʾ
        return genes;
    }
}
