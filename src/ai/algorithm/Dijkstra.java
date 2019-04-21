package ai.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import ai.util.NodeUtil;
import map.Position;
import ai.graph.Graph;

/**
 * Cette classe est une implementation de l'algorithme de Dijkstra.
 * Elle permet de trouver le chemin le plus court entre deux points.
 * Source : https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra
 * 
 * @author PHPierre
 *
 */
public class Dijkstra
{
	private Map<String, Node> listNode;
	private Map<String, Edge> listEdge;
	private Map<String, Node> shortestPath;

	/// test : Graph graph = new Graph(map);
	/// test : Dijkstra dijkstra = new Dijkstra(graph);
	public Dijkstra(Graph graph)
	{
		this.listNode = graph.getListNode();
		this.listEdge = graph.getListEdge();
		this.shortestPath = new HashMap<String, Node>();
	}

	///test : dijkstra.shortestPathFromTo( // hunter.pos.ToEdge // monster.pos.ToEdge )
	public List<Position> shortestPathFromTo(String from, String to)
	{
		List<Position> shortestPath = new ArrayList<Position>();
		String tmpNode = to;
		
		this.executeDijkstra(from);
		
		while(!tmpNode.equals(from))
		{
			shortestPath.add(NodeUtil.formatNode(tmpNode));
			tmpNode = this.listNode.get(tmpNode).getPrecedent();
		}
		
		Collections.reverse(shortestPath);
		
		return shortestPath;
	}
	
	private void executeDijkstra(String from)
	{
		Map<String, Node> nodes = new HashMap<String, Node>();
		nodes.putAll(this.listNode);
		// Set distance from source
		nodes.get(from).setDistanceFromOrigin(0);
		
		while(!nodes.isEmpty()) {
			String s = this.findMin(nodes);
			nodes.remove(s);

			for (String neighbourNode : this.listNode.get(s).getAdjacentNodes().keySet()) {
				this.updateDistance(s, neighbourNode);
			}
		}
	}
	
	private String findMin(Map<String, Node> nodeList)
	{
		int min = Integer.MAX_VALUE;
		String sommet = null;
		
		for (String nodeName : nodeList.keySet()) {
			int distance = nodeList.get(nodeName).getDistanceFromOrigin();

			if(min > distance) {
				min = distance;
				sommet = nodeName;
			}
		}
		
		return sommet;
	}
	
	private void updateDistance(String s1, String s2)
	{
		String name = EdgeUtil.formatEdge(s1, s2);
		int weightOld = this.listNode.get(s2).getDistanceFromOrigin();
		int weightNew = this.listNode.get(s1).getDistanceFromOrigin() + this.listEdge.get(name).getWeight();
		
		if(weightOld > weightNew) {
			this.listNode.get(s2).setDistanceFromOrigin(weightNew);
			this.listNode.get(s2).setPrecedent(s1);
		}
	}
}
