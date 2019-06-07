package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
import core.game.GameBeast;
import map.AbstractMap;
import map.Mouvment;

public class GamePlayIA extends AbstractGamePlay{
	public AbstractGame ag;
	
	public GamePlayIA(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
		ag = new GameBeast(map);
		ag.map.setBeastWalk();
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
