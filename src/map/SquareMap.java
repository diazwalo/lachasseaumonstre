package map;

import config.Config;
import textRender.Beast;
import textRender.Hunter;

public class SquareMap implements Map {
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
				boolean posBeast=this.beast.getPosX()==x && this.beast.getPosY()==y;
				CaseType caseType=CaseType.SOL;
				if(x%3==2 && y%3==2) caseType=CaseType.OBSTACLE;
				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}

	/**
	 * Retourne le tableau à deux dimentions de Case
	 * @return tab
	 */
	public Case[][] getTab() {
		return tab;
	}

	/**
	 * Retourne le Hunter
	 * @return hunter
	 */
	public Hunter getHunter() {
		return hunter;
	}

	/**
	 * Retourne la Beast
	 * @return beast
	 */
	public Beast getBeast() {
		return beast;
	}
	
	/**
	 * Retourne la Config
	 * @return config
	 */
	public Config getConfig() {
		return this.config;
	}
	
	/**
	 * Defini (grace à aModifier un tableau de 4 booleans) s'il faut changer la Config
	 * @param aModifier
	 */
	public void setConfig(boolean[] aModifier) {
		config.setConfig(aModifier);
	}
	
	/**
	 * Petit Test pour voir ou pop le Buff 'p'
	 */
	public void testModifBuff() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				boolean obstacle=tab[i][j].getCaseType()==CaseType.OBSTACLE;
				if(i==(this.tab.length/2)+1 && j==this.tab[i].length/2 && !this.beast.estSurCase(i, j) && !this.hunter.estSurCase(i, j) && !obstacle) {
					this.tab[i][j].setBuff(new boolean[] {true, false, false, false});
				}
			}
		}
	}
	
	/**
	 * Retourne les La Map sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entity
	 */
	public String toString() {
		String affichage="";
		for (int x = 0; x < tab.length; x++) {
			affichage+="\n|";
			for (int y = 0; y < tab[x].length; y++) {
				if(this.beast.estSurCase(x, y)) affichage+=" "+this.beast.toString()+" |";
				else if(this.hunter.estSurCase(x, y)) affichage+=" "+this.hunter.toString()+" |";
				else affichage+=" "+tab[x][y].toString()+" |";
			}
		}
		return affichage;
	}
}