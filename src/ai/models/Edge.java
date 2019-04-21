package ai.models;

public class Edge {
	
	public static int defaultWeight = 1;
	private String nodeOneName;
	private String nodeTwoName;
	private Node nodeOne;
	private Node nodeTwo;
	private int weight;

	/**
	 * Cr�er une ar�te a partir de deux sommets et de son poids.
	 * @param nodeOneName L'identifiant du premier sommet.
	 * @param nodeTwoName L'identifiant du second sommet.
	 * @param weigth Le poids de l'ar�te
	 */
	public Edge(String nodeOneName, String nodeTwoName, int weigth)
	{
		this.nodeOneName = nodeOneName;
		this.nodeTwoName = nodeTwoName;
		this.weight = weigth;
	}
	
	/**
	 * Cr�er une ar�te a partir de deux sommets en fixant son poids a celui par default.
	 * @param nodeOneName L'identifiant du premier sommet.
	 * @param nodeTwoName L'identifiant du second sommet.
	 */
	public Edge(String nodeOneName, String nodeTwoName)
	{
		this(nodeOneName, nodeTwoName, defaultWeight);
	}

	/**
	 * Retourne l'identifiant du premier sommet de l'ar�te.
	 * @return String
	 */
	public String getNodeOneName()
	{
		return this.nodeOneName;
	}

	/**
	 * Retourne l'identifiant du second sommet de l'ar�te.
	 * @return String
	 */
	public String getNodeTwoName()
	{
		return this.nodeTwoName;
	}

	/**
	 * Retourne le poids de l'ar�te.
	 * @return int
	 */
	public int getWeight()
	{
		return this.weight;
	}
}
