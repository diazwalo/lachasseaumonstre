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
	
	public void launchGame() {
		while(!this.map.isBeastWin() && !this.map.isHunterWin() && GameBeast.gameStatus.equals(GameStatus.INGAME)) {
			System.out.println("grandeBoucle");
			while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			
			System.out.println("YourTurn: ");
			System.out.println(this.map);
			this.afficherBeastPas();
			
			//Interaction.waitASec(0.5); 
			if(! this.map.isHunterWin()) {
				this.beastTurn();
				this.map.setBeastWalk();
				System.out.println("BeastTurn: \n"+this.map);
				//Interaction.waitASec(0.5);
			}
		}
		this.EndGame();
	}
	
	public boolean hunterTurn() {
		boolean mvtValide=false;
		System.out.println(map+"\n");
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveHunter(mvt);
		
		this.checkGameStatus();
		return mvtValide;
	}
	
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= this.map.getBeast().getMvtEmptyCase(this.map.getTab());

		if(mvtBeast.size()>0) {
			while(! map.moveBeast(mvtBeast.get(new Random().nextInt(mvtBeast.size()))));
			this.checkGameStatus();
			return true;
		}
		else {
			GameBeast.gameStatus=GameStatus.BEASTBLOCK;
			return false;
		}
	}
	
	public void checkGameStatus() {
		GameBeast.gameStatus=GameStatus.INGAME;
		if(this.statusBeastFound()) {
			GameBeast.gameStatus=GameStatus.BEASTFOUND;
			this.map.setHunterWin(true);
		}
		else if(this.statusMapDiscovered()) {
			GameBeast.gameStatus=GameStatus.MAPDISCOVERED;
			this.map.setBeastWin(true);
		}
		else if(this.statusBeastblock()) {
			GameBeast.gameStatus=GameStatus.BEASTBLOCK;
			this.map.setHunterWin(true);
		}
	}
	
	public boolean statusBeastFound() {
		/*boolean res=false;
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().getMvtEmptyCase(this.map.getTab());
		for (Mouvment mouvment : mvtHunter) {
			int[] posHunterAfterMvt=this.map.getHunter().getPos().getModifPosTempo(mouvment.getMvt());
			res=this.map.getBeast().isPosEnt(posHunterAfterMvt[0], posHunterAfterMvt[1]);
		}return res;*/
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	//a changer car le deplacement vers la position enemy n'est pas dans les deplacements possible ...
	public boolean statusBeastblock() {
		return this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty();
	}
	
	public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab().length; j++) {
				
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastWalk()==-1) 
					pasBeast=false;
				}
		}
		return pasBeast;
	}
	
	public void EndGame() {
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
			System.out.println("La bete est passee par ici il y'a "+this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBeastWalk()+" tours.");
		}
	}
}
