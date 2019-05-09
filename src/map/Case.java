package map;

import java.util.ArrayList;
import java.util.List;

import interaction.Interaction;
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
	private final int TRACE=5;
	private boolean blinded;
	
	/**
	 * Creer une class Case avec en parametre CaseType et posBeast respectivment le type de la case
	 * et si la bete est passe sur la case, instansie par defaut les buff a false.
	 * @param caseType
	 * @param posBeast
	 */
	public Case(CaseType caseType, boolean posBeast) {
		this(caseType, posBeast, new boolean[] {false, false }, new boolean[] {false, false});
	}
	
	/**
	 * Meme principe que le premier constructeur avec les tableau de buff souhaite en parametre
	 * @param caseType
	 * @param posBeast
	 * @param buff
	 */
	public Case(CaseType caseType, boolean posBeast, boolean[] bonusBeast, boolean[] bonusHunter) {
		this.caseType=caseType;
		if(posBeast) this.beastWalk=0;
		else this.beastWalk=-1;
		this.hideToHunter=false;
		this.bonusBeast=bonusBeast;
		this.bonusHunter=bonusHunter;
		this.blinded = false;
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
	 * @param beastWalk
	 */
	public void setBeastWalk(int beastWalk) {
		this.beastWalk=beastWalk;
	}
	
	/**
	 * Rend aveugle le chasseur 
	 */
	public void setBlinded() {
		this.blinded=true;
	}

	/**
	 * passe setHideToHunter a vrai afin que Hunter ne le voit pas
	 */
	public void setHideToHunter() {
		this.hideToHunter=true;
	}
	
	/**
	 * modifie le tableau de buff en le remplacant par celui en parametre
	 * @param buff
	 */
	public void setBonusHunter(boolean[] bonusHunter) {
		this.bonusHunter=bonusHunter;
	}
	
	/**
	 * modifie le tableau de buff en le remplacant par celui en parametre
	 * @param buff
	 */
	public void setBonusBeast(boolean[] bonusBeast) {
		this.bonusBeast=bonusBeast;
	}
	
	/**
	 * Ajoute � l'inventaire du Chasseur, le pi�ge de la Case (s'il y en a un).
	 * @param hunter
	 * @param posCase
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
	 * Ajoute � l'inventaire de la Bete, le pi�ge de la Case (s'il y en a un).
	 * @param beast
	 * @param posCase
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
	 * @param posBeast
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
	 * Montre la map tel que le joueur doit la voir lorsqu'il joue la Bete
	 * @param map
	 * @param posCase
	 * @return
	 */
	public String gameBeastShowView(AbstractMap map, Position posCase) {
		String res=caseType.toString();
		String[] toStringBonusBeast=new String[] { new Bait().toString(), new Camouflage().toString() };
		
		//TODO : a changer
		/*if( ! this.bonusHunter[0] && ! this.bonusHunter[1]) {
			res=this.toString();
		}*/
		for (int i = 0; i < bonusBeast.length; i++) {
			if(this.bonusHunter[i]) res=toStringBonusBeast[i];
		}
		
		if(map.getBeast().isPosEnt(posCase.getPosX(), posCase.getPosY())) res=map.getBeast().toString();
		else if(map.getHunter().isPosEnt(posCase.getPosX(), posCase.getPosY())) res=map.getHunter().toString();
		return res;
	}
	
	/**
	 * Montre la map tel que le joueur doit la voir lorsqu'il joue le chasseur
	 * @param map
	 * @param posCase
	 * @return
	 */
	public String gameHunterShowView(AbstractMap map, Position posCase) {
		String res=caseType.toString();

		String[] toStringBonusHunter=new String[] { new Trap().toString(), new Ward().toString() };
		
		for (int i = 0; i < bonusHunter.length; i++) {
			if(this.bonusHunter[i]) res=toStringBonusHunter[i];
		}

		List<Position> posAdj=posCase.getAdjacentPosition(map);

		if(this.beastWalk >= 0 && this.beastWalk < this.TRACE && !this.blinded) {
			for (Position position : posAdj) {
				if(map.getHunter().isPosEnt(position.getPosX(), position.getPosY())) {
					if(this.beastWalk==0) {
						res=map.getBeast().toString();
					}else {
						res=this.beastWalk+"";
					}
				}
			}
		}else if(map.getHunter().isPosEnt(posCase.getPosX(), posCase.getPosY())){
			res=map.getHunter().toString();
		}

		if(map.getBeast().getPos().equals(map.getHunter().getPos()) && map.getHunter().getPos().equals(posCase)) {
			res=map.getHunter().toString();
		}

		return res;
	}
	
	/**
	 * Renvoie sous forme de chaine de caractere le buff actif sur la Case ou la trace de la bete au cas echeant
	 */
	public String toString() {
		String[] toStringBonusBeast=new String[] { new Bait().toString(), new Camouflage().toString()};
		String[] toStringBonusHunter=new String[] { new Trap().toString(), new Ward().toString()};
		
		String res=caseType.toString();
		if(this.beastWalk>=this.TRACE) res=".";
		if(this.beastWalk>0 && this.beastWalk<this.TRACE && !this.blinded) res=this.beastWalk+"";

		for (int i = 0; i < this.bonusBeast.length; i++)
			if(bonusBeast[i]) res=toStringBonusBeast[i];
		for (int i = 0; i < this.bonusHunter.length; i++)
			if(bonusHunter[i]) res=toStringBonusHunter[i];
		return res;
	}
}