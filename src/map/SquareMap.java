package map;

import java.util.Random;

import config.Config;
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


	/**
	 * Construit un tableau de longueur len et de largeur wid et set Beast, Hunter et la Config
	 * @param len
	 * @param wid
	 */
	public SquareMap(Config config) {
		this.hunter = new Hunter(0, 0, config);
		int widthTab=config.getWidth()-1;
		int heightTab=config.getHeight()-1;
		
		if(widthTab < 5 || heightTab < 5) {
			widthTab=5;
			heightTab=5;
		}
		
		this.beast = new Beast(widthTab-1, heightTab-1, config);
		this.tab = new Case[widthTab][heightTab];
		//ajouter les pieges
		//this.generationPiege();
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
		/*
		 * NORMAL SI CA MARCHE PAS, GENERATION MAP N'A PAS ENCORE ETE FAIT !
		 */
		IBonus[] bonus=new IBonus[]{new Trap(), new Ward(), new Bait(), new Camouflage()};
		Random r=new Random();
		
		Position posTrap=null;
		Position posWard=null;
		Position posBait=null;
		Position posCamouflage=null;
	
		while(posTrap==null) {
			int posTrapX=r.nextInt(this.tab.length)/2;
			int posTrapY=r.nextInt(this.tab[posTrapX].length)/2;
			Position posTrapTempo=new Position(posTrapX, posTrapY);
			/*
			 * LAAAAA ca peut pas marché le tableau n'est pas encore fait
			 */
			if(! this.tab[posTrapX][posTrapY].isObstacle() && ! this.beast.getPos().equals(posTrapTempo) && ! this.hunter.getPos().equals(posTrapTempo)) {
				posTrap=posTrapTempo;
			}
		}
		tab[posTrap.getPosX()][posTrap.getPosY()].setBuff(new boolean[] { true, false, false, false});

		while(posWard.equals(null)) {
			int posWardX=r.nextInt(this.tab.length)/2;
			int posWardY=r.nextInt(this.tab[posWardX].length)/2;
			Position posWardTempo=new Position(posWardX, posWardY);
			
			if(! this.tab[posWardX][posWardY].isObstacle() && ! this.beast.getPos().equals(posWardTempo) && ! ! this.hunter.getPos().equals(posWardTempo) && ! posWard.equals(posTrap)) {
				posWard=posWardTempo;
			}
		}
		tab[posWard.getPosX()][posWard.getPosY()].setBuff(new boolean[] { false, false, true, false});

		while(posBait.equals(null)) {
			int posBaitX=r.nextInt((this.tab.length)/2)+((this.tab.length)/2);
			int posBaitY=r.nextInt(this.tab[posBaitX].length)/2;
			Position posBaitTempo=new Position(posBaitX, posBaitY);
			
			if(! this.tab[posBaitX][posBaitY].isObstacle() && ! this.beast.getPos().equals(posBaitTempo) && ! ! this.hunter.getPos().equals(posBaitTempo)) {
				posBait=posBaitTempo;
			}
		}
		tab[posWard.getPosX()][posWard.getPosY()].setBuff(new boolean[] { false, false, false, true});

		while(posCamouflage.equals(null)) {
			int posCamouflageX=r.nextInt((this.tab.length)/2)+((this.tab.length)/2);
			int posCamouflageY=r.nextInt(this.tab[posCamouflageX].length)/2;
			Position posCamouflageTempo=new Position(posCamouflageX, posCamouflageY);
			
			if(! this.tab[posCamouflageX][posCamouflageY].isObstacle() && ! this.beast.getPos().equals(posCamouflageTempo) && ! ! this.hunter.getPos().equals(posCamouflageTempo) && ! posCamouflage.equals(posBait)) {
				posCamouflage=posCamouflageTempo;
			}
		}
		tab[posWard.getPosX()][posWard.getPosY()].setBuff(new boolean[] { false, true, false, false});
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
	 * Retourne si le chasseur a gagnï¿½
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