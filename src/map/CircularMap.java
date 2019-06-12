package map;

import config.Config;
import render.text.Beast;
import render.text.Hunter;

/**
 * Cette classe est un second de plateau de jeu pour jouer au jue. Il a une forme plus ou moins circulaire selon la congiruation lance.
 * @author PHPierre
 */
public class CircularMap extends AbstractMap
{
	
	/**
	 * Construit et configure le plateau de jeu de maniere circulaire avec les informations de la configuration.
	 * @param config
	 */
	public CircularMap(Config config) {
		this.config = config;
		int widthTab=config.getWidth();
		int heightTab=config.getHeight();
		
		this.hunter = new Hunter(0, 0, config);
		this.beast = new Beast(widthTab-1, heightTab-1, config);
		this.tab = new Case[widthTab][heightTab];
		
		this.generationMap();
		super.generationBonus();
		
		this.beastWin = false;
		this.hunterWin = false;
	}

	/**
	 * Remplie le tableau en mettant dans les cases soit des Obstacles soit des Sol.
	 */
	public void generationMap() {
		
		Position lastGround = new Position(0, 0);
		boolean hunterIsPlaced = false;
		
		int radius = (int) Math.floor(this.tab.length / 2);
		int preRadius = (int) Math.ceil(this.tab[radius].length / 2) / 2;
		
		
		
		for (int x = 0; x < this.tab.length; x++) {
			for (int y = 0; y < this.tab[x].length; y++) {

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
							caseType = CaseType.SOL;
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
				
				// On pose le chasseur a la premiere case qui est parcouru et n'a pas d'obstacle.
				if(this.tab[x][y].getCaseType() != CaseType.OBSTACLE && !hunterIsPlaced)
				{
					this.hunter.setPosition(new Position(x, y));
					hunterIsPlaced = true;
				}
				
				if(this.tab[x][y].getCaseType() != CaseType.OBSTACLE)
				{
					lastGround = new Position(x, y);
				}
			}
		}
		
		//On pose la bete a la derniere position explore qui ne contient pas d'obstacle.
		this.beast.setPosition(lastGround);
	}
}
