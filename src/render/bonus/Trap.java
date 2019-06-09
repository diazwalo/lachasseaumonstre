package render.bonus;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Trap implements IBonus {

	private boolean used;
	private Position pos;
	private final String NAME = "Piege";
	
	/**
	 * Instancie le trap
	 */
	
	public Trap() {
		this.pos = null;
		this.used = false;
	}
	
	/**
	 * Instancie le trap en le posant au coordon�es x y donn�es
	 * @param posX
	 * @param posY
	 */
	
	public Trap(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
	}


	/**
	 *  Revoie le boolean determinant  si le trap est utilis� ou non
	 * @return
	 */
	public boolean getUsed() {
		return this.used;
	}
	
	/**
	 * Met la valeur du boolean determinant si le trap est  utilis� a true
	 */
	
	public void setUsed() {
		this.used=true;
	}
	
	/**
	 * Place le trap sur la position donn�es
	 */
	
	@Override
	public void install(int x, int y) {
		// TODO Auto-generated method stub
		this.pos = new Position(x, y);
	}

	/**
	 *  Retourne le nom du trap
	 */
 
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.NAME;
	}
	
	/**
	 * Donne la position du trap
	 */

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}

	/**
	 * Actualise le trap et met sa position a null si il est utilis�
	 */
	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		if(getUsed()) {
			this.pos=null;
		}
	}

	/**
	 * Renvoie la version  textuelle du trap.
	 */

	public String toString() {
		return "t";
	}

	/**
	 * Declenche le pi�ge
	 */
	@Override
	public void setTriggered() {
		// TODO Auto-generated method stub
		this.setUsed();
	}
	
}
