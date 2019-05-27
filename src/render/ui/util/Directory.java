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
}
