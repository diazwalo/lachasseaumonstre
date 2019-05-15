package render.bonus;
 
import map.Position;
import render.text.Hunter;

/**
 * @author prognonq
 *
 */
public class Bait implements IBonus {
	private Position pos;
	private final String NAME = "leurre";
	private boolean used;
	private boolean discovered;
	private int count;
	
	
	/**
	 * Instancie Bait
	 */
	public Bait() {
		this.pos = null;
		this.used=false;
		this.discovered =false;
		this.count=0;
	}
	
	/**
	 * Instancie Bait et le place a la position dont l'abscice et l'ordonnee sont donnees en parametre
	 * @param posX
	 * @param posY
	 */
	public Bait(int posX, int posY) {
		this();
		this.pos=new Position(posX, posY);
		
	}
	
	/**
	 * retourne le nom du Bonus
	 */
	@Override
	public String getName() {
		return this.NAME;
	}

	/**
	 * place ce Bonus a la position d abscice x et d ordonnee y
	 */
	@Override
	public void install(int x, int y) {
		this.pos = new Position(x ,y);
	}
	
	/**
	 * passe le leurre a null si le chasseur passe dessus
	 */
	public void unInstall(Hunter h) {
		if(this.pos.equals(h.getPos())) {
			this.pos = null;
		}
	}
		
	/**
	 * retourne Beast sous la forme textuelle
	 */
	public String toString() {
		return "b";
	}

	/**
	 * retourne la position du leurre
	 * @return
	 */
	public Position getPos() {
		return pos;
	}
	
	/**
	 * retourne le nom du Bonus
	 * @return
	 */
	public String getNAME() {
		return NAME;
	}
	
	
	public boolean getUsed() {
		return this.used;
	}
	
	public void setUsed() {
		this.used =true;
	}

	public boolean getDiscovered() {
		return this.discovered;
	}
	
	public void setTriggered() {
		this.discovered=true;
	}
	
	@Override
	public void nextTurnBonus() {
		// TODO Auto-generated method stub
		if (this.discovered  && this.count<5) {
			this.count++;
		}
		else if(this.discovered && this.count>4) {
			this.setUsed();
		}
		
		if(getUsed()) {
			this.pos=null;
		}
		
	}
	
	

}
