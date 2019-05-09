package map;

import java.util.ArrayList;
import java.util.Random;

import config.Config;
import interaction.Interaction;
import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;
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
		this.hunter = new Hunter(0, 0, config);
		int widthTab=config.getWidth();
		int heightTab=config.getHeight();

		if(widthTab < 5 || heightTab < 5) {
			widthTab=5;
			heightTab=5;
		}

		this.beast = new Beast(widthTab-1, heightTab-1, config);
		this.tab = new Case[widthTab][heightTab];
		this.generationMap();
		this.generationBonus(widthTab/2);

		this.config = config;
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

	/**
	 * place les bonus a ramasser sur la map
	 */
	public final void generationBonus(int middle) {
		Position posTrap=this.createPositionTempoFirstBonus(0, new boolean[] { true,  false }, false);
		this.createPositionTempoSecondBonus(0, new boolean[] { false,  true }, posTrap, false);
		System.out.println("Creation Bonus Hunter");
		Position posBait=this.createPositionTempoFirstBonus(middle, new boolean[] { true,  false }, true);
		this.createPositionTempoSecondBonus(middle, new boolean[] { false,  true }, posBait, true);
	}

}