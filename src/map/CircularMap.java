package map;

import config.Config;
import render.text.Beast;
import render.text.Hunter;

public class CircularMap extends AbstractMap {
	
	public CircularMap(Config config) {
		this.config = config;
		int widthTab=config.getWidth();
		int heightTab=config.getHeight();
		
		this.hunter = new Hunter(0, 0, config);
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
	public void generationMap() {
		// 14 -> 7
		// 15 -> 7
		int radius = (int) Math.floor(this.tab.length / 2);
		int preRadius = (int) Math.ceil(this.tab[radius].length / 2) / 2;
		
		for (int x = 0; x < this.tab.length; x++) {
			for (int y = 0; y < this.tab[x].length; y++) {//this.tab[x].length

				boolean posBeast = this.beast.isPosEnt(x, y);
				CaseType caseType;
				
				if(y >= preRadius && y < radius+preRadius) {
					caseType = CaseType.SOL;
				} else {
					if(preRadius-x > 0 || (radius+preRadius)-x <= 0)
					{
						if((preRadius-x <= y && y <= preRadius && x <= preRadius) || 
							(radius+preRadius > x && y > preRadius && (radius+preRadius)+x > y) ||
							((radius+preRadius <= x) && preRadius >= y && (radius+preRadius+y > x)) ||
							((radius+preRadius <= x && radius+preRadius <= y) && ((x+y) <= (this.tab.length + (Math.floor(2*this.tab.length/3)))))
							) {
							
							if(this.hunter.isPosEnt(0, 0)) {
								this.hunter.setPosition(new Position(x, y));
							}
							
							this.beast.setPosition(new Position(x, y));
							caseType = CaseType.SOL;//SOL
						}
						else {
							caseType = CaseType.OBSTACLE;
						}
					}else {
						caseType = CaseType.SOL;
					}
					
				}

				if(x%3==2 && y%3==2 && ! this.beast.isPosEnt(x, y) && ! this.hunter.isPosEnt(x, y)) caseType=CaseType.OBSTACLE;
				
				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}
}
