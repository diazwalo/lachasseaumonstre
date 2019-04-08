package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des méthodes statiques qui abordent sur 
 * la configuration des sommets.
 * @author PHPierre
 *
 */
public class NodeUtil {

	/**
	 * Génère un identifiant pour une position donné du plateau.
	 * @param position Une position sur le plateau.
	 * @return L'identifiant du sommet.
	 */
    public static String formatNode(Position position)
    {
        return position.getPosX() + ":" + position.getPosY();
    }

}
