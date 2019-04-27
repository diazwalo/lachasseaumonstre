package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;

/**
 * 
 * La classe GameHunter gere la partie lorsque que le joueur incarne le chasseur.
 * 
 */

public class GameHunter implements IGame {
	private IMap map; 
	public static GameStatus gameStatus;
	
	public GameHunter(IMap map) {
		this.map=map;
		GameBeast.gameStatus=GameStatus.INGAME;
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqué, decouverte ou si elle a decouvert toute la map.
	 */
	
	public void launchGame() {
		System.out.println(map+"\n");
		
		while(GameBeast.gameStatus.equals(GameStatus.INGAME)) {

			while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			
			System.out.println(this.map);
			this.afficherBeastPas();
			Interaction.pressEnter();
			
			if(! this.map.isHunterWin()) {
				this.beastTurn();
				System.out.println(this.map);
				Interaction.pressEnter();
			}
		}
		this.EndGame();
	}
	
	/**
	  * hunterTurn retourne true lorsque le mouvement entré par le joueur est valide et dans ce cas l'effectue. 
	  */
	
	public boolean hunterTurn() {
		boolean mvtValide=false;
		
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=map.moveHunter(mvt);
		
		this.checkGameStatus();
		return mvtValide;
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 */
	
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= this.map.getBeast().getMvtEmptyCase(this.map.getTab());

		if(mvtBeast.size()>0) {
			while(! map.moveBeast(mvtBeast.get(new Random().nextInt(mvtBeast.size()))));
			this.map.setBeastWalk();
			this.checkGameStatus();
			return true;
		}
		else {
			GameBeast.gameStatus=GameStatus.BEASTBLOCK;
			return false;
		}
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
	 */
	
	
	public boolean statusBeastFound() {
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement exploré la map.
	 */
	
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
	
	/**
	 * statusBeastblock retourne true si la bete se retrouve bloqué.
	 */
	
	//a changer car le deplacement vers la position enemy n'est pas dans les deplacements possible ...
	public boolean statusBeastblock() {
		return this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty();
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
	
	/**
	 * AfficherBeastPas affiche le nombre de pas depuis le dernier passage de la bete sur la case courante du chasseur.
	 */
	
	public void afficherBeastPas() {
		if (this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBeastWalk()>-1) {
			System.out.println("La bete est passee par ici il y'a "+this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBeastWalk()+" tours.");
		}
	}
}
