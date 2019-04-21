package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des méthodes statiques qui abordent 
 * sur la configuration des arêtes.
 * @author PHPierre
 */
public class EdgeUtil {

	public final static String SEPARATOR = "|";
	
	/**
	 * Génère un identifiant pour relier deux positions du plateau.
	 * @param positionOne Une position du plateau.
	 * @param positionTwo Une position adjacente à la première sur le plateau.
	 * @return L'identifiant du vecteur.
	 */
    public static String formatEdge(Position positionOne, Position positionTwo)
    {
        return NodeUtil.formatNode(positionOne) + SEPARATOR + NodeUtil.formatNode(positionTwo);
    }
    
    /**
     * Génère un identifiant pour relier deux positions du plateau a partir de deux identifiants.
     * @param s1 Un identifiant représentant la premiere position.
     * @param s2 Un identifiant représentant la seconde position.
     * @return L'identifiant du vecteur.
     */
    public static String formatEdge(String s1, String s2)
    {
    	return s1 + SEPARATOR + s2;
    }
}
