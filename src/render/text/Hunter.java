package render.text;

import map.Case;
import map.CaseType;
import map.Mouvment;

/**
 * Cette classe definit les caract�ristique principale de la classe jouable Hunter
 * @author Quentin Prognon
 *
 */
public class Hunter extends Entity {
	public Hunter(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * Renvoie un H
	 */
	public String toString() {
		return "H";
	}

	@Override
	public boolean verifDeplacementSpe(Case[][] tab, Mouvment mvt, Entity beast) {
		// TODO Auto-generated method stub
		boolean valide=super.verifDeplacement(tab, mvt);
		int[] posModif=super.getPos().getModifPosition(mvt.getMvt());
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType()==CaseType.SOL;
			valide=valide && !beast.isPosEnt(posModif[0], posModif[1]);
		}
		return valide;
	}
}