package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameMulti;
import core.game.GameStatus;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.view.EndScreen;

public class GamePlayMulti extends AbstractGamePlay{
	public AbstractGame ag;
	int entityTurn = 1;
	
	public GamePlayMulti(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshTransitionView(map);
		ag= new GameMulti(map);
		ag.map.setBeastWalk();
		
	}

	@Override
	public boolean play(Mouvment mouvment) {
	
			if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
				
				if(entityTurn==1) {
					entityTurn=(entityTurn+1)%2;
					
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
				}
				
				else {
					entityTurn=(entityTurn+1)%2;
					
					if(ag.map.moveHunter(mouvment)) {
						ag.checkGameStatus();
						ag.ramasserBonusHunter();
						
						ag.updateStartGame();
						super.refreshHunterView(map);
						return true;
					}
					return false;
				}

			}else {
				
				if(super.map.isBeastWin()) {
				
					EndScreen es =new EndScreen(super.window);
					es.setEndScreen(AbstractGame.gameStatus, ag.map.isBeastWin(), this.ag.map.getConfig(), " de la bete ");
				}
				else {
					
					EndScreen es =new EndScreen(super.window);
					es.setEndScreen(AbstractGame.gameStatus, ag.map.isHunterWin(), this.ag.map.getConfig(), " du chasseur ");
				}
				return false;
				
			}
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	
}
