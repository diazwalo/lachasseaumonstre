package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import map.AbstractMap;
import map.Mouvment;

public class GamePlayBeast extends GameBoard{
	
	public GamePlayBeast(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
	}
	
	@Override
	public void play(Mouvment mouvment) {
		// TODO Auto-generated method stub
		
	}
}
