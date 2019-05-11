package core.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import interaction.Interaction;
import map.*;
import render.bonus.IBonus;

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
		List<Position> path = dijkstra.shortestPathFromTo("A", "B");
		//cf doc. La fonction renvoit la liste des positions pour se rendre de A a B. (A,B) sont des positions au format texte : cf ai.NodeUtil.formatNode()
		
		System.out.println(map.gameBeastToString()+" \n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			while(! this.beastTurn()) System.out.println("Mvt Invalide");
			this.map.getBeast().untrapped();
			System.out.println(this.map.gameBeastToString());
			Interaction.pressEnter();
			//this.checkPiege();
			if(! super.map.isBeastWin()) {
				this.hunterTurn();
				System.out.println(super.map.gameBeastToString());
				Interaction.pressEnter();
			}
			//this.checkPiege();
		}
		this.EndGame();
	}
	
	
	 /**
	  * beastTurn retourne true lorsque le mouvement entr� par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean
	  */
	public boolean beastTurn() {
		boolean mvtValide=false;
		if(this.map.getBeast().getTrapped()) {
			System.out.println("Vous ne pouvez pas jouer ce tour ci car vous etes piege");
			mvtValide=true;
			this.map.getBeast().untrapped();
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
		ArrayList<Mouvment> mvtHunter=super.map.getHunter().getMvtEmptyCase(super.map.getTab());
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
	 * statusBastFound retourne true si la bete a �t� trouv� par le chasseur.
	 * @return boolean
	 */
	public boolean statusBeastFound() {
		return super.map.getBeast().isPosEnt(super.map.getHunter().getPos().getPosX(), super.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement explor� la map.
	 * @return boolean
	 */
	public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<super.map.getTab().length; i++) {
			for (int j=0; j<super.map.getTab().length; j++) {
				if(super.map.getTab()[i][j].getCaseType().equals(CaseType.SOL) && super.map.getTab()[i][j].getBeastWalk()==-1) {
					pasBeast=false;
				}
			}
		}
		return pasBeast;
	}
	
	/**
	 * statusBeastblock retourne true si la bete se retrouve bloquee.
	 * @return boolean
	 */
	public boolean statusBeastblock() {
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
	}
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void EndGame() {
		
		if (super.map.isBeastWin()) {
			System.out.println(AbstractGame.gameStatus);
			System.out.println("Victoire de la bete");
		}
		else if(super.map.isHunterWin()){
			System.out.println(AbstractGame.gameStatus);
			System.out.println("Victoire du Chasseur");
		}
	}
	
	
	/**
	 * Cherche si il existe une Position ou la bete peut etre tp et retourne vrai si cela est fait et faux dans le cas contraire
	 * @return boolean
	 */
	public boolean tpBeast(){
		ArrayList<Position> posDispo=super.map.getBeast().getCaseTp(super.map.getTab(), super.map.getHunter());
		
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
		//System.out.println("Vous avez encore "  +" camouflage dispo et " + /*this.map.getBeast().getBaitDispo() +*/ " leurre dispo.");
		/*System.out.println("Saisissez 1 pour activer votre camouflage, 2 pour poser un leurre et entrer si vous desirez ne rien poser");
		
		if(choix.equals("1")) {
			this.setCamouflage();
		}
		else if (choix.equals("2")) {
			this.setBait();
		}
		else {
			return;
		}*/
		String inventory = this.map.getBeast().toStringInventory();
		if(inventory.length() != 0) {
			System.out.println(inventory);
			String choix=Interaction.askBonus("le Camouflage", "le Leure");
			if(choix.equals("1")) {
				this.setCamouflage();
				System.out.println("DEDAAAAAAAAAAAAANS");
			}
			else if (choix.equals("2")) {
				this.setBait();
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
	public boolean setCamouflage() {
		if(this.map.getBeast().canSetCamouflage()) {
			this.map.getHunter().setBlinded();
			this.map.getBeast().takeCamouflage();
			System.out.println(this.map.getBeast().canSetCamouflage());
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
	public boolean setBait() {
		if(this.map.getBeast().canSetBait()) {
			IBonus ib = this.map.getBeast().takeBait();
			ib.install(this.map.getBeast().getPos().getPosX(), this.map.getBeast().getPos().getPosY());
			this.map.setBonusActif(ib);
			
			return true;
		}
		else {
			return false;
		}
	}

	
}