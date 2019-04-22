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
	
	private boolean trap;
	private boolean camouflage;
	private boolean ward;
	private boolean bait;

	public Config()
	{
		this.setDefaultConfig();
	}
	
	public void setDefaultConfig()
	{
		this.width = 15;
		this.height = 15;
		this.trap = false;
		this.camouflage = false;
		this.ward = false;
		this.bait = false;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public GameMode getGameMode()
	{
		return this.gameMode;
	}

	public void setGameMode(GameMode gameMode)
	{
		this.gameMode = gameMode;
	}

	public boolean isTrap()
	{
		return this.trap;
	}

	public void setTrap(boolean trap)
	{
		this.trap = trap;
	}

	public boolean isCamouflage()
	{
		return this.camouflage;
	}

	public void setCamouflage(boolean camouflage)
	{
		this.camouflage = camouflage;
	}

	public boolean isWard()
	{
		return this.ward;
	}

	public void setWard(boolean ward)
	{
		this.ward = ward;
	}

	public boolean isBait()
	{
		return this.bait;
	}
	
	public void setBait(boolean bait)
	{
		this.bait = bait;
	}
}