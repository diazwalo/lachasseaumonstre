package render.text;

/**
 * Cette classe definit les caractéristique principale de la classe jouable Hunter
 * @author Quentin Prognon
 *
 */
public class Hunter extends Entity {
	public Hunter(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * Renvoie un H
	 */
	public String toString() {
		return "H";
	}
}