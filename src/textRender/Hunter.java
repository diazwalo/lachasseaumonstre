package textRender;

import map.Mouvment;
import java.util.Scanner;
/**
 * Cette classe definit les caractéristique principale de la classe jouable Hunter
 * @author Quentin Prognon
 *
 */
public class Hunter implements Entity {
	public static Scanner sch = new Scanner(System.in);
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
	
	public Mouvment askMouvement() {
        int hor = 0;
        int vert = 0;
        String input = sch.nextLine();
        if(input != null && input.length()<3 && (input.charAt(0) == 'z' || input.charAt(0) == 'q' )) {
            if(input.charAt(0) == 'z') {
                vert++;
            }else {
                vert--;
            }
            if(input.length() == 2 && (input.charAt(1)=='q' || input.charAt(1)=='d')){
                if(input.charAt(1) == 'q') {
                    hor--;
                }else {
                    hor++;
                }    
            }else {
                return null;
            }
            
            for (Mouvment m : Mouvment.values()) {
                if(m.getMvtX() == hor && m.getMvtY() == vert) {
                    return Mouvment.valueOf(m.name());
                }
            }
        }
        return null;
    }
	/**
	 * Renvoie un H
	 */
	public String toString() {
		return "H";
	}
	
}