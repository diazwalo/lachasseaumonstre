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
	
	public boolean isAdjacent(String name)
	{
		if(this.adjacentEdges.containsKey(name)) return true;
		return false;
	}

	public int getDistanceFromOrigin()
	{
		return this.distanceFromOrigin;
	}

	public void setDistanceFromOrigin(int distanceFromOrigin)
	{
		this.distanceFromOrigin = distanceFromOrigin;
	}

	public Map<String, Edge> getAdjacentEdges()
	{
		return this.adjacentEdges;
	}

	public void setAdjacentEdges(Map<String, Edge> adjacentEdges)
	{
		this.adjacentEdges = adjacentEdges;
	}
	
	
}
