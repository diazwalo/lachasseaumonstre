package ai.models;

public class Edge {
	private String nodeOneName;
	private String nodeTwoName;
	private Node nodeOne;
	private Node nodeTwo;
	private int weight;

	public Edge(String nodeOneName, String nodeTwoName, int weigth)
	{
		this.nodeOneName = nodeOneName;
		this.nodeTwoName = nodeTwoName;
		this.weight = weigth;
	}
	
	public Edge(String nodeOneName, String nodeTwoName)
	{
		this(nodeOneName, nodeTwoName, Integer.MAX_VALUE);
	}

	public String getNodeOneName()
	{
		return this.nodeOneName;
	}

	public String getNodeTwoName()
	{
		return this.nodeTwoName;
	}

	public int getWeight()
	{
		return this.weight;
	}

}
