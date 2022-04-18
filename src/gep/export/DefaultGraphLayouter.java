package gep.export;

import java.util.Iterator;
import java.util.List;

/**
 * Ĭ�ϵĲ����� ÿһ��������ռ�õĿ��(width)��������ߵ��Ӵ��ڵ�����ұߵ��Ӵ��ڵ������ ������������֮����һ���ļ��(space)
 */
public class DefaultGraphLayouter implements GraphLayouter
{
	private double space; // ���, ����ˮƽ�ڵ�֮�����̼��
	private double height; // ���

	public DefaultGraphLayouter()
	{
		this.space = 25;
		this.height = 25;
	}

	public DefaultGraphLayouter(double space, double height)
	{
		this.space = space;
		this.height = height;
	}

	public void layout(Node root)
	{
		layout1(root);
		root.x = 0.0;
		root.y = 0.0;
	}

	/** ���в��� ���¶��Ͻ��� */
	private final void layout1(Node node)
	{
		List<Node> children = node.children;

		// Ҷ�ڵ�
		if (children.size() == 0) return;

		// ��Ҷ�ڵ�
		// �ֱ𲼾�ÿһ������
		for (Iterator<Node> i = children.iterator(); i.hasNext();)
		{
			Node child = i.next();
			layout1(child);
		}

		// ��������� ������x����Ϊ�ӵ�һ����������߽��ƫ����
		node.width = ((Node) children.get(0)).width;
		for (int i = 1; i < children.size(); i++)
		{
			node.width += space;

			Node child = (Node) children.get(i);
			child.x += node.width;

			node.width += child.width;
		}

		// ����x����
		node.x = (((Node) children.get(0)).x + ((Node) children.get(children.size() - 1)).x) / 2;
		for (int i = 0; i < children.size(); i++)
		{
			Node child = (Node) children.get(i);
			child.x = child.x - node.x;
			child.y = -height;
		}
	}
}
