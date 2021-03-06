package render.bonus;

import map.Position;

/**
 * @author prognonq
 *
 */ 
public class Ward implements IBonus {

	private int timer;
	private Position pos;
	private boolean triggered;
	private final String NAME = "Balise";

	/**
	 * Instancie Ward
	 */
	public Ward() {
		this.timer = 3;
		this.pos = null;
		this.triggered = false;
	}

	/**
	 * Instancie Ward et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX	s
	 * @param posY	les coordonnees du point y sur la map
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
	 * Revoin le temps de vie de la ward
	 * @return un int
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * retourne la position du Bonus courant
	 * @return Position une position
	 */
	public Position getPos() {
		return pos;
	}


	/**
	 * met pour la valeur de radius le parametre qui defini le champ de vision de la balise de vision
	 * @param time Le temps de vie de la ward
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
		if(this.triggered) {
			this.timer--;
			if(this.timer <= 0) {
				this.pos = null;
			}
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
		this.triggered = true;
	}
}
