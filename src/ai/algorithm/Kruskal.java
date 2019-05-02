package ai.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ai.comparator.EdgeComparator;
import ai.graph.Graph;
import ai.models.Edge;
import ai.models.EdgeTmp;
import ai.models.Node;
import ai.util.NodeUtil;
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
	
	/**
	 * Cette fonction applique l'algorithme de Kruskal sur un objet Graph.
	 * @return La file des deplacements a executer
	 */
	public List<Position> getPath()
	{
		List<Position> path = new ArrayList<Position>();
		
		path.addAll(this.executeKruskal());

		return path;
	}
	
	private List<Position> executeKruskal()
	{
		EdgeComparator edgeComparator = new EdgeComparator();
		List<EdgeTmp> edgeTmp = new ArrayList<>();
		List<Position> positions = new ArrayList<>();
		
		
		for (String name : this.listEdge.keySet()) {
			EdgeTmp edge = new EdgeTmp(
				name,
				this.listEdge.get(name).getNodeOneName(),
				this.listEdge.get(name).getNodeTwoName(),
				this.listEdge.get(name).getWeight()
			);
			edgeTmp.add(edge);
		}
		
		Collections.sort(edgeTmp, edgeComparator);
		
		for (String name : this.listNode.keySet()) {
			this.listNode.get(name).setPrecedent(null);
		}

		for (int i = 0; i < edgeTmp.size(); i++) {
			EdgeTmp e = edgeTmp.get(i);
			if(!this.find(e.getNodeOneName()).equals(this.find(e.getNodeTwoName()))) {
				Position p = NodeUtil.formatNode(edgeTmp.get(i).getNodeTwoName());
				positions.add(p);
				System.out.println(p);
				this.union(edgeTmp.get(i).getNodeOneName(), edgeTmp.get(i).getNodeTwoName());
			}
		}
		
		return positions;
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

		//if(this.listNode.get(x) != this.listNode.get(y)) {
		if(!this.listNode.get(x).equals(this.listNode.get(y))) {
			this.listNode.get(x).setPrecedent(y);
		}
	}
}
