package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameHunter;
import core.game.GameStatus;
import map.AbstractMap;

public class GamePlayHunter extends GameBoard{
	public AbstractGame ag;
	
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
	}
	
	public void launchGame() {
		while(ag.gameStatus.equals(GameStatus.INGAME)) {
			/*this.hunterTurnPlayer();
			this.updateStartGame();
			this.endOfTurn(super.map.gameHunterToString());
			
			if(! super.map.isHunterWin() && ! super.map.isBeastWin()) {
				this.beastTurn();
				super.checkBeastRevealed();
				this.updateEndGame();
				this.endOfTurn(super.map.gameHunterToString());
			}*/
		}
		ag.endGame();
	}
}
