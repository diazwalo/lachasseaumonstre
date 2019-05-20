package core.game;

import java.util.List;
import java.util.Random;

import ai.algorithm.Curiosity;
import ai.graph.Graph;
import ai.util.NodeUtil;
import interaction.Interaction;
import map.*;
import render.bonus.Bait;
import render.bonus.IBonus;
import render.bonus.Trap;

/**
 * 
 * La classe GameHunter gere la partie lorsque que le joueur incarne le chasseur.
 * 
 */

public class GameHunter extends AbstractGame {
	
	public GameHunter(AbstractMap map) {
		super(map);
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloque, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		Graph graph = new Graph(super.map);
		Curiosity curiosity = new Curiosity(graph);
		//List<Position> path = curiosity.getPath(NodeUtil.formatNode(this.map.getBeast().getPos()), super.map);
		//System.out.println(path); // La liste path contient tout le chemin que la bete devra parcourir pendant le jeu.
				
		
		System.out.println(map.gameHunterToString()+"\n");
		
		this.map.getHunter().setBlinded();
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			// TEST
		
			System.out.println(this.map.getHunter().isBlinded());
			
			
			while( ! this.hunterTurn() ) {
				System.out.println("Mvt Invalide");
			}
			this.map.getHunter().decrementBlinded();
			
			System.out.println(map.gameHunterToString()+"\n");
			Interaction.pressEnter();
			
			if(! super.map.isHunterWin()) {
				this.beastTurn();
				ramasserBonusBeast();
				System.out.println(super.map.gameHunterToString());
				Interaction.pressEnter();
			}
			this.map.passTurnBonus();
		}
		this.EndGame();
	}
	
	/**
	  * hunterTurn retourne true lorsque le mouvement entr� par le joueur est valide et dans ce cas l'effectue. 
	  */
	public boolean hunterTurn() {
		boolean mvtValide=false;
		this.poserBonus();
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=super.map.moveHunter(mvt);
		
		super.checkGameStatus();
		super.ramasserBonusHunter();
		return mvtValide;
	}

	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 */
	public boolean beastTurn() {
		this.map.getBeast().setUnTrapped();
		IBonus bo=super.checkBeastTrapped();
		
		List<Mouvment> mvtBeast = super.map.getBeast().getMvtEmptyCase(super.map.getTab());

		if(this.map.getBeast().getTrapped()) {
			super.triggerBonus();
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
	public void poserBonus() {
		String inventory = this.map.getHunter().toStringInventory();
		if(inventory.length() != 0) {
			System.out.println(inventory);
			String choix=Interaction.askBonus("la Balise de Vision (1)", "le Piege (2) ");
			if(choix.equals("1")) {
				this.activateWard();
			}
			else if (choix.equals("2")) {
				this.activateTrap();
			}
			else {
				return;
			}
		}
	}
	
	
	/**
	 * Active le piege sur la case courante.
	 * Retourne faux si le joueur n'a plus de piege.
	 * @return boolean
	 */
	public boolean activateTrap() {
		if(this.map.getHunter().canSetTrap()) {
			Position posTrap = this.map.getHunter().getPos();
			IBonus trap = this.map.getHunter().takeTrap();
			trap.install(posTrap.getPosX(), posTrap.getPosY());
			this.map.addBonusActif(trap);
			return true;
		}
		return false;
	}

	/**
	 * Active une balsie de vision sur le case courante.
	 * Retourne faux si le joueur n'a plus de balise.
	 * @return boolean
	 */
	public boolean activateWard() {
		if(this.map.getHunter().canSetWard()) {
			Position posWard = this.map.getHunter().getPos();
			IBonus ward = this.map.getHunter().takeWard();
			ward.install(posWard.getPosX(), posWard.getPosY());
			this.map.addBonusActif(ward);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * le joueur acitve un leurre sur une case qui affiche un faux nombre de pas sur la case courante pour le chasseur qui disparait quand le chasseur passe dessus
	 * Retourne faux si le joueur n'a plus de camouflage
	 * @return boolean 
	 */
	public boolean acctivateBait() {
		Position posBait=this.map.getBeast().getPos();
		IBonus bait=this.map.getBeast().takeBait();
		bait.install(posBait.getPosX(), posBait.getPosY());
		this.map.addBonusActif(bait);
		
		//A voir si le traitement de la possibilite de poser le piege se ferai pas ici plutot que dans poserBonusBeast
		return true;
		
	}
	
	
	public void setBonusIA() {
		IBonus bonus=this.map.getBeast().takeFirstBonus();
		
		if (bonus !=null) {
			
			if(bonus instanceof Bait) {
				this.acctivateBait();
			}
			
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
