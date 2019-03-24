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
	
	private CaseType(int typeCase) {
		this.caseType=typeCase;
	}
	
	public int getCaseType(){
		return this.caseType;
	}
	
	public String toString() {
		if(caseType==1) return " ";
		return "0";
	}
}