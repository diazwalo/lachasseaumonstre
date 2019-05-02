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
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqu�, decouverte ou si elle a decouvert toute la map.
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
	  * hunterTurn retourne true lorsque le mouvement entr� par le joueur est valide et dans ce cas l'effectue. 
	  */
	public boolean hunterTurn() {
		boolean mvtValide=false;
		this.poserPiege();
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=super.map.moveHunter(mvt);
		
		super.checkGameStatus();
		this.map.getHunter().isBlinded();
		return mvtValide;
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 */
	public boolean beastTurn() {
		ArrayList<Mouvment> mvtBeast= super.map.getBeast().getMvtEmptyCase(super.map.getTab());

		if(this.map.getBeast().getTrapped()) {
			System.out.println("La bete est prise au piege !"); 
			this.map.getBeast().untrapped();
			return true;
		}
		else {
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
	}
	
	
	/**
	 * Propose au joueur d'utiliser un bonus au choix: un piege ou une balise de vision
	 */
	public void poserPiege() {
		System.out.println("Vous avez encore " + this.map.getHunter().getTrapDispo() + " pieges dispo et " + this.map.getHunter().getWardDispo() + " balises dispo.");
		System.out.println("Saisissez 1 pour poser un piege, 2 pour poser une balise et entrer si vous desirez ne rien poser");
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
	
	
	/**
	 * Pose le piege sur la case courante.
	 * Retourne faux si le joueur n'a plus de piege.
	 * @return boolean
	 */
	public boolean setTrap() {
		if(this.map.getHunter().canSetTrap()) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBonus(new boolean[] {true,false,false,false});
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Pose une balsie de vision sur le case courante.
	 * Retourne faux si le joueur n'a plus de balise.
	 * @return boolean
	 */
	public boolean setWard() {
		if(this.map.getHunter().canSetWard()) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBonus(new boolean[] {false,false,true,false});
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * AfficherBeastPas affiche le nombre de pas depuis le dernier passage de la bete sur la case courante du chasseur.
	 */
	public void afficherBeastPas() {
		if (super.map.getTab()[super.map.getHunter().getPos().getPosX()][super.map.getHunter().getPos().getPosY()].getBeastWalk()>-1) {
			System.out.println("La bete est passee par ici il y'a "+super.map.getTab()[super.map.getHunter().getPos().getPosX()][super.map.getHunter().getPos().getPosY()].getBeastWalk()+" tours.");
		}
	}
}
