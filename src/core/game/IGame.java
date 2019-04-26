package core.game;

public interface IGame {
	
	public void launchGame();
	public void checkGameStatus();
	public boolean statusBeastFound();
	public boolean statusMapDiscovered();
	public boolean statusBeastblock();
	public boolean beastTurn();
	public boolean hunterTurn();
	
}