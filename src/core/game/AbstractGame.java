package core.game;

import java.util.ArrayList;
import java.util.Random;

import map.CaseType;
import map.IMap;
import map.Position;

public abstract class AbstractGame {

	protected IMap map;
	protected static GameStatus gameStatus;
	
	public AbstractGame(IMap map) {
		this.map=map;
		AbstractGame.gameStatus=GameStatus.INGAME;
	}

	public abstract void launchGame();
	public abstract boolean beastTurn();
	public abstract boolean hunterTurn();
	public abstract void poserPiege();
	
	/**
	 * statusBastFound retourne true si la bete a �t� trouv� par le chasseur.
	 */
	public boolean statusBeastFound() {
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * statusMapDiscovered retourne true si la bete a entierement explor� la map.
	 */
	public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab().length; j++) {
				
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastWalk()==-1) 
					pasBeast=false;
				}
		}
		return pasBeast;
	}
	
	/**
	 * statusBeastblock retourne true si la bete se retrouve bloquee.
	 */
	public boolean statusBeastblock() {
		if(this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty() ) {
			
			if(this.map.getBeast().teleportation()) {
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
	 * checkGameStatus met a jour le status de la partie, INGAME lorsque la partie est toujours en cours, BEASTFOUND lorsque la bete a �t� trouv� par le chasseur,
	 * MAPDISCOVERED si la map a �t� entierrement decouverte par la bete et BEASTBLOCK lorsque la bete est bloqu�.
	 */
	public void checkGameStatus() {
		AbstractGame.gameStatus=GameStatus.INGAME;
		if(statusBeastFound()) {
			AbstractGame.gameStatus=GameStatus.BEASTFOUND;
			this.map.setHunterWin(true);
		}
		else if(statusMapDiscovered()) {
			AbstractGame.gameStatus=GameStatus.MAPDISCOVERED;
			this.map.setBeastWin(true);
		}
		else if(statusBeastblock()) {
			AbstractGame.gameStatus=GameStatus.BEASTBLOCK;
			this.map.setHunterWin(true);
		}
	}
	
	/**
	 * Cherche si il existe une Position ou la bete peut etre tp et retourne vrai si cela est fait et faux dans le cas contraire
	 * @return boolean
	 */
	public boolean tpBeast(){
		ArrayList<Position> posDispo=this.map.getBeast().getCaseTp(this.map.getTab(), this.map.getHunter());
		
		boolean tp=false;
		int idxPosDispo;
		
		if(posDispo.size()>0) {
			idxPosDispo=new Random().nextInt(posDispo.size());
			this.map.getBeast().setPosition(posDispo.get(idxPosDispo));
			this.map.setBeastWalk();
			this.map.getBeast().setTp(this.map.getBeast().getTp()-1);
			tp=true;
		}
		return tp;
	}
	
	/**
	 * Verifie si la bete ou le chasseur se trouve sur une case o� un piege est actif, l'active et l'enleve	
	 */
	public void checkPiege() {
		if(this.map.getTab()[this.map.getBeast().getPos().getPosX()][this.map.getBeast().getPos().getPosY()].getBuff().equals(new boolean[] {true,false,false,false})) {
			this.map.getBeast().setTrapped();
			this.map.getTab()[this.map.getBeast().getPos().getPosX()][this.map.getBeast().getPos().getPosY()].setBuff(new boolean[] {false,false,false,false});
		}
		if(this.map.getTab()[this.map.getBeast().getPos().getPosX()][this.map.getBeast().getPos().getPosY()].getBuff().equals(new boolean[] {false,false,true,false})) {
			this.map.getTab()[this.map.getBeast().getPos().getPosX()][this.map.getBeast().getPos().getPosY()].setBuff(new boolean[] {false,false,false,false});
		}
		/*if(this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBuff().equals(new boolean[] {false,true,false,false})) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBuff(new boolean[] {false,false,false,false});
		}*/
		if(this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].getBuff().equals(new boolean[] {false,false,false,true})) {
			this.map.getTab()[this.map.getHunter().getPos().getPosX()][this.map.getHunter().getPos().getPosY()].setBuff(new boolean[] {false,false,false,false});
		}
		
	}
	
	
	
	/**
	 * EndGame retourne true lorsque le chasseur ou la bete gagne et affiche le gagnant ainsi les raison de la victoire
	 */
	public void EndGame() {
		
		if (this.map.isBeastWin()) {
			System.out.println("Status: "+AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire de la bete");
		}
		else if(this.map.isHunterWin()){
			System.out.println("Status: "+AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire du Chasseur");
		}
		
	}
}
