package ai.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ai.comparator.EdgeComparator;
import ai.graph.Graph;
import ai.models.Edge;
import ai.models.Node;
import map.Position;

/**
 * Cette classe est une implementation de l'algorithme de Kruskal.
 * Elle permet de trouver le chemin qui necessite un minimun de temps pour le parcourir
 * tout en explorant toute la map.
 * 
 * Sources :
 * https://fr.wikipedia.org/wiki/Algorithme_de_Kruskal
 * https://fr.wikipedia.org/wiki/Union-find
 * @author PHPierre
 *
 */
public class Kruskal
{

	private Map<String, Node> listNode;
	private Map<String, Edge> listEdge;
	
	public Kruskal(Graph graph)
	{
		this.listNode = graph.getListNode();
		this.listEdge = graph.getListEdge();
	}
	
	public List<Position> getPath()
	{
		List<Position> path = new ArrayList<Position>();
		
		this.executeKruskal();
		
		return path;
	}
	
	private void executeKruskal()
	{
		EdgeComparator edgeComparator = new EdgeComparator(this.listEdge);
		Map<String, Edge> edgeList = new TreeMap<String, Edge>(edgeComparator);

		List<Edge> graphEdge = new ArrayList<>();
		
		for (String name : this.listNode.keySet()) {
			this.listNode.get(name).setPrecedent(null);
		}
		
		for (String nameEdge : edgeList.keySet()) {
			Edge edge = this.listEdge.get(nameEdge);
			if(this.find(edge.getNodeOneName()) != this.find(edge.getNodeTwoName())) {
				graphEdge.add(edge);
				this.union(edge.getNodeOneName(), edge.getNodeTwoName());
			}
		}
	}
	
	private String find(String name)
	{
		if(this.listNode.get(name).getPrecedent() == null) {
			return name;
		}
		return this.listNode.get(name).getPrecedent();
	}
	
	private void union(String x, String y)
	{
		this.listNode.get(x).setPrecedent(this.find(x));
		this.listNode.get(y).setPrecedent(this.find(y));
		
		if(this.listNode.get(x) != this.listNode.get(y)) {
			this.listNode.get(x).setPrecedent(y);
		}
	}
}
