package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import map.AbstractMap;

public class GamePlayBeast extends GameBoard{
	
	public GamePlayBeast(AbstractMap map) throws FileNotFoundException {
		super(map);
		super.refreshBeastView(map);
	}
	
	public void launchGame() {
		
	}
}
