package map;

import java.util.ArrayList;

import render.bonus.IBonus;
import render.text.Beast;
import render.text.Hunter;

public interface IMap {
	public void generationMap();
	public boolean mvtValideBeast(Mouvment mvt);
	public boolean mvtValideHunter(Mouvment mvt);
	public Hunter getHunter();
	public Case[][] getTab();
	public Beast getBeast();
	public boolean moveBeast(Mouvment mvt);
	public boolean moveHunter(Mouvment mvt);
	public void setBeastWalk();
	public boolean isBeastWin();
	public boolean isHunterWin();
	public void setHunterWin(boolean hunterWin);
	public void setBeastWin(boolean beastWin);
	public String gameBeastToString();
	public String gameHunterToString();
	public void setBonusActif(IBonus bonus);
	public ArrayList<IBonus> getBonusActif();
	public void removePiege();
	public void HunterIsNearBait();
}  