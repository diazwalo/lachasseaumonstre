package map;

public enum CaseType {
	SOL(1), OBSTACLE(2);
	private int caseType;
	
	private CaseType(int typeCase) {
		this.caseType=typeCase;
	}
	
	public int getCaseType(){
		return this.caseType;
	}
}