package map;

/**
 * Cette classe defini tous les mouvements possible pour les joueurs
 * (bete ou chasseurs)
 * @author nath
 *
 */

public enum Mouvment {
	NORD(0,-1), SUD(0, 1), EST(1, 0), OUEST(-1, 0), NORDEST(1,-1), NORDOUEST(-1, -1), SUDEST(1, 1), SUDOUEST(-1, 1);
	private int mvtX;
	private int mvtY;
	
	/**
	 * associe au mouvement 2 int pour leurs mouvement sur x et y associees respectivement au parametres mvtX et mvtY
	 * @param mvtX
	 * @param mvtY
	 */
	private Mouvment(int mvtX, int mvtY) {
		this.mvtX=mvtX;
		this.mvtY=mvtY;
	}
	
	/**
	 * retourn le mouvement sur x
	 * @return int
	 */
	public int getMvtX() {
		return this.mvtX;
	}
	
	/**
	 * retourn le mouvement sur y
	 * @return int
	 */
	public int getMvtY() {
		return this.mvtY;
	}
	
	/**
	 * retourn un tableau a une dimention contenant la composante sur x et celle sur y
	 * @return int[]
	 */
	public int[] getMvt() {
		return new int[] {this.mvtX, this.mvtY};
	}
}