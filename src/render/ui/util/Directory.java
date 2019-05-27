package render.ui.util;

import java.io.File;

public class Directory
{
	//System path
	private static final String dir = "user.dir";
	
	private static final String DS = File.separator;
	
	private static final String TEXTURES_FOLDER = DS + "rsc" + DS + "textures" + DS;
	
	//Textures path
	
	private static final String HOME_BACKGROUND = TEXTURES_FOLDER + "background.gif";
	
	private static final String GAME_GROUND = TEXTURES_FOLDER + "ground.png";
	
	private static final String GAME_TEXTURE = TEXTURES_FOLDER + "texture.png";
	
	private static final String GAME_OBSTACLE = TEXTURES_FOLDER + "obstacle.png";
	
	private static final String GAME_BEAST = TEXTURES_FOLDER + "beast.png";
	
	//Getter
	
	public static String getHomeBackground()
	{
		return System.getProperty(Directory.dir) + HOME_BACKGROUND;
	}
	
	public static String getGameGround()
	{
		return System.getProperty(Directory.dir) + GAME_GROUND;
	}
	
	public static String getGameTexture()
	{
		return System.getProperty(Directory.dir) + GAME_TEXTURE;
	}
	
	public static String getGameObstacle()
	{
		return System.getProperty(Directory.dir) + GAME_OBSTACLE;
	}
	
	public static String getGameBeast()
	{
		return System.getProperty(Directory.dir) + GAME_BEAST;
	}
}
