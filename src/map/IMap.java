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
	public void moveBeast(Mouvment mvt);
	public void moveHunter(Mouvment mvt);
}
