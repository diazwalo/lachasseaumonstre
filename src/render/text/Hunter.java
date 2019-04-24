package render.text;

import java.util.ArrayList;

import map.Case;
import map.CaseType;
import map.Mouvment;

/**
 * Cette classe definit les caracteristique principale de la classe jouable Hunter
 * @author Quentin Prognon
 *
 */
public class Hunter extends Entity {
	
	/**
	 * instancie Hunter a la Position posX, posY
	 * @param posX
	 * @param posY
	 */
	public Hunter(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * retourne Hunter sous la forme textuelle
	 */
	public String toString() {
		return "H";
	}

	/**
	 * verifie si le deplacement souhaite est realisable pour Hunter
	 * @return boolean
	 */
	@Override
	public boolean verifDeplacementSpe(Case[][] tab, Mouvment mvt, Entity beast) {
		// TODO Auto-generated method stub
		boolean valide=super.verifDeplacement(tab, mvt);
		int[] posModif=super.getPos().getModifPosTempo(mvt.getMvt());
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType()==CaseType.SOL;
			//valide=valide && !beast.isPosEnt(posModif[0], posModif[1]);
		}
		return valide;
	}
	
	public ArrayList<Mouvment> getMvtPossiblePlusEntity(Case[][] tab) {
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values())
			if(super.verifDeplacement(tab, m))
				mouvTab.add(m);
		return mouvTab;
	}
	
	/**
	 * retourne la liste des differents Mouvment possible pour Hunter.
	 * @param tab
	 * @return ArrayList<Mouvment>
	 */
	public ArrayList<Mouvment> mvtPossible(Case[][] tab) {
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values())
			if(super.verifDeplacement(tab, m))
				mouvTab.add(m);
		return mouvTab;
	}
	
	public ArrayList<Mouvment> getMvtPossiblePlusOther(Case[][] tab) { 
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values()) {
			if(super.verifDeplacementOutOfBonds(tab, m.getMvt()) && super.verifDeplacementColisionObstacle(tab, m.getMvt())) {
				mouvTab.add(m);
			}
		}
		return mouvTab;
	}
	
	/**
	 * Verfie si le Hunter a encore la possibilite de jouer ou non.
	 * @param tab
	 * @param hunter
	 * @return boolean
	 */
	public boolean isLock(Case[][] tab, Entity beast) {
		ArrayList<Mouvment> possible = this.mvtPossible(tab);
		for(Mouvment m : possible)
			if(this.verifDeplacementSpe(tab, m, beast))
				return false;
		return true;
	}
}