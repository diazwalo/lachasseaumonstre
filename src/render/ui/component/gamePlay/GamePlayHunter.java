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
	public AbstractGame ag;
	
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
	}

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
		}else {
			buildScore();
			
			EndScreen es =new EndScreen(super.window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isHunterWin(), this.ag.map.getConfig(), "");
			return false;
		}
	}

	public boolean useBonus(IBonus bonus) {
		return false;
	}

	@Override
	public void next() {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			((GameHunter)ag).beastTurn();
			ag.checkBeastRevealed();
			ag.updateEndGame();
			super.refreshHunterView(map);
		}else {
			buildScore();
			
			EndScreen es =new EndScreen(window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isHunterWin(), this.ag.map.getConfig(), "");
		}
	}

	@Override
	public void buildScore()
	{
		Score s = new Score("Joueur", Curiosity.NAME);
		ag.saveScore(ScoreFile.HUNTER, s);
	}
}
