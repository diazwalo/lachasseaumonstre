package core.game;

public interface IGame {
	
	public void launchGame();
	public void checkGameStatus();
	public boolean statusFound();
	public boolean statusDiscovered();
	public boolean statusEnemyblock();
	public boolean beastTurn();
	public boolean hunterTurn();
	
}