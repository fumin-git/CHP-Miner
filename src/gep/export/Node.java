package gep.export;

import gep.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * �������ʽͼ�Ľڵ�
 */
public class Node
{
	Expression expression; // ����ı��ʽ�ڵ�

	int id; // Ψһ�ı��
	char label; // �ڵ�ı�ǩ
	double width = 0.0; // �Ըýڵ�Ϊ���������Ŀ��
	double x = 0.0; // �ýڵ�ĺ�����(����ڸ��ڵ�) (����׶������������������˵�)
	double y = 0.0; // �ýڵ��������(����ڸ��ڵ�)
	List<Node> children = new ArrayList<Node>(); // �ӽڵ�
}
