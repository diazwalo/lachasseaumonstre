package core;

import config.Config;
import core.game.GameBeast;
import core.game.GameHunter;
import interaction.Interaction;
import map.IMap;
import map.Mouvment;
import map.SquareMap;

public class Core {
	public static void main(String[] args) {
		/*
		 * TODO : Map example : 1carre, 2rectangle, etc
		 */
		// --width=20 --height=20 --gamemode=beast --trap --bait --camouflage --ward
		
		CommandParser commandParser = new CommandParser(args);
		
		IMap map = new SquareMap(commandParser.getConfig()); 
		map.generationMap();
		
		GameHunter gameHunter=new GameHunter(map);
		gameHunter.launchGame();
		

		/*IMap map = new SquareMap(11, 11);
		map.generationMap();
		boolean mvtValide=true;
		while(mvtValide) {
			System.out.println(map+"\n");
			Mouvment mvt=Interaction.askMouvement();
			mvtValide=map.moveBeast(mvt);
			map.setBeastPas();
		}System.out.println("Mvt invalide");*/

		//active la pr�sence de pi�ge et en met un dans le tableau � un endroit ou il y a du sol.
	}
}