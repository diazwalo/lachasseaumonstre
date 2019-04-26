package core.game;

public enum GameStatus {
	INGAME("la partie en cours"), BEASTFOUND("le chasseur a trouve la bete"), MAPDISCOVERED("la map a ete entierement decouverte par la bete"), BEASTBLOCK("la bete est bloquee");
	
	private String status;
	
	private GameStatus(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return this.status;
	} 
}