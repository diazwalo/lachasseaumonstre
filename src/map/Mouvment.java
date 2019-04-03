package map;

/**
 * Cette classe defini tous les mouvements possible pour les joueurs
 * (bete ou chasseurs)
 * @author nath
 *
 */

public enum Mouvment {
	NORD(0,1), SUD(0, -1), EST(1, 0), OUEST(-1, 0), NORDEST(1,1), NORDOUEST(-1, 1), SUDEST(1, -1), SUDOUEST(-1, -1);
	private int mvtX;
	private int mvtY;
	
	private Mouvment(int mvtX, int mvtY) {
		this.mvtX=mvtX;
		this.mvtY=mvtY;
	}
	
	public int getMvtX() {
		return this.mvtX;
	}
	
	public int getMvtY() {
		return this.mvtY;
	}
	
	public int[]getMvt() {
		return new int[] {this.mvtX, this.mvtY};
	}
	 /**
	  * Renvoie un tableau contenant tous les deplacement de tous les mouvements possible.
	  * @return
	  */
	public int[][] allMouv(){
		int [][] tab = new int[8][2];
		int index = 0;
		for(Mouvment m :this.values()){
		  tab[index][0]= m.mvtX;
		  tab[index][1] = m.mvtY;
		  index++;
		}
		return tab;
	}
}