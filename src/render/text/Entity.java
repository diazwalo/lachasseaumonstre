package render.text;

import java.util.ArrayList;

import map.Position;

/**
 * Cette Class defini les caracteristiques d'une Entity
 * @author diazw
 *
 */

public abstract class Entity {
	private Position pos;
	private ArrayList<Bonus> inventory = new ArrayList<>();
	
	public Entity(int posX, int posY) {
		this.pos=new Position(posX, posY);
	}
	
	/**
	 * Determine si la position donnee en parametre est similaire a celle courante
	 */
	public boolean isPosEnt(int posX, int posY) {
		return this.pos.isPos(posX, posY);
	}

	/**
	 * Retourne la Position Courante
	 * @return Position
	 */
	public Position getPos() {
		return this.pos;
	}

	/**
	 * Change la Position Courante
	 * @param pos
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void dispInventory() {
		if(this.inventory.size() == 0) {
			System.out.println("Il n'y aucun objet dans votre inventaire");
		}else {
			for(int i = 0 ; i< this.inventory.size() ; i++) {
				System.out.println(this.inventory.get(i).getName());
			}
		}
	}
}