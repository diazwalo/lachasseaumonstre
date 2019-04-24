package ai.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ai.comparator.EdgeComparator;
import ai.graph.Graph;
import ai.models.Edge;
import map.Position;

/**
 * Cette classe est une implementation de l'algorithme de Kruskal.
 * Elle permet de trouver le chemin qui necessite un minimun de temps pour le parcourir
 * tout en explorant toute la map.
 * 
 * @author PHPierre
 *
 */
public class Kruskal
{

	private Map<String, Edge> listEdge;
	
	public Kruskal(Graph graph)
	{
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

	}
}
