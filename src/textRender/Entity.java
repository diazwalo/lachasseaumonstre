package textRender;

public interface Entity {
	public int[] position();
	public int getPosY();
	public int getPosX();
	public boolean estSurCase(int posX, int posY);
}
