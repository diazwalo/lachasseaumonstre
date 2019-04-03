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
	 * Modifie la composante sur X de la Position Courante avec le parametre
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX=posX;
	}
	
	/**
	 *  Modifie la composante sur Y de la Position Courante avec le parametre
	 *  @param posY
	 */
	public void setPosY(int posY) {
		this.posY=posY;
	}
	
	public void movePosition(Mouvment mvt) {
		int[] tabMvt=mvt.getMvt();
		this.posX=this.posX+tabMvt[0];
		this.posY=this.posY+tabMvt[1];
	}
	
	/**
	 * Renvoie un tableau d'entier contenant les deux composantes sous la forme(coord x , coord y)
	 */
	public int[] position() {
		return new int[] {this.posX ,this.posY};
	}

	public int[] getModifPosition(int[] mvt) {
		return new int[] {this.posX+mvt[0] ,this.posY+mvt[1]};
	}
	
	public List<Position> getAdjacentPosition(IMap sm){
		List<Position> posAdj =new ArrayList<Position>();
		
		for (int i=posX-1; i<posX+2; i++) {
			for(int j=posY-1; j<posY+2; j++) {
				
				if (i>=0 || j>=0 || (i!=posX && j!=posY) || i<sm.getTab()[i].length || i<sm.getTab()[j].length || sm.getTab()[i][j].getCaseType().equals(CaseType.SOL) ) {
					posAdj.add(new Position(i,j));
				}
				
			}
		}
		return posAdj;
				
	}
		
}