package core.game;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Position;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;


/**
 * 
 * La classe GameBeast gere la partie lorsque que le joueur incarne la bete.
 * 
 */


public class GameBeast implements IGame{
	private IMap map;
	public static GameStatus gameStatus;
	
	public GameBeast(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqué, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		System.out.println(map+" \n");
		
		while(GameBeast.gameStatus.equals(GameStatus.INGAME)) {
			
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
	
	
	 /**
	  * beastTurn retourne true lorsque le mouvement entré par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean
	  */
	public boolean beastTurn() {
		boolean mvtValide=false;
		
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveBeast(mvt);
		
		this.map.setBeastWalk();
		this.checkGameStatus();
		return mvtValide;
	}
	
	

	/**
	 * hunterTurn retourne true lorsque le deplacement du chasseur est valide, c'est a dire si il ne va pas sur un obstacle.
	 * @return boolean
	 */
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
	
	/**
	 * checkGameStatus met a jour le status de la partie, INGAME lorsque la partie est toujours en cours, BEASTFOUND lorsque la bete a été trouvé par le chasseur,
	 * MAPDISCOVERED si la map a été entierrement decouverte par la bete et BEASTBLOCK lorsque la bete est bloqué.
	 */

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
	
	/**
	 * statusBastFound retourne true si la bete a été trouvé par le chasseur.
	 * @return boolean
	 */
	public boolean statusBeastFound() {
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement exploré la map.
	 * @return boolean
	 */
	public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab().length; j++) {
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastWalk()==-1) {
					pasBeast=false;
				}
			}
		}
		return pasBeast;
	}
	
	/**
	 * statusBeastblock retourne true si la bete se retrouve bloqué.
	 * @return boolean
	 */
	public boolean statusBeastblock() {
		if(this.map.getBeast().teleportation() && this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty() ) {
			this.tpBeast();
			return false;
		}
		else if(!this.map.getBeast().teleportation() && this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void EndGame() {
		System.out.println(GameBeast.gameStatus);
		
		if (this.map.isBeastWin()) {
			System.out.println(GameBeast.gameStatus);
			System.out.println("Victoire de la bete");
		}
		else {
			System.out.println(GameBeast.gameStatus);
			System.out.println("Victoire du Chasseur");
		}
	}
	
	public boolean tpBeast(){
		ArrayList<Mouvment> mvtDispo=this.map.getBeast().getMvtEmptyCase(this.map.getTab());
		Random randPos=new Random();
		int pos;
		boolean tp=false;
		if(this.statusBeastblock() && mvtDispo.size()>0) {
			pos= randPos.nextInt(mvtDispo.size());
			this.map.getBeast().getPos().setPosX(mvtDispo.get(pos).getMvtX());
			this.map.getBeast().getPos().setPosY(mvtDispo.get(pos).getMvtY());
			tp=true;
		}
		return false;
	}
	
}