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
		/*
		 * TODO : Map example : 1carre, 2rectangle, etc
		 */
		// --width=20 --height=20 --gamemode=beast --trap --bait --camouflage --ward
		
		CommandParser commandParser = new CommandParser(args);
		Config config = commandParser.getConfig();
		
		IMap map = new SquareMap(config); 
		map.generationMap();
		AbstractGame game=null;
		
		switch(config.getGameMode())
		{
		case AIvsAI:
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
		//fin du a supprimer

		//TODO : reecritre les tests unitaires ayant besoin de square map
	}
}