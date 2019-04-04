package ai.models;

public class Edge {
	private Node NodeOne;
	private Node NodeTwo;
	private int weight;

	public Edge(Node NodeOne, Node NodeTwo, int weigth)
	{
		this.NodeOne = NodeOne;
		this.NodeTwo = NodeTwo;
		this.weight = weigth;
	}
	
	public Edge(Node NodeOne, Node NodeTwo)
	{
		this(NodeOne, NodeTwo, Integer.MAX_VALUE);
	}

	public Node getNodeOne()
	{
		return this.NodeOne;
	}

	public void setNodeOne(Node nodeOne)
	{
		this.NodeOne = nodeOne;
	}

	public Node getNodeTwo()
	{
		return this.NodeTwo;
	}

	public void setNodeTwo(Node nodeTwo)
	{
		this.NodeTwo = nodeTwo;
	}

	public int getWeight()
	{
		return this.weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

}
