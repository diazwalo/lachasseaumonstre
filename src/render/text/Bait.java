package render.text;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Bait implements Bonus {
	private Position pos;
	private final String NAME = "leurre";
	private boolean decouvert;
	
	/**
	 * Instancie Bait
	 */
	public Bait() {
		this.decouvert = false;
		this.pos = null;
	}
	
	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);

	}

	/**
	 * retourn le nom du Bonus
	 */
	@Override
	public String getName() {
		return this.NAME;
	}

}
