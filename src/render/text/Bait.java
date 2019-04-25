package render.text;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Bait implements Bonus {
	private Position pos;
	private final String NAME = "leurre";
	
	/**
	 * Instancie Bait
	 */
	public Bait() {
		this.pos = null;
	}
	
	/**
	 * Instancie Bait et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX
	 * @param posY
	 */
	public Bait(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
	}
	
	/**
	 * retourne le nom du Bonus
	 */
	@Override
	public String getName() {
		return this.NAME;
	}

	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}
	
	/**
	 * passe le leurre a null si le chasseur passe dessus
	 */
	public void unInstall(Hunter h) {
		if(this.pos.equals(h.getPos())) {
			this.pos = null;
		}
	}
		
	/**
	 * retourne Beast sous la forme textuelle
	 */
	public String toString() {
		return "B";
	}

}
