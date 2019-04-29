package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.Case;
import map.CaseType;
import map.IMap;
import map.Mouvment;
import map.Position;


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
	 * statusBeastblock retourne true si la bete se retrouve bloquee.
	 * @return boolean
	 */
	public boolean statusBeastblock() {
		if(this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty() ) {
			
			if(this.map.getBeast().teleportation()) {
				if(! this.tpBeast()) {
					return true;
				}
			}else {
				return true;
			}
			
		}
		return false;

	}
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void EndGame() {
		
		if (this.map.isBeastWin()) {
			System.out.println(GameBeast.gameStatus);
			System.out.println("Victoire de la bete");
		}
		else if(this.map.isHunterWin()){
			System.out.println(GameBeast.gameStatus);
			System.out.println("Victoire du Chasseur");
		}
	}
	
	
	/**
	 * Cherche si il existe une Position ou la bete peut etre tp et retourne vrai si cela est fait et faux dans le cas contraire
	 * @return boolean
	 */
	public boolean tpBeast(){
		ArrayList<Position> posDispo=this.map.getBeast().getCaseTp(this.map.getTab(), this.map.getHunter());
		
		boolean tp=false;
		int idxPosDispo;
		
		if(posDispo.size()>0) {
			idxPosDispo=new Random().nextInt(posDispo.size());
			this.map.getBeast().setPosition(posDispo.get(idxPosDispo));
			this.map.setBeastWalk();
			this.map.getBeast().setTp(this.map.getBeast().getTp()-1);
			tp=true;
		}
		return tp;
	}
}