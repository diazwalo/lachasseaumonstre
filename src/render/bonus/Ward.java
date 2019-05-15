package render.bonus;

import map.Position;

/**
 * @author prognonq
 *
 */ 
public class Ward implements IBonus {
	
	private int timer;
	private Position pos;
	private final String NAME = "Balise";;
	
	/**
	 * Instancie Ward
	 */
	public Ward() {
		this.timer = 5;
		this.pos = null;
	}
	
	/**
	 * Instancie Ward et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX
	 * @param posY
	 */
	public Ward(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
	}
	
	/**
	 * retourne le nom du Bonus
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * met pour la valeur de radius le parametre qui defini le champ de vision de la balise de vision
	 * @param radius
	 */
	public void setTimer(int time) {
		this.timer = time;
	}

	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	public void install(int x , int y) {
		this.pos = new Position(x ,y);
	}
	
	/**
	 * Decremente le rayon du champ de vision de la ward. Si il tombe a zero met la position de la ward a null
	 */
	public void refresh() {
		this.timer--;
		if(this.timer == 0) {
			this.pos = null;
		}
	}
	
	/**
	 * retourne la balise sous la forme textuelle
	 */
	public String toString() {
		return "w";
	}

	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		if(this.timer > 0) {
			this.refresh();
		}
	}

	@Override
	public void setTriggered() {
		// TODO Auto-generated method stub
		
	}
}
