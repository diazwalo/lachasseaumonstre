package render.text;

import java.util.ArrayList;

import map.Case;
import map.Mouvment;
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
	
	public abstract boolean verifDeplacementSpe(Case [][] tab, Mouvment mvt, Entity other);
	
	protected boolean verifDeplacement(Case [][] tab, Mouvment mvt ) {
		int[] posModif=this.pos.getModifPosition(mvt.getMvt());
		boolean valide=posModif[0]>-1 && posModif[1]>-1 && posModif[0]<tab.length && posModif[1]<tab[posModif[0]].length;
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType().getCaseType()==1;
		}return valide;
	}
	
	/**
	 * deplace la position selon le Mouvment mvt
	 * @param mvt
	 */
	public void setPos(Mouvment mvt) {
		this.pos.movePosition(mvt);
		
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