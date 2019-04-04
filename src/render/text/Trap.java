package render.text;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Trap implements Bonus {
	
	private Position pos;
	private final String NAME = "Piège";
	private boolean activate;
	
	/**
	 * Instancie Trap
	 */
	public Trap() {
		this.pos = null;
		this.activate = false;
	}
	
	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}

	/**
	 * retourne le nom du Bonus
	 */
	@Override
	public String getName() {
		return this.NAME;
	}

	/**
	 * retourne vrai si le piege est active
	 * @return boolean
	 */
	public boolean isActivate() {
		return activate;
	}

	/**
	 * met la valeur de activate a la valeur passee en parametre
	 * @param activate
	 */
	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public Position getPos() {
		return pos;
	}
  
}
