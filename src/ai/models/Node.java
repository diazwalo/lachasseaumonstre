package ai.models;

import java.util.*;

public class Node {
	private int distanceFromOrigin;
	private List<Edge> adjacentEdges;
	
	public Node() {
		this.distanceFromOrigin = Integer.MAX_VALUE;
		this.adjacentEdges = new ArrayList<Edge>();
	}
	
	public boolean isAdjacent(String name)
	{
		for (Edge edge : this.adjacentEdges) {
			if(edge.getNodeTwoName().equals(name)) return true;
		}
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

	public List<Edge> getAdjacentEdges()
	{
		return this.adjacentEdges;
	}
	
	public boolean addAdjacentEdge(Edge edge)
	{
		return this.adjacentEdges.add(edge);
	}
	
	public boolean addAdjacentEdge(List<Edge> edgeList)
	{
		return this.adjacentEdges.addAll(edgeList);
	}
	
}
