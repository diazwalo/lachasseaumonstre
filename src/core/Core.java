package core;

import config.Config;
import core.game.GameBeast;
import core.game.GameHunter;
import core.game.IGame;
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
		
		IGame game=null;
		
		switch(config.getGameMode())
		{
		case AIvsAI:
			//game = new GameAI(map); 
			break;
		case BEASTvsAI:
			game = new GameBeast(map);
			break;
		case HUNTERvsAI:
			game = new GameHunter(map);
			break;
		case BEASTvsHUNTER:
			//Jalon 2 !
			game = new GameHunter(map);
			break;
		}
		
		game.launchGame();
		
		//A supprimer une fois les classes finies
		/*IGame game = new GameBeast(map);
		game.launchGame();*/
		//fin du a supprimer

		//TODO : reecritre les tests unitaires ayant besoin de square map
	}
}