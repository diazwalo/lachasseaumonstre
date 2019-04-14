package ai.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
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

	/// test : Graph graph = new Graph(map);
	/// test : Dijkstra dijkstra = new Dijkstra(graph);
	public Dijkstra(Graph graph)
	{
		this.listNode = graph.getListNode();
		this.listEdge = graph.getListEdge();
	}

	///test : dijkstra.shortestPathFromTo( // hunter.pos.ToEdge // monster.pos.ToEdge )
	public List<Node> shortestPathFromTo(Node from, Node to)
	{
		Map<String, Node> nodes = this.listNode;
		// Set distance from source
		from.setDistanceFromOrigin(0);
		
		while(!nodes.isEmpty()) {
			String s = this.findMin();
			nodes.remove(s);
			
			for (String neighbourEdge : this.listEdge.keySet()) {
				this.updateDistance(s, neighbourEdge);
			}
		}

		for (String name : this.listNode.keySet()) {
			System.out.println("name:" + name + "|"+ this.listNode.get(name).getDistanceFromOrigin());
		}
		return null;
	}
	
	public String findMin()
	{
		int min = Integer.MAX_VALUE;
		String sommet = null;
		
		for (String nodeName : this.listNode.keySet()) {
			int distance = this.listNode.get(nodeName).getDistanceFromOrigin();
			if(min > distance) {
				min = distance;
				sommet = nodeName;
			}
		}
		
		return sommet;
	}
	
	public void updateDistance(String s1, String s2)
	{
		String name = s1 + "|" + s2;
		//String name = EdgeUtil.formatEdge(positionOne, positionTwo)
		int weightOld = this.listNode.get(s2).getDistanceFromOrigin();
		int weightNew =  this.listNode.get(s1).getDistanceFromOrigin() + this.listEdge.get(name).getWeight();
		
		if(weightOld > weightNew) {
			this.listNode.get(s2).setDistanceFromOrigin(weightNew);
		}
	}
}
