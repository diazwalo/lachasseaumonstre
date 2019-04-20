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
	
	/**
	 * Instancie Beast a la Position posX, posY
	 * @param posX
	 * @param posY
	 */
	public Beast(int posX, int posY) {
		super(posX, posY);
	}

	
	/**
	 * retourne Beast sous la forme textuelle
	 */
	public String toString() {
		return"B";
	}

	/**
	 * verifie si le deplacement souhaite est realisable pour Beast
	 * @return boolean
	 */
	@Override
	public boolean verifDeplacementSpe(Case[][] tab, Mouvment mvt, Entity hunter) {
		// TODO Auto-generated method stub
		boolean valide=super.verifDeplacement(tab, mvt);
		int[] posModif=super.getPos().getModifPosTempo(mvt.getMvt());
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType().equals(CaseType.SOL);
			valide=valide && !hunter.isPosEnt(posModif[0], posModif[1]);
			valide=valide && tab[posModif[0]][posModif[1]].getBeastPas()==-1;
		}
		return valide;
	}
	
	
	/**
	 * retourne la liste des differents Mouvment possible pour Beast.
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
	
	
	/**
	 * Verfie si la Beast a encore la possibilite de jouer ou non.
	 * @param tab
	 * @param hunter
	 * @return boolean
	 */
	public boolean isLock(Case[][] tab, Entity hunter) {
		ArrayList<Mouvment> possible = this.mvtPossible(tab);
		for(Mouvment m : possible)
			if(this.verifDeplacementSpe(tab, m, hunter))
				return false;
		return true;
	}
}