package core.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import interaction.Interaction;
import map.*;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;

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
			
			while(! this.beastTurn()) {
				System.out.println("Mvt Invalide");
			}
			
			System.out.println(this.map.gameBeastToString());
			Interaction.pressEnter();
			if(! super.map.isBeastWin()) {
				this.hunterTurn();
				this.map.getHunter().decrementBlinded();
				System.out.println(super.map.gameBeastToString());
				Interaction.pressEnter();
			}
			this.map.passTurnBonus();
			
		}
		this.EndGame();
	}
	
	
	 /**
	  * beastTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean
	  */
	public boolean beastTurn() {
		boolean mvtValide=false;
		this.map.getBeast().setUnTrapped();
		IBonus bo=super.checkBeastTrapped();
		
		if(this.map.getBeast().getTrapped()) {
			System.out.println("Vous ne pouvez pas jouer ce tour ci car vous etes piege");
			mvtValide=true;
			this.map.getBeast().setUnTrapped();
			if(bo != null) {
				this.map.removePiege(bo);
			}
			return mvtValide;
		}else {
			this.poserBonus();
			Mouvment mvt=Interaction.askMouvement();
			mvtValide=super.map.moveBeast(mvt);
			
			this.map.setBeastWalk();
			super.checkGameStatus();
			super.ramasserBonusBeast();
			return mvtValide;
		}
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
		
		for (int i = 0; i < mvtHunter.size(); i++) {
			int[] modifPosTempo=super.map.getHunter().getPos().getModifPosTempo(mvtHunter.get(i).getMvt());
			if(super.map.getBeast().isPosEnt(modifPosTempo[0], modifPosTempo[1])) idxMvt=i;
		}
		
		if(mvtHunter.size()>0) super.map.moveHunter(mvtHunter.get(idxMvt));
		super.checkGameStatus();
		
		super.ramasserBonusHunter();
		
		return true;
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
	 * propose au joueur de poser un bonus au choix: un camouflage ou un leurre.
	 */
	public void poserBonus() {
		String inventory = this.map.getBeast().toStringInventory();
		if(inventory.length() != 0) {
			System.out.println(inventory);
			String choix=Interaction.askBonus("le Camouflage (1)", "le Leure (2)");
			if(choix.equals("1")) {
				this.actuvateCamouflage();
			}
			else if (choix.equals("2")) {
				this.acctivateBait();
			}
			else {
				return;
			}
		}
	}
	
	/**
	 * Le joueur active sont camouflage, le chasseur ne peut plus voir les pas de la bete tant que le camouflage est actif.
	 * Retourne faux si le joueur n'a plus de camouflage
	 * @return boolean
	 */
	public boolean actuvateCamouflage() {
		if(this.map.getBeast().canSetCamouflage()) {
			this.map.addBonusActif(this.map.getBeast().takeCamouflage());
			this.map.getHunter().setBlinded();
			return true;
		}
		return false;
	}
	
	/**
	 * le joueur acitve un leurre sur une case qui affiche un faux nombre de pas sur la case courante pour le chasseur qui disparait quand le chasseur passe dessus
	 * Retourne faux si le joueur n'a plus de camouflage
	 * @return boolean 
	 */
	public boolean acctivateBait() {
		if(this.map.getBeast().canSetBait()) {
			Position posBait = this.map.getBeast().getPos();
			IBonus bait = this.map.getBeast().takeBait();
			bait.install(posBait.getPosX(), posBait.getPosY());
			this.map.addBonusActif(bait);
			return true;
		}
		return false;
	}

	
}