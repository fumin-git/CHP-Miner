package miner;

import numeric.NumDataSet;
import numeric.NumEvaluator;
import nominal.BoolDataSet;
import nominal.BoolEvaluator;
import funcs.CHPGep;
import ga.Evaluator;
import ga.GepException;
import ga.MaxGenerationStopper;
import ga.Stopper;

import java.util.ArrayList;
import java.util.List;

public class Main implements Runnable{
    private CHPGep gep;
    public long time = 0;
    public List<Result> finalResult = new ArrayList<>();

    public static void main(String[] args) {
        int runTimes = 100;
        for (int i = 0; i < runTimes; i++) {
            System.out.println("runTimes: "+(i));
            Main main = new Main();
            Thread worker = new Thread(main);
            worker.setPriority(Thread.MIN_PRIORITY);
            worker.start();
            while(worker.isAlive())
            {
                ;
            }
            Utils.storeResult(Parameter.dataset, main.finalResult, i);
        }
    }

    @Override
    public void run() {
        List<String[]> posData = DatasetProcessor.getDataset(Parameter.posDataPath);
        List<String[]> negData = DatasetProcessor.getDataset(Parameter.negDataPath);
        List<boolean[]> boolPosData = DatasetProcessor.getBoolDataset(posData);
        List<boolean[]> boolNegData = DatasetProcessor.getBoolDataset(negData);
        BoolDataSet trainPosBool = new BoolDataSet(boolPosData);
        BoolDataSet trainNegBool = new BoolDataSet(boolNegData);
        List<double[]> posData1 = DatasetProcessor.getLogicalDataset(posData);
        List<double[]> negData1 = DatasetProcessor.getLogicalDataset(negData);
        NumDataSet trainPosLogical = new NumDataSet(posData1);
        NumDataSet trainNegLogical = new NumDataSet(negData1);
        try {
            gep = GepConstructor.ConstructCHPGep();
            gep.ratioThreshold = Parameter.ratioThreshold;
            Evaluator boolEvaluator = new BoolEvaluator(trainPosBool, trainNegBool);
            Evaluator logicalEvaluator = new NumEvaluator(trainPosLogical, trainNegLogical);
            gep.setBoolEvaluator(boolEvaluator);
            gep.setLogicalEvaluator(logicalEvaluator);
            Stopper stopper = new MaxGenerationStopper(gep, Parameter.logicalMaxGenerationStopper);
            gep.setStopper(stopper);
            gep.run();
            time += gep.bestTime;
            finalResult = gep.gaFinalResult;
            finalResult = Utils.duplicateRemove(finalResult);
            finalResult = Utils.resultSort(finalResult);
        } catch (GepException e) {
            e.printStackTrace();
        }
    }
}
