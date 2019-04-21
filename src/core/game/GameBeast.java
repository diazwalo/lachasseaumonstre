package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.IMap;
import map.Mouvment;

public class GameBeast {
	private IMap map;
	public static GameStatus gameStatus;
	
	public GameBeast(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	
	public void launchGame() {
		while(! this.beastTurn()) System.out.println("Mvt invalide");
		this.hunterTurn();
	}
	
	public boolean beastTurn() {
		boolean mvtValide=false;
		System.out.println(map+"\n");
		
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveBeast(mvt);
		
		map.setBeastPas();
		
		return mvtValide;
	}
	
	public boolean hunterTurn() {
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().mvtPossible(this.map.getTab());
		
		if(mvtHunter.size()>0) {
			int hazard=new Random().nextInt(mvtHunter.size()-1);
			map.moveHunter(mvtHunter.get(hazard));
		}
		
		return true;
	}
	
	// c'est pas bo (comme pierre)
	/*public static void main(String[] args) {
		IMap map = new SquareMap(11, 11);
		map.generationMap();
		boolean mvtValide=true;
		while(mvtValide) {
			System.out.println(map+"\n");
			Mouvment mvt=Interaction.askMouvement();
			mvtValide=map.moveBeast(mvt);
			map.setBeastPas();
		}System.out.println("Mvt invalide");
	}*/
}