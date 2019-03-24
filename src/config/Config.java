package config;

public class Config {
	private static boolean piege=false;
	private static boolean camouflage=false;
	private static boolean ward=false;
	private static boolean leurre=false;
	
	public static boolean[] getConfig() {
		return new boolean[] {piege, camouflage, ward, leurre};
	}
	
	public void setConfig(boolean[] aModifier) {
		if(aModifier[0]) setPiege();
		else if(aModifier[1]) setCamouflage();
		else if(aModifier[2]) setWard();
		else if(aModifier[3]) setLeurre();
	}
	
	public static boolean isPiege() {
		return piege;
	}
	private static void setPiege() {
		Config.piege = !piege;
	}
	public static boolean isCamouflage() {
		return camouflage;
	}
	private static void setCamouflage() {
		Config.camouflage = !camouflage;
	}
	public static boolean isWard() {
		return ward;
	}
	private static void setWard() {
		Config.ward = !ward;
	}
	public static boolean isLeurre() {
		return leurre;
	}
	private static void setLeurre() {
		Config.leurre = !leurre;
	}
}