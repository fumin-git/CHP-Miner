package miner;

import funcs.*;
import ga.Decoder;
import ga.GepException;
import ga.TournamentSelectionOperator;

/**
 * @ClassName GepConstructer
 * @Description 构造gepBool、gelLogical
 * @思路 TODO
 * @Author Xu Yifan
 * @Date 2021/10/30 12:47
 **/
class GepConstructor {
    static CHPGep ConstructCHPGep() throws GepException {
        CHPGep gep = new CHPGep();
        gep.setBoolFunctionSet("!&|^!&|^!&|^!&|^!&|^");
        gep.setLogicalFunctionSet("+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/+-*/");
//        gep.setCombineFunctionSet("&||&||&||&||");

        int itemNum1 = Parameter.boolIndexCount;
        int itemNum2 = Parameter.logicalIndexCount;

        char []varSet1 = new char[itemNum1];
        for (int i = 0; i < itemNum1; i++) {
            varSet1[i] = (char) (i + 970);
        }

        char []varSet2 = new char[itemNum2];
        for (int i = 0; i < itemNum2; i++) {
            varSet2[i] = (char) (i + 970);
        }

        //变量集
        gep.setBoolVariableSet(new String(varSet1));
        gep.setLogicalVariableSet(new String(varSet2));
        //混合类型的变量集在GA中
        //初始化
        gep.setBoolInitializer(new CHPBoolInitializer(gep, Parameter.boolGepInitializer));//布尔初始化
        gep.setLogicalInitializer(new CHPLogicalInitializer(gep, Parameter.logicalGepInitializer));//逻辑初始化
//        gep.setCombineInitializer(new FuCombineInitializer(gep, Parameter.combineGepInitializer));//逻辑初始化
        //linkfunction
        gep.setBoolLinkFunction('|');
        gep.setLogicalLinkFunction('+');
//        gep.setCombineLinkFunction('|');

        //数值 relationset
        gep.setLogicalRelationSet("<>{}");

        //genehead
        gep.setBoolGeneHead(Parameter.boolGeneHead);
        gep.setLogicalGeneHead(Parameter.logicalGeneHead);
//        gep.setCombineGeneHead(Parameter.combineGeneHead);
        //genenumber
        gep.setBoolGeneNumber(Parameter.boolGeneNumber);
        gep.setLogicalGeneNumber(Parameter.logicalGeneNumber);
//        gep.setCombineGeneNumber(Parameter.combineGeneNumber);
        /// maxL
        gep.setMaxL(Parameter.maxL);
        //布尔进化
        gep.setBoolSelectionOperator(new TournamentSelectionOperator(gep, 4));
        gep.addBoolMutationOperator(new CHPBoolMutationOperator(gep, 0.04));
        gep.addBoolCrossoverOperator(new CHPBoolOnePointCrossoverOpetator(gep, 0.4));

        //数值进化
        gep.setLogicalSelectionOperator(new TournamentSelectionOperator(gep, 4));
        gep.addLogicalMutationOperator(new CHPLogicalMutationOperator(gep, 0.04));
        gep.addLogicalMutationOperator(new CHPLogicalRelationMutationOperator(gep, 0.04));
        gep.addLogicalCrossoverOperator(new CHPLogicalOnePointCrossoverOperator(gep, 0.4));

        //布尔解码器
        Decoder boolDecoder = new CHPBoolDecoder(gep);
        gep.setBoolDecoder(boolDecoder);

        //数值解码器
        Decoder logicalDecoder = new CHPLogicalDecoder(gep);
        gep.setLogicalDecoder(logicalDecoder);

        //混合模式解码器
        //todo 组合模式的解码器和布尔解码器思路相同，需要新建对应的类FuCombineGepDecoder(需要记录index,输出作为布尔和数值个体的索引，取出对应个体)
//        Decoder combineDecoder = new FuCombineDecoder(gep);
//        gep.setCombineDecoder(combineDecoder);
//

        return gep;

    }
}
