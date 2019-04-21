package core.game;

public enum GameStatus {
	INGAME("partie en cours"), FOUND("trouve par le chasseur"), DISCOVERED("la map a ete entierement decouverte par la bete"), ENEMYBLOCK("l adversaire est bloque");
	
	private String status;
	
	private GameStatus(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return this.status;
	}
}