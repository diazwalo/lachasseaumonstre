package ai.util;

import map.Position;

/**
 * Cette classe contient exclusivement des méthodes statiques qui abordent sur 
 * la configuration des sommets.
 * @author PHPierre
 *
 */
public class NodeUtil {

	public final static String SEPARATOR = ":";
	
	/**
	 * Génère un identifiant pour une position donné du plateau.
	 * @param position Une position sur le plateau.
	 * @return L'identifiant du sommet.
	 */
    public static String formatNode(Position position)
    {
        return position.getPosX() + SEPARATOR + position.getPosY();
    }

    /**
     * Genere La position a partir de son identifiant.
     * @param name Un identifiant sous forme de texte representant une position sur le plateau.
     * @return La position de l'identifiant.
     */
    public static Position formatNode(String name)
    {
    	String[] explodeArray = name.split(SEPARATOR);
    	return new Position(Integer.valueOf(explodeArray[0]), Integer.valueOf(explodeArray[1]));
    }
}
