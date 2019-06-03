package core.game;

import java.util.List;
import java.util.Random;

import map.*;
import render.bonus.IBonus;

/**
 * 
 * La classe GameBeast gere la partie lorsque que le joueur incarne la bete.
 * 
 */

public class GameBeast extends AbstractGame{
	private List<Position> pathHunter;
	
	public GameBeast(AbstractMap map) {
		super(map);
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloque, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		
		System.out.println(map.gameBeastToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			
			this.beastTurnPlayer();
			this.updateStartGame();
			
			this.endOfTurn(super.map.gameBeastToString());
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin()) {
				this.hunterTurn();
				this.map.getHunter().decrementBlinded();
				
				this.endOfTurn(super.map.gameBeastToString());
			}
			this.map.passTurnBonus();
			this.updateEndGame();
		}
		this.endGame();
	}
	
	/**
	 * Applique les mises a jours du plateau necessaires au debut de chaques tours
	 */
	public void updateStartGame() {
		this.map.getHunter().decrementBlinded();
		this.map.hunterIsNearBait();
	}

	/**
	 * hunterTurn retourne true lorsque le deplacement du chasseur est valide, c'est a dire si il ne va pas sur un obstacle. 
	 * @return boolean
	 */
	public boolean hunterTurn() {
		List<Mouvment> mvtHunter=super.map.getHunter().getMvtToEmptyCase(super.map.getTab());
		int idxMvt=0;
		
		if(mvtHunter.size()>0) {
			idxMvt=new Random().nextInt(mvtHunter.size()-1);
		}
		
		if(! this.map.getHunter().isBlinded()) {
		
			for (int i = 0; i < mvtHunter.size(); i++) {
				int[] modifPosTempo=super.map.getHunter().getPos().getModifPosTempo(mvtHunter.get(i).getMvt());
				if(super.map.getBeast().isPosEnt(modifPosTempo[0], modifPosTempo[1])) idxMvt=i;
			}
		}
		
		if(mvtHunter.size()>0) super.map.moveHunter(mvtHunter.get(idxMvt));
		super.checkGameStatus();
		
		setBonusIAHunter();
		
		super.ramasserBonusHunter();
		
		return true;
	}
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void endGame() {
		
		if (super.map.isBeastWin()) {
			System.out.println(AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire de la bete");
		}
		else if(super.map.isHunterWin()){
			System.out.println(AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire du Chasseur");
		}
	}
	
	
	/**
	 * Cherche si il existe une Position ou la bete peut etre tp et retourne vrai si cela est fait et faux dans le cas contraire
	 * @return boolean
	 */
	public boolean tpBeast(){
		List<Position> posDispo=super.map.getBeast().getCaseTp(super.map.getTab(), super.map.getHunter());
		
		boolean tp=false;
		int idxPosDispo;
		
		if(posDispo.size()>0) {
			idxPosDispo=new Random().nextInt(posDispo.size());
			super.map.getBeast().setPosition(posDispo.get(idxPosDispo));
			super.map.setBeastWalk();
			super.map.getBeast().setTp(super.map.getBeast().getTp()-1);
			tp=true;
		}
		return tp;
	}
	
	
	
	/**
	 * Propose au joueur de poser un bonus au choix: un camouflage ou un leurre.
	 */
	public void poserBonus() {
		String inventory = this.map.getBeast().toStringInventory();
		if(inventory.length() != 0) {
			String choix = this.askBonusChoice(inventory);
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
		return super.askBonus("le Camouflage (1)", "le Leure (2) ");
	}
	
	
	/**
	 * Active le bonus mis en parametre
	 * @param s
	 */
	public void bonusChoice(String s) {
		if(s.equals("1")) {
			this.activateCamouflage();
		}
		else if (s.equals("2")) {
			this.activateBait();
		}
		else {
			return;
		}
	}
	
}