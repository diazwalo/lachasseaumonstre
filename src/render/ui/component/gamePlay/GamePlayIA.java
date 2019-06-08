package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameAI;
import core.game.GameBeast;
import core.game.GameHunter;
import core.game.GameStatus;
import map.AbstractMap;
import map.Mouvment;
import render.ui.view.EndScreen;

public class GamePlayIA extends AbstractGamePlay{
	public AbstractGame ag;
	//1 si c'est au tour du Chasseur et a 2 si c'est au tour de la Bete
	int entityTurn = 1;

	public GamePlayIA(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
		ag = new GameBeast(map);
		ag.map.setBeastWalk();
	}

	@Override
	public boolean play(Mouvment mouvment) {
		return false;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		if(entityTurn == 1) {
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
				((GameAI)ag).hunterTurn();
				ag.checkBeastRevealed();
				ag.updateEndGame();
				ag.checkGameStatus();
				super.refreshIAView(super.map);
			}else {
				EndScreen es =new EndScreen(window);
				es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig());
			}
			
		}else if(entityTurn == 2) {
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
				((GameAI)ag).beastTurn();
				ag.checkBeastRevealed();
				ag.updateEndGame();
				super.refreshIAView(map);
			}else {
				EndScreen es =new EndScreen(window);
				es.setEndScreen(AbstractGame.gameStatus, ag.map.isHunterWin(), this.ag.map.getConfig());
			}
			
		}
	}
	
}
