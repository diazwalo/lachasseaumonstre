package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameHunter;
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
		super.refreshBeastView(map);
		ag = new GameHunter(map);
	}
	
	@Override
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

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
}
