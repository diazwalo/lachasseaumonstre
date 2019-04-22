package core;

import core.game.GameBeast;
import core.game.GameHunter;
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
		GameHunter gameHunter=new GameHunter(map);
		gameHunter.launchGame();
		
		int param[] = new int[4];
		int indexParam = 0;
		String argValide[] = new String[]{"--largeur=" , "--longueur=", "--ward"   , "--trap"};
		if(args.length == 4) {
			for(int i = 1 ; i<args.length ; i++) {
				for(String chaine : argValide) {
					if(args[i].equals("--ward") || args[i].equals("--trap")) {
						param[indexParam] = 1;
					}else {
						if(chaine.equals(args[i].substring(0, chaine.length()))) {
							if(args[i].length() > chaine.length()) {
								param[indexParam] = Integer.valueOf(args[i].substring(chaine.length()));
								//param[0] = largeur
								//param[1] = longueur
								//param[2] = ward
								//param[4] = trap
								//test
							}
						}
					}  

				}
				indexParam++;
			}
			indexParam = 0;
		}

		for(int num : param){
			System.out.println(num);
		}

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