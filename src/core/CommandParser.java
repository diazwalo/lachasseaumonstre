package core;

import config.Config;
import config.GameMode;
import config.Map;

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
				break;
			case "tp":
				this.config.setNbTeleportation(Integer.valueOf(argumentExploded[1]));
				break;
			case "map":
				this.handleMap(argumentExploded[1]);
				break;
			default:
				this.throwWhenStart(argument);
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
				this.throwWhenStart(argument);
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
	
	/**
	 * Cette fonction analyse le plateau de jeu dans la ligne de commande, le reconnnait et modifie la configuration du jeu.
	 * @param map Un argument sous la forme --map=XX
	 */
	private void handleMap(String map)
	{
		switch(map)
		{
		case "carre":
			this.config.setMap(Map.SQUARE);
			break;
		case "circular":
			this.config.setMap(Map.CIRCULAR);
			break;
		default:
			new IllegalArgumentException("Le plateau de jeu " + map + " n'existe pas.");
			break;
		}
	}
	
	/**
	 * Cette fonction affiche le nom de l'argument ayant un default, affiche ensuite l'aide et les parametres disponible 
	 * pour le jeu, et stop le jeu.
	 * @param arg Le nom de l'argument ayant un default
	 */
	public void throwWhenStart(String arg)
	{
		System.out.println("L'argument " + arg + " n'a pas ete reconnu. \n");
		System.out.println("--width=XX           Modifie la largeur du plateau\r\n" + 
				"--height=XX          Modifie la hauteur du plateau\r\n" + 
				"--tp=XX              Indique le nombre de teleportation disponible pour le monstre durant une partie.\r\n" + 
				"--gamemode=ZZ        Selection du mode de jeu avec pour ZZ les valeurs disponibles suivantes :\r\n" + 
				"ai        : L'ordinateur joue contre lui meme et controle les deux entites.\r\n" + 
				"beast     : Le joueur controle le monstre et l'ordinateur controle le chasseur.\r\n" + 
				"hunter    : Le joueur controle le chasseur et l'ordinateur controle le monstre.\r\n" + 
				"multi     : Un des deux joueurs controle le monstre et son adversaire controle le chasseur.\r\n" + 
				"\r\n" + 
				"--map=AA\r\n" + 
				"carre     : Un plateau de jeu rectangulaire ou carre selon la largueur/hauteur.\r\n" + 
				"circular  : Un plateau de jeu circulaire selon la largueur/hauteur.\r\n" + 
				"\r\n" + 
				"--trap               Active les pieges sur le plateau\r\n" + 
				"--camouflage         Active les camouflages sur le plateau\r\n" + 
				"--ward               Active les balises de vision sur le plateau\r\n" + 
				"--bait               Active les leurres sur le plateau\r\n" + 
				"");
		System.exit(1);
	}
	
}
