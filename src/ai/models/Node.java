package ai.models;

import java.util.*;

public class Node {
	private int distanceFromOrigin;
	private Map<String, Node> adjacentNodes;
	private String precedent;
	
	public Node() {
		this.distanceFromOrigin = Integer.MAX_VALUE;
		this.adjacentNodes = new HashMap<String, Node>();
	}
	
	public boolean isAdjacent(String name)
	{
		return this.adjacentNodes.containsKey(name);
	}

	public int getDistanceFromOrigin()
	{
		return this.distanceFromOrigin;
	}

	public void setDistanceFromOrigin(int distanceFromOrigin)
	{
		this.distanceFromOrigin = distanceFromOrigin;
	}

	public Map<String, Node> getAdjacentNodes()
	{
		return this.adjacentNodes;
	}
	
	public void setPrecedent(String precedent)
	{
		this.precedent = precedent;
	}

	public String getPrecedent()
	{
		return this.precedent;
	}
	
	public void addAdjacentNode(String name, Node node)
	{
		this.adjacentNodes.putIfAbsent(name, node);
	}
	
	public void addAdjacentNode(Map<String, Node> nodeList)
	{
		this.adjacentNodes.putAll(nodeList);
	}
	
}
