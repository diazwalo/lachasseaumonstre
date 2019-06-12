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

	/**
	 * Instancie un GamePlayBeast;
	 * @param map la map de la partie en cours
	 * @throws FileNotFoundException exception se declachant si il ne trouve pas les texture voulu
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
			bo=ag.checkBeastTrapped();
			if(super.map.getBeast().getTrapped()) {
				this.ag.triggerTrap();
			}
			super.refreshBeastView(super.map);
			return true;
		}
		return false;
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
		}
	}
	
	/**
	 * Cree un score et le sauvegarde
	 */
	@Override
	public void buildScore(String username)
	{
		Score s = new Score(username, Dijkstra.NAME);
		ag.saveScore(ScoreFile.BEAST, s);
	}
}
