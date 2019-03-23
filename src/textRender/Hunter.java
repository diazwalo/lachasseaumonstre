package textRender;

public class Hunter implements Entite {
	private int posX;
	private int posY;
	
	public Hunter(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}

	public boolean estSurCase(int posX, int posY) {
		return this.posX==posX && this.posY==posY;
	}
	
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
	
	public void setPosX(int posX) {
		this.posX=posX;
	}
	
	public void setPosY(int posY) {
		this.posY=posY;
	}
	
	public int[] position() {
		return new int[] {posX ,posY};
	}
	
	
}