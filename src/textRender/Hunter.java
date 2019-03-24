package textRender;
/**
 * 
 * @author Quentin Prognon
 *
 */
public class Hunter implements Entity {
	private int posX;
	private int posY;
	
	public Hunter(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}
/**
 * Renvoie un boolean qui deternime si la bete est presente au coordonnées passer en paramètre,
 */
	public boolean estSurCase(int posX, int posY) {
		return this.posX==posX && this.posY==posY;
	}
	/**
	 * Renvoie la position en X du chasseur
	 * @see textRender.Entity#getPosY()
	 */
	public int getPosX() {
		return this.posX;
	}
	/**
	 * Renvoie la position en y du chasseur
	 * @see textRender.Entity#getPosY()
	 */
	public int getPosY() {
		return this.posY;
	}
	/**
	 * Edite la potion en X du chasseur
	 */
	public void setPosX(int posX) {
		this.posX=posX;
	}
	/**
	 * Edite la potion en X du chasseur
	 */
	public void setPosY(int posY) {
		this.posY=posY;
	}
	/**
	 * Renvois un tableau d'entier contenant les coordonées du chasseur sous la forme (coord x , coord y)
	 */
	public int[] position() {
		return new int[] {posX ,posY};
	}
	/**
	 * Renvoie un H
	 */
	public String toString() {
		return "H";
	}
	
}