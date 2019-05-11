package render.bonus;

import map.Position;

/**
 * @author prognonq
 *
 */
public class Trap implements IBonus {

	private boolean used;
	private Position pos;
	private final String NAME = "Piege";
	
	
	public Trap() {
		this.pos = null;
		this.used = false;
	}
	
	
	public Trap(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
	}


	public boolean getUsed() {
		return this.used;
	}
	
	public void setUsed() {
		this.used=true;
	}
	
	@Override
	public void install(int x, int y) {
		// TODO Auto-generated method stub
		this.pos.setPosX(x);
		this.pos.setPosY(y);
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.NAME;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}
	
	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		if(getUsed()) {
			this.pos=null;
		}
	}
	
	public String toString() {
		return "t";
	}


	@Override
	public void setDiscovered() {
		// TODO Auto-generated method stub
		
	}
	
}
