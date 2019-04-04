package ai.models;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private int distanceFromOrigin;
	private Map<String, Edge> adjacentEdges;
	
	public Node() {
		this.distanceFromOrigin = Integer.MAX_VALUE;
		this.adjacentEdges = new HashMap<String, Edge>();
	}
	
	public boolean isAdjacent(Node node)
	{
		for (String name : this.adjacentEdges.keySet()) {
			if (node.equals(name)) return true;
		}
		return false;
	}
	
}
