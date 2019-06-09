package core.game;

public enum GameStatus {
	INGAME("La partie en cours"), 
	BEASTFOUND("Le chasseur a trouve la bete"), 
	MAPDISCOVERED("La map a ete decouverte"), 
	BEASTBLOCK("La bete est bloquee");
	
	private String status;
	
	private GameStatus(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return this.status;
	} 
} 