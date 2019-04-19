package map;

import java.util.ArrayList;
import java.util.List;
import map.SquareMap;

/**
 * Cette Class defini les caracteristiques de la Position
 * @author diazw
 *
 */

public class Position {
	private int posX;
	private int posY;
	
	/**
	 * associe a la composante x et y les valeurs en parametre rexpectivement posX et posY
	 * @param posX
	 * @param posY
	 */
	public Position(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}
	
	/**
	 * Determine si l'Entity est a la position donnee en Parametre
	 * @param posX
	 * @param posY
	 * @return boolean
	 */
	public boolean isPos(int posX, int posY) {
		return this.posX==posX && this.posY==posY;
	}
	
	/**
	 * Retourne la composante sur X de la Position
	 * @return int
	 */
	public int getPosX() {
		return this.posX;
	}
	
	/**
	 * Retourne la composante sur Y de la Position
	 * @return int
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * Modifie la composante sur X de la Position Courante en lui associant la valeur ud parametre posX
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX=posX;
	}
	
	/**
	 *  Modifie la composante sur Y de la Position Courante en lui associant la valeur ud parametre posY
	 *  @param posY
	 */
	public void setPosY(int posY) {
		this.posY=posY;
	}
	
	/**
	 * Affiche la position de l'objet.
	 */
	public String toString()
	{
		return "Position : [x:" + this.posX + ",y:" + this.posY + "]";
	}
	
	/**
	 * deplace la Position courante en lui applicant le Mouvment mvt passe en parametre
	 * @param mvt
	 */
	public void movePosition(Mouvment mvt) {
		int[] tabMvt=mvt.getMvt();
		this.posX=this.posX+tabMvt[0];
		this.posY=this.posY+tabMvt[1];
	}
	
	/**
	 * Renvoie un tableau d'entier contenant les deux composantes sous la forme{coord x , coord y}
	 * @return int[]
	 */
	public int[] position() {
		return new int[] {this.posX ,this.posY};
	}

	/**
	 * retourne la position telle qu elle serait si le Mouvment mvt etait applique
	 * @param mvt
	 * @return int[]
	 */
	public int[] getModifPosTempo(int[] mvt) {
		return new int[] {(this.posX+(mvt[0])) ,(this.posY+(mvt[1]))};
	}
	
	/**
	 * retourn la liste des Positions reliees a la Position courante
	 * @param sm
	 * @return List<Position>
	 */
	public List<Position> getAdjacentPosition(IMap sm) {
		List<Position> positionAdjacent =new ArrayList<Position>();

		checkPosition(new Position(this.posX-1, this.posY-1), positionAdjacent, sm);
		checkPosition(new Position(this.posX, this.posY-1), positionAdjacent, sm);
		checkPosition(new Position(this.posX+1, this.posY-1), positionAdjacent, sm);
		
		checkPosition(new Position(this.posX-1, this.posY), positionAdjacent, sm);
		checkPosition(new Position(this.posX+1, this.posY), positionAdjacent, sm);
		
		checkPosition(new Position(this.posX-1, this.posY+1), positionAdjacent, sm);
		checkPosition(new Position(this.posX, this.posY+1), positionAdjacent, sm);
		checkPosition(new Position(this.posX+1, this.posY+1), positionAdjacent, sm);
		
		return positionAdjacent;
	}
	
	/**
	 * Ajoute la position a une liste donnee si elle est accessible
	 * @param p la position actuelle
	 * @param positionAdjacent La position est ajoute dedans si elle est accessible
	 * @param sm Le plateau de jeu
	 */
	public void checkPosition(Position p, List<Position> positionAdjacent, IMap sm) {
		if(p.getPosX() < 0 || p.getPosY() < 0) return;
		if(p.getPosX() > sm.getTab().length-1 || p.getPosY() > sm.getTab()[p.getPosX()].length-1) return;
		if(sm.getTab()[p.getPosX()][p.getPosY()] instanceof Case 
				&& sm.getTab()[p.getPosX()][p.getPosY()].getCaseType().equals(CaseType.SOL)) {
			positionAdjacent.add(p);
		}
	}
	
	public boolean equals(Position p) {
		return this.posX==p.posX && this.posY==p.posY;
	}
}