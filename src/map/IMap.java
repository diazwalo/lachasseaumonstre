package map;

import render.text.Beast;
import render.text.Hunter;

public interface IMap {
	public void generationMap();
	public void testModifBuff();
	public boolean mvtValideBeast(Mouvment mvt);
	public boolean mvtValideHunter(Mouvment mvt);
	public Hunter getHunter();
	public Case[][] getTab();
	public Beast getBeast();
	public boolean moveBeast(Mouvment mvt);
	public boolean moveHunter(Mouvment mvt);
	public void setBeastPas();
}
