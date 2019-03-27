package ai.models;

import java.util.UUID;

public class Edge {
	private UUID UUID;
	private String name;
	private UUID NodeOne;
	private UUID NodeTwo;
	private int weight;

	public Edge(UUID NodeOne, UUID NodeTwo, String name, int weigth)
	{
		this.UUID = UUID.randomUUID();
		this.NodeOne = NodeOne;
		this.NodeTwo = NodeTwo;
		this.name = name;
		this.weight = weigth;
	}
	
	public Edge(UUID NodeOne, UUID NodeTwo, String name)
	{
		this(NodeOne, NodeTwo, null, Integer.MAX_VALUE);
	}
	
	public Edge(UUID NodeOne, UUID NodeTwo)
	{
		this(NodeOne, NodeTwo, null);
	}

	public boolean equals(Edge edge)
	{
		return (this.UUID.toString().equals(edge.UUID.toString()));
	}

	public UUID getUUID()
	{
		return this.UUID;
	}

	public void setUUID(UUID UUID)
	{
		this.UUID = UUID;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public UUID getNodeOne()
	{
		return this.NodeOne;
	}

	public void setNodeOne(UUID nodeOne)
	{
		this.NodeOne = nodeOne;
	}

	public UUID getNodeTwo()
	{
		return this.NodeTwo;
	}

	public void setNodeTwo(UUID nodeTwo)
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
