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
	private static boolean leurre=false;

	/**
	 * Retourne la Config sous la forme d'un tableau de 4 boolean 
	 * @return boolean[]config
	 */
	public static boolean[] getConfig() {
		return new boolean[] {piege, camouflage, ward, leurre};
	}
	
	/**
	 * Pour les Cases a "true" dans "aModifier", inverse les le boolean qui lui est associé dans Config
	 * @param aModifier
	 */
	public void setConfig(boolean[] aModifier) {
		if(aModifier[0]) setPiege();
		else if(aModifier[1]) setCamouflage();
		else if(aModifier[2]) setWard();
		else if(aModifier[3]) setLeurre();
	}
	
	public static boolean isPiege() {
		return piege;
	}
	
	/**
	 * Inverse les reglages de Piege
	 */
	private static void setPiege() {
		Config.piege = !piege;
	}
	
	public static boolean isCamouflage() {
		return camouflage;
	}
	
	/**
	 * Inverse les reglages de Camouflage
	 */
	private static void setCamouflage() {
		Config.camouflage = !camouflage;
	}
	
	public static boolean isWard() {
		return ward;
	}
	
	/**
	 * Inverse les reglages de Ward
	 */
	private static void setWard() {
		Config.ward = !ward;
	}
	
	public static boolean isLeurre() {
		return leurre;
	}
	
	/**
	 * Inverse les reglages de Leurre
	 */
	private static void setLeurre() {
		Config.leurre = !leurre;
	}
}