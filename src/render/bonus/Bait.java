package render.bonus;

import map.Position;
import render.text.Hunter;

/**
 * Cette classe definit le comportement de l'un de nos quatre bonus, ici le leurre/Bait.
 *
 */
public class Bait implements IBonus {
	private Position pos;
	private final String NAME = "Leurre";
	private boolean used;
	private boolean discovered;
	private int count;
	private boolean isVisible;


	/**
	 * Instancie Bait
	 */
	public Bait() {
		this.pos = null;
		this.used=false;
		this.discovered =false;
		this.count=1;
		this.isVisible=false;
	}

	/**
	 * Instancie Bait et le place a la position dont l'abscisse et l'ordonnee sont donnees en parametre
	 * @param posX L'abscisse de la position de la balise.
	 * @param posY L'ordonnee de la position de la balise.
	 */
	public Bait(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);

	}

	/**
	 * Retourne le nom du Bonus
	 */
	@Override
	public String getName() {
		return this.NAME;
	}


	 
	/**
	 * Place ce bonus a la position de l'abscisse x et d ordonnee y
	 * @param x les coordonnées en x
	 * @param y les coordonnées en y
	 */
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}

	/**
	 * Desinstalle le bait si le chasseur se trouve dessus.
	 * @param h le chasseur de la partie.
	 */
	public void unInstall(Hunter h) {
		if(this.pos.equals(h.getPos())) {
			this.pos = null;
		}
	}

	/**
	 * Retourne "b" pour "beast" sous la forme textuelle.
	 */
	public String toString() {
		return "b";
	}

	/**
	 * Retourne la position du leurre.
	 * @return La position de ce bonus.
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Retourne le nom du Bonus.
	 * @return le nom du bonus.
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * Retourne le statut d'utilisation du bonus.
	 * @return Si le bonus a deja ete utilise
	 */
	public boolean getUsed() {
		return this.used;
	}

	/**
	 * Met a jour le statut du leurre concernant son utilisation.
	 */
	public void setUsed() {
		this.used =true;
	}

	/**
	 * Retourne le statut de la decouverte du bonus.
	 * @return Si le bonus a ete decouvert.
	 */
	public boolean getDiscovered() {
		return this.discovered;
	}

	/**
	 * Met a jour le statut de l'activation du piege.
	 */
	public void setTriggered() {
		this.discovered=true;
	}

	public int getCount() {
		return this.count;
	}

	public void setVisible(boolean vis) {
		this.isVisible=vis;
	}

	public boolean getVisible() {
		return this.isVisible;
	}

	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		if (this.discovered  && this.count<4) {
			this.count++;
		}
		else if(this.discovered && this.count>3) {
			this.setUsed();
		}

		if(getUsed()) {
			this.pos=null;
		}

	}
}
