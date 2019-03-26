package render.text;

/**
 * Cette classe definit les caractéristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast extends Entity{
	public Beast(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * Renvoie un B
	 */
	public String toString() {
		return"B";
	}
}