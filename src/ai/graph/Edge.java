package ai.graph;

import java.util.UUID;

public class Edge {
	private String name;
	private UUID NodeOne;
	private UUID NodeTwo;
	
	public Edge(UUID NodeOne, UUID NodeTwo, String name)
	{
		this.NodeOne = NodeOne;
		this.NodeTwo = NodeTwo;
		this.name = name;
	}
	
	public Edge(UUID NodeOne, UUID NodeTwo)
	{
		this(NodeOne, NodeTwo, null);
	}
	
}
