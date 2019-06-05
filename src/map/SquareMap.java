package map;

import config.Config;
import render.text.Beast;
import render.text.Hunter;

/**
 * Cette Class Definit le fonctionnement du plateau ou evolue les Entites tout en tenant compte de la Config
 * @author diazw
 *
 */

public class SquareMap extends AbstractMap {

	/**
	 * Construit un tableau de longueur len et de largeur wid et set Beast, Hunter et la Config
	 * @param len
	 * @param wid
	 */
	public SquareMap(Config config) {
		this.config = config;
		this.hunter = new Hunter(0, 0, config);
		int widthTab=config.getWidth();
		int heightTab=config.getHeight();

		this.beast = new Beast(widthTab-1, heightTab-1, config);
		this.tab = new Case[widthTab][heightTab];
		this.generationMap();
		super.generationBonus(/*widthTab/2*/);

		this.beastWin = false;
		this.hunterWin = false;
	}

	/**
	 * Remplie le tableau en mettant dans les cases soit des Obstacles soit des Sol.
	 */
	public final void generationMap() {
		for (int x = 0; x < this.tab.length; x++) {
			for (int y = 0; y < this.tab[x].length; y++) {
				boolean posBeast=this.beast.isPosEnt(x, y);
				CaseType caseType=CaseType.SOL;

				if(x%3==2 && y%3==2 && ! this.beast.isPosEnt(x, y) && ! this.hunter.isPosEnt(x, y)) caseType=CaseType.OBSTACLE;

				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}
}