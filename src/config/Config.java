package config;

import java.util.prefs.Preferences;

/**
 * 
 * Cette classe gere la configuration de la partie qui va etre instancie.
 * @author PHPierre
 *
 */
public class Config
{
	public static final int MINSIZE = 5;
	public static final int MAXSIZE = 20;
	public static final int MINTP = 0;
	public static final int MAXTP = 5;
	
	private int width;
	private int height;
	private GameMode gameMode;
	private int nbTeleportation;
	private Map map;
	
	private boolean trap;
	private boolean camouflage;
	private boolean ward;
	private boolean bait;
	
	private Preferences prefs;

	/**
	 * Instancie une nouvelle configuration qui applique la configuration par default.
	 */
	public Config() {
		this.setDefaultConfig();
	}
	
	/**
	 * Restaure la configuration par default.
	 */
	public void setDefaultConfig() {
		this.width = Config.MINSIZE;
		this.height = Config.MINSIZE;
		this.gameMode = GameMode.BEASTvsAI;
		this.nbTeleportation = (Config.MINTP+Config.MAXTP)/2;
		this.map = Map.SQUARE;

		this.trap = false;
		this.camouflage = false;
		this.ward = false;
		this.bait = false;
	}
	
	/**
	 * Applique à la configuration existante les valeurs de dernière partie avant l'extinction du programme.
	 */
	public void loadLastSave()
	{
		this.prefs = Preferences.userRoot().node("lachasseauxmonstres_settings");
		this.width = prefs.getInt("width", this.getWidth());
		this.height = prefs.getInt("height", this.getHeight());
		this.nbTeleportation = prefs.getInt("tp", this.getNbTeleportation());

		int mapIndex = prefs.getInt("map", 0);
		for (int i = 0; i < Map.values().length; i++) {
			if(mapIndex == i) {
				this.map = Map.values()[i];
				break;
			}
		}
		
		this.trap = prefs.getBoolean("trap", this.isTrap());
		this.camouflage = prefs.getBoolean("camouflage", this.isCamouflage());
		this.ward = prefs.getBoolean("ward", this.isWard());
		this.bait = prefs.getBoolean("bait", this.isBait());
	}
	
	public boolean saveConfig(
			int width, int height, int nbTeleportation, int indexMap, 
			boolean trap, boolean camouflage, boolean ward, boolean bait)
	{
		this.width = width;
		this.height = height;
		this.nbTeleportation = nbTeleportation;
		this.map = Map.values()[indexMap];
		this.trap = trap;
		this.camouflage = camouflage;
		this.ward = ward;
		this.bait = bait;
		
		//Virgil : Je met la la width a la min size pour pas que ca bug
		this.prefs.putInt("width", MINSIZE/*width*/);
		this.prefs.putInt("height", MINSIZE/*height*/);
		this.prefs.putInt("tp", nbTeleportation);
		this.prefs.putInt("map", indexMap);
		this.prefs.putBoolean("trap", trap);
		this.prefs.putBoolean("ward", ward);	
		this.prefs.putBoolean("bait", bait);
		this.prefs.putBoolean("camouflage", camouflage);
		return true;
	}

	/**
	 * Retourne la largueur du plateau (axe X).
	 * @return int La largueur du plateau.
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Definit la largeur du plateau (axe X).
	 * @param width La largeur du plateau.
	 */
	public void setWidth(int width)
	{
		if(this.width < width) {
			this.width = width;
		}
	}

	/**
	 * Retourne la hauteur du plateay (axe Y).
	 * @return int La hauteur du plateau.
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Definit la hauteur du plateau (axe Y).
	 * @param height La hauteur du plateau.
	 */
	public void setHeight(int height)
	{
		if(this.height < height)
		this.height = height;
	}
	
	/**
	 * Retourne le type de mode de jeu de la partie.
	 * @return GameMode Le mode de jeu de la partie.
	 */
	public GameMode getGameMode()
	{
		return this.gameMode;
	}

	/**
	 * Definit le mode de jeu de la partie.
	 * @param gameMode Le mode de jeu de la partie.
	 */
	public void setGameMode(GameMode gameMode)
	{
		this.gameMode = gameMode;
	}

	/**
	 * Retourne le nombre de teleportation disponible pour le monstre durant la partie.
	 * @return int Le nombre de téléportation autorisé pendant la partie.
	 */
	public int getNbTeleportation()
	{
		return this.nbTeleportation;
	}

	/**
	 * Definit le nombre de teleportation disponible pour le monstre durant la partie.
	 * @param nbTeleportation Le nombre de teleportation lors de la partie.
	 */
	public void setNbTeleportation(int nbTeleportation)
	{
		this.nbTeleportation = nbTeleportation;
	}
	
	/**
	 * Retourne le plateau de jeu utilise pour la future partie.
	 * @return Map Le plateau de jeu de la partie.
	 */
	public Map getMap()
	{
		return this.map;
	}

	/**
	 * Definit le plateau de jeu utilise pour la future partie.
	 * @param map Le plateau de jeu.
	 */
	public void setMap(Map map)
	{
		this.map = map;
	}

	/**
	 * Retourne le statut concernant l'utlisation des pieges pendant la partie.
	 * @return boolean Le statut des pieges durant la partie.
	 */
	public boolean isTrap()
	{
		return this.trap;
	}

	/**
	 * Definit si les pieges sont disponibles ou non.
	 * @param trap Le statut des pieges.
	 */
	public void setTrap(boolean trap)
	{
		this.trap = trap;
	}

	/**
	 * Retourne le statut concernant l'utlisation des camouflages pendant la partie.
	 * @return boolean Le statut des camouflages durant la partie.
	 */
	public boolean isCamouflage()
	{
		return this.camouflage;
	}

	/**
	 * Definit si les camouflages sont disponibles ou non.
	 * @param camouflage Le statut des camouflages.
	 */
	public void setCamouflage(boolean camouflage)
	{
		this.camouflage = camouflage;
	}

	/**
	 * Retourne le statut concernant l'utlisation des balises de vision pendant la partie.
	 * @return boolean Le statut des balises de visions durant la partie.
	 */
	public boolean isWard()
	{
		return this.ward;
	}

	/**
	 * Definit si les balises de vision sont disponibles ou non.
	 * @param ward Le statut des balises de vision.
	 */
	public void setWard(boolean ward)
	{
		this.ward = ward;
	}

	/**
	 * Retourne le statut concernant l'utlisation des leurres pendant la partie.
	 * @return boolean Le statut des leurres durant la partie.
	 */
	public boolean isBait()
	{
		return this.bait;
	}
	
	/**
	 * Definit si les leurres sont disponibles ou non.
	 * @param bait Le statut des leurres.
	 */
	public void setBait(boolean bait)
	{
		this.bait = bait;
	}
}