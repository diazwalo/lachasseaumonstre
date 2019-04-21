package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.IMap;
import map.Mouvment;
import map.SquareMap;

public class GameHunter {

	private IMap map;
	
	public GameHunter(IMap map) {
		this.map=map;
		
	}
	
	public void launchGame() {
		while(! this.hunterTurn()) System.out.println("Mvt Invalide");
		this.beastTurn();
	}
	
	public boolean hunterTurn() {
		boolean mvtValide=false;
			System.out.println(map+"\n");
			Mouvment mvt=Interaction.askMouvement();
			mvtValide=map.moveHunter(mvt);
		return mvtValide;
	}
	
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= this.map.getBeast().mvtPossible(this.map.getTab());
		int rand;
		if(mvtBeast.size()>0) {
			rand = new Random().nextInt(mvtBeast.size()-1);
			map.moveBeast(mvtBeast.get(rand));
			
			return true;
		}
		else {
			return false;
		}
	
	}
	
	
	
	/*
	public static void main(String [] args) {
	
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
