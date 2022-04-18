package funcs;

import ga.GA;
import ga.GepException;
import gep.Expression;
import gep.FunctionFactory;
import gep.bool.*;
import gep.num.*;
import gep.rel.GreaterEqualsFactory;
import gep.rel.GreaterFactory;
import gep.rel.LessEqualsFactory;
import gep.rel.LessFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CHPGep extends GA {

    private char[] boolFunction = new char[0];
    private char[] logicalFunction = new char[0];
    public char[] combineFunction = new char[0];

    private char[] boolTerminals = new char[0];
    private char[] logicalTerminals = new char[0];
    public char[] combineTerminals = new char[0];

    private Map<Character, FunctionFactory> boolFunctionFactoryMap = new HashMap<Character, FunctionFactory>();
    public Map<Character, FunctionFactory> combineFunctionFactoryMap = new HashMap<Character, FunctionFactory>();
    private Map<Character, FunctionFactory> functionFactoryMap = new HashMap<Character, FunctionFactory>();
    private Map<Character, FunctionFactory> relationFactories  = new HashMap<Character, FunctionFactory>();

    private char boolLinkFunction = '无'; // 连接函数(代码)
    private char logicalLinkFunction = '无'; // 连接函数(代码)
    public char combineLinkFunction = '无'; // 连接函数(代码)
    private FunctionFactory boolLinkFunctionFactory; // 连接函数工厂
    private FunctionFactory logicalLinkFunctionFactory; // 连接函数工厂
    public FunctionFactory combineLinkFunctionFactory; // 连接函数工厂

    private char[] relations; // 数值pattern 的所有关系函数代码
    private Random random = new Random();

    // TODO 如果需要，分成两部分
    public int boolMaxArity = 1; // 函数的最大目数
    public int boolGeneHead = 0; // 基因头部长度
    public int boolGeneTail = 0; // 基因尾部长度
    public int boolGeneNumber = 1; // 基因数量
    public int boolMaxFuncLimit = 0;    // 基因头部中函数个数的最大数

    public int logicalMaxArity = 1; // 函数的最大目数
    public int logicalGeneHead = 0; // 基因头部长度
    public int logicalGeneTail = 0; // 基因尾部长度
    public int logicalGeneNumber = 1; // 基因数量
    public int logicalMaxFuncLimit = 0;    // 基因头部中函数个数的最大数

    public int combineMaxArity = 1; // 函数的最大目数
    public int combineGeneHead = 0; // 基因头部长度
    public int combineGeneTail = 0; // 基因尾部长度
    public int combineGeneNumber = 1; // 基因数量
    public int combineMaxFuncLimit = 0;    // 基因头部中函数个数的最大数


    public CHPGep() {
        // BGEP
        boolAddFunctionFactory(new AndFactory());
        boolAddFunctionFactory(new OrFactory());
        boolAddFunctionFactory(new NotFactory());
        boolAddFunctionFactory(new XorFactory());
        boolAddFunctionFactory(new BFactory());
        boolAddFunctionFactory(new IfFactory());

        // DuanGep->NGEP
        addFunctionFactory(new PlusFactory());
        addFunctionFactory(new MinusFactory());
        addFunctionFactory(new MultiplyFactory());
        addFunctionFactory(new DivideFactory());
        addFunctionFactory(new NegativeFactory());
        addFunctionFactory(new SqrtFactory());
        addFunctionFactory(new ExpFactory());
        addFunctionFactory(new LogFactory());
        addFunctionFactory(new SinFactory());
        addFunctionFactory(new CosFactory());
        addFunctionFactory(new TanFactory());
        addFunctionFactory(new AbsFactory());

        addRelationFactory(new GreaterFactory());
        addRelationFactory(new GreaterEqualsFactory());
        addRelationFactory(new LessFactory());
        addRelationFactory(new LessEqualsFactory());
    }

    //布尔
    public void boolAddFunctionFactory(FunctionFactory factory) {
        boolFunctionFactoryMap.put(new Character(factory.getCode()), factory);
        combineFunctionFactoryMap.put(new Character(factory.getCode()), factory);
    }
    //数值
    public void addFunctionFactory(FunctionFactory factory) {
        functionFactoryMap.put(new Character(factory.getCode()), factory);
    }

    public void setCombineFunctionSet(String set) throws GepException {
        combineFunction = set.toCharArray();
        for (int i = 0; i < combineFunction.length; i++) {
            char function = combineFunction[i];
            FunctionFactory factory = boolFunctionFactoryMap.get(new Character(combineFunction[i])); //混合模式的functionfactory和布尔模式的相同，都是布尔类型

            if (factory == null) throw new GepException("Unknown function: '" + function + "'");

            int arity = factory.getArity();
            if (arity > combineMaxArity) {
                combineMaxArity = arity;
                combineGeneTail = combineGeneHead * (combineMaxArity - 1) + 1;
            }
        }
    }

    public void setBoolFunctionSet(String set) throws GepException {
        boolFunction = set.toCharArray();
        for (int i = 0; i < boolFunction.length; i++) {
            char function = boolFunction[i];
            FunctionFactory factory = boolFunctionFactoryMap.get(new Character(boolFunction[i]));

            if (factory == null) throw new GepException("Unknown function: '" + function + "'");

            int arity = factory.getArity();
            if (arity > boolMaxArity) {
                boolMaxArity = arity;
                boolGeneTail = boolGeneHead * (boolMaxArity - 1) + 1;
            }
        }
    }

    public void setLogicalFunctionSet(String set) throws GepException {
        logicalFunction = set.toCharArray();
        for (int i = 0; i < logicalFunction.length; i++) {
            char function = logicalFunction[i];
            FunctionFactory factory = functionFactoryMap.get(new Character(logicalFunction[i]));

            if (factory == null) throw new GepException("Unknown function: '" + function + "'");

            int arity = factory.getArity();
            if (arity > logicalMaxArity) {
                logicalMaxArity = arity;
                logicalGeneTail = logicalGeneHead * (logicalMaxArity - 1) + 1;
            }
        }
    }

    /**
     * 组合模式的变量集合来源于布尔个体和数值个体，需要区分开这两种terminal
     * @param set
     * @throws GepException
     */

    public void setCombineVariableSet(String set) throws GepException
    {
        char[] terminals = new char[0];
        for (int i = 0; i < set.length(); i++)
        {
            char code = set.charAt(i);
            if (code<970) throw new GepException("Unknown variable: '" + code + "'");
        }

        combineTerminals = (set + new String(combineTerminals)).toCharArray();
//        terminals = (set + new String(terminals)).toCharArray();

//        return terminals;
    }


    public void setBoolVariableSet(String set) throws GepException
    {
        for (int i = 0; i < set.length(); i++)
        {
            char code = set.charAt(i);
            if (code<970) throw new GepException("Unknown variable: '" + code + "'");
        }

        boolTerminals = (set + new String(boolTerminals)).toCharArray();
    }

    public void setLogicalVariableSet(String set) throws GepException
    {
        for (int i = 0; i < set.length(); i++)
        {
            char code = set.charAt(i);
            if (code<970) throw new GepException("Unknown variable: '" + code + "'");
        }

        logicalTerminals = (set + new String(logicalTerminals)).toCharArray();
    }

    public void setCombineLinkFunction(char c) throws GepException
    {
        combineLinkFunction = c;
        combineLinkFunctionFactory = combineFunctionFactoryMap.get(new Character(c));

        if (combineLinkFunctionFactory == null) throw new GepException("Unknown function: '" + c + "'");
    }

    public void setBoolLinkFunction(char c) throws GepException
    {
        boolLinkFunction = c;
        boolLinkFunctionFactory = boolFunctionFactoryMap.get(new Character(c));

        if (boolLinkFunctionFactory == null) throw new GepException("Unknown function: '" + c + "'");
    }

    public void setLogicalLinkFunction(char c) throws GepException
    {
        logicalLinkFunction = c;
        logicalLinkFunctionFactory = functionFactoryMap.get(new Character(c));

        if (logicalLinkFunctionFactory == null) throw new GepException("Unknown function: '" + c + "'");
    }

    //只有数值的有关系符号
    public void setLogicalRelationSet(String relations)
    {
        this.relations = relations.toCharArray();
    }

    public void setCombineGeneNumber(int i)
    {
        combineGeneNumber = i;
    }

    public void setBoolGeneNumber(int i)
    {
        boolGeneNumber = i;
    }
    public void setLogicalGeneNumber(int i)
    {
        logicalGeneNumber = i;
    }

    public void setCombineGeneHead(int i)
    {
        combineGeneHead = i;
        combineGeneTail = combineGeneHead * (combineMaxArity - 1) + 1;
    }
    public void setBoolGeneHead(int i)
    {
        boolGeneHead = i;
        boolGeneTail = boolGeneHead * (boolMaxArity - 1) + 1;
    }
    public void setLogicalGeneHead(int i)
    {
        logicalGeneHead = i;
        logicalGeneTail = logicalGeneHead * (logicalMaxArity - 1) + 1;
    }


    public Expression getCombineFunction(char code)
    {
        FunctionFactory factory = combineFunctionFactoryMap.get(new Character(code));
        return factory.get(code);
    }
    public Expression getBoolFunction(char code)
    {
        FunctionFactory factory = boolFunctionFactoryMap.get(new Character(code));
        return factory.get(code);
    }
    public Expression getLogicalFunction(char code)
    {
        FunctionFactory factory = functionFactoryMap.get(new Character(code));
        return factory.get(code);
    }

    public Expression getCombineLinkFunction()
    {
        return combineLinkFunctionFactory.get(combineLinkFunction);
    }
    public Expression getBoolLinkFunction()
    {
        return boolLinkFunctionFactory.get(boolLinkFunction);
    }
    public Expression getLogicalLinkFunction()
    {
        return logicalLinkFunctionFactory.get(logicalLinkFunction);
    }

    /**
     * 返回连接函数的目数
     *
     * @return
     */

    public int getCombineLinkFunctionArity()
    {
        return combineLinkFunctionFactory.getArity();
    }
    public int getBoolLinkFunctionArity()
    {
        return boolLinkFunctionFactory.getArity();
    }

    public int getLogicalLinkFunctionArity()
    {
        return logicalLinkFunctionFactory.getArity();
    }
    //----------------------------------
    public int getCombineGeneHead()
    {
        return combineGeneHead;
    }
    public int getCombineGeneTail()
    {
        return combineGeneTail;
    }
    public int getCombineGeneNumber()
    {
        return combineGeneNumber;
    }
    public int getCombineMaxFunc()
    {
        return combineMaxFuncLimit;
    }
    public int getCombineGeneLength()
    {
        return combineGeneHead + combineGeneTail;
    }

    public int getCombineHeadFuncIndex(){
        return (int) (Math.random() * combineFunction.length);//返回0-functions.length 的随机数
    }

    public int getCombineHeadIndex(){
        return (int) (Math.random() * (combineFunction.length + combineTerminals.length));//返回0-functions.length + terminals.length 的随机数
    }
    public char getCombineHeadCode()
    {

//        int index = (int) (Math.random() * (combineFunction.length + combineTerminals.length));//返回0-functions.length + terminals.length 的随机数
//        if (index < combineFunction.length) { return combineFunction[index]; }
//        return combineTerminals[index - combineFunction.length];
        double k = Math.random();
        if(k < 0.8){
//            int m = (int) (Math.random() * combineTerminals.length);
            return combineFunction[(int) (Math.random() * combineFunction.length)];
        }
        else{
            return combineTerminals[(int) (Math.random() * combineTerminals.length)];
        }
//        return combineTerminals[(int) (Math.random() * combineTerminals.length)];

    }

    public int getCombineTailIndex(){
        return (int) (Math.random() * combineTerminals.length);
    }

    public char getCombineTailCode()
    {
        return combineTerminals[(int) (Math.random() * combineTerminals.length)];
    }

    public int getBoolGeneHead()
    {
        return boolGeneHead;
    }
    public int getBoolGeneTail()
    {
        return boolGeneTail;
    }
    public int getBoolGeneNumber()
    {
        return boolGeneNumber;
    }
    public int getBoolMaxFunc()
    {
        return boolMaxFuncLimit;
    }
    public int getBoolGeneLength()
    {
        return boolGeneHead + boolGeneTail;
    }
    public char getBoolHeadCode()
    {
        int index = (int) (Math.random() * (boolFunction.length + boolTerminals.length));//返回0-functions.length + terminals.length 的随机数
        if (index < boolFunction.length) { return boolFunction[index]; }
        return boolTerminals[index - boolFunction.length];
    }
    public char getBoolTailCode()
    {
        return boolTerminals[(int) (Math.random() * boolTerminals.length)];
    }


    public int getLogicalGeneHead()
    {
        return logicalGeneHead;
    }
    public int getLogicalGeneTail()
    {
        return logicalGeneTail;
    }
    public int getLogicalGeneNumber()
    {
        return logicalGeneNumber;
    }
    public int getLogicalMaxFunc()
    {
        return logicalMaxFuncLimit;
    }
    public int getLogicalGeneLength()
    {
        return logicalGeneHead + logicalGeneTail;
    }
    public char getLogicalHeadCode()
    {
        int index = (int) (Math.random() * (logicalFunction.length + logicalTerminals.length));//返回0-functions.length + terminals.length 的随机数
        if (index < logicalFunction.length) { return logicalFunction[index]; }
        return logicalTerminals[index - logicalFunction.length];
    }
    public char getLogicalTailCode()
    {
        return logicalTerminals[(int) (Math.random() * logicalTerminals.length)];
    }
    public char getLogicalRelationCode()
    {
        int index = random.nextInt(relations.length);
        return relations[index];
    }

//    /** 返回关系函数 */
//    public Expression getRelation(char code)
//    {
//        FunctionFactory factory = functionFactoryMap.get(new Character(code));
//        return factory.get(code);
//    }

    public boolean isLogicalFunction(char code)
    {
        for (int i = 0; i < logicalFunction.length; i++)
        {
            if (logicalFunction[i] == code) { return true; }
        }

        return false;
    }

    /**返回指定函数的目数  2011-06-27
     * @return
     */
    public int getLogicalFunctionArity(char code)
    {
        FunctionFactory factory = functionFactoryMap.get(new Character(code));
        return factory.getArity() ;
    }
    /** 返回关系函数 */
    public Expression getRelation(char code)
    {
        FunctionFactory factory = (FunctionFactory) relationFactories.get(new Character(code));
        return factory.get(code);
    }

    /** 添加关系函数 */
    public void addRelationFactory(FunctionFactory factory)
    {
        relationFactories.put(new Character(factory.getCode()), factory);
    }

    /** 返回随机的关系函数 */
    public char getRelationCode()
    {
        int index = random.nextInt(relations.length);
        return relations[index];
    }

    /** 设置可选关系函数集合 */
    public void setRelationSet(String relations)
    {
        this.relations = relations.toCharArray();
    }

    public void setMaxL(int maxL) { this.maxL = maxL; }
}
