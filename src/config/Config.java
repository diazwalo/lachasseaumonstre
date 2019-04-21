package config;

/**
 * 
 * Cette Class Enregistre et modifie les settings du Jeu
 * @author diazw
 *
 */
public class Config {
	public static boolean piege=false;
	public static boolean camouflage=false;
	public static boolean ward=false;
	public static boolean bait=false;

	/**
	 * Retourne la Config sous la forme d'un tableau de 4 boolean 
	 * @return boolean[]
	 */
	public static boolean[] getConfig() {
		return new boolean[] {piege, camouflage, ward, bait};
	}
	
	/**
	 * Pour les Cases a "true" dans "aModifier", inverse le boolean qui lui est associe dans Config
	 * @param aModifier
	 */
	public void setConfig(boolean[] aModifier) {
		if(aModifier[0]) setPiege();
		if(aModifier[1]) setCamouflage();
		if(aModifier[2]) setWard();
		if(aModifier[3]) setBait();
	}
	
	/**
	 * determine si la Config accepte les pieges
	 * @return boolean
	 */
	public static boolean isPiege() {
		return piege;
	}
	
	/**
	 * Inverse les reglages de Piege
	 */
	private static void setPiege() {
		Config.piege = !piege;
	}
	
	/**
	 * determine si la Config accepte les Camouflages
	 * @return boolean
	 */
	public static boolean isCamouflage() {
		return camouflage;
	}
	
	/**
	 * Inverse les reglages de Camouflages
	 */
	private static void setCamouflage() {
		Config.camouflage = !camouflage;
	}
	
	/**
	 * determine si la Config accepte les balises de vision
	 * @return boolean
	 */
	public static boolean isWard() {
		return ward;
	}
	
	/**
	 * Inverse les reglages de balise de vision
	 */
	private static void setWard() {
		Config.ward = !ward;
	}
	
	/**
	 * determine si la Config accepte les Leurre
	 * @return boolean
	 */
	public static boolean isBait() {
		return bait;
	}
	
	/**
	 * Inverse les reglages de Leurre
	 */
	private static void setBait() {
		Config.bait = !bait;
	}
	
	/**
	 * Affiche la configuration du jeu en cours.
	 */
	public String toString()
	{
		return "Piège: " + piege +
				", Camouflage: " + camouflage +
				", Balise de vision: " + ward + 
				", Leurre: " + bait + ".";
	}
}