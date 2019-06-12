package map;

import java.util.List;

import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;
import render.text.Beast;
import render.text.Hunter;

/**
 * Definit le type de case (obstacle ou sol), un compteur pour
 * du nombre tours depuis le dernier passage de la bete, un boolean qui 
 * precise si le case est visible pour le chasseur et un tableaux qui defini
 * le buff ramassable sur cete case.
 * @author nath
 *
 */
public class Case {
	private CaseType caseType;
	private int beastWalk;
	private boolean hideToHunter;
	private boolean[] bonusBeast;
	private boolean[] bonusHunter;
	private final int TRACE = 5;

	
	/**
	 * Creer une class Case avec en parametre CaseType et posBeast respectivment le type de la case
	 * et si la bete est passe sur la case, instansie par defaut les buff a false.
	 * @param caseType le type de case voulue
	 * @param posBeast la position de la bete dans la partie
	 */
	public Case(CaseType caseType, boolean posBeast) {
		this(caseType, posBeast, new boolean[] {false, false }, new boolean[] {false, false});
	}
	
	/**
	 * Creer une class Case avec en parametre CaseType et posBeast respectivment le type de la case
	 * et si la bete est passe sur la case, instansie par defaut les bonus a false.
	 * @param caseType le type de case souhaiter
	 * @param posBeast la position de la bete
	 * @param bonusBeast le bonus disponible pour la bete
	 * @param bonusHunter le bonus disponible pour le chasseur
	 */
	public Case(CaseType caseType, boolean posBeast, boolean[] bonusBeast, boolean[] bonusHunter) {
		this.caseType=caseType;
		if(posBeast) this.beastWalk=0;
		else this.beastWalk=-1;
		this.hideToHunter=false;
		this.bonusBeast=bonusBeast;
		this.bonusHunter=bonusHunter;
	}
	
	/**
	 * renvoie le type de la case
	 * @return caseType
	 */
	public CaseType getCaseType() {
		return this.caseType;
	}
	
	/**
	 * retourne le nombre tour depuis le dernier passage de la bete
	 * @return int
	 */
	public int getBeastWalk() {
		return this.beastWalk;
	}
	
	/**
	 * retourne si la case est visible pour le chasseur ou non
	 * @return boolean
	 */
	public boolean getHideToHunter() {
		return this.hideToHunter;
	}
	
	/**
	 * retourne le tableau de buff ramassables sur cette case pour le Hunter
	 * @return boolean[]
	 */
	public boolean[] getBonusHunter() {
		return this.bonusHunter;
	}
	
	/**
	 * retourne le tableau de buff actif ramassables sur cette case pour le HunteBeast
	 * @return boolean[]
	 */
	public boolean[] getBonusBeast() {
		return this.bonusBeast;
	}
		
	/**
	 * passe beastWalk a la valeur passee en parametre
	 * @param beastWalk le nombre de tour ou la bete et passer sur la case
	 */
	public void setBeastWalk(int beastWalk) {
		this.beastWalk=beastWalk;
	}


	/**
	 * passe setHideToHunter a vrai afin que Hunter ne le voit pas
	 */
	public void setHideToHunter() {
		this.hideToHunter=true;
	}
	
	/**
	 * modifie le tableau de bonus en le remplacant par celui en parametre
	 * @param bonusHunter un tableau contenant tous les bonus du chasseur
	 */
	public void setBonusHunter(boolean[] bonusHunter) {
		this.bonusHunter=bonusHunter;
	}
	
	/**
	 * modifie le tableau de bonus en le remplacant par celui en parametre
	 * @param bonusBeast un tableau contenant tous les bonus de la bete
	 */
	public void setBonusBeast(boolean[] bonusBeast) {
		this.bonusBeast=bonusBeast;
	}
	
	/**
	 * Ajoute a l'inventaire du Chasseur, le piee de la Case (s'il y en a un).
	 * @param hunter la chasseur de la partie
	 * @param posCase la position de la case sur laquel le chasseur est.
	 */
	public void PickUpBonusHunter(Hunter hunter, Position posCase) {
		if(hunter.getPos().isPos(posCase.getPosX(), posCase.getPosY())) {
			//bait puis cam
			for (int i = 0; i < this.bonusHunter.length; i++) {
				if(this.bonusHunter[i]) {
					if(i==0) {
						hunter.addToTraps(new Trap());
					}else if(i==1) {
						hunter.addToWards(new Ward());
					}
				}
			}
		}
	}
	
