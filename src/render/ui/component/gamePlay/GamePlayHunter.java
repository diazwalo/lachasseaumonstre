package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameHunter;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;

public class GamePlayHunter extends GameBoard
{
	private AbstractGame ag;
	
	public GamePlayHunter(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshHunterView(map);
		ag = new GameHunter(map);
		
	}
	
	public boolean play(Mouvment mouvment) {
		if(ag.map.moveHunter(mouvment)) {
			super.refreshHunterView(super.map);
			return true;
		}
		return false;
	}
	
	public boolean useBonus(IBonus bonus)
	{
		return false;
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

}
