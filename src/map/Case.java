package map;

/**
 * Definit le type de case (obstacle ou sol), un compteur pour
 * du nombre tours depuis le dernier passage de la bete, un boolean qui 
 * precise si le case est visible pour le chasseur et un tableaux qui defini
 * le buff actif de cete case.
 * @author nath
 *
 */


public class Case {
	private CaseType caseType;
	private int beastWalk;
	private boolean hideToHunter;
	private boolean[] buff;
	private final int TRACE=5;
	private boolean blinded;
	
	/**
	 * Creer une class Case avec en parametre CaseType et posBeast respectivment le type de la case
	 * et si la bete est passe sur la case, instansie par defaut les buff a false.
	 * @param caseType
	 * @param posBeast
	 */
	public Case(CaseType caseType, boolean posBeast) {
		this(caseType, posBeast, new boolean[] {false, false, false, false});
	}
	
	/**
	 * Meme principe que le premier constructeur avec les tableau de buff souhaite en parametre
	 * @param caseType
	 * @param posBeast
	 * @param buff
	 */
	public Case(CaseType caseType, boolean posBeast, boolean[] buff) {
		this.caseType=caseType;
		if(posBeast) this.beastWalk=0;
		else this.beastWalk=-1;
		this.hideToHunter=false;
		this.buff=buff;
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
	 * retourne le tableau de buff actif
	 * @return boolean[]
	 */
	public boolean[] getBuff() {
		return this.buff;
	}
	
	/**
	 * Passe beastPas a 0 cette case correspond a la position de la bete et incremente beastPas si Beast est deja passe ici
	 * @param posBeast
	 */
	public void modifBeastWalk(boolean posBeast) {
		if(posBeast) this.setBeastWalk(0);
		else if(this.beastWalk!=-1) this.setBeastWalk(this.beastWalk+1);
	}
	
	/**
	 * passe beastPas a la valeur passee en parametre
	 * @param beastPas
	 */
	public void setBeastWalk(int beastPas) {
		this.beastWalk=beastPas;
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
	public void setBuff(boolean[] buff) {
		this.buff=buff;
	}
	
	/**
	 * Returne un boolean qui indique si la case et in obstacle ou non
	 * @return
	 */
	
	public boolean isObstacle() {
		return this.caseType == CaseType.OBSTACLE;
	}
	
	/**
	 * Renvoie sous forme de chaine de caractere le buff actif sur la Case ou la trace de la bete au cas echeant
	 */
	
	public String toString() {
		String res=caseType.toString();
		if(this.beastWalk>=this.TRACE) res=".";
		if(this.beastWalk>0 && this.beastWalk<this.TRACE) res=this.beastWalk+"";
		String[] toStringConf=new String[] {"p", "c", "w", "l"};
		for (int i = 0; i < buff.length; i++)
			if(buff[i]) res=toStringConf[i];
		return res;
	}
}