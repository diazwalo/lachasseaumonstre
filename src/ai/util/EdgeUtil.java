package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des methodes statiques qui abordent 
 * sur la configuration des aretes.
 * @author PHPierre
 */
public class EdgeUtil {

	public final static String SEPARATOR = "|";
	
	/**
	 * Genere un identifiant pour relier deux positions du plateau.
	 * @param positionOne Une position du plateau.
	 * @param positionTwo Une position adjacente a la premiere sur le plateau.
	 * @return L'identifiant du vecteur.
	 */
    public static String formatEdge(Position positionOne, Position positionTwo)
    {
        return NodeUtil.formatNode(positionOne) + SEPARATOR + NodeUtil.formatNode(positionTwo);
    }
    
    /**
     * Genere un identifiant pour relier deux positions du plateau a partir de deux identifiants.
     * @param s1 Un identifiant representant la premiere position.
     * @param s2 Un identifiant representant la seconde position.
     * @return L'identifiant du vecteur.
     */
    public static String formatEdge(String s1, String s2)
    {
    	return s1 + SEPARATOR + s2;
    }
}
