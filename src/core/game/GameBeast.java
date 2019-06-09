package core.game;

import java.util.List;
import java.util.Random;

import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.*;
import render.bonus.IBonus;

/**
 * 
 * La classe GameBeast gere la partie lorsque que le joueur incarne la bete.
 * 
 */

public class GameBeast extends AbstractGame implements IScore{
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
		super.nbTurnEntityTwo++;
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
		buildScore();
	}
	
	public void buildScore()
	{
		Score s = new Score("Joueur", "IA Dijkstra");
		super.saveScore(ScoreFile.BEAST, s);
	}
}