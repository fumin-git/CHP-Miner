package ga;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miner.Parameter;
import miner.Result;


import funcs.*;

public abstract class GA {
    public Initializer boolInitializer = null;
    public Initializer logicalInitializer = null;
    public Initializer combineInitializer = null;
    public SelectionOperator selectionOperator = null;
    public List<MutationOperator> boolMutationOperators = new ArrayList<>();
    public List<MutationOperator> logicalMutationOperators = new ArrayList<>();
    public List<CrossoverOperator> boolCrossoverOperators = new ArrayList<>();
    public List<CrossoverOperator> logicalCrossoverOperators = new ArrayList<>();
    public Decoder boolDecoder;
    public Decoder logicalDecoder;
    public Decoder combineDecoder;
    public Evaluator boolEvaluator;
    public Evaluator logicalEvaluator;
    public Evaluator combineEvaluator;
    public Stopper stopper;
    public Fitness fitnessFunction;
    public double threshold;
    public double negThreshold;
    public double ratioThreshold;
    public double gamma;
    public long startTime;
    public long stopTime;
    public long bestTime;
    public double boolBestPosSupport = -200.0;
    public int maxL;
    public int generation = 0;

    public Population boolPopulation;
    public Population logicalPopulation;
    public Chromosome boolBestChromosome;
    public Chromosome logicalBestChromosome;
    public Protein boolBestProtein;
    public Protein logicalBestProtein;
    public double boolBestFitness = -200;
    public double logicalBestFitness = -200;
    public int boolBestGeneration;
    public int logicalBestGeneration;
    public double boolLastBestFitness = -300;
    public double logicalLastBestFitness = -300;
    public List<Result> boolResult = new ArrayList<>();
    public List<Result> logicalResult = new ArrayList<>();
    public List<Double> combineAverageSupportHistory = new ArrayList<>();
    public List<Double> combineAverageRatioHistory = new ArrayList<>();
    public List<Result> gaFinalResult = new ArrayList<>();
    public void initialize() {

    }

    public void setBoolInitializer(Initializer boolInitializer) {
        this.boolInitializer = boolInitializer;
    }

    public void setLogicalInitializer(Initializer logicalInitializer) {
        this.logicalInitializer = logicalInitializer;
    }

    public void setCombineInitializer(Initializer combineInitializer) {
        this.combineInitializer = combineInitializer;
    }

    public void setSelectionOperator(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
    }

    public void setBoolSelectionOperator(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
    }

    public void setLogicalSelectionOperator(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
    }

    public void addMutationOperator(MutationOperator mutationOperator) {
        this.boolMutationOperators.add(mutationOperator);
    }

    public void addBoolMutationOperator(MutationOperator mutationOperator) {
        this.boolMutationOperators.add(mutationOperator);
    }

    public void addLogicalMutationOperator(MutationOperator mutationOperator) {
        this.logicalMutationOperators.add(mutationOperator);
    }

    public void addCrossoverOperator(CrossoverOperator crossoverOperator) {
        this.boolCrossoverOperators.add(crossoverOperator);
    }
    public void addBoolCrossoverOperator(CrossoverOperator crossoverOperator) {
        this.boolCrossoverOperators.add(crossoverOperator);
    }
    public void addLogicalCrossoverOperator(CrossoverOperator crossoverOperator) {
        this.logicalCrossoverOperators.add(crossoverOperator);
    }

