package config;

/**
 * 
 * Cette classe gere la configuration de la partie qui va etre instancie.
 * @author PHPierre
 *
 */
public class Config
{
	//private int mod;
	private int width;
	private int height;
	private GameMode gameMode;
	private int nbTeleportation;
	private Map map;
	
	private boolean trap;
	private boolean camouflage;
	private boolean ward;
	private boolean bait;

	/**
	 * Instancie une nouvelle configuration qui applique la configuration par default.
	 */
	public Config()
	{
		this.setDefaultConfig();
	}
	
	/**
	 * Restaure la configuration par default.
	 */
	public void setDefaultConfig()
	{
		this.width = 11;
		this.height = 11;
		this.gameMode = GameMode.BEASTvsAI;
		this.nbTeleportation = 3;
		this.map = Map.SQUARE;

		this.trap = false;
		this.camouflage = false;
		this.ward = false;
		this.bait = false;
	}

	/**
	 * Retourne la largueur du plateau (axe X).
	 * @return int
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
		this.width = width;
	}

	/**
	 * Retourne la hauteur du plateay (axe Y).
	 * @return int
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
		this.height = height;
	}
	
	/**
	 * Retourne le type de mode de jeu de la partie.
	 * @return GameMode
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
	 * @return int
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
	 * @return Map
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
	 * @return boolean
	 */
	public boolean isTrap()
	{
		return this.trap;
	}

	/**
	 * Definit si les pieges sont disponibles ou non.
	 * @param trap Le statut des pieges
	 */
	public void setTrap(boolean trap)
	{
		this.trap = trap;
	}

	/**
	 * Retourne le statut concernant l'utlisation des camouflages pendant la partie.
	 * @return boolean
	 */
	public boolean isCamouflage()
	{
		return this.camouflage;
	}

	/**
	 * Definit si les camouflages sont disponibles ou non.
	 * @param camouflage Le statut des camouflages
	 */
	public void setCamouflage(boolean camouflage)
	{
		this.camouflage = camouflage;
	}

	/**
	 * Retourne le statut concernant l'utlisation des balises de vision pendant la partie.
	 * @return boolean
	 */
	public boolean isWard()
	{
		return this.ward;
	}

	/**
	 * Definit si les balises de vision sont disponibles ou non.
	 * @param ward Le statut des balises de vision
	 */
	public void setWard(boolean ward)
	{
		this.ward = ward;
	}

	/**
	 * Retourne le statut concernant l'utlisation des leurres pendant la partie.
	 * @return boolean
	 */
	public boolean isBait()
	{
		return this.bait;
	}
	
	/**
	 * Definit si les leurres sont disponibles ou non.
	 * @param bait Le statut des leurres
	 */
	public void setBait(boolean bait)
	{
		this.bait = bait;
	}
}