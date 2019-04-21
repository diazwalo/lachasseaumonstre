package ai.models;

import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe défini comment les sommets dont a besoin la classe Graph sont définies.
 * @author PHPierre
 *
 */
public class Node {
	private int distanceFromOrigin;
	private Map<String, Node> adjacentNodes;
	private String precedent;
	
	/**
	 * Genere un sommet avec une distance infinie de l'origine et une list de voisin vide.
	 */
	public Node() {
		this.distanceFromOrigin = Integer.MAX_VALUE;
		this.adjacentNodes = new HashMap<String, Node>();
	}
	
	/**
	 * Verifie si deux sommets sont connectes entre-eux a l'aide d'une arete.
	 * @param name L'identifiant du sommet a verifier
	 * @return boolean
	 */
	public boolean isAdjacent(String name)
	{
		return this.adjacentNodes.containsKey(name);
	}

	/**
	 * Retourne la longueur entre l'origine/puit et le sommet, il sera infini si le sommet n'a pas ete traité par un algorithme de parcours de chemin.
	 * @return int
	 */
	public int getDistanceFromOrigin()
	{
		return this.distanceFromOrigin;
	}

	/**
	 * Modifie la distance depuis l'origine du sommet
	 * @param distanceFromOrigin La distance depuis l'origine/puit.
	 */
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
