package core;

import config.Config;
import config.GameMode;

/**
 * Cette classe va manipuler les arguments passe en ligne de commande lors du lancement du jeu afin de creer la configuration du jeu.
 * @author PHPierre
 *
 */
public class CommandParser
{
	private Config config;
	
	public CommandParser(String[] arguments)
	{
		this.config = new Config();
		for (int i = 0; i < arguments.length; i++) {
			if(arguments[i].startsWith("--")) {
				this.handleArguments(arguments[i].toLowerCase().substring(2));
			}
		}
	}
	
	private void handleArguments(String argument)
	{
		if(detectValueInArgument(argument))
		{
			String[] argumentExploded = argument.split("=");
			String argumentName = argumentExploded[0];
			
			switch (argumentName) {
			case "w":
			case "width":
				this.config.setWidth(Integer.valueOf(argumentExploded[1]));
				break;
			case "h":
			case "height":
				this.config.setHeight(Integer.valueOf(argumentExploded[1]));
				break;
			case "g":
			case "gamemode":
				this.handleGameMode(argumentExploded[1]);
			default:
				new IllegalArgumentException("L'argument " + argumentName + " n'a pas ete reconnu.");
				break;
			}
		} 
		else {
			switch(argument)
			{
			case "b":
			case "bait":
				this.config.setBait(true);
				break;
			case "c":
			case "camouflage":
				this.config.setCamouflage(true);
				break;
			case "t":
			case "trap":
				this.config.setTrap(true);
				break;
			case "w":
			case "ward":
				this.config.setWard(true);
				break;
			default:
				new IllegalArgumentException("L'argument " + argument + " n'a pas ete reconnu.");
				break;
			}
		}
	}

	public Config getConfig()
	{
		return this.config;
	}
	
	private boolean detectValueInArgument(String argument)
	{
		return argument.contains("=");
	}
	
	private void handleGameMode(String gameMode)
	{
		switch(gameMode)
		{
		case "ai":
			this.config.setGameMode(GameMode.AIvsAI);
			break;
		case "beast":
			this.config.setGameMode(GameMode.BEASTvsAI);
			break;
		case "hunter":
			this.config.setGameMode(GameMode.HUNTERvsAI);
			break;
		case "multi":
			this.config.setGameMode(GameMode.BEASTvsHUNTER);
			break;
		default:
			new IllegalArgumentException("Le mode de jeu " + gameMode + " n'existe pas.");
			break;
		}
	}
	
}
