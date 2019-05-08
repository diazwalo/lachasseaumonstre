package map;

import java.util.ArrayList;

import config.Config;
import render.bonus.IBonus;
import render.text.Beast;
import render.text.Hunter;

public class CircularMap implements IMap
{
	private Case[][] tab;
	private Hunter hunter;
	private Beast beast;
	private Config config;
	private boolean beastWin;
	private boolean hunterWin;
	
	public CircularMap(Config config)
	{
		this.hunter = new Hunter(0, 0, config);
		this.beast = new Beast(config.getWidth()-1, config.getHeight()-1, config);
		this.tab = new Case[config.getWidth()][config.getHeight()];
		this.config = config;
		this.beastWin = false;
		this.hunterWin = false;
	}
	
	/**
	 * Remplie le tableau en mettant dans les cases soit des Obstacles soit des Sol.
	 */
	public void generationMap()
	{
		// 14 -> 7
		// 15 -> 7
		int radius = (int) Math.floor(this.getConfig().getWidth() / 2);
		int preRadius = (int) Math.ceil(this.getConfig().getWidth() / 2) / 2;
		
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
							caseType = CaseType.VOID;//VOID
						}
					}else {
						caseType = CaseType.SOL;
					}
					
				}
				if(x%3==2 && y%3==2 && ! this.beast.isPosEnt(x, y) && ! this.hunter.isPosEnt(x, y) && caseType != CaseType.VOID) caseType=CaseType.OBSTACLE;
				
				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}

	/**
	 * Retourne le tableau a deux dimentions de Case
	 * @return Case[][]
	 */
	public Case[][] getTab()
	{
		return this.tab;
	}

	/**
	 * Retourne le Hunter
	 * @return Hunter
	 */
	public Hunter getHunter()
	{
		return this.hunter;
	}

	/**
	 * Retourne la Beast
	 * @return Beast
	 */
	public Beast getBeast()
	{
		return this.beast;
	}
	
	/**
	 * Retourne si la bete a gagnee
	 * @return boolean
	 */
	public boolean isBeastWin()
	{
		return this.beastWin;
	}
	
	/**
	 * Retourne si le chasseur a gagn�
	 * @return boolean
	 */
	public boolean isHunterWin()
	{
		return hunterWin; 
	}
	
	public void setHunterWin(boolean hunterWin)
	{
		this.hunterWin=hunterWin;
	}
	
	public void setBeastWin(boolean beastWin)
	{
		this.beastWin=beastWin;
	}

	/**
	 * Retourne la Config
	 * @return Config
	 */
	public Config getConfig()
	{
		return this.config;
	}

	/**
	 * Teste si le deplacement souhaite pour Beast ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Hunter 
	 * @param mvt
	 * @return boolean
	 */
	public boolean mvtValideBeast(Mouvment mvt)
	{
		return this.beast.verifDeplacementSpe(tab, mvt, hunter);
	}

	/**
	 * Teste si le deplacement souhaite pour Hunter ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Beast
	 * @param mvt
	 * @return boolean
	 */
	public boolean mvtValideHunter(Mouvment mvt)
	{
		return this.hunter.verifDeplacementSpe(tab, mvt, beast);
	}

	/**
	 * Deplace la Beast avec le Mouvment passe en parametre si ce dernier est possible
	 * @return boolean
	 */
	public boolean moveBeast(Mouvment mvt)
	{
		if(this.mvtValideBeast(mvt)) {
			this.beast.setPos(mvt);
			this.tab[this.beast.getPos().getPosX()][this.beast.getPos().getPosY()].modifBeastWalk(true);
			return true;
		}return false;
	}
	
	/**
	 * Deplace la Hunter avec le Mouvment passe en parametre si ce dernier est possible
	 * @return boolean
	 */
	public boolean moveHunter(Mouvment mvt)
	{
		if(this.mvtValideHunter(mvt)) {
			this.hunter.setPos(mvt);
			return true;
		}return false;
	}
	
	/**
	 * Parcours le tableau de Case et met a jour "beastPas" qui correspond a la duree depuis laquel Beast a ete sur la Case
	 */
	public void setBeastWalk()
	{
		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab[i].length; j++)
				this.tab[i][j].modifBeastWalk(this.beast.isPosEnt(i, j));
	}
	
	
	/**
	 * Renvoie un boolean indiquant si le chasseur a gagn� ou non
	 * @return boolean
	 */
	/*private boolean beastVictory() {
		int i = 0;
		int j = 0;
		boolean victory = true;
		while(victory && i< this.tab.length) {
			while(victory && j<this.tab[i].length) {
				if(!this.tab[i][j].isObstacle() && this.tab[i][j].getBeastWalk() == -1) {
					victory = false;
				}
				j++;
			}
			j=0;
			i++;
		}
		return victory;
	}*/
	
	/**
	 * Renvoie un boolean indiquant si le chasseur a gagn� ou non
	 * @return
	 */
	/*private boolean hunterVictory() {
		return this.hunter.isPosEnt(this.beast.getPos().getPosX(), this.beast.getPos().getPosY()) || this.beast.getMvtEmptyCase(tab).isEmpty();
	}*/
	
	/**
	 * Retourne les La Map sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entity
	 */
	public String toString() {
		String affichage="";
		for (int x = 0; x < this.tab.length; x++) {
			affichage+="\n|";
			for (int y = 0; y < this.tab[x].length; y++) {
				if(this.beast.isPosEnt(y, x)) affichage+=" "+this.beast.toString()+" |";
				else if(this.hunter.isPosEnt(y, x)) affichage+=" "+this.hunter.toString()+" |";
				else affichage+=" "+this.tab[x][y].toString()+" |";
			}
		}
		return affichage;
	}

	@Override
	public String gameBeastToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gameHunterToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBonusActif(IBonus bonus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IBonus> getBonusActif() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePiege() {
		// TODO Auto-generated method stub
		
	}
}
