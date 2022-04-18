package numeric;

import gep.Expression;
import gep.FunctionFactory;
import gep.num.NGEP;
import gep.rel.GreaterEqualsFactory;
import gep.rel.GreaterFactory;
import gep.rel.LessEqualsFactory;
import gep.rel.LessFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ���ڵ�����
 * 
 * ���ҳ�һЩ����ʽ����һ��ѵ��������֧�ֶȸߣ���������һ��ѵ��������֧�ֶȵ�
 */
public class NumGep extends NGEP
{
	private Random random = new Random();

	private char[] relations; // ���й�ϵ��������

	private Map<Character, FunctionFactory> relationFactories = new HashMap<Character, FunctionFactory>(); // ��ϵ����

	public NumGep()
	{
		addRelationFactory(new GreaterFactory());
		addRelationFactory(new GreaterEqualsFactory());
		addRelationFactory(new LessFactory());
		addRelationFactory(new LessEqualsFactory());
	}

	public void initialize()
	{
		super.initialize();
	}

	/** ���ع�ϵ���� */
	public Expression getRelation(char code)
	{
		FunctionFactory factory = (FunctionFactory) relationFactories.get(new Character(code));
		return factory.get(code);
	}

	/** ��ӹ�ϵ���� */
	public void addRelationFactory(FunctionFactory factory)
	{
		relationFactories.put(new Character(factory.getCode()), factory);
	}

	/** ��������Ĺ�ϵ���� */
	public char getRelationCode()
	{
		int index = random.nextInt(relations.length);
		return relations[index];
	}

	/** ���ÿ�ѡ��ϵ�������� */
	public void setRelationSet(String relations)
	{
		this.relations = relations.toCharArray();
	}
}
