package render.bonus;
 
import map.Position;

/**
 * @author prognonq
 *
 */
public class Camouflage implements IBonus {
	
	private Position pos;
	/*private int lifetime = 4;*/
	private final String NAME = "Camouflage";

	/**
	 * Instancie Camouflage
	 */
	public Camouflage() {
		this.pos = null;
	}
	
	/*public void activate() {
		this.lifetime=4;
	}*/
	
	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x,y);
	}
	
	/**
	 * place ce Bonus a la position donnees
	 * @param pos La position ou le bonus doit etre installer
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
	 * @return Une position
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * retourne le nom
	 * @return un String avec le nom de l'objet
	 */
	@Override
	public String getName() {
		return this.NAME;
	}
	
	public String toString() {
		return "c";
	}

	/*public boolean lifetimeOut() {
		return this.lifetime <= 0;
	}*/
	
	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		/*this.lifetime--;*/
	}

	@Override
	public void setTriggered() {
		// TODO Auto-generated method stub
		// ne sert pas pour ce Bonus
	}
}
