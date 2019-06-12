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

public class GamePlayMulti extends AbstractGamePlay implements IScore{
	int entityTurn;
	
	/**
	 * Instancie un GamePlayMulti;
	 * @param map la map de la partie en cours
	 * @throws FileNotFoundException exception se declachant si il ne trouve pas les texture voulu
	 */
	public GamePlayMulti(AbstractMap map) throws FileNotFoundException {
		super(map);
		this.entityTurn = 1;
		super.refreshTransitionView(map);
		ag= new GameMulti(map);
		ag.map.setBeastWalk();
	}

	/**
	 *  Execute un tour de jeu. Renvoie true si le tour c'est passer comme prevue, renvoie false si c'est la fin de la partie
	 */
	@Override
	public boolean play(Mouvment mouvment) {
		
		if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			
			if(entityTurn==0) {
				ag.updateStartGame();
				IBonus bo=ag.checkBeastTrapped();
				if(super.map.getBeast().getTrapped()) {
					
					if(bo != null) {
						this.ag.triggerTrap();
						this.map.removeBonus(bo);
						entityTurn=(entityTurn+1)%2;
						ag.updateEndGame();
						super.refreshBeastView(super.map);
					}
					
					return true;
				}else if(ag.map.moveBeast(mouvment)) {
					this.map.setBeastWalk();
					ag.checkGameStatus();
					ag.incrementNbTurnEntityTwo();
					ag.ramasserBonusBeast();
					entityTurn=(entityTurn+1)%2;
					ag.updateEndGame();
					
					bo=ag.checkBeastTrapped();
					if(super.map.getBeast().getTrapped()) {
						this.ag.triggerTrap();
					}
					
					super.refreshBeastView(super.map);
					return true;
				}
				
				//TEST
				ag.updateEndGame();
				
				return false;
			}
			
			else {
				//ag.updateEndGame();
				ag.updateStartGame();
				if(ag.map.moveHunter(mouvment)) {
					ag.checkGameStatus();
					ag.ramasserBonusHunter();
					ag.incrementNbTurnEntityOne();
					
					
					//TEST
					//ag.updateEndGame();
					
					super.refreshHunterView(map);
					entityTurn=(entityTurn+1)%2;
					
					if(super.map.getBeast().getTrapped()) {
						super.map.getBeast().setUntrapped();
					}
					
					return true;
				}
				return false;
			}

		}else {
			return false;
		}
}
	/**
	 * Fais passer la partie au tour suivant
	 */
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
	
	/**
	 * Cree un score et le sauvegarde
	 */
	@Override
	public void buildScore(String username)
	{
		Score s = new Score("Joueur 1", "Joueur 2");
		ag.saveScore(ScoreFile.MULTI, s);
	}
	
}
