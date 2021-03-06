package render.text;


import java.util.ArrayList;
import java.util.List;

import config.Config;
import map.Case;
import map.CaseType;
import map.Mouvment;
import map.Position;
import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;

/**
 * Cette classe definit les caracteristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast extends Entity{
	private List<IBonus> camouflages = new ArrayList<>();
	private List<IBonus> baits = new ArrayList<>();
	private boolean trapped;
	private boolean revealedByWard;
	private int tp;

	/**
	 * Instancie Beast a la Position posX, posY
	 * @param posX la position souhaiter en X
	 * @param posY la position souhaiter en Y
	 * @param config la config de la partie
	 */
	public Beast(int posX, int posY, Config config) {
		super(posX, posY);
		this.trapped = false;
		this.revealedByWard = false;
		this.tp=config.getNbTeleportation();
	}
	
	public List<IBonus> getCamouflages() {
		return this.camouflages;
	}
	public List<IBonus> getbaits() {
		return this.baits;
	}
	
	/**
	 * Retourne le premier bonus disponible dans l'une des deux listes de bonus
	 * @return IBonus
	 */
	public IBonus takeFirstBonus() {
		IBonus first = null;
		for (IBonus bai : baits) {
			first=bai;
		}
		for (IBonus cam : camouflages) {
			first=cam;
		}
		return first;
	}
	
	/**
	 * Retourne vrai si la Bete est vu grace a la Ward
	 * @return boolean
	 */
	public boolean getRevealedByWard() {
		return this.revealedByWard;
	}
	 
	/**
	 * Met a la valeur b passee en parametre, revealedByWard
	 * @param b boolean determinant si la bete est reveler ou non 
	 */
	public void setRevealedByWard(boolean b) {
		this.revealedByWard=b;
	}
	
	/**
	 * Renvoie l'incentaire de la Bete sous la forme d'une chaine de char
	 * @return String
	 */
	public String toStringInventory() {
		String cam="", bai="";
		
		for (IBonus camouflage : camouflages) {
			if(! camouflage.equals(null)) {
				cam="Camouflage";
			}
		}
		for (IBonus bait : baits) {
			if(! bait.equals(null)) {
				if(cam.length()!= 0) {
					bai=", Leure";
				}else {
					bai="Leure";
				}
			}
		}
		if(cam.length()+bai.length() != 0) {
			return "Votre Inventaire: "+cam+bai;
		}
		return "";
	}
	
	/**
	 * reinitialise l'inventaire (avec possiblement les 2 bonus)
	 */
	public void putItAllInInventory() {
		List<IBonus> addition = new ArrayList<>();
		for (IBonus t : this.baits) {
			addition.add(t);
		}
		for (IBonus w : this.camouflages) {
			addition.add(w);
		}
		super.inventory = addition;
	}

	public void addToCamouflages(Camouflage cam) {
		this.camouflages.add(cam);
	}
	
	public void addToBaits(Bait bait) {
		this.baits.add(bait);
	}
	
	public IBonus takeCamouflage() {
		return this.camouflages.remove(0);
	}
	
	public IBonus takeBait() {
		return this.baits.remove(0);
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

	/**
	 * retourn le nombre de tp restant
	 * @return le nombre de teleportation
	 */
	public int getTp() {
		return this.tp;
	}
	
	/**
	 * passe la valeur de tp a celle passee en parametre
	 * @param tp le nombre de tp voulu
	 */
	public void setTp(int tp) {
		this.tp=tp;
	}
	
	/**
	 * renvoie si il est possible de poser un leurre et en enleve une si c'est possible
	 * @return boolean
	 */
	public boolean canSetBait() {
		return ! this.baits.isEmpty();
	}
	
	/**
	 * renvoie si il est possible de poser un camouflage et en enleve un si c'est possible
	 * @return boolean
	 */
	public boolean canSetCamouflage() {
		return ! this.camouflages.isEmpty();
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
			valide=valide && tab[posModif[0]][posModif[1]].getBeastWalk()==-1;
		}
		return valide;
	}
	
	
	/**
	 * retourne la liste des differents Mouvment possible pour Beast sans se soucier de si il y a le chasseur.
	 * @param tab le tableau de case de la map de la partie jouee
	 * @return List
	 */
	public List<Mouvment> getMvtToEmptyCase(Case[][] tab) {
		List<Mouvment> mouvTab = new ArrayList<>();
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
	 * retourne la liste des differents Mouvment possible pour Beast sans se soucier de si il y a le chasseur ou encore si elle a deja marche vers la case visee.
	 * @param tab la tableau de case de la map de la partie
	 * @return List
	 */
	public List<Mouvment> getMvtToNonObstacleCase(Case[][] tab) {
		List<Mouvment> mouvTab = this.getMvtToEmptyCase(tab);
		for(Mouvment m : Mouvment.values()) {
			int[] modifPosTempo=this.getPos().getModifPosTempo(m.getMvt());
			
			if(super.verifDeplacementOutOfBonds(tab, modifPosTempo) && super.verifDeplacementColisionObstacle(tab, modifPosTempo)) {
				if(! mouvTab.contains(m)) {
					mouvTab.add(m);
				}
			}
		}
		
		return mouvTab;
	}
	
	/**
	 * Verfie si la Beast a encore la possibilite de jouer ou non.
	 * @param tab le table de case de la map de la partie
	 * @param hunter le chasseur de la partie
	 * @return boolean
	 */
	public boolean isLock(Case[][] tab, Entity hunter) {
		List<Mouvment> possible = this.getMvtToEmptyCase(tab);
		for(Mouvment m : possible) {
			if(this.verifDeplacementSpe(tab, m, hunter)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Libere la bete du piege
	 */
	public void setUntrapped() {
		this.trapped = false;
	}
	
	/**
	 * Retourne une ArrayList contenant les position sur lesquel la Bete peut se teleporter
	 * @param tab le tableau de case de la map de la partie
	 * @param hunter le chasseur de la partie
	 * @return ArrayList
	 */
	public List<Position> getCaseTp(Case[][] tab, Hunter hunter) {
		List<Position> ALTab = new ArrayList<>();
		
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
	public boolean isTpAvailable() {
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
