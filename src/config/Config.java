package config;

/**
 * 
 * Cette Class Enregistre et modifie les settings du Jeu
 * @author diazw
 *
 */
public class Config {
	private static boolean piege=false;
	private static boolean camouflage=false;
	private static boolean ward=false;
	private static boolean bait=false;

	/**
	 * Retourne la Config sous la forme d'un tableau de 4 boolean 
	 * @return boolean[]
	 */
	public static boolean[] getConfig() {
		return new boolean[] {piege, camouflage, ward, bait};
	}
	
	/**
	 * Pour les Cases a "true" dans "aModifier", inverse les le boolean qui lui est associe dans Config
	 * @param aModifier
	 */
	public void setConfig(boolean[] aModifier) {
		if(aModifier[0]) setPiege();
		else if(aModifier[1]) setCamouflage();
		else if(aModifier[2]) setWard();
		else if(aModifier[3]) setBait();
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
}