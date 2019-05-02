package render.text;

import java.util.ArrayList;

import config.Config;
import map.Case;
import map.CaseType;
import map.Mouvment;

/**
 * Cette classe definit les caracteristique principale de la classe jouable Hunter
 * @author Quentin Prognon
 *
 */
public class Hunter extends Entity {
	private int trapDispo;
	private int wardDispo;
	private int blinded=0;
	/**
	 * instancie Hunter a la Position posX, posY
	 * @param posX
	 * @param posY
	 */
	public Hunter(int posX, int posY, Config config) {
		super(posX, posY);
		this.wardDispo=0;
		this.trapDispo=0;
	}

	/**
	 * renvoie le nombre de piege disponible
	 * @return int
	 */
	public int getTrapDispo() {
		return this.trapDispo;
	}
	
	/**
	 * renvoie le nombre de balise disponible
	 * @return int
	 */
	public int getWardDispo() {
		return this.wardDispo;
	}
	
	/**
	 * renvoie si le hunter est aveugl� par le camouflage de la bete
	 * @return boolean
	 */
	public boolean isBlinded() {
		if(blinded>0) {
			blinded--;
			return true;
		}
		return false;
	}
	
	/**
	 * set le nombre de tour o� le chasseur est aveugl� par le camouflage de la bete
	 */
	public void setBlinded() {
		blinded=2;
	}
	
	/**
	 * renvoie si il est possible de poser un piege et en enleve un si c'est possible
	 * @return boolean
	 */
	public boolean canSetTrap() {
		if(this.trapDispo > 0) {
			this.trapDispo--;
			return true;
		}
		return false;
	}
	
	
	/**
	 * renvoie si il est possible de poser une balise et en enleve une si c'est possible
	 * @return boolean
	 */
	public boolean canSetWard() {
		if(this.wardDispo > 0) {
			this.wardDispo--;
			return true;
		}
		return false;
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
		boolean valide=super.verifDeplacementEntity(tab, mvt);
		int[] posModif=super.getPos().getModifPosTempo(mvt.getMvt());
		
		if(valide) {
			valide=valide && tab[posModif[0]][posModif[1]].getCaseType()==CaseType.SOL;
		}
		return valide;
	}
	
	/**
	 * retourne la liste des differents Mouvment possible pour Hunter.
	 * @param tab
	 * @return ArrayList<Mouvment>
	 */
	public ArrayList<Mouvment> getMvtEmptyCase(Case[][] tab) {
		ArrayList<Mouvment> mouvTab = new ArrayList<>();
		for(Mouvment m : Mouvment.values())
			if(super.verifDeplacementEntity(tab, m))
				mouvTab.add(m);
		return mouvTab;
	}
	
	/**
	 * Verfie si le Hunter a encore la possibilite de jouer ou non.
	 * @param tab
	 * @param hunter
	 * @return boolean
	 */
	public boolean isLock(Case[][] tab, Entity beast) {
		ArrayList<Mouvment> possible = this.getMvtEmptyCase(tab);
		for(Mouvment m : possible)
			if(this.verifDeplacementSpe(tab, m, beast))
				return false;
		return true;
	}
}