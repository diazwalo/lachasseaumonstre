package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des m�thodes statiques qui abordent sur 
 * la configuration des sommets.
 * @author PHPierre
 *
 */
public class NodeUtil {

	/**
	 * G�n�re un identifiant pour une position donn� du plateau.
	 * @param position Une position sur le plateau.
	 * @return L'identifiant du sommet.
	 */
    public static String formatNode(Position position)
    {
        return position.getPosX() + ":" + position.getPosY();
    }

    public static Position formatNode(String name)
    {
    	String[] explodeArray = name.split(":");
    	return new Position(Integer.valueOf(explodeArray[0]), Integer.valueOf(explodeArray[1]));
    }
}
