package textRender;

public class Beast {
	private int posX;
	private int posY;
	
	public Beast(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}
	
	public boolean posBeast(int posX, int posY) {
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
}