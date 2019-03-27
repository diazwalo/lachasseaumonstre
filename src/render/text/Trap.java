package render.text;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Trap implements Bonus {
	
	private Position pos;
	private final String NAME = "Piège";
	private boolean activate;
	
	public Trap() {
		this.pos = null;
		this.activate = false;
	}
	
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}

	@Override
	public String getName() {
		return this.NAME;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public Position getPos() {
		return pos;
	}
  
}
