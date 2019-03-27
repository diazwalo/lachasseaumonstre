package ai.models;

import java.util.UUID;

public class Node {
	private UUID UUID;
	private String name;
	
	public Node(String name) {
		this.UUID = UUID.randomUUID();
		this.name = name;
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

	public boolean equals(Node node)
	{
		return (this.UUID.toString().equals(node.UUID.toString()));
	}
	
	public boolean isAdjacent(Node node)
	{
		//TODO 
		return false;
	}
	
}
