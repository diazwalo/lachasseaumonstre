package map;

/**
 * Il s'agis de la classe qui defini l'etat de la case
 * comme etant un le sol sur lequel le personnage peut se deplacer ou
 * un obstacle qu'il ne peut traverser.
 * @author nath
 *
 */

public enum CaseType
{
	SOL(1, " "), 
	OBSTACLE(2, "#"), 
	DEBUG(3, "@");
	
	private int caseType;
	private String display;
	
	/**
	 * Associe un nombre (passe en parametre) au type de la Case
	 * @param typeCase
	 */
	private CaseType(int typeCase, String display) {
		this.caseType=typeCase;
		this.display = display;
	}
	
	/**
	 * Retourne l'entier associe au type de la Case
	 * @return l'identifiant de la case.
	 */
	public int getCaseType(){
		return this.caseType;
	}
	
	/**
	 * Retourne le rendu de la case pour le plateau de jeu.
	 * @return L'affichage ASCII de la case.
	 */
	public String getCaseDisplay()
	{
		return this.display;
	}
	
	@Override
	public String toString() {
		return this.display;
	}
}