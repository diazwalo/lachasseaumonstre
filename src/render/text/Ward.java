package render.text;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Ward implements Bonus {
	
	private int radius;
	private Position pos;
	private final String NAME = "Balise";;
	
	/**
	 * Instancie Ward
	 */
	public Ward() {
		this.radius = 3;
		this.pos = null;
	}

	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	public void install(int x , int y) {
		this.pos = new Position(x ,y);
	}
	
	/**
	 * retourne le nom du Bonus
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * met pour la valeur de radius le parametre qui defini le champ de vision de la balise de vision
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public Position getPos() {
		return pos;
	}
}
