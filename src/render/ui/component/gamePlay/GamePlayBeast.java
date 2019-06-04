package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;

public class GamePlayBeast extends GameBoard{
	
	public GamePlayBeast(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
	}
	
	@Override
	public boolean play(Mouvment mouvment) {
		return false;
	}

	@Override
	public boolean useBonus(IBonus bonus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
}
