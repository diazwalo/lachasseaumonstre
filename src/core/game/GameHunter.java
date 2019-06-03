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
		this.updateStartGame();
		System.out.println(map.gameHunterToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.hunterTurnPlayer();
			this.updateStartGame();
			this.endOfTurn(super.map.gameHunterToString());
			
			if(! super.map.isHunterWin() && ! super.map.isBeastWin()) {
				this.beastTurn();
				super.checkBeastRevealed();
				this.updateEndGame();
				this.endOfTurn(super.map.gameHunterToString());
			}
		}
		this.endGame();
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 * @return boolean
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
	
	/**
	 * Choisie un Mouvment qui ne correspond pas a la position du Chasseur
	 * @param mvtBeast
	 * @return Mouvment
	 */
	public Mouvment choseMvtNotOnHunter(List<Mouvment> mvtBeast) {
		int idxPosDispo = new Random().nextInt(mvtBeast.size());
		Mouvment mvtTempo = mvtBeast.get(idxPosDispo);
		int[] posModif = this.map.getBeast().getPos().getModifPosTempo(mvtTempo.getMvt());
		Position posMvtTempo = new Position(posModif[0], posModif[1]);
		
		if(mvtBeast.size()>1) {
			while(posMvtTempo.equals(this.map.getHunter().getPos())) {
				mvtTempo = mvtBeast.get(new Random().nextInt(mvtBeast.size()));
				posModif = this.map.getBeast().getPos().getModifPosTempo(mvtTempo.getMvt());
				posMvtTempo = new Position(posModif[0], posModif[1]);
			}
		}
		return mvtTempo;
	}
	
	/**
	 * Propose au joueur d'utiliser un bonus au choix: un piege ou une balise de vision
	 */
	public void poserBonus() {
		String inventory = this.map.getHunter().toStringInventory();
		if(inventory.length() != 0) {
			String choix =this.askBonusChoice(inventory);
			this.bonusChoice(choix);
		}
	}
	
	/**
	 * Propose au joueur de choisir quel bonus de son inventaire il veut utiliser
	 * @param inventory
	 * @return String
	 */
	public String askBonusChoice(String inventory) {
		System.out.println(inventory);
		return super.askBonus("la Balise de Vision (1)", "le Piege (2) ");
	}
	
	
	/**
	 * Active le bonus mis en parametre
	 * @param s
	 */
	public void bonusChoice(String s) {
		if(s.equals("1")) {
			this.activateWard();
		}
		else if (s.equals("2")) {
			this.activateTrap();
		}
		else {
			return;
		}
	}
}
