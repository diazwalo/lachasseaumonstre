package map;

import java.util.ArrayList;
import java.util.List;

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
	 * Determine si l'Entity est a la position donnee en Parametre
	 * @param posX
	 * @param posY
	 * @return boolean
	 */
	public boolean isPos(int posX, int posY) {
		return this.posX==posX && this.posY==posY;
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
	public int[] getTabPosition() {
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
	public List<Position> getAdjacentPosition(AbstractMap sm) {
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
	public void checkPosition(Position p, List<Position> positionAdjacent, AbstractMap sm) {
		if(p.getPosX() < 0 || p.getPosY() < 0) return;
		if(p.getPosX() > sm.getTab().length-1 || p.getPosY() > sm.getTab()[p.getPosX()].length-1) return;
		
		if(sm.getTab()[p.getPosX()][p.getPosY()] instanceof Case && sm.getTab()[p.getPosX()][p.getPosY()].getCaseType().equals(CaseType.SOL)) {
			positionAdjacent.add(p);
		}
	}


	/**
	 * Affiche la position de l'objet.
	 */
	public String toString() {
		return "Position : [x:" + this.posX + ",y:" + this.posY + "]";
	}

	public Position toSouth()
	{
		return new Position(this.posX, this.posY+1);
	}

	public Position toSouthWest()
	{
		return new Position(this.posX-1, this.posY+1);
	}

	public Position toSouthEast()
	{
		return new Position(this.posX+1, this.posY+1);
	}

	public Position toLeft()
	{
		return new Position(this.posX-1, this.posY);
	}

	public Position toRight()
	{
		return new Position(this.posX+1, this.posY);
	}

	public Position toNorth()
	{
		return new Position(this.posX, this.posY-1);
	}

	public Position toNorthWest()
	{
		return new Position(this.posX-1, this.posY-1);
	}

	public Position toNorthEast()
	{
		return new Position(this.posX+1, this.posY-1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	/**
	 * Effectue la difference entre deux positions.
	 * @param currentPosition la position actuelle.
	 * @param toPosition La position finale.
	 * @return Une position qui indique la différence.
	 */
	public static Position difference(Position currentPosition, Position toPosition)
	{
		int newX = toPosition.getPosX() - currentPosition.getPosX();
		int newY = toPosition.getPosY() - currentPosition.getPosY();
		
		return new Position(newX, newY);
	}
	
	/**
	 * Retourne le mouvement effectué pour se deplacer entre deux positions.
	 * @param currentPosition La position actuelle.
	 * @param toPosition la position finale.
	 * @return Le mouvement pour se deplacer de la position courante à la position finale.
	 */
	public static Mouvment toMouvment(Position currentPosition, Position toPosition)
	{
		Position p = difference(currentPosition, toPosition);

		Mouvment[] allMvt = Mouvment.values();
		
		for(int i = 0; i < allMvt.length; i++) {
			Mouvment m = Mouvment.valueOf(allMvt[i].name());
			if(m.getMvtX() == p.getPosX() && m.getMvtY() == p.getPosY())
			{
				return allMvt[i];
			}
		}
		return null;
	}
}