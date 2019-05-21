package core.game;

import java.util.List;
import java.util.Random;

import ai.algorithm.Curiosity;
import ai.graph.Graph;
import ai.util.NodeUtil;
import interaction.Interaction;
import map.*;
import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;

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
		
		// TEST :
		this.map.addBonusActif(new Ward(9, 9));
		this.map.passTurnBonus();
		super.checkBeastRevealed();
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			// TEST : 
			for (int i = 0; i < this.map.getBonusActif().size(); i++) {
				System.out.println(this.map.getBonusActif().get(i));
			}
			
			while( ! this.hunterTurn() ) {
				System.out.println("Mvt Invalide");
			}
			this.map.getHunter().decrementBlinded();
			
			System.out.println(map.gameHunterToString()+"\n");
			Interaction.pressEnter();
			
			if(! super.map.isHunterWin()) {
				this.beastTurn();
				ramasserBonusBeast();
				super.checkBeastRevealed();
				System.out.println(super.map.gameHunterToString());
				Interaction.pressEnter();
			}
			this.map.passTurnBonus();
			super.checkBeastRevealed();
		}
		this.EndGame();
	}
	
	/**
	  * hunterTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue. 
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
				
				setBonusIA();
				
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
	 * Prends un IBonusdans l'inventaire de l'IA (laa Bete) puis l'active
	 */
	public void setBonusIA() {
		IBonus bonus=this.map.getBeast().takeFirstBonus();
		
		if (bonus != null) {
			
			if(bonus instanceof Bait) {
				super.activateBait();
			}else if(bonus instanceof Camouflage){
				super.activateCamouflage();
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
