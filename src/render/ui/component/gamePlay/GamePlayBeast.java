package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import ai.algorithm.Dijkstra;
import core.game.AbstractGame;
import core.game.GameBeast;
import core.game.GameStatus;
import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.view.EndScreen;

public class GamePlayBeast extends AbstractGamePlay implements IScore{
	public AbstractGame ag;
	
	/**
	 * Instancie un GamePlayBeast;
	 * @param map
	 * @throws FileNotFoundException
	 */
	public GamePlayBeast(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
		ag = new GameBeast(map);
		ag.map.setBeastWalk();
	}
	
	/**
	 * Execute un tour de jeu. Renvoie true si le tour c'est passer comme prevue, renvoie false si c'est la fin de la partie
	 */
	@Override
	public boolean play(Mouvment mouvment) {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			IBonus bo=ag.checkBeastTrapped();
			if(super.map.getBeast().getTrapped()) {
				super.map.getBeast().setUntrapped();
				if(bo != null) {
					this.map.removeBonus(bo);
				}
				return true;
			}else if(ag.map.moveBeast(mouvment)) {
				this.map.setBeastWalk();
				ag.checkGameStatus();
				ag.ramasserBonusBeast();
			}else {
				return false;
			}
			ag.incrementNbTurnEntityOne();	
			ag.updateStartGame();
			super.refreshBeastView(super.map);
			return true;
		}else {
			buildScore();
			
			EndScreen es =new EndScreen(super.window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig(), null);
			return false;
		}
	}
	
	/**
	 * Fais passer la partie au tour suivant
	 */
	@Override
	public void next() {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			((GameBeast)ag).hunterTurn();
			ag.checkBeastRevealed();
			ag.updateEndGame();
			ag.checkGameStatus();
			super.refreshBeastView(super.map);
		}else {
			buildScore();
			
			EndScreen es =new EndScreen(window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig(), null);
		}
	}
	
	/**
	 * Crée un score et le sauvegarde
	 */
	@Override
	public void buildScore()
	{
		Score s = new Score("Joueur", Dijkstra.NAME);
		ag.saveScore(ScoreFile.BEAST, s);
	}
}
