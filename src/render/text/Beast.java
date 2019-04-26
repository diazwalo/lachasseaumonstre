package render.text;


import java.util.ArrayList;

import map.Case;
import map.CaseType;
import map.Mouvment;

/**
 * Cette classe definit les caracteristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast extends Entity{
	private boolean trapped;
	
	/**
	 * Instancie Beast a la Position posX, posY
	 * @param posX
	 * @param posY
	 */
	public Beast(int posX, int posY) {
		super(posX, posY);
		trapped = false;
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
	 * retourne la liste des differents Mouvment possible pour Beast sans se soucier de si il y a le chasseur ou encore si elle à deja marche vers la case visee.
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
	 * retourne Beast sous la forme textuelle
	 */
	public String toString() {
		return"B";
	}

}
