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
import ai.exception.NodeNotFoundException;
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

	/**
	 * Applique l'algorithme de Dijkstra sur un objet Graph.
	 * @param graph Un graph contenant les aretes et les sommets.
	 */
	public Dijkstra(Graph graph)
	{
		this.listNode = graph.getListNode();
		this.listEdge = graph.getListEdge();
	}
	
	
	public Map<String, Node> getListNode()
	{
		return this.listNode;
	}

	public Map<String, Edge> getListEdge()
	{
		return this.listEdge;
	}

	/**
	 * Genere le chemin depuis le sommet final et le formate en position pour etre utilisable.
	 * en compte les obstacles.
	 * @param from L'identifiant de la position de depart.
	 * @param to L'identifiant de la position finale.
	 * @return Une liste de positions contenant l'itineraire a suivre.
	 * @throws NodeNotFoundException 
	 */
	public List<Position> shortestPathFromTo(String from, String to)
	{
		checkIfNodesExist(from, to);
		
		List<Position> shortestPath = new ArrayList<Position>();
		String tmpNode = to;
		
		executeDijkstra(from);
		
		while(!tmpNode.equals(from))
		{
			shortestPath.add(NodeUtil.formatNode(tmpNode));
			tmpNode = this.listNode.get(tmpNode).getPrecedent();
		}
		
		Collections.reverse(shortestPath);
		
		return shortestPath;
	}
	
	private void checkIfNodesExist(String from, String to)
	{
		if(!this.listNode.containsKey(from)) {
			throw new NodeNotFoundException(from);
		}

		if(!this.listNode.containsKey(to)) {
			throw new NodeNotFoundException(to);
		}
	}

	/**
	 * Execute l'algorithme de Dijkstra pour chaque sommets.
	 * @param from
	 */
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
	
	/**
	 * Explore une liste de sommets afin de trouver celui ayant la plus courte distance depuis l'origine.
	 * @param nodeList Une liste de sommets.
	 * @return String L'identifiant du sommet ayant la plus courte distance depuis l'origine.
	 */
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
	
	/**
	 * Mets a jour les distances entre les sommets et l'origine grace au chemin le plus cours.
	 * @param s1 L'identifiant du sommet ayant une distance depuis l'origine a verifier
	 * @param s2 L'identifiant du sommet avec la distance connue.
	 */
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
