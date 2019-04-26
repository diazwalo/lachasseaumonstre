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
	
	/**
	 * Instancie un nouvel analyseur de ligne de commande.
	 * @param arguments Un tableau contenant les arguments.
	 */
	public CommandParser(String[] arguments)
	{
		this.config = new Config();
		for (int i = 0; i < arguments.length; i++) {
			if(arguments[i].startsWith("--")) {
				this.handleArguments(arguments[i].toLowerCase().substring(2));
			}
		}
	}
	
	/**
	 * Cette fonction analyse un argument de la ligne de commande, le reconnnait et modifie la configuration du jeu.
	 * @param argument Un argument sous la forme --XXXXX=XX
	 */
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
			case "tp":
				this.config.setNbTeleportation(Integer.valueOf(argumentExploded[1]));
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

	/**
	 * Retourne la configuration analyse par l'analyseur de commande. 
	 * @return Config
	 */
	public Config getConfig()
	{
		return this.config;
	}
	
	/**
	 * Detecte si l'argument contient le symbole egal `=`
	 * @param argument Une chaine de caracteres a analyser
	 * @return boolean 
	 */
	private boolean detectValueInArgument(String argument)
	{
		return argument.contains("=");
	}
	
	/**
	 * Cette fonction analyse le mode de jeu contenu dans la ligne de commande, le reconnnait et modifie la configuration du jeu.
	 * @param gameMode Un argument sous la forme --gamemode=XX
	 */
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
