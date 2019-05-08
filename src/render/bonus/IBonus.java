package render.bonus;

import map.Position;

public interface IBonus {
	public void install(int x , int y);
	public String getName();
	public void nextTurnBonus();
	public Position getPos();
	public void setDiscovered();
}
