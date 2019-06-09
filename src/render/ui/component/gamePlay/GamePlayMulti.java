package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameMulti;
import core.game.GameStatus;
import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.view.EndScreen;

public class GamePlayMulti extends AbstractGamePlay implements IScore{
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
				
				if(entityTurn==0) {
					
					
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
						ag.incrementNbTurnEntityTwo();
						ag.ramasserBonusBeast();
						entityTurn=(entityTurn+1)%2;
					}else {
						return false;
					}
					super.refreshBeastView(super.map);
					ag.updateStartGame();
					
					return true;
				}
				
				else {

					if(ag.map.moveHunter(mouvment)) {
						ag.checkGameStatus();
						ag.ramasserBonusHunter();
						ag.incrementNbTurnEntityOne();
						
						ag.updateStartGame();
						
						super.refreshHunterView(map);
						entityTurn=(entityTurn+1)%2;
						return true;
					}
					return false;
				}

			}else {
				buildScore();
				
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
		
		super.refreshTransitionView(map);
	}
	
	
	public void setView(int playerTurn) {
		
		if(playerTurn==0) {
			super.refreshBeastView(super.map);
		}
		else {
			super.refreshHunterView(map);
		}
		
	}
	
	@Override
	public void buildScore()
	{
		Score s = new Score("Joueur 1", "Joueur 2");
		ag.saveScore(ScoreFile.MULTI, s);
	}
	
}
