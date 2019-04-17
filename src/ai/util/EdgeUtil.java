package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des m�thodes statiques qui abordent 
 * sur la configuration des ar�tes.
 * @author PHPierre
 */
public class EdgeUtil {

	public final static String SEPARATOR = "|";
	/**
	 * G�n�re un identifiant pour relier deux positions du plateau.
	 * @param positionOne Une position du plateau.
	 * @param positionTwo Une position adjacente � la premi�re sur le plateau.
	 * @return L'identifiant du vecteur.
	 */
    public static String formatEdge(Position positionOne, Position positionTwo)
    {
        return NodeUtil.formatNode(positionOne) + SEPARATOR + NodeUtil.formatNode(positionTwo);
    }
    
    public static String formatEdge(String s1, String s2)
    {
    	return s1 + SEPARATOR + s2;
    }
}
