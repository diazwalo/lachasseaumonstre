package map;

import textRender.Beast;
import textRender.Hunter;

public class Tray {
	private Case[][] tab;
	private Hunter hunter;
	private Beast beast;
	
	public Tray(int len, int wid) {
		this.hunter=new Hunter(0, 0);
		this.beast=new Beast(len-1, wid-1);
		this.tab=new Case[len][wid];
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[x].length; y++) {
				boolean posBeast=beast.getPosX()==x && beast.getPosY()==y;
				CaseType caseType=CaseType.SOL;
				if(x%3==2 && y%3==2) caseType=CaseType.OBSTACLE;
				this.tab[x][y]=new Case(caseType, posBeast);
			}
		}
	}

	public Case[][] getTab() {
		return tab;
	}

	public Hunter getHunter() {
		return hunter;
	}

	public Beast getBeast() {
		return beast;
	}
	
	public String toString() {
		String affichage="";
		for (int x = 0; x < tab.length; x++) {
			affichage+="\n|";
			for (int y = 0; y < tab[x].length; y++) {
				if(this.beast.estSurCase(x, y)) affichage+=" B |";
				else if(this.hunter.estSurCase(x, y)) affichage+=" H |";
				else if(this.tab[x][y].getCaseType().getCaseType()==1) affichage+="   |";
				else if(this.tab[x][y].getCaseType().getCaseType()==2) affichage+=" 0 |";
			}
		}
		return affichage;
	}
}