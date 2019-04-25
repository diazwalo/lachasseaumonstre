package map;

/**
 * Il s'agis de la classe qui defini l'etat de la case
 * comme etant un le sol sur lequel le personnage peut se deplacer ou
 * un obstacle qu'il ne peut traverser.
 * @author nath
 *
 */

public enum CaseType {
	SOL(1), OBSTACLE(2);
	private int caseType;
	
	/**
	 * Associe un nombre (passe en parametre) au type de la Case
	 * @param typeCase
	 */
	private CaseType(int typeCase) {
		this.caseType=typeCase;
	}
	
	/**
	 * retourne l entier associe au type de la Case
	 * @return int
	 */
	public int getCaseType(){
		return this.caseType;
	}
	
	/**
	 * retourne le type de la Case sous la forme textuelle
	 */
	public String toString() {
		if(caseType==1) return " ";
		return "#";
	}
}