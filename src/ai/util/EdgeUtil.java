package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des méthodes statiques qui abordent 
 * sur la configuration des arêtes.
 * @author PHPierre
 */
public class EdgeUtil {

	/**
	 * Génère un identifiant pour relier deux positions du plateau.
	 * @param positionOne Une position du plateau.
	 * @param positionTwo Une position adjacente à la première sur le plateau.
	 * @return L'identifiant du vecteur.
	 */
    public static String formatEdge(Position positionOne, Position positionTwo)
    {
        return NodeUtil.formatNode(positionOne) + "|" + NodeUtil.formatNode(positionTwo);
    }
}
