package core.game;

import java.util.List;
import java.util.Random;

import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import interaction.Interaction;
import map.*;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;

/**
 * 
 * La classe GameBeast gere la partie lorsque que le joueur incarne la bete.
 * 
 */

public class GameBeast extends AbstractGame{
	
	public GameBeast(AbstractMap map) {
		super(map);
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqu�, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		Graph graph = new Graph(super.map);
		Dijkstra dijkstra = new Dijkstra(graph);
		
		//List<Position> path = dijkstra.shortestPathFromTo("A", "B");
		//cf doc. La fonction renvoit la liste des positions pour se rendre de A a B. (A,B) sont des positions au format texte : cf ai.NodeUtil.formatNode()
		
		System.out.println(map.gameBeastToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			
			/*while(! this.beastTurn()) {
				System.out.println("Mvt Invalide");
			}super.checkGameStatus();
			*/
			this.beastTurn();
			this.updateStartGame();
			/*System.out.println(this.map.gameBeastToString());
			Interaction.pressEnter();
			*/
			
			this.endOfTurn();
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin()) {
				this.hunterTurn();
				this.map.getHunter().decrementBlinded();
				System.out.println(super.map.gameBeastToString());
				super.pressEnter();
			}
			this.map.passTurnBonus();
			this.updateEndGame();
		}
		this.EndGame();
	}
	
	
	 /**
	  * beastTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean
	  */
	public boolean beastTurn() {
		boolean mvtValide=false;
	
		IBonus bo=super.checkBeastTrapped();
		
		do {
			if(this.map.getBeast().getTrapped()) {
				System.out.println("Vous ne pouvez pas jouer ce tour ci car vous etes piege");
				this.map.getBeast().setUnTrapped();
				if(bo != null) {
					this.map.removeBonus(bo);
				}
				mvtValide=true;
			}else {
				this.poserBonus();
				Mouvment mvt=super.askMouvement();
				mvtValide=super.map.moveBeast(mvt);
				
				this.map.setBeastWalk();
				super.checkGameStatus();
				super.ramasserBonusBeast();
				
			}
		}while(! mvtValide);
		
		return mvtValide;
	}
	
	public void updateStartGame() {
		this.map.getHunter().decrementBlinded();
		this.map.HunterIsNearBait();
	}
	
	public void updateEndGame() {
		this.map.passTurnBonus();
		super.checkBeastRevealed();
	}

	/**
	 * hunterTurn retourne true lorsque le deplacement du chasseur est valide, c'est a dire si il ne va pas sur un obstacle.
	 * @return boolean
	 */
	public boolean hunterTurn() {
		List<Mouvment> mvtHunter=super.map.getHunter().getMvtEmptyCase(super.map.getTab());
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
		
		setBonusIA();
		
		super.ramasserBonusHunter();
		
		return true;
	}
	
	public void endOfTurn() {
		
	}
	
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void EndGame() {
		
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
			System.out.println(inventory);
			String choix=Interaction.askBonus("le Camouflage (1)", "le Leure (2)");
			if(choix.equals("1")) {
				this.activateCamouflage();
			}
			else if (choix.equals("2")) {
				this.activateBait();
			}
			else {
				return;
			}
		}
	}
	
	/**
	 * Prends un IBonusdans l'inventaire de l'IA (le Chasseur) puis l'active
	 */
	public void setBonusIA() {
		IBonus bonus=this.map.getHunter().takeFirstBonus();
		
		if (bonus != null) {
			
			if(bonus instanceof Trap) {
				super.activateTrap();
			}
			else if(bonus instanceof Ward) {
				super.activateWard();
			}
		}
		
	}

	
}