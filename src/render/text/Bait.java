package render.text;

import map.Position;

public class Bait implements Bonus {
	private Position pos;
	private final String NAME = "leurre";
	private boolean decouvert;
	
	public Bait() {
		this.decouvert = false;
		this.pos = null;
	}
	
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);

	}

	@Override
	public String getName() {
		return this.NAME;
	}

}
