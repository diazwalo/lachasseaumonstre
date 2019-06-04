package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameHunter;
import core.game.GameStatus;
import map.AbstractMap;
import map.Mouvment;

public class GamePlayHunter extends GameBoard{
	public AbstractGame ag;
	
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
		
	}
	
	public void launchGame() {
		while(ag.gameStatus.equals(GameStatus.INGAME)) {
			boolean mvtValide = false;
			while(! mvtValide) {
				Mouvment mvt = null;
				while(mvt == null) {
					mvt = super.playButton.getMouvment();
				}
				System.out.println(mvt);
				mvtValide = ag.map.moveHunter(mvt);
			}
			ag.checkGameStatus();
			ag.ramasserBonusHunter();
			super.refreshBeastView(ag.map);
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
