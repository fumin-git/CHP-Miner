package miner;

import numeric.NumFormula;
import gep.bool.BFormula;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName Utils
 * @Description π§æﬂ¿‡
 * @Author Xu Yifan
 * @Date 2021/10/30 12:45
 **/
public class Utils {
    public static void filePrinter(String fileOutput, String formula, double fitness) {
        Date now = new Date();
        DateFormat format2 = new SimpleDateFormat("MMdd");
        String outputFile = getFileName(fileOutput) + format2.format(now) + ".data";
        PrintWriter writer = null;

        try {
            String pureFormula = "";
            for (int i = 0; i < formula.length(); i++) {
                char ch = formula.charAt(i);
                if (ch == '1')
                    continue;
                if (ch == '<') {
                    char ch2 = formula.charAt(i + 1);
                    if (ch2 == '=') {
                        ch = '{';
                        i++;
                    }
                }
                if (ch == '>') {
                    char ch2 = formula.charAt(i + 1);
                    if (ch2 == '=') {
                        ch = '}';
                        i++;
                    }
                }
                pureFormula += ch;
            }
            pureFormula += ("\t" + "% " + fitness);
            writer = new PrintWriter(new FileWriter(outputFile, true));
            writer.println(pureFormula);
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public static String getFileName(String fileName) {
        StringTokenizer st = new StringTokenizer(fileName, ".");
        if (st.hasMoreTokens())
            fileName = st.nextToken();

        return fileName;
    }

    public static List<String[]> filterDatasetByBoolFormula(BFormula formula, List<boolean[]> judgeData, List<String[]> data) {
        assert judgeData.size() == data.size();
        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < judgeData.size(); i++) {
            boolean b = false;
            try {
                formula.setValues(judgeData.get(i));
                b = formula.evaluate();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }

            if (b) {
                result.add(data.get(i));
            }
        }
        return result;
    }

    public static List<String[]> filterDatasetByLogicalFormula(NumFormula formula, List<double[]> judgeData, List<String[]> data) {
        assert judgeData.size() == data.size();
        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < judgeData.size(); i++) {
            boolean b = false;
            try {
                formula.setValues(judgeData.get(i));
                b = formula.evaluate();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }

            if (b) {
                result.add(data.get(i));
            }
        }

        return result;
    }

    public static List<Result> resultSort(List<Result> results) {
        results.sort((o1, o2) -> {
            if (o1.getRatio() > o2.getRatio()) {
                return -1;
            } else if (o1.getRatio() == o2.getRatio()) {
                if (o1.getSupport() > o2.getSupport()) {
                    return -1;
                } else if (o1.getSupport() == o2.getSupport()) {
                    if (o1.getFitness() > o2.getFitness()) {
                        return -1;
                    } else if (o1.getFitness() == o2.getFitness()) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        });
        return results;
    }

    public static List<Result> duplicateRemove(List<Result> results) {
        return results.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.toString()))), ArrayList::new));
    }

    public static void storeResult(String dataset, List<Result> results, int index) {
        String dirName = "result_" + dataset;
        List<String> patternAllValues = new ArrayList<>();
        List<String> patternNames = new ArrayList<>();
        List<String> patternSupports = new ArrayList<>();
        List<String> patternRatios = new ArrayList<>();
        List<String> patternFitness = new ArrayList<>();
        List<String> patternGeneration = new ArrayList<>();
        List<String> patternTime = new ArrayList<>();
        for (Result result : results) {
            patternAllValues.add(result.toStringCombine());
            patternNames.add(result.getCombineString());
            patternSupports.add(String.valueOf(result.getSupport()));
            patternRatios.add(String.valueOf(result.getRatio()));
            patternFitness.add(String.valueOf(result.getFitness()));
            patternGeneration.add(String.valueOf(result.getGenerate()));
            patternTime.add(String.valueOf(result.getTime()));
        }
        try {
            creataDir(dirName + "/");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dirName + "/No." + index + " experiment_result.txt"));
            bufferedWriter.write(list2String(patternAllValues));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creataDir(String name) {
        File file = new File(name);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static String list2String(List<String> lists) {
        StringBuilder sb = new StringBuilder();
        for (String list : lists) {
            sb.append(list);
            sb.append("\n");
        }
        return sb.toString();
    }
}
