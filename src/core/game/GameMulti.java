package core.game;

import map.AbstractMap;

public class GameMulti extends AbstractGame{

	public GameMulti(AbstractMap map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void launchGame() {
		// TODO Auto-generated method stub
		/*super.updateStartGame();
		System.out.println(map.gameHunterToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.hunterTurnPlayer();
			this.updateStartGame();
			this.endOfTurn();
			
			if(! super.map.isHunterWin() && ! super.map.isBeastWin()) {
				super.beastTurnPlayer();
				super.checkBeastRevealed();
				this.updateEndGame();
				this.endOfTurn();
			}
		}
		this.endGame();*/
	}

	@Override
	public void poserBonus() {
		// TODO Auto-generated method stub
		
	}
}
