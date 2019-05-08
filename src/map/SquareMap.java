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

public class SquareMap implements IMap {
	private Case[][] tab;
	private Hunter hunter;
	private Beast beast;
	private Config config;
	private boolean beastWin;
	private boolean hunterWin;
	private ArrayList<IBonus> bonusActif=new ArrayList<IBonus>();
	
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
		this.generationBonus();
		
		this.config = config;
		this.beastWin = false;
		this.hunterWin = false;
	}

	/**
	 * Remplie le tableau en mettant dans les cases soit des Obstacles soit des Sol.
	 */
	public void generationMap() {
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
	public void generationBonus() {
		Position posTrap=this.createPositionTempoFirstBonus(0, new boolean[] { true,  false }, true);
		this.createPositionTempoSecondBonus(0, new boolean[] { false,  true }, posTrap, true);
		Position posBait=this.createPositionTempoFirstBonus((this.tab.length)/2, new boolean[] { true,  false }, false);
		this.createPositionTempoSecondBonus((this.tab.length)/2, new boolean[] { false,  true }, posBait, false);
	}
	
	public Position createPositionTempoFirstBonus(int addToLength, boolean[] tabBonus, boolean bonusHunterOrBeast) {
		Random r=new Random();
		Position posBonusFinal=new Position(-1 , -1);
		
		while(posBonusFinal.isPos(-1, -1)) {
			int posBonusX=r.nextInt((this.tab.length)/2+addToLength);
			int posBonusY=r.nextInt((this.tab[posBonusX].length)/2)+addToLength;
			Position posBonusTempo=new Position(posBonusX, posBonusY);
			
			if(! this.tab[posBonusX][posBonusY].isObstacle() && ! this.beast.getPos().equals(posBonusTempo) && ! this.hunter.getPos().equals(posBonusTempo)) {
				posBonusFinal=posBonusTempo;
			}
		}
		
		if(bonusHunterOrBeast) tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusHunter(tabBonus);
		else tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusBeast(tabBonus);

		return posBonusFinal;
	}
	
	public void createPositionTempoSecondBonus(int addToLength, boolean[] tabBonus, Position posFirstBonus, boolean bonusHunterOrBeast) {
		Random r=new Random();
		Position posBonusFinal=new Position(-1 , -1);

		while(posBonusFinal.isPos(-1, -1)) {
			int posBonusX=r.nextInt((this.tab.length)/2+addToLength);
			int posBonusY=r.nextInt((this.tab[posBonusX].length)/2)+addToLength;
			Position posBonusTempo=new Position(posBonusX, posBonusY);
				
			if(! this.tab[posBonusX][posBonusY].isObstacle() && ! this.beast.getPos().equals(posBonusTempo) && ! this.hunter.getPos().equals(posBonusTempo) && ! posBonusFinal.equals(posFirstBonus)) {
				posBonusFinal=posBonusTempo;
			}
		}
		
		if(bonusHunterOrBeast) tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusHunter(tabBonus);
		else tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusBeast(tabBonus);
	}

	/**
	 * Retourne le tableau a deux dimentions de Case
	 * @return Case[][]
	 */
	public Case[][] getTab() {
		return this.tab;
	}

	/**
	 * Retourne le Hunter
	 * @return Hunter
	 */
	public Hunter getHunter() {
		return this.hunter;
	}

	/**
	 * Retourne la Beast
	 * @return Beast
	 */
	public Beast getBeast() {
		return this.beast;
	}
	
	/**
	 * Retourne si la bete a gagnee
	 * @return boolean
	 */
	public boolean isBeastWin() {
		return beastWin;
	}
	
	/**
	 * Retourne si le chasseur a gagn�
	 * @return boolean
	 */
	public boolean isHunterWin() {
		return hunterWin; 
	}
	
	public void setHunterWin(boolean hunterWin) {
		this.hunterWin=hunterWin;
	}
	
	public void setBeastWin(boolean beastWin) {
		this.beastWin=beastWin;
	}
	
	public void setBonusActif(IBonus bonus) {
		this.bonusActif.add(bonus);
	}
	
	public ArrayList<IBonus> getBonusActif() {
		return this.bonusActif;
	}
	
	public void removePiege() {
		for (IBonus ib : this.bonusActif) {
			if(ib.getPos().equals(null)) {
				this.bonusActif.remove(ib);
			}
		}
	}
 
	public void HunterIsNearBait() {
		for(IBonus ib : this.bonusActif) {
			
			if(ib.getClass().isInstance(new Bait())) {
				if(this.getHunter().getPos().getPosX()-ib.getPos().getPosX()<2 && this.getHunter().getPos().getPosX()-ib.getPos().getPosX()>-2 && 
						this.getHunter().getPos().getPosY()-ib.getPos().getPosY()<2 && this.getHunter().getPos().getPosY()-ib.getPos().getPosY()>-2 ) {
					ib.setDiscovered();
				}
			}
		}
	}
	

	/**
	 * Retourne la Config
	 * @return Config
	 */
	public Config getConfig() {
		return this.config;
	}

	/**
	 * Teste si le deplacement souhaite pour Beast ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Hunter 
	 * @param mvt
	 * @return boolean
	 */
	public boolean mvtValideBeast(Mouvment mvt) {
		return this.beast.verifDeplacementSpe(tab, mvt, hunter);
	}

	/**
	 * Teste si le deplacement souhaite pour Hunter ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Beast
	 * @param mvt
	 * @return boolean
	 */
	public boolean mvtValideHunter(Mouvment mvt) {
		return this.hunter.verifDeplacementSpe(tab, mvt, beast);
	}

	/**
	 * Deplace la Beast avec le Mouvment passe en parametre si ce dernier est possible
	 * @return boolean
	 */
	public boolean moveBeast(Mouvment mvt) {
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
	public boolean moveHunter(Mouvment mvt) {
		if(this.mvtValideHunter(mvt)) {
			this.hunter.setPos(mvt);
			return true;
		}return false;
	}
	
	/**
	 * Parcours le tableau de Case et met a jour "beastPas" qui correspond a la duree depuis laquel Beast a ete sur la Case
	 */
	public void setBeastWalk() {
		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab[i].length; j++)
				this.tab[i][j].modifBeastWalk(this.beast.isPosEnt(i, j));
	}

	public String gameBeastToString() {
		String affichage="";
		for (int x = 0; x < this.tab.length; x++) {
			affichage+=" \n|";
			for (int y = 0; y < this.tab[x].length; y++) {
				affichage+=" "+this.tab[y][x].gameBeastShowView(this, new Position(y, x))+" |";
			}
		}
		return affichage;
	}
	
	public String gameHunterToString() {
		String affichage="";
		for (int x = 0; x < this.tab.length; x++) {
			affichage+=" \n|";
			for (int y = 0; y < this.tab[x].length; y++) {
				if(this.hunter.isBlinded()) {
					this.tab[y][x].setBlinded();
					affichage+=" "+this.tab[y][x].gameHunterShowView(this, new Position(y, x))+" |";
				}else {
				affichage+=" "+this.tab[y][x].gameHunterShowView(this, new Position(y, x))+" |";
				}
			}
		}
		return affichage;
	}
	
	/**
	 * Retourne les La Map sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entity
	 */
	public String toString() {
		String affichage="";
		for (int x = 0; x < this.tab.length; x++) {
			affichage+=" \n|";
			for (int y = 0; y < this.tab[x].length; y++) {
				if(this.beast.isPosEnt(y, x)) affichage+=" "+this.beast.toString()+" |";
				else if(this.hunter.isPosEnt(y, x)) affichage+=" "+this.hunter.toString()+" |";
				else affichage+=" "+this.tab[y][x].toString()+" |";
			}
		}
		return affichage;
	}
}