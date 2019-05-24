package core.game;

import java.util.List;
import java.util.Random;

import interaction.Interaction;
import map.AbstractMap;
import map.CaseType;
import map.Mouvment;
import map.Position;
import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;

/**
 * Cette classe contient la base d'une partie qui sera instancie par une classe enfant.
 * Elle contient des methodes qui permettent de lier le plateau de jeu aux controles utilisateurs
 * ainsi que la gestion des bonus disponible pour tous les modes de jeux.
 *
 */
public abstract class AbstractGame {

	protected AbstractMap map;
	protected static GameStatus gameStatus;

	public AbstractGame(AbstractMap map) {
		this.map=map;
		AbstractGame.gameStatus=GameStatus.INGAME;
	}

	public abstract void launchGame();
	public abstract boolean beastTurn();
	public abstract boolean hunterTurn();
	public abstract void poserBonus();
	
	/**
	 * Affiche le statut si la bete a ete trouve par le chasseur.
	 * @return Si la bete a ete trouve par le chasseur.
	 */
	public boolean statusBeastFound() {
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * Affiche le statut si la bete a entierement explore la map.
	 * @return Si la bete a entierement explore la map.
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
	 * Affiche le statut si la bete se retrouve bloquee.
	 * @return Si la bete se retrouve bloquee.
	 */
	public boolean statusBeastblock() {
		if(this.map.getBeast().getMvtEmptyCase(this.map.getTab()).isEmpty()) {
			
			if(this.map.getBeast().isTpAvailable()) {
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
	 * Met a jour le status de la partie, 
	 * INGAME lorsque la partie est toujours en cours, 
	 * BEASTFOUND lorsque la bete a ete trouvee par le chasseur,
	 * MAPDISCOVERED si la map a ete entierrement decouverte par la bete
	 * BEASTBLOCK lorsque la bete est bloquee.
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
	 * Recherche si il existe une Position ou la bete peut etre tp.
	 * @return si une/des cases sont disponible a la teleportation.
	 */
	public boolean tpBeast(){
		List<Position> posDispo = this.map.getBeast().getCaseTp(this.map.getTab(), this.map.getHunter());
		
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
	 * Met le Bonus de la case ou se trouve la Bete dans son inventaire si un Bonus peut etre ramasse sur cette Case
	 */
	protected void ramasserBonusBeast() {
		Position posBeast = this.map.getBeast().getPos();
		boolean[] tabBeastBonus =this.map.getTab()[posBeast.getPosX()][posBeast.getPosY()].getBonusBeast();
		if(tabBeastBonus[0]) {
			this.map.getBeast().addToBaits(new Bait());
			this.map.getTab()[posBeast.getPosX()][posBeast.getPosY()].setBonusBeast(new boolean[] {false, false});
		}else if(tabBeastBonus[1]) {
			this.map.getBeast().addToCamouflages(new Camouflage());
			this.map.getTab()[posBeast.getPosX()][posBeast.getPosY()].setBonusBeast(new boolean[] {false, false});
		}
 	}
	
	/**
	 * Met le Bonus de la case ou se trouve le Chasseur dans son inventaire si un Bonus peut etre ramasse sur cette Case
	 */
	protected void ramasserBonusHunter() {
		Position posHunter = this.map.getHunter().getPos();
		boolean[] tabHunterBonus =this.map.getTab()[posHunter.getPosX()][posHunter.getPosY()].getBonusHunter();
		if(tabHunterBonus[0]) {
			this.map.getHunter().addToTraps(new Trap());
			this.map.getTab()[posHunter.getPosX()][posHunter.getPosY()].setBonusHunter(new boolean[] {false, false});
		}else if(tabHunterBonus[1]) {
			this.map.getHunter().addToWards(new Ward());
			this.map.getTab()[posHunter.getPosX()][posHunter.getPosY()].setBonusHunter(new boolean[] {false, false});
		}
 	}
	
	/**
	 * Prends la Bete au Piege si elle est sur un IBonus de type Piege
	 * @return Le piege qui a piege la bete.
	 */
	public IBonus checkBeastTrapped() {
		IBonus res = null;
		for(IBonus ib: this.map.getBonusActif()) {
			if(ib.getPos() != null && ib.getPos().equals(this.map.getBeast().getPos()) && ib  instanceof Trap) {
				this.map.getBeast().setTrapped();
				ib.setTriggered();
				res = ib;
			}
		}
		return res;
	}
	
	/**
	 * Declanche le Bonus si il est de Type Trap
	 */
	//A renomer
	public void triggerBonus() {
    	for(IBonus b : this.map.getBonusActif()) {
    		if(b.getPos() != null && b.getPos().equals(this.map.getBeast().getPos()) && b instanceof Trap) {
    			b.setTriggered();
    		}
    	}
    }
	
	/**
	 * Verifie si la bete est sous dans sur une case adjacente a la ward et passe revealed a true si cela s'avere vrai
	 */
	public void checkBeastRevealed() {
		boolean beastStillUnderWard = false;
		for(IBonus ib: this.map.getBonusActif()) {
			if(ib.getPos() != null && ib  instanceof Ward) {
				Position posAdjBeast = this.map.getBeast().getPos();
				if(ib.getPos().equals(posAdjBeast)) {
					this.map.getBeast().setRevealedByWard(true);
					ib.setTriggered();
					beastStillUnderWard = true;
				}else {
					for (Mouvment mouvment : this.map.getBeast().getMvtCase(this.map.getTab())) {
						int[] tabPosAdjBeast = posAdjBeast.getModifPosTempo(mouvment.getMvt());
						if(ib.getPos().equals(new Position(tabPosAdjBeast[0], tabPosAdjBeast[1]))) {
							this.map.getBeast().setRevealedByWard(true);
							ib.setTriggered();
							beastStillUnderWard = true;
						}
					}
				}
			}
		} 
		
		// TODO : trouver un autre moyen de le faire
		if(! beastStillUnderWard) {
			this.map.getBeast().setRevealedByWard(false);
		}
	}
	
	/**
	 * Affiche le gagnant ainsi les raison de la victoire
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
	
	// TEST
	
	public boolean activateCamouflage() {
		if(this.map.getBeast().canSetCamouflage()) {
			this.map.addBonusActif(this.map.getBeast().takeCamouflage());
			this.map.getHunter().setBlinded();
			return true;
		}
		return false;
	}
	
	public boolean activateBait() {
		if(this.map.getBeast().canSetBait()) {
			Position posBait = this.map.getBeast().getPos();
			IBonus bait = this.map.getBeast().takeBait();
			bait.install(posBait.getPosX(), posBait.getPosY());
			this.map.addBonusActif(bait);
			return true;
		}
		return false;
	} 
	
	public boolean activateTrap() {
		if(this.map.getHunter().canSetTrap()) {
			Position posTrap=this.map.getHunter().getPos();
			IBonus trap=this.map.getHunter().takeTrap();
			trap.install(posTrap.getPosX(), posTrap.getPosY());
			this.map.addBonusActif(trap);
			
			return true;
		}
		return false;
	}
	
	public boolean activateWard() {
		if(this.map.getHunter().canSetWard()) {
			Position posWard=this.map.getHunter().getPos();
			IBonus ward=this.map.getHunter().takeWard();
			ward.install(posWard.getPosX(), posWard.getPosY());
			this.map.addBonusActif(ward);
			
			return true;
		}
		return false;
	}
	
	public Mouvment askMouvement() {
		return Interaction.askMouvement();
	}

	public void pressEnter() {
		Interaction.pressEnter();
	}
	
	public String askBonus(String firstBonus, String secondBonus) {
		return Interaction.askBonus(firstBonus, secondBonus);
	}
}