    public void setBoolDecoder(Decoder boolDecoder) {
        this.boolDecoder = boolDecoder;
    }
    public void setLogicalDecoder(Decoder logicalDecoder) {
        this.logicalDecoder = logicalDecoder;
    }
    public void setCombineDecoder(Decoder combineDecoder) {
        this.combineDecoder = combineDecoder;
    }
    public void setFitnessFunction(Fitness fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }
    public void setBoolEvaluator(Evaluator boolEvaluator) {
        this.boolEvaluator = boolEvaluator;
    }
    public void setLogicalEvaluator(Evaluator logicalEvaluator) {
        this.logicalEvaluator = logicalEvaluator;
    }
    public void setCombineEvaluator(Evaluator combineEvaluator) {
        this.combineEvaluator = combineEvaluator;
    }
    public void setStopper(Stopper stopper) {
        this.stopper = stopper;
    }
    private TwoTuple<Population, Population> select(Population population1, Population population2, double fitnesses11[], double fitnesses22[]) {
        int[] index1 = selectionOperator.select(fitnesses11);
        int[] index2 = selectionOperator.select(fitnesses22);
        Population pop1 = new Population();
        Population pop2 = new Population();
        pop1.add(boolBestChromosome);
        pop2.add(logicalBestChromosome);
        int size = population1.size();
        for (int i = 1; i < size; i++) {
            pop1.add(population1.get(index1[i]));
            pop2.add(population2.get(index2[i]));
        }

        return new TwoTuple<>(pop1, pop2);
    }

    private Population boolMutation(Population population) {
        int size = population.size();
        for (Iterator<MutationOperator> iterator = boolMutationOperators.iterator(); iterator.hasNext(); ) {
            MutationOperator mutationOperator = iterator.next();
            Population pop = new Population();
            for (int i = 0; i < size; i++) {
                Chromosome chromosome = population.get(i);
                chromosome = mutationOperator.mutate(chromosome);
                pop.add(chromosome);
            }
            population = pop;
        }
        return population;
    }

    private Population logicalMutation(Population population) {
        int size = population.size();
        for (Iterator<MutationOperator> iterator = logicalMutationOperators.iterator(); iterator.hasNext(); ) {
            MutationOperator mutationOperator = iterator.next();
            Population pop = new Population();
            for (int i = 0; i < size; i++) {
                Chromosome chromosome = population.get(i);
                chromosome = mutationOperator.mutate(chromosome);
                pop.add(chromosome);
            }
            population = pop;
        }

        return population;
    }

    private Population boolCrossover(Population population) {
        int size = population.size();
        for (Iterator<CrossoverOperator> iterator = boolCrossoverOperators.iterator(); iterator.hasNext(); ) {
            CrossoverOperator crossoverOperator = (CrossoverOperator) iterator.next();
            Population pop = new Population();
            for (int i = 0; i < size; i += 2) {
                Chromosome[] parents = new Chromosome[2];
                parents[0] = population.get(i);
                parents[1] = population.get(i + 1);
                Chromosome[] sons = crossoverOperator.operate(parents);
                pop.add(sons[0]);
                pop.add(sons[1]);
            }
            population = pop;
        }
        return population;
    }
    private Population logicalCrossover(Population population) {
        int size = population.size();
        for (Iterator<CrossoverOperator> iterator = logicalCrossoverOperators.iterator(); iterator.hasNext(); ) {
            CrossoverOperator crossoverOperator = (CrossoverOperator) iterator.next();
            Population pop = new Population();
            for (int i = 0; i < size; i += 2) {
                Chromosome[] parents = new Chromosome[2];
                parents[0] = population.get(i);
                parents[1] = population.get(i + 1);
                Chromosome[] sons = crossoverOperator.operate(parents);
                pop.add(sons[0]);
                pop.add(sons[1]);
            }
            population = pop;
        }
        return population;
    }

