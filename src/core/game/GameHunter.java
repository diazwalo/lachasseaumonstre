package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;

public class GameHunter implements IGame {

	private IMap map; 
	public static GameStatus gameStatus;
	
	public GameHunter(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	 
	public void checkGameStatus() {
		GameBeast.gameStatus=GameStatus.INGAME;
		if(this.statusFound()) GameBeast.gameStatus=GameStatus.FOUND;
		else if(this.statusDiscovered()) GameBeast.gameStatus=GameStatus.DISCOVERED;
		else if(this.statusEnemyblock()) GameBeast.gameStatus=GameStatus.ENEMYBLOCK;
	}
	
	public boolean statusFound() {
		boolean res=false;
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().getMvtPossiblePlusOther(this.map.getTab());
		for (Mouvment mouvment : mvtHunter) {
			int[] posHunterAfterMvt=this.map.getHunter().getPos().getModifPosTempo(mouvment.getMvt());
			res=this.map.getBeast().isPosEnt(posHunterAfterMvt[0], posHunterAfterMvt[1]);
		}return res;
	}
	
	//a changer car le deplacement vers la position enemy n'est pas dans les deplacements possible ...
	public boolean statusEnemyblock() {
		/*ArrayList<Mouvment> mvtHunter=this.map.getHunter().mvtPossible(this.map.getTab());
		return mvtHunter.size()==0;*/
		return this.map.isHunterWin();
	}
	
	public boolean statusDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab().length; j++) {
				
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastWalk()==-1) 
					pasBeast=false;
				}
		}
		return pasBeast;
	}
	
	public void launchGame() {
		while(!this.map.isBeastWin() && !this.map.isHunterWin() && GameBeast.gameStatus.equals(GameStatus.INGAME)) {
			//System.out.println(GameBeast.gameStatus);
			//while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			//this.map.winRefresh();
			//System.out.println(this.map.isBeastWin());
			//System.out.println("avant "+GameBeast.gameStatus);
			/*this.beastTurn() ;
			this.map.winRefresh();
			this.checkGameStatus();
			System.out.println(this.map.isBeastWin());
			//System.out.println("apres "+GameBeast.gameStatus);
			this.map.setBeastWalk();
			this.afficherBeastPas();
			//this.map.winRefresh();*/
			while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			this.beastTurn() ;
			this.map.setBeastWalk();
			this.afficherBeastPas();
			
		}
		
		System.out.println(GameBeast.gameStatus);
		
		if (this.map.isBeastWin()) {
			System.out.println("Victoire de la bete");
		}
		else {
			System.out.println("Victoire du Chasseur");
		}
	}
	
	public void afficherBeastPas() {
		if (this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBeastWalk()>-1) {
			System.out.println("La bete est passé par ici il y'a "+this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBeastWalk()+" tours.");
		}
	}
	
	public boolean hunterTurn() {
		boolean mvtValide=false;
		System.out.println(map+"\n");
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveHunter(mvt);
		
		this.map.winRefresh();
		return mvtValide;
	}
	
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= this.map.getBeast().getMvtPossibleWithoutBeastWalk(this.map.getTab());

		if(mvtBeast.size()>0) {
			while(! map.moveBeast(mvtBeast.get(new Random().nextInt(mvtBeast.size())))) System.out.println(mvtBeast.size());;
			
			this.map.winRefresh();
			return true;
		}
		else {
			this.map.winRefresh();
			return false;
		}
	}
	
}
