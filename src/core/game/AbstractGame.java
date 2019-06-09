package core.game;

import java.util.List;
import java.util.Random;

import data.score.Score;
import data.score.ScoreFile;
import data.score.ScoreManagement;
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

	public AbstractMap map;
	public static GameStatus gameStatus;
	
	protected int nbTurnEntityOne = 0;
	protected int nbTurnEntityTwo = 0;

	public AbstractGame(AbstractMap map) {
		this.map=map;
		AbstractGame.gameStatus=GameStatus.INGAME;
	}

	public abstract void launchGame();
	
	/**
	 * Affiche le statut si la bete a ete trouve par le chasseur.
	 * @return boolean 
	 */
	public boolean statusBeastFound() {
		return this.map.getBeast().isPosEnt(this.map.getHunter().getPos().getPosX(), this.map.getHunter().getPos().getPosY());
	}
	
	/**
	 * Affiche le statut si la bete a entierement explore la map.
	 * @return boolean
	 */
	public boolean statusMapDiscovered() {
		boolean pasBeast=true;
		for(int i=0; i<this.map.getTab().length; i++) {
			for (int j=0; j<this.map.getTab()[i].length; j++) {
				
				if(this.map.getTab()[i][j].getCaseType()==CaseType.SOL && this.map.getTab()[i][j].getBeastWalk()==-1) 
					pasBeast=false;
				}
		}
		return pasBeast;
	}
	
	/**
	 * Affiche le statut si la bete se retrouve bloquee.
	 * @return boolean
	 */
	public boolean statusBeastblock() {
		if(this.map.getBeast().getMvtToEmptyCase(this.map.getTab()).isEmpty()) {
			
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
	 * @return boolean
	 */
	public boolean tpBeast(){
		List<Position> posDispo = this.map.getBeast().getCaseTp(this.map.getTab(), this.map.getHunter());
		
		if(posDispo.size()>0) {
			Position posTpTempo = this.chosePosNotOnHunter(posDispo);
			this.map.getBeast().setPosition(posTpTempo);
			this.map.setBeastWalk();
			this.map.getBeast().setTp(this.map.getBeast().getTp()-1);
			return true;
		}
		return false;
	}

	/**
	 * Retourne une Position differente de celle du Chasseur vers laquel la Bete pourra se deplacer sauf si il n'y a que cette Position
	 * @param posDispo
	 * @return Position 
	 */
	public Position chosePosNotOnHunter(List<Position> posDispo) {
		int idxPosDispo = new Random().nextInt(posDispo.size());
		Position posTpTempo = posDispo.get(idxPosDispo);
		
		if(posDispo.size()>1) {
			while(posTpTempo.equals(this.map.getHunter().getPos())) {
				idxPosDispo=new Random().nextInt(posDispo.size());
				posTpTempo = posDispo.get(idxPosDispo);
			}
		}
		return posTpTempo;
	}
	
	/**
	 * Met le Bonus de la case ou se trouve la Bete dans son inventaire si un Bonus peut etre ramasse sur cette Case
	 */
	public void ramasserBonusBeast() {
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
	public void ramasserBonusHunter() {
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
	 * @return IBonus
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
	public void triggerTrap() {
    	for(IBonus b : this.map.getBonusActif()) {
    		if(b.getPos() != null && b.getPos().equals(this.map.getBeast().getPos()) && b instanceof Trap) {
    			b.setTriggered();
    		}
    	}
    }
	
	//TODO : Decouper checkBeastRevealed()
	
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
					for (Mouvment mouvment : this.map.getBeast().getMvtToNonObstacleCase(this.map.getTab())) {
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
		if(! beastStillUnderWard) {
			this.map.getBeast().setRevealedByWard(false);
		}
	}
	
	/**
	 * Affiche le gagnant ainsi les raison de la victoire
	 */
	public void endGame() {
		
		if (this.map.isBeastWin()) {
			System.out.println("Status: "+AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire de la bete");
		}
		else if(this.map.isHunterWin()){
			System.out.println("Status: "+AbstractGame.gameStatus.getStatus());
			System.out.println("Victoire du Chasseur");
		}
		
	}
	
	/**
	 * Retourne vrai si le Camouflage peut etre active et l'active, retourne faux sinon
	 * @return boolean
	 */
	public boolean activateCamouflage() {
		if(this.map.getBeast().canSetCamouflage()) {
			this.map.addBonusActif(this.map.getBeast().takeCamouflage());
			this.map.getHunter().setBlinded();
			return true;
		}
		return false;
	}
	
	/**
	 * Retourne vrai si le Leurre peut etre active et l'active, retourne faux sinon
	 * @return boolean
	 */
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
	
	/**
	 * Retourne vrai si le Piege peut etre active et l'active, retourne faux sinon
	 * @return boolean 
	 */
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
	
	/**
	 * Retourne vrai si la Balise peut etre activee, retourne faux sinon
	 * @return boolean 
	 */
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
	
	/**
	 * Prends un IBonus dans l'inventaire de l'IA (la Bete) puis l'active
	 */
	public void setBonusIABeast() {
		IBonus bonus=this.map.getBeast().takeFirstBonus();
		
		if (bonus != null) {
			if(bonus instanceof Bait) {
				this.activateBait();
			}else if(bonus instanceof Camouflage){
				this.activateCamouflage();
			}	
		}	
	}
	
	/**
	 * Prends un IBonusdans l'inventaire de l'IA (le Chasseur) puis l'active
	 */
	public void setBonusIAHunter() {
		IBonus bonus=this.map.getHunter().takeFirstBonus();
		
		if (bonus != null) {
			
			if(bonus instanceof Trap) {
				this.activateTrap();
			}
			else if(bonus instanceof Ward) {
				this.activateWard();
			}
		}
		
	}
	
	/**
	 * Retourne le Mouvement saisie par le Joueur
	 * @return Mouvement 
	 */
	public Mouvment askMouvement() {
		return Interaction.askMouvement();
	}

	/**
	 * Cette fonction attend que l'utilisateur saisisse le bouton clavier "enter"
	 */
	public void pressEnter() {
		Interaction.pressEnter();
	}
	
	/**
	 * Retourne le choix du Bonus du Joueur sous la forme d'une chaine de charactere ( Soit 1, soit 2, ou le reste 
	 * @param String Le nom du premier bonus.
	 * @param String Le nom du second bonus.
	 * @return String
	 */
	public String askBonus(String firstBonus, String secondBonus) {
		return Interaction.askBonus(firstBonus, secondBonus);
	}
	
	/**
	  * hunterTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue.
	  * @return boolean 
	  */
	public boolean hunterTurnPlayer() {
		boolean mvtValide=false;
		this.poserBonusHunter();
		do {
			Mouvment mvt=askMouvement();
			mvtValide=map.moveHunter(mvt);
			
		}while(! mvtValide);
		
		checkGameStatus();
		ramasserBonusHunter();
		
		this.nbTurnEntityOne++;
		return mvtValide;
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement entre par le joueur est valide et dans ce cas l'effectue.
	 * @return boolean
	 */
	public boolean beastTurnPlayer() {
		boolean mvtValide=false;

		IBonus bo=this.checkBeastTrapped();
		
		do {
			if(this.map.getBeast().getTrapped()) {
				System.out.println("Vous ne pouvez pas jouer ce tour ci car vous etes piege");
				this.map.getBeast().setUntrapped();
				if(bo != null) {
					this.map.removeBonus(bo);
				}
				mvtValide=true;
			}else {
				this.poserBonusBeast();
				Mouvment mvt=askMouvement();
				mvtValide=map.moveBeast(mvt);
				
				this.map.setBeastWalk();
				checkGameStatus();
				ramasserBonusBeast();
				
			}
		}while(! mvtValide);
		
		this.nbTurnEntityOne++;
		return mvtValide;
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
	 * Applique les mises a jours du plateau necessaires au debut de chaques tours
	 */
	public void updateStartGame() {
		this.map.getHunter().decrementBlinded();
		this.map.hunterIsNearBait();
	}
	
	/**
	 * Applique les mises a jours du plateau necessaires ï¿½ la fin de chaques tours
	 */
	public void updateEndGame() {
		this.map.passTurnBonus();
		checkBeastRevealed();
	}
	
	/**
	 * confirme la fin du tour au joueur
	 */
	public void endOfTurn(String affichage) {
		System.out.println(affichage+"\n");
		pressEnter();
	}
	
	/**
	 * Propose au joueur d'utiliser un bonus au choix: un piege ou une balise de vision
	 */
	public void poserBonusHunter() {
		String inventory = this.map.getHunter().toStringInventory();
		if(inventory.length() != 0) {
			String choix =this.askBonusChoiceHunter(inventory);
			this.bonusChoiceHunter(choix);
		}
	}
	
	/**
	 * Propose au joueur de choisir quel bonus de son inventaire il veut utiliser
	 * @param inventory
	 * @return String
	 */
	public String askBonusChoiceHunter(String inventory) {
		System.out.println(inventory);
		return this.askBonus("la Balise de Vision (1)", "le Piege (2) ");
	}
	
	/**
	 * Active le bonus mis en parametre
	 * @param s
	 */
	public void bonusChoiceHunter(String s) {
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
	
	/**
	 * Propose au joueur de poser un bonus au choix: un camouflage ou un leurre.
	 */
	public void poserBonusBeast() {
		String inventory = this.map.getBeast().toStringInventory();
		if(inventory.length() != 0) {
			String choix = this.askBonusChoiceBeast(inventory);
			this.bonusChoiceBeast(choix);
		}
	}
	
	/**
	 * Propose au joueur de choisir quel bonus de son inventaire il veut utiliser
	 * @param inventory
	 * @return String
	 */
	public String askBonusChoiceBeast(String inventory) {
		System.out.println(inventory);
		return this.askBonus("le Camouflage (1)", "le Leure (2) ");
	}
	
	/**
	 * Active le bonus mis en parametre
	 * @param s
	 */
	public void bonusChoiceBeast(String s) {
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
	
	public void incrementNbTurnEntityOne()
	{
		this.nbTurnEntityOne++;
	}
	
	public void incrementNbTurnEntityTwo()
	{
		this.nbTurnEntityTwo++;
	}
	
	/**
	 * Sauvegarde les scores de la partie de la partie en cours durablement.
	 * @param scoreFile Le fichier correspondant au type de la partie.
	 * @param s L'objet score contenant les informations a sauvegarder.
	 */
	public void saveScore(ScoreFile scoreFile, Score s)
	{
		ScoreManagement sm = new ScoreManagement();
		s.setNbMouvment1(nbTurnEntityOne);
		s.setNbMouvment2(nbTurnEntityTwo);
		s.setSize((this.map.getConfig().getWidth() * this.map.getConfig().getHeight()));
		
		sm.addScore(s);
		sm.saveScore(scoreFile);
	}
}
