package render.text;

import java.util.ArrayList;

import map.Case;
import map.CaseType;
import map.Mouvment;

/**
 * Cette classe definit les caract�ristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast extends Entity{
	public Beast(int posX, int posY) {
		super(posX, posY);
	}

	
	/**
	 * Renvoie un B
	 */
	public String toString() {
		return"B";
	}


	@Override
	public boolean verifDeplacementSpe(Case[][] tab, Mouvment mvt, Entity hunter) {
		// TODO Auto-generated method stub
		boolean valide=super.verifDeplacement(tab, mvt);
		int[] posModif=super.getPos().getModifPosition(mvt.getMvt());
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType()==CaseType.SOL;
			valide=valide && !hunter.isPosEnt(posModif[0], posModif[1]);
			valide=valide && tab[posModif[0]][posModif[1]].getBeastPas()==-1;
		}
		return valide;
	}
	
	public ArrayList<Mouvment> PossibleMouv(Case[][] tab) {
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values()){
			if(super.verifDeplacement(tab, m)) {
				mouvTab.add(m);
			}
		}
		return mouvTab;
	}
	
	
	public boolean isLock(Case[][] tab, Entity hunter) {
		ArrayList<Mouvment> possible = this.PossibleMouv(tab);
		for(Mouvment m : possible) {
			if(this.verifDeplacementSpe(tab, m, hunter)) {
				return false;
			}
		}
		return true;
	}
}