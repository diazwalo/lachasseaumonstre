package ai.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ai.models.Edge;
import ai.models.Node;
import ai.graph.Graph;

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
	public List<Node> shortestPathFromTo(Edge from, Edge to)
	{
	    //Découvrir les distances depuis l'origine avec chaque Edge
		List<Edge> distances = new ArrayList<Edge>();
		for(String name : this.listEdge.keySet()) {
			//distances.add();
		}

		return null;
	}
}
