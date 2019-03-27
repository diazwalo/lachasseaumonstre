package render.text;

import map.Position;

public class Ward implements Bonus {
	
	private int radius;
	private Position pos;
	private final String NAME = "Balise";;
	
	public Ward() {
		this.radius = 3;
		this.pos = null;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Position getPos() {
		return pos;
	}
	
	public void install(int x , int y) {
		this.pos = new Position(x ,y);
	}

	public String getName() {
		return NAME;
	}
	
}
