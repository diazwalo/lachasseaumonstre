package render.text;


import java.util.ArrayList;

import config.Config;
import map.Case;
import map.CaseType;
import map.Mouvment;
import map.Position;

/**
 * Cette classe definit les caracteristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast extends Entity{
	private boolean trapped;
	private int tp;
	private int camDispo;
	private int baitDispo;

	/**
	 * Instancie Beast a la Position posX, posY
	 * @param posX
	 * @param posY
	 */
	public Beast(int posX, int posY, Config config) {
		super(posX, posY);
		this.trapped = false;
		this.tp=config.getNbTeleportation();
		this.camDispo=2;
		this.baitDispo=2;
	}
	
	/**
	 * Renvoie le nombre de camouflage disponible
	 * @return int
	 */
	public int getCamDispo() {
		return this.camDispo;
	}

	
	/**
	 * Renvoie le nombre de leurre disponible
	 * @return int 
	 */
	public int getBaitDispo() {
		return this.baitDispo;
	} 
	/**
	 * retourne vrai si la bete est prise au piege
	 * @return boolean
	 */
	public boolean getTrapped() {
		return this.trapped;
	}

	/**
	 * la bete est prise au piege
	 */
	public void setTrapped() {
		this.trapped=true;
	}

	/*
	 * retourn le nombre de tp restant
	 */
	public int getTp() {
		return this.tp;
	}
	
	/*
	 * passe la valeur de tp a celle passee en parametre
	 */
	public void setTp(int tp) {
		this.tp=tp;
	}
	
	/**
	 * renvoie si il est possible de poser un leurre et en enleve une si c'est possible
	 * @return boolean
	 */
	public boolean canSetBait() {
		if(this.baitDispo > 0) {
			this.baitDispo--;
			return true;
		}
		return false;
	}
	
	/**
	 * renvoie si il est possible de poser un camouflage et en enleve un si c'est possible
	 * @return boolean
	 */
	public boolean canSetCamouflage() {
		if(this.camDispo > 0) {
			this.camDispo--;
			return true;
		}
		return false;
	}
	
	/**
	 * verifie si le deplacement souhaite est realisable pour la bete
	 * @return boolean
	 */
	@Override
	public boolean verifDeplacementSpe(Case[][] tab, Mouvment mvt, Entity hunter) {
		// TODO Auto-generated method stub
		boolean valide=super.verifDeplacementEntity(tab, mvt);
		int[] posModif=super.getPos().getModifPosTempo(mvt.getMvt());
		
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType().equals(CaseType.SOL);
			valide=valide && !hunter.isPosEnt(posModif[0], posModif[1]);
			valide=valide && tab[posModif[0]][posModif[1]].getBeastWalk()==-1;
		}
		return valide;
	}
	
	
	/**
	 * retourne la liste des differents Mouvment possible pour Beast sans se soucier de si il y a le chasseur ou encore si elle a deja marche vers la case visee.
	 * @param tab
	 * @return ArrayList<Mouvment>
	 */
	public ArrayList<Mouvment> getMvtEmptyCase(Case[][] tab) {
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values()) {
			int[] modifPosTempo=this.getPos().getModifPosTempo(m.getMvt());
			
			if(super.verifDeplacementOutOfBonds(tab, modifPosTempo) && super.verifDeplacementColisionObstacle(tab, modifPosTempo)) {
				
				if(tab[modifPosTempo[0]][modifPosTempo[1]].getBeastWalk() ==-1) {
					mouvTab.add(m);
				}
			}
		}
		
		return mouvTab;
	}
	
	/**
	 * Verfie si la Beast a encore la possibilite de jouer ou non.
	 * @param tab
	 * @param hunter
	 * @return boolean
	 */
	public boolean isLock(Case[][] tab, Entity hunter) {
		ArrayList<Mouvment> possible = this.getMvtEmptyCase(tab);
		for(Mouvment m : possible)
			if(this.verifDeplacementSpe(tab, m, hunter))
				return false;
		return true;
	}
	
	/**
	 * Libere la bete du piege
	 */
	public void untrapped() {
		this.trapped = false;
	}
	
	/**
	 * Retourne une ArrayList contenant les position sur lesquel la Bete peut se teleporter
	 * @param tab
	 * @param hunter
	 * @return ArrayList<Position>
	 */
	public ArrayList<Position> getCaseTp(Case[][] tab, Hunter hunter) {
		ArrayList<Position> ALTab = new ArrayList<>();
		
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				
				Case caseCour=tab[i][j];
				if(! caseCour.isObstacle() && (caseCour.getBeastWalk() == -1) && ! hunter.isPosEnt(i, j)) {
					ALTab.add(new Position(i, j));
				}
				
			}
		}
		
		return ALTab;
	}
	
	/*
	 * verifie qu'il reste au moins une tp a la bete puis retourne vrai au cas echeant
	 * @return boolean
	 */
	public boolean teleportation() {
		if(this.tp > 0) {
			this.tp--;
			return true;
		}else {
			return false;
		}
	}

	/**
	 * retourne Beast sous la forme textuelle
	 */
	public String toString() {
		return "B";
	}

}
