package map;

import textRender.Beast;

public class Case {
	private CaseType caseType;
	private int beastPas;
	
	public Case(CaseType caseType, boolean posBeast) {
		this.caseType=caseType;
		if(posBeast) this.beastPas=0;
		else this.beastPas=-1;
	}
	
	public CaseType getCaseType() {
		return this.caseType;
	}
	
	public int getBeastPas() {
		return this.beastPas;
	}
	
	public void setBeastPas(int beastPas) {
		this.beastPas=beastPas;
	}
}