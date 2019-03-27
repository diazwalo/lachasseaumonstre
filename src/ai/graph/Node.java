package ai.graph;

import java.util.UUID;

public class Node {
	private UUID UUID;
	private String name;
	
	public Node(String name) {
		this.UUID = UUID.randomUUID();
		this.name = name;
	}
}
