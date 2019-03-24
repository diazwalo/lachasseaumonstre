package textRender;
/**
 * Cette classe definit les caract�ristique principale de la classe jouable Beast
 * @author Quentin Prognon
 *
 */
public class Beast implements Entity{
	private int posX;
	private int posY;
	
	public Beast(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
	}
/**
 * Renvoie un boolean qui deternime si la bete est presente au coordonn�es passer en param�tre,
 */
	public boolean estSurCase(int posX, int posY) {
		return this.posX==posX && this.posY==posY;
	}
	/**
	 * Renvoie la position en X de la Bete
	 * @see textRender.Entity#getPosY()
	 */
	public int getPosX() {
		return this.posX;
	}
	/**
	 * Renvoie la position en y de la Bete
	 * @see textRender.Entity#getPosY()
	 */
	public int getPosY() {
		return this.posY;
	}
	/**
	 * Edite la potion en X de la bete
	 */
	public void setPosX(int posX) {
		this.posX=posX;
	}
	/**
	 *  Edite la potion en Y la bete
	 */
	public void setPosY(int posY) {
		this.posY=posY;
	}
/**
 * Renvois un tableau d'entier contenant les coordon�es de la bete sous la forme (coord x , coord y)
 */
	public int[] position() {
		return new int[] {posX ,posY};
	}
	
	/**
	 * Renvoie un B
	 */
	public String toString() {
		return"B";
	}

}