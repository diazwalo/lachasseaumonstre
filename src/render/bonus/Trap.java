package render.bonus;

import map.Position;
import render.text.Beast;

/**
 * @author prognonq
 *
 */
public class Trap implements IBonus {
	
	private Position pos;
	private final String NAME = "Piege";
	private boolean activate;
	
	/**
	 * Instancie Trap
	 */
	public Trap() {
		this.pos = null;
		this.activate = false;
	}
	
	/**
	 * Instancie Trap et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX
	 * @param posY
	 */
	public Trap(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public Position getPos() {
		return pos;
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
	public boolean getActivate() {
		return activate;
	}

	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}

	/**
	 * met la valeur de activate si la bete passe par la
	 * @param activate
	 */
	public void isActivate(Beast b) {
		if(this.pos.equals(b.getPos())) {
			this.activate = true;
			this.isUsed();
		}
	}
	
	/**
	 * Enleve le piege de la map si il a ete utilise
	 */
	public void isUsed() {
		this.pos=null;
	}
  
}
