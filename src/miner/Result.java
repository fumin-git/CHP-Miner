package miner;

import ga.Protein;

import java.util.Objects;

public class Result {

    private Protein protein;
    private double support;
    private double negSupport;
    private double fitness;
    private double ratio;
    private String combineString = "";
    private int generate;
    private double time;


    public Protein getProtein() {
        return this.protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public double getSupport() {
        return this.support;
    }

    public double getNegSupport() {
        return this.negSupport;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    public void setNegSupport(double negSupport) {
        this.negSupport = negSupport;
    }

    public double getFitness() {
        return this.fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getRatio() {
        return this.ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setGenerate(int generate) {this.generate = generate;}
    public int getGenerate() {return this.generate;}

    public void setTime(double time) {this.time = time; }
    public double getTime() {return this.time;}

    public String getCombineString(){return this.combineString;}

    public void setCombineString(String combineString){
        this.combineString = combineString;
    }


//    public String toString(){
//        String replace = "protein is null";
//        if (this.protein != null) {
//            replace = this.protein.toString();
//        }
//        return "single pattern : " + replace +
//                "\tposSupport :" + this.support +
//                "\tnegSupport :" + this.negSupport +
//                "\tfitness : " + this.fitness +
//                "\tratio : " + this.ratio+
//                "\tgeneration : " + this.generate;
//    }


    public String toStringCombine(){
        return  "hybrid expression : " + this.combineString +
                "\tcscore : " + this.ratio +
                "\tposSupport : " + this.support +
                "\tnegSupport : " + this.negSupport +
                "\tgeneration : " + this.generate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(protein, result.protein);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protein);
    }
}

