package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;
import map.SquareMap;

public class GameHunter {

	private IMap map;
	public static GameStatus gameStatus;
	
	public GameHunter(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	
	public void checkGameStatus() {
		this.gameStatus=gameStatus.INGAME;
		if(this.statusFound()) GameBeast.gameStatus=GameStatus.FOUND;
		else if(this.statusDiscovered()) GameBeast.gameStatus=GameStatus.DISCOVERED;
		else if(this.statusEnemyblock()) GameBeast.gameStatus=GameStatus.ENEMYBLOCK;
	}
	
	public boolean statusFound() {
		boolean res=false;
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().mvtPossible(this.map.getTab());
		for (Mouvment mouvment : mvtHunter) {
			int[] posHunterAfterMvt=this.map.getHunter().getPos().getModifPosTempo(mouvment.getMvt());
			res=this.map.getHunter().isPosEnt(posHunterAfterMvt[0], posHunterAfterMvt[1]);
		}return res;
	}
	
	public boolean statusEnemyblock() {
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().mvtPossible(this.map.getTab());
		return mvtHunter.size()==0;
	}
	
	
	public boolean statusDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab().length; j++) {
				
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastPas()==-1) 
					pasBeast=false;
					
				}
		}
		return pasBeast;
	}
	
	public void launchGame() {
		
		while(this.gameStatus.equals(gameStatus.INGAME)) {
			while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			this.beastTurn();
			this.checkGameStatus();
		}
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