	/**
	 * Ajoute a l'inventaire de la Bete, le bonus de la Case (s'il y en a un).
	 * @param beast la bete de la partie
	 * @param posCase la case courante
	 */
	public void PickUpBonusBeast(Beast beast, Position posCase) {
		if(beast.getPos().isPos(posCase.getPosX(), posCase.getPosY())) {
			//bait puis cam
			for (int i = 0; i < this.bonusBeast.length; i++) {
				if(this.bonusHunter[i]) {
					if(i==0) {
						beast.addToBaits(new Bait());
					}else if(i==1) {
						beast.addToCamouflages(new Camouflage());
					}
				}
			}
		}
	}
	
	/**
	 * Passe beastWalk a 0 cette case correspond a la position de la bete ou incremente beastWalk si Beast est deja passe ici
	 * @param posBeast un boolean si la case courante est la case ou est situe la bete
	 */
	public void modifBeastWalk(boolean posBeast) {
		if(posBeast) this.setBeastWalk(0);
		else if(this.beastWalk!=-1) this.setBeastWalk(this.beastWalk+1);
	}

	/**
	 * Retourne un boolean qui indique si la case est un obstacle ou non
	 * @return boolean
	 */
	public boolean isObstacle() {
		return this.caseType == CaseType.OBSTACLE;
	}
	
	/**
	 * Montre la map tel que le joueur doit la voir lorsqu'il joue le chasseur
	 * @param map la map de la partie en cour
	 * @param posCase la position de la case courante
	 * @return String la vue du chasseur sous forme textuelle
	 */
	public String gameHunterShowView(AbstractMap map, Position posCase) {
		String res=caseType.toString();
		
		if(! this.getFogOnCaseToString(map, posCase).equals("")) {
			res= this.getFogOnCaseToString(map, posCase);
		}
		
		if(! this.getHunterBonusActifToString(map, posCase).equals("")) {
			res=this.getHunterBonusActifToString(map, posCase);
		}
		
		if(! this.getBaitOnCaseToString(map, posCase).equals("")) {
			res = this.getBaitOnCaseToString(map, posCase);
		}
		
		if(! this.getBonusOnCaseNonActifToString(this.bonusHunter).equals("")) {
			res = this.getBonusOnCaseNonActifToString(this.bonusHunter);
		}
		
		if(! this.getBeastWalkNearHunterToString(map, posCase).equals("")) {
			res = this.getBeastWalkNearHunterToString(map, posCase);
		}
		
		if(! this.getBeastNearHunterToString(map, posCase).equals("")) {
			res = this.getBeastNearHunterToString(map, posCase);
		}
		
		if(! this.getHunterOnCaseToString(map, posCase).equals("")) {
			res = this.getHunterOnCaseToString(map, posCase);
		}

		if(! this.getBeastIfRevealedToString(map, posCase).equals("")) {
			res = this.getBeastIfRevealedToString(map, posCase);
		}
		
		if(! this.getEntityOnSameCaseToString(map, posCase).equals("")) {
			res = this.getEntityOnSameCaseToString(map, posCase);
		}
		
		

		return res;
	}

	/**
	 * Montre la map tel que le joueur doit la voir lorsqu'il joue la Bete
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String la vue de la bete sous forme textuelle
	 */
	public String gameBeastShowView(AbstractMap map, Position posCase) {
		String res=caseType.toString();
		
		if(this.beastWalk>=1) res=".";
		
		if(! this.getBeastBonusActifToString(map, posCase).equals("")) {
			res=this.getBeastBonusActifToString(map, posCase);
		}
		
		if(! this.getBonusOnCaseNonActifToString(this.bonusBeast).equals("")) {
			res = this.getBonusOnCaseNonActifToString(this.bonusBeast);
		}
		
		if(! this.getBeastOnCaseBeastModeToString(map, posCase).equals("")) {
			res = this.getBeastOnCaseBeastModeToString(map, posCase);
		}
		
		if(! this.getHunterOnCaseToString(map, posCase).equals("")) {
			res = this.getHunterOnCaseToString(map, posCase);
		}
		
		return res;
	}
	