    public void run() throws GepException {
        startTime = System.currentTimeMillis();
        List<List<Boolean>> boolPosMatrix = new ArrayList<>();
        List<List<Boolean>> logicalPosMatrix = new ArrayList<>();
        List<List<Boolean>> boolNegMatrix = new ArrayList<>();
        List<List<Boolean>> logicalNegMatrix = new ArrayList<>();
        double[] boolRatios;
        double[] logicalRatios;
        String[] boolProStrs;
        String[] logicalProStrs;
        boolPopulation = boolInitializer.generateInitialPopulation();
        logicalPopulation = logicalInitializer.generateInitialPopulation();
        TenTuple<double[], double[], List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, double[], double[], String[], String[]> results = evaluateGa(boolPopulation, logicalPopulation);
        double[] boolFitnesses = results.first;
        double[] logicalFitnesses = results.second;
        for (; !stopper.canStop(); generation++) {
            verbose(0);
            boolPopulation = select(boolPopulation, logicalPopulation, boolFitnesses, logicalFitnesses).first;
            logicalPopulation = select(boolPopulation, logicalPopulation, boolFitnesses, logicalFitnesses).second;
            boolPopulation = boolMutation(boolPopulation);
            logicalPopulation = logicalMutation(logicalPopulation);
            boolPopulation = boolCrossover(boolPopulation);
            logicalPopulation = logicalCrossover(logicalPopulation);
            TenTuple<double[], double[], List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, double[], double[], String[], String[]> evaluateResult = evaluateGa(boolPopulation, logicalPopulation);
            boolFitnesses = evaluateResult.first;
            logicalFitnesses = evaluateResult.second;
            boolPosMatrix = evaluateResult.third;
            logicalPosMatrix = evaluateResult.fourth;
            boolNegMatrix = evaluateResult.fifth;
            logicalNegMatrix = evaluateResult.sixth;
            boolRatios = evaluateResult.seventh;
            logicalRatios = evaluateResult.eighth;
            boolProStrs = evaluateResult.ninth;
            logicalProStrs = evaluateResult.tenth;
            SixTuple<Population, Population, List<List<Boolean>>, List<List<Boolean>>, List<String>, List<String>> comResults = saveBoolAndLogicalRes(gamma, boolRatios, logicalRatios, boolPopulation, logicalPopulation,
                    boolPosMatrix,logicalPosMatrix, boolNegMatrix, logicalNegMatrix, boolProStrs, logicalProStrs);
            if(comResults == null){
                continue;
            }
            else{
                CHPGep comGep = new CHPGep();
                Population combineBoolPop = comResults.first;
                Population combineLogicalPop = comResults.second;
                List<List<Boolean>> combinePosMatrix = comResults.third;
                List<List<Boolean>> combineNegMatrix = comResults.fourth;
                List<String> boolProteinStrs = comResults.fifth;
                List<String> logicalProteinStrs = comResults.sixth;
                Population combinePopulation = getCombinePop(comGep, combineBoolPop, combineLogicalPop);
                gaFinalResult = evaluateCombinePop(comGep, combinePopulation, combineBoolPop, combineLogicalPop, combinePosMatrix, combineNegMatrix, boolProteinStrs, logicalProteinStrs);
            }
        }
        stopTime = System.currentTimeMillis();
    }

    private Population getCombinePop(CHPGep comGep, Population combineBoolPop, Population combineLogicalPop) throws GepException {
        Population combinePopulation;
        int boolSize = combineBoolPop.size();
        int logicalSize = combineLogicalPop.size();
        char[] comVarSet = new char[boolSize+logicalSize];
        comGep.setCombineFunctionSet("&||&||&||&||");
        for (int i = 0; i < boolSize+logicalSize; i++) {
            comVarSet[i] = (char) (i + 970);
        }
        try {
            comGep.setCombineVariableSet(new String(comVarSet));
        } catch (GepException e) {
            e.printStackTrace();
        }
        comGep.setCombineInitializer(new CHPCombineInitializer(comGep, Parameter.combineGepSize));
        try {
            comGep.setCombineLinkFunction('|');
        } catch (GepException e) {
            e.printStackTrace();
        }
        comGep.setCombineGeneHead(Parameter.combineGeneHead);
        comGep.setCombineGeneNumber(Parameter.combineGeneNumber);
        comGep.combineDecoder = new CHPCombineDecoder(comGep);
        comGep.setCombineDecoder(comGep.combineDecoder);


        combinePopulation = comGep.combineInitializer.generateInitialPopulation();
        return combinePopulation;
    }

