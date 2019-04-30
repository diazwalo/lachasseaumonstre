package core.game;

import java.util.ArrayList;
import java.util.Random;

import interaction.Interaction;
import map.Case;
import map.CaseType;
import map.IMap;
import map.Mouvment;
import map.Position;

/**
 * 
 * La classe GameBeast gere la partie lorsque que le joueur incarne la bete.
 * 
 */

public class GameBeast extends AbstractGame{
	
	public GameBeast(IMap map) {
		super(map);
	}
	
	/**
	 * LaunchGame lance la partie, celle-ci s'arrete lorsque la bete est bloqué, decouverte ou si elle a decouvert toute la map.
	 */
	public void launchGame() {
		System.out.println(map+" \n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			
			while(! this.beastTurn()) System.out.println("Mvt Invalide");
			
			System.out.println(this.map);
			Interaction.pressEnter();
			
			if(! super.map.isBeastWin()) {
				this.hunterTurn();
				System.out.println(super.map);
				Interaction.pressEnter();
			}
		}
		this.EndGame();
	}
	
	
	 /**
	  * beastTurn retourne true lorsque le mouvement entré par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean
	  */
	public boolean beastTurn() {
		boolean mvtValide=false;
		
		Mouvment mvt=Interaction.askMouvement();
		mvtValide=super.map.moveBeast(mvt);
		
		this.map.setBeastWalk();
		super.checkGameStatus();
		return mvtValide;
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
		
		return true;
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
	 * @return boolean
	 */
	public boolean statusBeastFound() {
		return super.map.getBeast().isPosEnt(super.map.getHunter().getPos().getPosX(), super.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement exploré la map.
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
}