	/**
	 * retourn la Bete sous la forme d'une Chaine de Char si elle est revelee
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String  le bete sous forme textuelle
	 */
	public String getBeastIfRevealedToString(AbstractMap map, Position posCase) {
		String beast = "";
		if(getBeastIfRevealed(map, posCase) != null) {
			beast = getBeastIfRevealed(map, posCase).toString();
		}
		return beast;
	}
	
	/**
	 * Retourn la Bete si elle est revelee et null dans le cas echeant
	 * @param map la map de  la partie en cours
	 * @param posCase la position de la case courante
	 * @return Beast la bete de la partie
	 */
	public Beast getBeastIfRevealed(AbstractMap map, Position posCase) {
		if((map.getBeast().getTrapped() || map.getBeast().getRevealedByWard()) && map.getBeast().isPosEnt(posCase.getPosX(), posCase.getPosY())) {
			return map.getBeast();
		}
		return null;
	}
	
	/**
	 * Retourne le Chasseur sous la forme d'une Chaine de Char si ce dernier est sur la meme Caseque la Bete est null le cas echeant
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String la version textuelle de l'entité
	 */
	public String getEntityOnSameCaseToString(AbstractMap map, Position posCase) {
		String entity = "";
		if(this.getEntityOnSameCase(map, posCase) != null){
			entity = this.getEntityOnSameCase(map, posCase).toString();
		}
		return entity;
	}
	
	/**
	 * Retourne le Chasseur si ce dernier est sur la meme Case que la Bete et null le cas echenat
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return Hunter le chasseur de la partie
	 */
	public Hunter getEntityOnSameCase(AbstractMap map, Position posCase) {
		if(map.getBeast().getPos().equals(map.getHunter().getPos()) && map.getHunter().getPos().equals(posCase)) {
			return map.getHunter();
		}
		return null;
	}
	
	/**
	 * Retourne le nombre de Pas depuis lequel la Bete est passee sure cette Case sous la forme d'une Chaine de Charactere (entre 1 et 4)
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String le normbre de tour que la bete est passer la case
	 */
	public String getBeastWalkNearHunterToString(AbstractMap map, Position posCase) {
		String beastWalk = "";
		if(this.getBeastWalkNearHunter(map, posCase) != -1) {
			beastWalk= this.getBeastWalkNearHunter(map, posCase)+"";
		}
		return beastWalk;
	}
	
	/**
	 * Retourne le nombre de pas depuis lequel la Bete est passee sur cette Case (entre 1 et 4) ou -1 si ce dernier n'y est pas passe depuis 4 tours
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String le normbre de tour que la bete est passer la case
	 */
	public int getBeastWalkNearHunter(AbstractMap map, Position posCase) {
		int beastWalk = -1;
		List<Position> posAdj=posCase.getAdjacentPosition(map);
	
		if(this.beastWalk >= 0 && this.beastWalk < this.TRACE && !map.getHunter().isBlinded() && !map.getHunter().isPosEnt(posCase.getPosX(), posCase.getPosY())) {
			for (Position position : posAdj) {
				if(map.getHunter().isPosEnt(position.getPosX(), position.getPosY())) {
						beastWalk = this.beastWalk;
				}
			}
		}
		return beastWalk;
	}
	
	/**
	 * retourn la bete sous a forme d'une Chaine de Char si cette derniere est proche du Chasseur
	 * @param map  la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String la bete de la partie
	 */
	public String getBeastNearHunterToString(AbstractMap map, Position posCase) {
		String beast = "";
		if(this.getBeastNearHunter(map, posCase) != null) {
			beast = this.getBeastNearHunter(map, posCase).toString();
		}
		return beast;
	}
	