    private List<Result> evaluateCombinePop(CHPGep comGep, Population combinePopulation, Population combineBoolPop, Population combineLogicalPop,
                                            List<List<Boolean>> combinePosMatrix, List<List<Boolean>> combineNegMatrix, List<String> boolProStrs, List<String> logicalProStrs){
        int comPopSize = combinePopulation.size();
        int boolSize = combineBoolPop.size();
        int logicalSize = combineLogicalPop.size();
        double combineAverageSupport = 0.0;
        double combineAverageRatio = 0.0;
        assert combinePosMatrix.size() == combineNegMatrix.size();
        List<List<Boolean>> transPosMatrix = new ArrayList<>();
        List<List<Boolean>> transNegMatrix = new ArrayList<>();
        List<Result> combineResult = new ArrayList<>();
        for(int i=0; i<combinePosMatrix.get(0).size(); i++){
            List<Boolean> posTmp = new ArrayList<>();
            for(int j=0; j<combinePosMatrix.size(); j++){
                posTmp.add(combinePosMatrix.get(j).get(i));
            }
            transPosMatrix.add(i, posTmp);
        }
        for(int i=0; i<combineNegMatrix.get(0).size(); i++){
            List<Boolean> negTmp = new ArrayList<>();
            for(int j=0; j<combineNegMatrix.size(); j++){
                negTmp.add(combineNegMatrix.get(j).get(i));
            }
            transNegMatrix.add(negTmp);
        }
        Evaluator combineEvaluator = new CHPCombineEvaluator(transPosMatrix, transNegMatrix);
        comGep.setCombineEvaluator(combineEvaluator);
        double validCombineNum = 0.0;
        for(int i=0; i<comPopSize; i++){
            List boolIndex = new ArrayList();
            List logicalIndex = new ArrayList();
            List allIndex = new ArrayList();
            Chromosome combineChromosome = combinePopulation.get(i);
            Protein combineProtein = comGep.combineDecoder.decode(combineChromosome);
            String popStr = combineProtein.toString();
            List popIndex = getNumberInString(popStr);
            for(int k=0; k< popIndex.size(); k++){
                if((int)popIndex.get(k) < boolSize){
                    boolIndex.add(popIndex.get(k));
                }else if ((int)popIndex.get(k) >= boolSize && (int)popIndex.get(k) < boolSize+logicalSize){
                    logicalIndex.add(popIndex.get(k));
                }
            }
            double sup0;
            double sup1;
            double ratios;
            if(boolIndex.size() != 0 && logicalIndex.size() != 0){
                Result comres = new Result();
                ThreeTuple<Double, Double, Double> comRes = (combineEvaluator).combineEvaluate(combineProtein);
                sup0 = comRes.first;
                sup1 = comRes.second;
                ratios = comRes.third;
                if(ratios > Parameter.ratioThreshold){
                    allIndex.addAll(boolIndex);
                    allIndex.addAll(logicalIndex);
                    String comStr = getCombineExpression(combineProtein, boolProStrs, logicalProStrs, allIndex);
                    ratios = ratios + getDelta(comStr, this.maxL);
                    System.out.println("hybrid expression: " + comStr);
                    comres.setProtein(combineProtein);
                    comres.setCombineString(comStr);
                    comres.setSupport(sup0);
                    comres.setNegSupport(sup1);
                    comres.setRatio(ratios);
                    comres.setGenerate(this.generation);
                    comres.setTime(System.currentTimeMillis()-this.startTime);
                    combineResult.add(comres);
                }
                combineAverageSupport += sup0;
                combineAverageRatio += ratios;
                validCombineNum = validCombineNum+1;
            }
        }
        combineAverageSupport = combineAverageSupport / validCombineNum;
        combineAverageRatio = combineAverageRatio / validCombineNum;
        combineAverageSupportHistory.add(combineAverageSupport);
        combineAverageRatioHistory.add(combineAverageRatio);
        return combineResult;
    }

