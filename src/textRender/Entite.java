package textRender;

public interface Entite {
	public int[] position();
	public int getPosY();
	public int getPosX();
	public boolean estSurCase(int posX, int posY);
}
