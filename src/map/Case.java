package map;

import config.Config;

public class Case {
	private CaseType caseType;
	private int beastPas;
	private boolean hideToHunter;
	private boolean[] buff;
	
	public Case(CaseType caseType, boolean posBeast) {
		this(caseType, posBeast, new boolean[] {false, false, false, false});
	}
	
	public Case(CaseType caseType, boolean posBeast, boolean[] buff) {
		this.caseType=caseType;
		if(posBeast) this.beastPas=0;
		else this.beastPas=-1;
		this.hideToHunter=false;
		this.buff=buff;
	}
	
	public CaseType getCaseType() {
		return this.caseType;
	}
	
	public int getBeastPas() {
		return this.beastPas;
	}
	
	public boolean getHideToHunter() {
		return this.hideToHunter;
	}
	
	public boolean[] getBuff() {
		return this.buff;
	}
	
	public void setBeastPas(int beastPas) {
		this.beastPas=beastPas;
	}
	
	public void setHideToHunter() {
		this.hideToHunter=true;
	}
	
	public void setBuff(boolean[] buff) {
		this.buff=buff;
	}
	
	public String toString() {
		String res=caseType.toString();
		String[] toStringConf=new String[] {"p", "c", "w", "l"};
		for (int i = 0; i < buff.length; i++) {
			if(buff[i]) res=toStringConf[i];
		}
		return res;
	}
}