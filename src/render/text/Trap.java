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
	 * Instancie Trap et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX
	 * @param posY
	 */
	public Trap(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
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
	public void isActivate(Beast b) {
		if(this.pos.equals(b.getPos())) {
			this.activate = true;
		}
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Enleve le piège de la map si il a été utilisé
	 * @param b
	 */
	public void isUsed(Beast b) {
		if(this.pos.equals(b.getPos())) {
			this.pos = null;
		}
	}
  
}
