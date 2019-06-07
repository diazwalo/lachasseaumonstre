package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameBeast;
import core.game.GameStatus;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.view.EndScreen;

public class GamePlayBeast extends AbstractGamePlay{
	public AbstractGame ag;
	
	public GamePlayBeast(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
		ag = new GameBeast(map);
		ag.map.setBeastWalk();
	}
	
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
				
			ag.updateStartGame();
			super.refreshBeastView(super.map);
			return true;
		}else {
			EndScreen es =new EndScreen(super.window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig());
			return false;
		}
	}

	@Override
	public void next() {
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			((GameBeast)ag).hunterTurn();
			ag.checkBeastRevealed();
			ag.updateEndGame();
			ag.checkGameStatus();
			super.refreshBeastView(super.map);
		}else {
			EndScreen es =new EndScreen(window);
			es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig());
		}
	}
}
