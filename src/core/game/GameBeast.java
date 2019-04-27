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
		System.out.println(map+"\n");
		
		while(!this.map.isBeastWin() && !this.map.isHunterWin() && GameBeast.gameStatus.equals(GameStatus.INGAME)) {
			
			while(! this.beastTurn()) System.out.println("Mvt Invalide");
			
			System.out.println(this.map);
			Interaction.pressEnter();
			
			if(! this.map.isBeastWin()) {
				this.hunterTurn();
				System.out.println(this.map);
				Interaction.pressEnter();
			}
		}
		this.EndGame();
	}
	
	public boolean beastTurn() {
		boolean mvtValide=false;
		
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveBeast(mvt);
		
		this.map.setBeastWalk();
		this.checkGameStatus();
		return mvtValide;
	}
	
	public boolean hunterTurn() {
		ArrayList<Mouvment> mvtHunter=this.map.getHunter().getMvtEmptyCase(this.map.getTab());
		int idxMvt=0;
		
		if(mvtHunter.size()>0) {
			idxMvt=new Random().nextInt(mvtHunter.size()-1);
		}
		
		for (int i = 0; i < mvtHunter.size(); i++) {
			int[] modifPosTempo=this.map.getHunter().getPos().getModifPosTempo(mvtHunter.get(i).getMvt());
			if(this.map.getBeast().isPosEnt(modifPosTempo[0], modifPosTempo[1])) idxMvt=i;
		}
		
		if(mvtHunter.size()>0) map.moveHunter(mvtHunter.get(idxMvt));
		this.checkGameStatus();
		
		return true;
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
			res=this.map.getHunter().isPosEnt(posHunterAfterMvt[0], posHunterAfterMvt[1]);
		}return res;*/
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
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
	
	//a changer car le deplacement vers la position enemy n'est pas dans les deplacements possible ...
	public boolean statusBeastblock() {
		return this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty();
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
}