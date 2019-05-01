package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.CaseType;
import map.IMap;
import map.Mouvment;
import map.Position;

/**
 * 
 * La classe GameHunter gere la partie lorsque que le joueur incarne le chasseur.
 * 
 */

public class GameHunter extends AbstractGame {
	
	public GameHunter(IMap map) {
		super(map);
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqué, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		System.out.println(map.gameHunterToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {

			while(! this.hunterTurn()) System.out.println("Mvt Invalide");
			
			System.out.println(super.map.gameHunterToString());
			this.afficherBeastPas();
			Interaction.pressEnter();
			
			if(! super.map.isHunterWin()) {
				this.beastTurn();
				System.out.println(super.map.gameHunterToString());
				System.out.println(super.map);
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
		mvtValide=super.map.moveHunter(mvt);
		
		super.checkGameStatus();
		return mvtValide;
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 */
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= super.map.getBeast().getMvtEmptyCase(super.map.getTab());

		if(mvtBeast.size()>0) {
			while(! super.map.moveBeast(mvtBeast.get(new Random().nextInt(mvtBeast.size()))));
			super.map.setBeastWalk();
			super.checkGameStatus();
			return true;
		}
		else {
			AbstractGame.gameStatus=GameStatus.BEASTBLOCK;
			return false;
		}
	}
	
	
	public void poserPiege() {
		System.out.println("Vous avez encore " + this.map.getHunter().getTrapDispo() + " pieges dispo et " + this.map.getHunter().getWardDispo() + " balises dispo.");
		String choix=Interaction.askBonus();
		if(choix.equals("1")) {
			this.setTrap();
		}
		else if (choix.equals("2")) {
			this.setWard();
		}
		else {
			return;
		}
	}
	
	public boolean setTrap() {
		if(this.map.getHunter().canSetTrap()) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBuff(new boolean[] {true,false,false,false});
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean setWard() {
		if(this.map.getHunter().canSetWard()) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBuff(new boolean[] {false,false,true,false});
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	/**
	 * checkGameStatus met a jour le status de la partie, INGAME lorsque la partie est toujours en cours, BEASTFOUND lorsque la bete a été trouvé par le chasseur,
	 * MAPDISCOVERED si la map a été entierrement decouverte par la bete et BEASTBLOCK lorsque la bete est bloqué.
	 */
	/*public void checkGameStatus() {
		AGame.gameStatus=GameStatus.INGAME;
		if(super.statusBeastFound()) {
			AGame.gameStatus=GameStatus.BEASTFOUND;
			super.map.setHunterWin(true);
		}
		else if(super.statusMapDiscovered()) {
			AGame.gameStatus=GameStatus.MAPDISCOVERED;
			super.map.setBeastWin(true);
		}
		else if(super.statusBeastblock()) {
			AGame.gameStatus=GameStatus.BEASTBLOCK;
			super.map.setHunterWin(true);
		}
	}*/
	
	/**
	 * statusBastFound retourne true si la bete a été trouvé par le chasseur.
	 */
	/*public boolean statusBeastFound() {
		return super.map.getBeast().isPosEnt(super.map.getHunter().getPos().getPosX(), super.map.getHunter().getPos().getPosY());
	}*/
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement exploré la map.
	 */
	/*public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<super.map.getTab().length; i++) {
			for (int j=0; j<super.map.getTab().length; j++) {
				
				if(super.map.getTab()[i][j].getCaseType()==CaseType.SOL && super.map.getTab()[i][j].getBeastWalk()==-1) 
					pasBeast=false;
				}
		}
		return pasBeast;
	}*/
	
	/**
	 * statusBeastblock retourne true si la bete se retrouve bloquee.
	 */
	/*public boolean statusBeastblock() {
		if(super.map.getBeast().getMvtEmptyCase(super.map.getTab()).isEmpty() ) {
			
			if(super.map.getBeast().teleportation()) {
				if(! this.tpBeast()) {
					return true;
				}
			}else {
				return true;
			}
			
		}
		return false;
	}*/
	
	/**
	 * AfficherBeastPas affiche le nombre de pas depuis le dernier passage de la bete sur la case courante du chasseur.
	 */
	public void afficherBeastPas() {
		if (super.map.getTab()[super.map.getHunter().getPos().getPosX()][super.map.getHunter().getPos().getPosY()].getBeastWalk()>-1) {
			System.out.println("La bete est passee par ici il y'a "+super.map.getTab()[super.map.getHunter().getPos().getPosX()][super.map.getHunter().getPos().getPosY()].getBeastWalk()+" tours.");
		}
	}
}
