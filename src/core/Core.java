package core;

import config.Config;
import core.game.GameHunter;
import core.game.AbstractGame;
import core.game.GameBeast;
import map.CircularMap;
import map.IMap;
import map.SquareMap;

public class Core {
	public static void main(String[] args) {
		
		AbstractGame game = null;
		IMap map = null;
		
		CommandParser commandParser = new CommandParser(args);
		Config config = commandParser.getConfig();
		
		switch(config.getMap())
		{
		case SQUARE:
			map = new SquareMap(config);
			break;
		case CIRCULAR:
			map = new CircularMap(config);
			break;
		}
		
		//map.generationMap();
		
		switch(config.getGameMode())
		{
		case AIvsAI:
			//Jalon 2 !
			//IGame game = new GameAI(map); 
			break;
		case BEASTvsAI:
			game = new GameBeast(map);
			break;
		case HUNTERvsAI:
			game = new GameHunter(map);
			break;
		case BEASTvsHUNTER:
			//Jalon 2 !
			//IGame game = new GameHunter(map);
			break;
		}
		
		game.launchGame();
		
	}
}