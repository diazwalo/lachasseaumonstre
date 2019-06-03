package core.game;

import interaction.Interaction;
import map.AbstractMap;

public class GameMulti extends AbstractGame{

	public GameMulti(AbstractMap map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void launchGame() {
		// TODO Auto-generated method stub
		super.updateStartGame();
		this.aToiDeJouer("du Chasseur");
		System.out.println(map.gameHunterToString()+"\n");
		
		while(AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.hunterTurnPlayer();
			this.updateStartGame();
			this.endOfTurn(this.map.gameHunterToString());
			
			if(! super.map.isHunterWin() && ! super.map.isBeastWin()) {
				this.aToiDeJouer("de la Bete");
				System.out.println(map.gameBeastToString()+"\n");
				super.beastTurnPlayer();
				super.checkBeastRevealed();
				this.updateEndGame();
				this.endOfTurn(map.gameBeastToString());
			}
			this.aToiDeJouer("du Chasseur");
			System.out.println(map.gameHunterToString()+"\n");
		}
		this.endGame();
	}
	
	/**
	 * Affiche un message informant a qui s'en est de jouer selon le parametre
	 * @param entity
	 */
	private void aToiDeJouer(String entity) {
		Interaction.clearScreen();
		System.out.println("Au tour " + entity);
		super.pressEnter();
		Interaction.clearScreen();
	}
}