	/**
	 * Retourne la Bete si celle ci est sur une case a cote du Chasseur et null le cas echeant
	 * @param map  la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return Beast la bete de la partie
	 */
	public Beast getBeastNearHunter (AbstractMap map, Position posCase) {
		List<Position> posAdj=posCase.getAdjacentPosition(map);
		
		if(this.beastWalk >= 0 && this.beastWalk < this.TRACE && !map.getHunter().isBlinded() && !map.getHunter().isPosEnt(posCase.getPosX(), posCase.getPosY())) {
			for (Position position : posAdj) {
				if(map.getHunter().isPosEnt(position.getPosX(), position.getPosY())) {
					if(this.beastWalk==0) {
						return map.getBeast();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Retourne le compteur du Bait si ce dernier est Active et retourne la Chaine vide ce cas echeant
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String le compteur du Bait
	 */
	public String getBaitOnCaseToString(AbstractMap map, Position posCase) {
		String baitOnCase = "";
		if(this.getBaitOnCase(map, posCase) != null) {
			Bait baitTempo = (Bait) (this.getBaitOnCase(map, posCase));
			baitOnCase = baitTempo.getCount()+"";
		}
		return baitOnCase;
	}
	
	/**
	 * Retourne un Bait si ce dernier est sur la Case Courante et si il est active et null si il n'y en a pas
	 * @param map la map de la partie en cours
	 * @param posCase  la position de la case courante
	 * @return IBonus le bonus sur la case donne
	 */
	public IBonus getBaitOnCase(AbstractMap map, Position posCase) {
		IBonus baitOnCase = null;
		for (IBonus bonus : map.getBonusActif()) {
			if(bonus instanceof Bait && ((Bait) bonus).getVisible() && !map.getHunter().isBlinded() && posCase.equals(bonus.getPos())) {
				baitOnCase = bonus;
			}
		}
		return baitOnCase;
	}
	
	/**
	 * retourne le CHasseur sous la forme d'une Cahaine de Char si ce dernier est sur la Case courante
	 * @param map	la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String le hunter de la partie
	 */
	public String getHunterOnCaseToString(AbstractMap map, Position posCase) {
		String res = "";
		if(getHunterOnCase( map, posCase)) {
			res = map.getHunter().toString();
		}
		return res;
	}
	
	/**
	 * Retourne vrai si le Chasseur est sur la Case courante et faux le cas echeant
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return boolean determinant si le chasseur est sur la case indiquer
	 */
	public boolean getHunterOnCase(AbstractMap map, Position posCase) {
		if(map.getHunter().isPosEnt(posCase.getPosX(), posCase.getPosY())) {
			return true;
		}
		return false;
	}
	
	/**
	 * retourne la Bete sous la forme d'une Cahaine de Char si cette derniere est sur la Case courante
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String la bete de la partie
	 */
	public String getBeastOnCaseBeastModeToString(AbstractMap map, Position posCase) {
		String res = "";
		if(getBeastOnCaseBeastMode( map, posCase)) {
			res = map.getBeast().toString();
		}
		return res;
	}
	
	/**
	 * Retourne vrai si la Bete est sur la Case courante et faux le cas echeant
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return boolean determinant si le chasseur est sur la case indiquer
	 */
	public boolean getBeastOnCaseBeastMode(AbstractMap map, Position posCase) {
		if(map.getBeast().isPosEnt(posCase.getPosX(), posCase.getPosY())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retourne le Bonus a ramasser sous la forme d'une Chaine de Char si la Case en contient un
	 * @param bonus liste des bonus disponible
	 * @return String affichage du bonus poser sur la map
	 */
	public String getBonusOnCaseNonActifToString(boolean[] bonus) {
		String res = "";
		if(this.bonusOnCase(bonus)) {
			res = "?";
		}
		return res;
	}

	
	 /**
	 * retourne vrai si un bonus peut etre ramasse sur cette Case
	 * @param bonus les bonus  disponible
	 * @return boolean determinant si le bonus peut etre ramasser
	 */
	public boolean bonusOnCase(boolean[] bonus) {
		for (int i = 0; i < bonus.length; i++) {
			if(bonus[i]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retourne le Bonus Actif sur la Case sous la forme d'une Chaine de Characteresi il y en a un et la Chaine Vide sinon (Cela si c'est un Bonus pose par le Chasseur)
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String version textuelle du bonus
	 */
	public String getHunterBonusActifToString(AbstractMap map, Position posCase) {
		String res="";
		IBonus bonusActif = this.getHunterBonusActifOnCase(map, posCase);
		if(bonusActif != null) {
			res = bonusActif.toString();
		}
		return res;
	}

	/**
	 * Retourne le Bonus Actif sur la Case sous la forme d'une Chaine de Charactere si il y en a un et la Chaine Vide sinon (Cela si c'est un Bonus pose par la Bete)
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String un bonus sous forme textuelle
	 */
	public String getBeastBonusActifToString(AbstractMap map, Position posCase) {
		String res="";
		IBonus bonusActif = this.getBeastBonusActifOnCase(map, posCase);
		if(bonusActif != null) {
			res = bonusActif.toString();
		}
		return res;
	}
	
	/**
	 * Retourne le Bonus Actif sur la Case si il y en a un et null sinon (Cela si c'est un Bonus pose par le Chasseur)
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return IBonus le bonus de la case courante
	 */
	public IBonus getHunterBonusActifOnCase(AbstractMap map, Position posCase) {
		IBonus bonusActif = null;
		for (IBonus bonus : map.getBonusActif()) {
			if((bonus instanceof Trap || bonus instanceof Ward) && bonus.getPos() != null && bonus.getPos().equals(posCase)) {
				bonusActif = bonus;
			}
		}
		return bonusActif;
	}
	
	/**
	 * Retourne le Bonus Actif sur la Case si il y en a un et null sinon (Cela si c'est un Bonus pose par la Bete)
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return IBonus le bonus de la case courante
	 */
	public IBonus getBeastBonusActifOnCase(AbstractMap map, Position posCase) {
		IBonus bonusActif = null;
		for (IBonus bonus : map.getBonusActif()) {
			if((bonus instanceof Camouflage || bonus instanceof Bait) && bonus.getPos() != null && bonus.getPos().equals(posCase)) {
				bonusActif = bonus;
			}
		}
		return bonusActif;
	}
	
	/**
	 * Retourne vrai si il doit y avoir du brouillard sur la case
	 * @param map la map de la partie en cours
	 * @param posCase  la position de la case courante
	 * @return boolean determinant si il y'a du fog sur un case ou non
	 */
	public boolean getFogOnCase(AbstractMap map, Position posCase) {
		Position posHunter=map.getHunter().getPos();
		boolean fogOnCase = false;
		IBonus ibonus=null;
		
		if (!posCase.getAdjacentPosition(map).contains(posHunter)) {
			fogOnCase = true;
		}
		
		for(IBonus ib : map.getBonusActif()) {
			if(ib instanceof Ward) ibonus =ib;
		}
		
		if(ibonus!=null) {
			
			if(posCase.getAdjacentPosition(map).contains(ibonus.getPos())) {
				fogOnCase = false;
			}
			
		}
		return fogOnCase;
	}
	
	/**
	 * Retourne la chaine de charactere associee au brouillard si cette derniere doit etre placee sur la case ou la chaine vide sinon
	 * @param map la map de la partie en cours
	 * @param posCase la position de la case courante
	 * @return String le fog sous sa forme textuelle
	 */
	public String getFogOnCaseToString(AbstractMap map, Position posCase) {
		String res="";
		if(this.getFogOnCase(map, posCase)) {
			res = "~";
		}
		return res;
	}
	
	
	/**
	 * Renvoie sous forme de chaine de caractere le buff actif sur la Case ou la trace de la bete au cas echeant
	 */
	public String toString() {
		String[] toStringBonusBeast=new String[] { new Bait().toString(), new Camouflage().toString() };
		String[] toStringBonusHunter=new String[] { new Trap().toString(), new Ward().toString()};
		
		String res=caseType.getCaseDisplay();
		if(this.beastWalk>=this.TRACE) res=".";
		if(this.beastWalk>0 && this.beastWalk<this.TRACE) res=this.beastWalk+"";

		for (int i = 0; i < this.bonusBeast.length; i++)
			if(bonusBeast[i]) res=toStringBonusBeast[i];
		for (int i = 0; i < this.bonusHunter.length; i++)
			if(bonusHunter[i]) res=toStringBonusHunter[i];
		return res;
	}
}