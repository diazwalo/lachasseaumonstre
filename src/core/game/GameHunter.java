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
		this.updateStartGame();
		System.out.println(map.gameHunterToString()+"\n");
		
		//TEST
		this.map.addBonusActif(new Bait (2,1));
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
		
			
			
			/*while( ! this.hunterTurn() ) {
				System.out.println("Mvt Invalide");
			}
			this.map.getHunter().decrementBlinded();
			*/
			this.hunterTurn();
			this.updateStartGame();
			/*System.out.println(map.gameHunterToString()+"\n");
			Interaction.pressEnter();*/
			this.endOfTurn();
			
			
			if(! super.map.isHunterWin() && ! super.map.isBeastWin()) {
				this.beastTurn();
				//ramasserBonusBeast();
				super.checkBeastRevealed();
				this.updateEndGame();
				System.out.println(super.map.gameHunterToString());
				super.pressEnter();
			} 
			/*this.map.passTurnBonus();
			super.checkBeastRevealed();
			*/
			
			//this.updateEndGame();
		}
		this.endGame();
	}
	
	/**
	  * hunterTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue. 
	  */
	public boolean hunterTurn() {
		boolean mvtValide=false;
		this.poserBonus();
		 
		do {
			Mouvment mvt=super.askMouvement();
			mvtValide=super.map.moveHunter(mvt);
			
		}while(! mvtValide);
		
		super.checkGameStatus();
		super.ramasserBonusHunter();
		return mvtValide;
	}
	

	public void endOfTurn() {
		System.out.println(map.gameHunterToString()+"\n");
		super.pressEnter();
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 */
	public boolean beastTurn() {
		this.map.getBeast().setUntrapped();
		IBonus bo=super.checkBeastTrapped();
		
		List<Mouvment> mvtBeastDispo = super.map.getBeast().getMvtToEmptyCase(super.map.getTab());

		if(this.map.getBeast().getTrapped()) {
			super.triggerTrap();
			return true;
		}
		else {
			if(mvtBeastDispo.size()>0) {
				
				/*while(! super.map.moveBeast(mvtBeastDispo.get(new Random().nextInt(mvtBeastDispo.size()))));*/
				Mouvment mvtBeast = this.choseMvtNotOnHunter(mvtBeastDispo);
				while(! super.map.moveBeast(mvtBeast)) {
					mvtBeast = this.choseMvtNotOnHunter(mvtBeastDispo);
				}
				super.map.setBeastWalk();
				super.checkGameStatus();
				
				setBonusIABeast();
				
				ramasserBonusBeast();
				
				return true;
			}
			else {
				AbstractGame.gameStatus=GameStatus.BEASTBLOCK;
				return false;
			}
		}
	}
	
	public Mouvment choseMvtNotOnHunter(List<Mouvment> mvtBeast) {
		int idxPosDispo = new Random().nextInt(mvtBeast.size());
		Mouvment mvtTempo = mvtBeast.get(idxPosDispo);
		int[] posModif = this.map.getHunter().getPos().getModifPosTempo(mvtTempo.getMvt());
		Position posTpTempo = new Position(posModif[0], posModif[1]);
		
		if(mvtBeast.size()>1) {
			System.out.println("in");
			// le boolean n'est pas réevalué 
			// TODO : mettre a jour le boolean (ou plutot le mettre seulement dans la condition du while)
			boolean hunterPos = posTpTempo.equals(this.map.getHunter().getPos());
			while(hunterPos) {
				idxPosDispo=new Random().nextInt(mvtBeast.size());
				mvtTempo = mvtBeast.get(idxPosDispo);
			}
		}
		return mvtTempo;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void updateStartGame() {
		this.map.getHunter().decrementBlinded();
		this.map.hunterIsNearBait();
	
	}
	
	
	public void updateEndGame() {
		this.map.passTurnBonus();
		super.checkBeastRevealed();
	}
	
	/**
	 * Propose au joueur d'utiliser un bonus au choix: un piege ou une balise de vision
	 */
	public void poserBonus() {
		String inventory = this.map.getHunter().toStringInventory();
		if(inventory.length() != 0) {
			System.out.println(inventory);
			String choix=super.askBonus("la Balise de Vision (1)", "le Piege (2) ");
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
}