    public String getCombineExpression(Protein combineProtein, List<String> boolProStrs, List<String> logicalProStrs, List allIndex){
        int boolSize = boolProStrs.size();
        int logicalSize = logicalProStrs.size();
        String comProteinStr = combineProtein.toString();
        String changePro = "";
        String boolProterinStr;
        String logicalProteinStr;
        Map<String, String> attri2pop = new HashMap<>();
        for(int i=0; i<boolSize+logicalSize; i++){

            if(i < boolSize){
                boolProterinStr = boolProStrs.get(i);
                attri2pop.put("p" + i, boolProterinStr);
            }
            else{
                logicalProteinStr = logicalProStrs.get(i-boolSize);
                attri2pop.put("p" + i, logicalProteinStr);
            }
        }

        String s = comProteinStr.replace("a", "p");
        String lastChangePro = s;

        for(int k=0; k<allIndex.size(); k++){
            String tmp = "p"+ allIndex.get(k);
            changePro = lastChangePro.replace(tmp, String.valueOf(attri2pop.get(tmp)));
            lastChangePro = changePro;
        }
        return changePro;
    }

    private List getNumberInString(String str){
        List result = new ArrayList();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                readNumber(str, i, result);
            }
        }
        return result;
    }

    private void readNumber(String str, int i, List result){
        int index = i;
        int count = 1;
        int numberTarget = Integer.valueOf(String.valueOf(str.charAt(i)));
        if (i == 0){
            result.add(numberTarget);
        }else {
            if (result.size() != 0 && index > 0 && Character.isDigit(str.charAt(index - 1)))
                result.remove(result.size() - 1);
            while (index > 0 && Character.isDigit(str.charAt(index - 1))){
                count *= 10;
                numberTarget = Integer.valueOf(String.valueOf(str.charAt(index - 1))) * count + numberTarget;
                index--;
            }
            result.add(numberTarget);
        }
    }

    private SixTuple<Population, Population, List<List<Boolean>>, List<List<Boolean>>, List<String>, List<String>> saveBoolAndLogicalRes(double gamma, double[] boolRatios, double[] logicalRatios,
                                                                                                              Population boolPopulation, Population logicalPopulation,
                                                                                                              List<List<Boolean>> boolPosMatrix, List<List<Boolean>> logicalPosMatrix,
                                                                                                              List<List<Boolean>> boolNegMatrix, List<List<Boolean>> logicalNegMatrix,
                                                                                                              String[] boolProStrs, String[] logicalProStrs){
        Population comBoolPop = new Population();
        Population comLogicalPop = new Population();
        List<List<Boolean>> comBoolPosMatrix = new ArrayList<>();
        List<List<Boolean>> comLogicalPosMatrix = new ArrayList<>();
        List<List<Boolean>> comBoolNegMatrix = new ArrayList<>();
        List<List<Boolean>> comLogicalNegMatrix = new ArrayList<>();
        List<List<Boolean>> comPosMatrix = new ArrayList<>();
        List<List<Boolean>> comNegMatrix = new ArrayList<>();
        List<String> boolStrings = new ArrayList<>();
        List<String> logicalStrings = new ArrayList<>();
        for(int i=0; i< boolRatios.length; i++){
            String bs = boolProStrs[i];
            if(!boolStrings.contains(bs)){
                comBoolPop.add(boolPopulation.get(i));
                comBoolPosMatrix.add(boolPosMatrix.get(i));
                comBoolNegMatrix.add(boolNegMatrix.get(i));
                boolStrings.add(bs);
            }
            String ls = logicalProStrs[i];
            if(!logicalStrings.contains(ls)){
                comLogicalPop.add(logicalPopulation.get(i));
                comLogicalPosMatrix.add(logicalPosMatrix.get(i));
                comLogicalNegMatrix.add(logicalNegMatrix.get(i));
                logicalStrings.add(ls);
            }
        }

        if(comBoolPop.size() ==0 || comLogicalPop.size() == 0){
            return null;
        }
        else{
            comPosMatrix.addAll(comBoolPosMatrix);
            comPosMatrix.addAll(comLogicalPosMatrix);

            comNegMatrix.addAll(comBoolNegMatrix);
            comNegMatrix.addAll(comLogicalNegMatrix);

            return new SixTuple<>(comBoolPop, comLogicalPop, comPosMatrix, comNegMatrix, boolStrings, logicalStrings);
        }
    }

    private double getDelta(String str, int maxL){
        String regex = "a";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        double count = 0;
        double delta = 0;
        while (matcher.find()) {
            count++;
        }
        DecimalFormat df = new DecimalFormat("0.00");

        if (count < maxL) {
            delta = 1 - (double) count / maxL;
            delta = Double.parseDouble(df.format(delta));
        }
        return delta;
    }

    private TenTuple<double[], double[], List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, List<List<Boolean>>, double[], double[], String[], String[]> evaluateGa(Population boolPopulation, Population logicalPopulation) {
        assert boolPopulation.size() == logicalPopulation.size();
        int size = boolPopulation.size();

        double[] boolFitnesses = new double[size];
        double[] logicalFitnesses = new double[size];
        double[] boolRatios = new double[size];
        double[] logicalRatios = new double[size];
        String[] boolProStrs = new String[size];
        String[] logicalProStrs = new String[size];
        List<List<Boolean>> boolPosMatrix = new ArrayList<>();
        List<List<Boolean>> boolNegMatrix = new ArrayList<>();
        List<List<Boolean>> logicalPosMatrix = new ArrayList<>();
        List<List<Boolean>> logicalNegMatrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Chromosome boolChromosome = boolPopulation.get(i);
            Chromosome logicalChromosome = logicalPopulation.get(i);
            Result boolResult = new Result();
            Result logicalResult = new Result();
            Protein boolProtein = boolDecoder.decode(boolChromosome);
            Protein logicalProtein = logicalDecoder.decode(logicalChromosome);
            String boolProStr = boolProtein.toString();
            String logicalProStr = logicalProtein.toString();
            boolProStrs[i] = boolProStr;
            logicalProStrs[i] = logicalProStr;
            ThreeTuple<Double, List<Boolean>, List<Boolean>> boolTmpResult = boolEvaluator.evaluate1(boolProtein);
            ThreeTuple<Double, List<Boolean>, List<Boolean>> logicalTmpResult = logicalEvaluator.evaluate1(logicalProtein);
            boolPosMatrix.add(boolTmpResult.second);
            boolNegMatrix.add(boolTmpResult.third);
            logicalPosMatrix.add(logicalTmpResult.second);
            logicalNegMatrix.add(logicalTmpResult.third);
            double boolFitness = boolTmpResult.first;
            double logicalFitness = logicalTmpResult.first;
            boolFitnesses[i] = boolFitness + getDelta(boolProStr, this.maxL);
            logicalFitnesses[i] = logicalFitness + getDelta(logicalProStr, this.maxL);

            double curBoolPosSupport = boolEvaluator.getSup0();
            double curLogicalPosSupport = logicalEvaluator.getSup0();
            double curBoolNegSupport = boolEvaluator.getSup1();
            double curLogicalNegSupport = logicalEvaluator.getSup1();
            double boolRatio;
            double logicalRatio;

            if (curBoolNegSupport == 0.0) {
                boolRatio = curBoolPosSupport / 0.001;
            } else {
                boolRatio = curBoolPosSupport / curBoolNegSupport;
            }
            if (curLogicalNegSupport == 0.0) {
                logicalRatio = curLogicalPosSupport / 0.001;
            } else {
                logicalRatio = curLogicalPosSupport / curLogicalNegSupport;
            }
            boolRatio = boolRatio + getDelta(boolProStr, this.maxL);
            logicalRatio = logicalRatio + getDelta(logicalProStr, this.maxL);

            boolRatios[i] = boolRatio;
            logicalRatios[i] = logicalRatio;

            if (curBoolPosSupport >= this.threshold && curBoolNegSupport <= this.negThreshold) {
                boolResult.setProtein(boolProtein);
                boolResult.setSupport(curBoolPosSupport);
                boolResult.setNegSupport(curBoolNegSupport);
                boolResult.setFitness(boolFitness);
                boolResult.setRatio(boolRatio);
                boolResult.setGenerate(this.generation);
                this.boolResult.add(boolResult);
            }

            if (curLogicalPosSupport >= this.threshold && curLogicalNegSupport <= this.negThreshold) {
                logicalResult.setProtein(logicalProtein);
                logicalResult.setSupport(curLogicalPosSupport);
                logicalResult.setNegSupport(curLogicalNegSupport);
                logicalResult.setFitness(logicalFitness);
                logicalResult.setRatio(logicalRatio);
                logicalResult.setGenerate(this.generation);
                this.logicalResult.add(logicalResult);
            }


            if (boolFitness > boolBestFitness) {
                boolBestChromosome = boolChromosome;
                boolBestProtein = boolProtein;
                boolBestFitness = boolFitness;
                boolBestGeneration = generation;
                bestTime = System.currentTimeMillis() - startTime;
            }

            if (logicalFitness > logicalBestFitness) {
                logicalBestChromosome = logicalChromosome;
                logicalBestProtein = logicalProtein;
                logicalBestFitness = logicalFitness;
                logicalBestGeneration = generation;
                bestTime = System.currentTimeMillis() - startTime;
            }
        }


        return new TenTuple<>(boolFitnesses, logicalFitnesses, boolPosMatrix, logicalPosMatrix, boolNegMatrix, logicalNegMatrix, boolRatios, logicalRatios, boolProStrs, logicalProStrs);
    }

    public Protein getBoolBestProtein() {
        return boolBestProtein;
    }

    public Protein getLogicalBestProtein() {
        return logicalBestProtein;
    }

    public double getBoolBestFitness() {
        return boolBestFitness;
    }

    public double getLogicalBestFitness() {
        return logicalBestFitness;
    }

    public double getBestTimeUsed() {
        return bestTime;
    }

    public double getBoolPosSupport() {
        return boolBestPosSupport;
    }

    public List<Result> getBoolResult() {
        return boolResult;
    }

    public List<Result> getLogicalResult() {
        return logicalResult;
    }

    private void verbose(int level) {

        if (boolBestFitness != boolLastBestFitness) {
            boolLastBestFitness = boolBestFitness;
            System.out.println("boolean newbest: " + generation + ": " + boolBestFitness + " : " + boolBestProtein);
        } else if (generation % 20 == 0)
        {
            System.out.println("" + generation + ": " + boolBestFitness + " : " + boolBestProtein);
        }
        if (logicalBestFitness != logicalLastBestFitness) {
            logicalLastBestFitness = logicalBestFitness;
            System.out.println("relatioanl newbest: " + generation + ": " + logicalBestFitness + " : " + logicalBestProtein);
        } else if (generation % 20 == 0)
        {
            System.out.println("" + generation + ": " + logicalBestFitness + " : " + logicalBestProtein);
        }
        if (level == 0) return;
    }

    public void report(PrintWriter writer) throws GepException {
        writer.println("Selection Operator : " + selectionOperator);
        for (Iterator<MutationOperator> i = boolMutationOperators.iterator(); i.hasNext(); ) {
            writer.println("Mutation Operator  : " + i.next());
        }
        for (Iterator<CrossoverOperator> i = boolCrossoverOperators.iterator(); i.hasNext(); ) {
            writer.println("Crossover Operator : " + i.next());
        }
        writer.println("Decoder            : " + boolDecoder);
        writer.println("Evaluator          : " + boolEvaluator);
        writer.println("Stopper            : " + stopper);
        writer.println("Fitness Function   : " + fitnessFunction);

        writer.println("Generation         : " + generation);
        writer.println("Population Size    : " + boolPopulation.size());

        writer.println("Best Chromosome    : " + boolBestChromosome);
        writer.println("Best Fitness       : " + boolBestFitness);
        writer.println("Best Formula       : " + boolBestProtein);

        writer.println("Used Time          : " + (stopTime - startTime));
        writer.println("Used Time to Best  : " + (bestTime - startTime));
    }
}
