package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;

public class GamePlayMulti extends AbstractGamePlay{

	public GamePlayMulti(AbstractMap map) throws FileNotFoundException {
		super(map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean play(Mouvment mouvment) {
		return false;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	
}
