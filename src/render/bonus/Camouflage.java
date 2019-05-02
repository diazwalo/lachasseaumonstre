package render.bonus;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Camouflage implements IBonus {
	
	private Position pos;
	private final String NAME = "Piege";

	/**
	 * Instancie Camouflage
	 */
	public Camouflage() {
		this.pos = null;
	}
	
	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x,y);
	}
	
	/**
	 * place ce Bonus a la position donn�es
	 */
	public void install(Position pos) {
		this.pos = pos;
	}
	
	
	/**
	 * passe la position du camouflage a null
	 */
	public void unistall() {
		this.pos = null;
	}
	
	/**
	 * retourne la position du camouflage
	 * @return
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * retourne le nom
	 * @return
	 */
	@Override
	public String getName() {
		return this.NAME;
	}
	
	public String toString() {
		return "c";
	}

	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		
	}
}
