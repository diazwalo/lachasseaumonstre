package map;

/**
 * Cette classe defini tous les mouvements possible pour les joueurs
 * (bête ou chasseurs)
 * @author nath
 *
 */

public enum Mouvement {
	NORD(0,1), SUD(0, -1), EST(1, 0), OUEST(-1, 0), NORDEST(1,1), NORDOUEST(-1, 1), SUDEST(1, -1), SUDOUEST(-1, -1);
	private int mvtX;
	private int mvtY;
	
	private Mouvement(int mvtX, int mvtY) {
		this.mvtX=mvtX;
		this.mvtY=mvtY;
	}
	
	public int getMvtX() {
		return this.mvtX;
	}
	
	public int getMvtY() {
		return this.mvtY;
	}
	
	public int[][]getMvt() {
		return new int[][] {{this.mvtX}, {this.mvtY}};
	}
}