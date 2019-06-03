package core;

import config.Config;
import core.game.GameHunter;
import core.game.GameMulti;
import core.game.AbstractGame;
import core.game.GameAI;
import core.game.GameBeast;
import map.CircularMap;
import map.AbstractMap;
import map.SquareMap;

/**
 * Cette classe est le controlleur d'entree de tout le jeu. Elle choisit les classes
 * a executer selon le plateau de jeu, le mode de jeu.
 *
 */
public class Core {
	public static void main(String[] args) {

		AbstractGame game = null;
		AbstractMap map = null;
		
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
		
		switch(config.getGameMode())
		{
		case AIvsAI:
			game = new GameAI(map); 
			break;
		case BEASTvsAI:
			game = new GameBeast(map);
			break;
		case HUNTERvsAI:
			game = new GameHunter(map);
			break;
		case BEASTvsHUNTER:
			game = new GameMulti(map);
			break;
		}
		
		game.launchGame();
		
	}
}