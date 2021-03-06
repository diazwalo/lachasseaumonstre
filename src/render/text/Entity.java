package render.text;

import java.util.ArrayList;
import java.util.List;

import map.Case;
import map.CaseType;
import map.Mouvment;
import map.Position;
import render.bonus.IBonus;

/**
 * Cette Class defini les caracteristiques d'une Entity
 * @author diazw
 *
 */

public abstract class Entity {
	private Position pos;
	protected List<IBonus> inventory = new ArrayList<>();

	public Entity(int posX, int posY) {
		this.pos=new Position(posX, posY);
	}

	public void setPosition(Position posTarget) {
		this.pos.setPosX(posTarget.getPosX());
		this.pos.setPosY(posTarget.getPosY());
	}

	/**
	 * Retourne la Position Courante
	 * @return Position
	 */
	public Position getPos() {
		return this.pos;
	}

	/**
	 * deplace la position selon le Mouvment mvt
	 * @param mvt le mouvement a effectuer
	 */
	public void setPos(Mouvment mvt) {
		this.pos.movePosition(mvt);

	}


	/**
	 * Retourne l'inventaire de l'entite.
	 * @return L'inventaire du l'entite qui contient les bonus.
	 */
	public List<IBonus> getInventory()
	{
		return inventory;
	}

	/**
	 * retourne sous la forme textuelle l inventaire
	 */
	public void dispInventory() {
		if(this.inventory.size() == 0) {
			System.out.println("Il n'y aucun objet dans votre inventaire");
		}else {
			for(int i = 0 ; i< this.inventory.size() ; i++) {
				System.out.println(this.inventory.get(i).getName());
			}
		}
	}

	/**
	 * Determine si la position donnee en parametre est similaire a celle courante
	 * @param posX la position a chercher en x
	 * @param posY la position a chercher en y
	 * @return boolean
	 */
	public boolean isPosEnt(int posX, int posY) {
		return this.pos.isPos(posX, posY);
	}

	public abstract boolean verifDeplacementSpe(Case [][] tab, Mouvment mvt, Entity other);
	public abstract boolean isLock(Case[][] tab, Entity hunter);
	public abstract List<Mouvment> getMvtToEmptyCase(Case[][] tab);

	/**
	 * verifie que le deplacement souhaite ne fait pas sortir du tableau ou aller sur un obstacle
	 * @param tab
	 * @param mvt
	 * @return boolean
	 */
	protected boolean verifDeplacementEntity(Case [][] tab, Mouvment mvt ) {
		int[] posModif=this.pos.getModifPosTempo(mvt.getMvt());
		boolean valide=this.verifDeplacementOutOfBonds(tab, posModif);

		if(valide) {
			valide=valide && this.verifDeplacementColisionObstacle(tab, posModif);
		}

		return valide;
	}

	/**
	 * verifie que le deplacement ne vas pas faire sortir du plateau
	 * @param tab la tableau de case de la map de la partie
	 * @param posModif les coordonnes de l'entite post deplacement
	 * @return boolean 
	 */
	public boolean verifDeplacementOutOfBonds(Case[][] tab, int[] posModif) {
		return posModif[0]>-1 && posModif[1]>-1 && posModif[0]<tab.length && posModif[1]<tab[posModif[0]].length;
	}

	/**
	 * verifie que le deplacement ne vas pas faire tomber sur un obstacle
	 * @param tab le tableau de case de la map de la partier
	 * @param posModif les coordonees post deplacement
	 * @return boolean
	 */
	public boolean verifDeplacementColisionObstacle(Case[][] tab, int[] posModif) {
		return ! tab[posModif[0]][posModif[1]].getCaseType().equals(CaseType.OBSTACLE);
	} 
}