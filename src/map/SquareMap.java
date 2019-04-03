package map;

import config.Config;
import render.text.Beast;
import render.text.Hunter;

/**
 * Cette Class D�finit le fonctionnement du plateau ou �volue les Entites tout en tenant compte de la Config
 * @author diazw
 *
 */

public class SquareMap implements IMap {
	private Case[][] tab;
	private Hunter hunter;
	private Beast beast;
	private Config config;

	/**
	 * Construit un tableau de longueur len et de largeur wid et set Beast, Hunter et la Config
	 * @param len
	 * @param wid
	 */
	public SquareMap(int len, int wid) {
		this.hunter=new Hunter(0, 0);
		this.beast=new Beast(len-1, wid-1);
		this.tab=new Case[len][wid];
		this.config=new Config();
	}
	
	/**
	 * Remplie le tableau en mettant dans les cases soit des Obstacles soit des Sol.
	 */
	public void generationMap() {
		for (int x = 0; x < this.tab.length; x++) {
			for (int y = 0; y < this.tab[x].length; y++) {
				boolean posBeast=this.beast.isPosEnt(x, y);
				CaseType caseType=CaseType.SOL;
				if(x%3==2 && y%3==2) caseType=CaseType.OBSTACLE;
				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}

	/**
	 * Retourne le tableau � deux dimentions de Case
	 * @return tab
	 */
	public Case[][] getTab() {
		return this.tab;
	}

	/**
	 * Retourne le Hunter
	 * @return hunter
	 */
	public Hunter getHunter() {
		return this.hunter;
	}

	/**
	 * Retourne la Beast
	 * @return beast
	 */
	public Beast getBeast() {
		return this.beast;
	}
	
	/**
	 * Retourne la Config
	 * @return config
	 */
	public Config getConfig() {
		return this.config;
	}
	
	/**
	 * Definis (grace � aModifier un tableau de 4 booleans) s'il faut changer la Config
	 * @param aModifier
	 */
	public void setConfig(boolean[] aModifier) {
		this.config.setConfig(aModifier);
	}
	
	/**
	 * Petit Test pour voir ou pop le Buff 'p'
	 */
	public void testModifBuff() {
		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab.length; j++) {
				boolean obstacle=tab[i][j].getCaseType()==CaseType.OBSTACLE;
				if(i==(this.tab.length/2)+1 && j==this.tab[i].length/2 && !this.beast.isPosEnt(i, j) && !this.hunter.isPosEnt(i, j) && !obstacle)
					this.tab[i][j].setBuff(new boolean[] {true, false, false, false});
			}
	}

	/**
	 * Teste si le d�placement souhait� pour Beast ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Hunter 
	 * @param mvt
	 * @return boolean valide
	 */
	public boolean mvtValideBeast(Mouvment mvt) {
		return this.beast.verifDeplacementSpe(tab, mvt, hunter);
	}

	/**
	 * Teste si le d�placement souhait� pour Hunter ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Beast
	 * @param mvt
	 * @return boolean valide
	 */
	public boolean mvtValideHunter(Mouvment mvt) {
		return this.hunter.verifDeplacementSpe(tab, mvt, beast);
	}

	public void moveBeast(Mouvment mvt) {
		if(this.mvtValideBeast(mvt)) this.beast.setPos(mvt);
	}
	
	public void moveHunter(Mouvment mvt) {
		if(this.mvtValideHunter(mvt)) this.hunter.setPos(mvt);
	}
	
	/**
	 * Retourne les La Map sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entity
	 */
	public String toString() {
		String affichage="";
		for (int x = 0; x < this.tab.length; x++) {
			affichage+="\n|";
			for (int y = 0; y < this.tab[x].length; y++) {
				if(this.beast.isPosEnt(x, y)) affichage+=" "+this.beast.toString()+" |";
				else if(this.hunter.isPosEnt(x, y)) affichage+=" "+this.hunter.toString()+" |";
				else affichage+=" "+this.tab[x][y].toString()+" |";
			}
		}
		return affichage;
	}
}