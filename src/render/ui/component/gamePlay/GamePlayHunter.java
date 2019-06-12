package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import ai.algorithm.Curiosity;
import core.game.AbstractGame;
import core.game.GameHunter;
import core.game.GameStatus;
import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.view.EndScreen;

public class GamePlayHunter extends AbstractGamePlay implements IScore{	
	/**
	 * Instancie un GamePlayBeast;
	 * @param map la map de la partie en cours
	 * @throws FileNotFoundException exception se declachant si il ne trouve pas les texture voulu
	 */
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
	}
	
	/**
	 *  Execute un tour de jeu. Renvoie true si le tour c'est passer comme prevue, renvoie false si c'est la fin de la partie
	 *  @return boolean
	 */
	public boolean play(Mouvment mouvment) {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			if(ag.map.moveHunter(mouvment)) {
				ag.checkGameStatus();
				ag.ramasserBonusHunter();
				
				ag.incrementNbTurnEntityOne();
				ag.updateStartGame();
				super.refreshHunterView(map);
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Fais passer la partie au tour suivant
	 */
	@Override
	public void next() {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			if(super.map.getBeast().getTrapped()) {
				this.ag.triggerTrap();
			}
			((GameHunter)ag).beastTurn();
			ag.checkBeastRevealed();
			ag.updateEndGame();
			super.refreshHunterView(map);
		}
	}

	/**
	 * Cr�e un score et le sauvegarde
	 */
	@Override
	public void buildScore(String username)
	{
		Score s = new Score(username, Curiosity.NAME);
		ag.saveScore(ScoreFile.HUNTER, s);
	}
}
