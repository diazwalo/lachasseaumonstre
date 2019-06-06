package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameHunter;
import core.game.GameStatus;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.core.Window;
import render.ui.view.EndScreen;

public class GamePlayHunter extends GameBoard
{
	private AbstractGame ag;
	
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
	}

	public boolean play(Mouvment mouvment) {
		if(this.ag.gameStatus.equals(GameStatus.INGAME)) {
			if(ag.map.moveHunter(mouvment)) {
				ag.checkGameStatus();
				ag.ramasserBonusHunter();
				ag.updateStartGame();
				super.refreshHunterView(super.map);
				return true;
			}
			return false;
		}else {
			EndScreen es =new EndScreen(super.window);
			es.setEndScreen(ag.gameStatus, ag.map.isHunterWin());
			return false;
		}
	}

	public boolean useBonus(IBonus bonus) {
		return false;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		if(this.ag.gameStatus.equals(GameStatus.INGAME)) {
			((GameHunter)ag).beastTurn();
			ag.checkBeastRevealed();
			ag.updateEndGame();
			super.refreshHunterView(super.map);
		}else {
			EndScreen es =new EndScreen(window);
			es.setEndScreen(ag.gameStatus, ag.map.isHunterWin());
		}
	}

}
