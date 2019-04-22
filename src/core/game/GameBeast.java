package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;

public class GameBeast implements IGame{
	private IMap map;
	public static GameStatus gameStatus;
	
	public GameBeast(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	
	public void launchGame() {
		while(gameStatus.equals(GameStatus.INGAME)) {
			while(! this.beastTurn()) System.out.println("Mvt invalide");
			this.hunterTurn();
		}System.out.println(GameBeast.gameStatus.getStatus());
	}
	
	public void checkGameStatus() {
		GameBeast.gameStatus=GameStatus.INGAME;
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
	
	public boolean statusDiscovered() {
		for (int i = 0; i < this.map.getTab().length; i++) {
			for (int j = 0; j < this.map.getTab()[i].length; j++) {
				if(this.map.getTab()[i][j].getBeastPas()==-1 && this.map.getTab()[i][j].getCaseType().equals(CaseType.SOL)) return false;
			}
		}
		return true;
	}
	
	//a changer car le deplacement vers la position enemy n'est pas dans les deplacements possible ...
	public boolean statusEnemyblock() {
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().mvtPossible(this.map.getTab());
		return mvtHunter.size()==0;
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