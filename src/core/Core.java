package core;

import interaction.Interaction;
import map.IMap;
import map.Mouvment;
import map.SquareMap;

public class Core {
	public static void main(String[] args) {
		/*
		 * Il va falloir charger les configurations du jeu. On va avoir une configuration pour la map.
		 * 1carre, 2rectangle, etc
		 * On va avoir des classes configs et c'est la class config qui va nous renvoyer la map
		 * 
		 * Ici on fera donc Config config = new Config();
		 * Map map = config.getMap();
		 * 
		 * Et dans le fichier config, il y aura cette ligne : Map map = new SquareMap(11, 11);
		 * On la bougera plus tard
		 */
		
		IMap map = new SquareMap(11, 11);
		map.generationMap();
		boolean mvtValide=true;
		while(mvtValide) {
			System.out.println(map+"\n");
			Mouvment mvt=Interaction.askMouvement();
			mvtValide=map.moveBeast(mvt);
			map.setBeastPas();
		}System.out.println("Mvt invalide");
		
		//active la pr�sence de pi�ge et en met un dans le tableau � un endroit ou il y a du sol.
	}
}