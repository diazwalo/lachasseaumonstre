package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import map.AbstractMap;
import map.Case;
import map.CaseType;
import map.Mouvment;
import map.Position;
import render.bonus.Bait;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;
import render.ui.component.Inventory;
import render.ui.core.Window;
import render.ui.form.button.PlayButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public abstract class AbstractGamePlay {
	protected GridPane grid = new GridPane();
	protected Image ground;
	protected Image obstacle;
	protected Image beast;
	protected Image hunter;
	protected Image bonus;
	protected Image trap;
	protected Image ward;
	protected Image bait;
	protected Image camouflage;
	protected Image traceUn;
	protected Image traceDeux;
	protected Image traceTrois;
	protected Image traceQuatre;
	protected Image tracePas;
	protected PlayButton playButton;
	protected Inventory inventory;
	protected Image fog;
	
	public AbstractMap map;

	public Window window;
	
	private final double size;

	/**
	 * initialise la window de l'AbstractGame avec celle passer en parametre
	 * @param window la fenetre de l'application
	 */
	public void setWindow(Window window)
	{
		this.window = window;
	}
	
	/**
	 * Instancie l'AbstractGamePlay
	 * @param map la map de jeu
	 * @throws FileNotFoundException exception declancher dans le cas ou il ne trouve pas le fichier
	 */
	public AbstractGamePlay(AbstractMap map) throws FileNotFoundException {
		this.map = map;
		
		ground = new Image(Directory.GAME_GROUND);
		obstacle = new Image(Directory.GAME_OBSTACLE);
		beast = new Image(Directory.GAME_BEAST);
		hunter = new Image(Directory.GAME_HUNTER);
		bonus = new Image(Directory.GAME_BONUS);
		fog  = new Image(Directory.GAME_FOG);

		trap = new Image(Directory.GAME_TRAP);
		ward = new Image(Directory.GAME_WARD);
		bait = new Image(Directory.GAME_BAIT);

		traceUn = new Image(Directory.GAME_TRACE_ONE);
		traceDeux = new Image(Directory.GAME_TRACE_TWO);
		traceTrois = new Image(Directory.GAME_TRACE_THREE);
		traceQuatre = new Image(Directory.GAME_TRACE_FOUR);
		
		tracePas = new Image(Directory.GAME_TRACE_PAS);
		camouflage =new Image(Directory.GAME_CAMOUFLAGE);
		
		this.size = Interface.calculCaseSize(map.getTab().length, map.getTab()[0].length);
	}

	public abstract boolean play(Mouvment mouvment);
	public abstract void next();

	
	/**
	 * Intialise le PlayButton avec celui passer en parametres
	 * @param pb les bouton de jeu
	 */
	public void setPlayButton(PlayButton pb) {
		this.playButton = pb;
	}

	/**
	 * Initialise l'Inventory avec celui passer en parametres
	 * @param i l'inventaire permettant de voir les bonus
	 */
	public void setInventory(Inventory i) {
		this.inventory = i;
	}

	/**
	 * Retourne le GridPane d'AbstracrGame
	 * @return GridPane le GridPane de la fenetre
	 */
	public GridPane getGrid() {
		return this.grid;
	}

	/**
	 * Configure la taille maximal d'abstractGame
	 * @param maxWidth la largeur souhaiter
	 * @param maxHeight la longueur souhaiter
	 */
	public void setMaxSize(int maxWidth, int  maxHeight ) {
		grid.setMaxSize(maxWidth, maxHeight);
	}

	/**
	 * Configure la taille minimal d'abstractGame
	 * @param minWidth la largeur souhaiter
	 * @param minHeight  la longueur souhaiter
	 */
	public void setMinSize(int minWidth, int  minHeight ) {
		grid.setMinSize(minWidth, minHeight);
	}

	/**
	 * Configure la taille optimal d'abstractGame
	 * @param prefWidth la largeur souhaiter
	 * @param prefHeight la longueur souhaiter
	 */
	public void setPrefSize(int prefWidth, int  prefHeight ) {
		grid.setPrefSize(prefWidth, prefHeight);
	}
	
	/**
	 * Adapte le GridPane pour qu'il soit conforme a vision d'une inteligence artificielle
	 * @param map la map de jeu
	 */
	public void refreshIAView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(size);
				rec.setHeight(size);
				
				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);

				this.paintRectangleIAView(rec, caseCour, map, col, row, posCase);
				
				GridPane.setRowIndex(rec, col);
				GridPane.setColumnIndex(rec, row);
				grid.getChildren().addAll(rec);
				
			}
		}
	}

	/**
	 * Adapte le GridPane pour qu'il soit conforme a vision du chasseur
	 * @param map la map de jeu
	 */
	public void refreshHunterView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(size);
				rec.setHeight(size);
				
				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);

				this.paintRectangleHunterView(rec, caseCour, map, col, row, posCase);
				
				GridPane.setRowIndex(rec, col);
				GridPane.setColumnIndex(rec, row);
				grid.getChildren().addAll(rec);
				
			}
		}
	}
	
	/**
	 * Adapte le GridPane pour qu'il soit conforme a vision de la bete
	 * @param map la map de jeu
	 */
	public void refreshBeastView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(size);
				rec.setHeight(size);
				
				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);
				
				this.paintRectangleBeastView(rec, caseCour, map, col, row, posCase);
				
				GridPane.setRowIndex(rec, col);
				GridPane.setColumnIndex(rec, row);
				grid.getChildren().addAll(rec);
			}
		}
	}
	
	/**
	 * Adapte le GridPane pour qu'elles soit adequat � la vue de transition
	 * @param map la map de jeu
	 */
	public void refreshTransitionView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(size);
				rec.setHeight(size);
				
				this.paintTransitionView(rec);
				
				GridPane.setRowIndex(rec, col);
				GridPane.setColumnIndex(rec, row);
				grid.getChildren().addAll(rec);
			}
		}
	}

	/**
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue du chasseur
	 * @param rec le rectangle sur lequel appliquer une texture
	 * @param caseCour la case ou appliquer la texture
	 * @param map la map de jeu
	 * @param row la ligne sur laquelle est la case
	 * @param col la colonne sur laquelle est la case
	 * @param posCase la position de la case
	 */
	private void paintRectangleHunterView(Rectangle rec , Case caseCour , AbstractMap map, int row , int col, Position posCase) {
		if(caseCour.isObstacle()) {
			rec.setFill(new ImagePattern(obstacle));
		}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
			rec.setFill(new ImagePattern(ground));
		}
		
		if(caseCour.getFogOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(fog));
		}

		if(caseCour.getHunterBonusActifOnCase(map, posCase) != null) {
			IBonus bonusActif = caseCour.getHunterBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Trap) {
				rec.setFill(new ImagePattern(trap));
			}else if(bonusActif instanceof Ward) {
				rec.setFill(new ImagePattern(ward));
			}
		}

		if(caseCour.getBaitOnCase(map, posCase) != null) {
			Bait baitOnCase = (Bait) (caseCour.getBaitOnCase(map, posCase));
			int cptBait = baitOnCase.getCount();
			Image[] tabImage = new Image[] {traceUn, traceDeux, traceTrois, traceQuatre};
			int[] tabCptBait = new int[] {1, 2, 3, 4};
			for (int i = 0; i < tabCptBait.length; i++) {
				if(tabCptBait[i] == cptBait) {
					rec.setFill(new ImagePattern(tabImage[i]));
				}
			}
		}

		if(caseCour.bonusOnCase(caseCour.getBonusHunter())) {
			rec.setFill(new ImagePattern(bonus));
		}

		if(caseCour.getBeastWalkNearHunter(map, posCase) != -1) {
			int beastWalk = caseCour.getBeastWalkNearHunter(map, posCase);
			
			Image[] tabImage = new Image[] {traceUn, traceDeux, traceTrois, traceQuatre};
			int[] tabCptBait = new int[] {1, 2, 3, 4};
			for (int i = 0; i < tabCptBait.length; i++) {
				if(tabCptBait[i] == beastWalk) {
					rec.setFill(new ImagePattern(tabImage[i]));
				}
			}
		}

		if(caseCour.getBeastNearHunter(map, posCase) != null) {
			rec.setFill(new ImagePattern(beast));
		}

		if(caseCour.getBeastIfRevealed(map, posCase) != null) {
			rec.setFill(new ImagePattern(beast));
		}
		
		if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	

	/**
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue de la bete
	 * @param rec le rectangle sur lequel appliquer une texture
	 * @param caseCour la case ou appliquer la texture
	 * @param map la map de jeu
	 * @param row la ligne sur laquelle est la case
	 * @param col la colonne sur laquelle est la case
	 * @param posCase la position de la case
	 */
	private void paintRectangleBeastView(Rectangle rec , Case caseCour , AbstractMap map, int row , int col, Position posCase) {
		if(caseCour.isObstacle()) {
			rec.setFill(new ImagePattern(obstacle));
		}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
			rec.setFill(new ImagePattern(ground));
		}
		if(caseCour.getBeastWalk()>=1) {
			rec.setFill(new ImagePattern(tracePas));
		}
		
		if(caseCour.getBeastBonusActifOnCase(map, posCase) != null) {
			IBonus bonusActif = caseCour.getBeastBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Bait) {
				rec.setFill(new ImagePattern(bait));
			}
		}
		
		if(caseCour.bonusOnCase(caseCour.getBonusBeast())) {
			rec.setFill(new ImagePattern(bonus));
		}
		
		if(caseCour.getBeastOnCaseBeastMode(map, posCase)) {
			rec.setFill(new ImagePattern(beast));
		}
		
		if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	
	/**
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue de l'intelligence artificielle
	 * @param rec le rectangle sur lequel appliquer une texture
	 * @param caseCour la case ou appliquer la texture
	 * @param map la map de jeu
	 * @param row la ligne sur laquelle est la case
	 * @param col la colonne sur laquelle est la case
	 * @param posCase la position de la case
	 */
	public void paintRectangleIAView(Rectangle rec , Case caseCour , AbstractMap map, int row , int col, Position posCase) {
		if(caseCour.isObstacle()) {
			rec.setFill(new ImagePattern(obstacle));
		}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
			rec.setFill(new ImagePattern(ground));
		}
		if(caseCour.getBeastWalk()>=1) {
			rec.setFill(new ImagePattern(tracePas));
		}
		
		if(caseCour.getBeastBonusActifOnCase(map, posCase) != null) {
			IBonus bonusActif = caseCour.getBeastBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Bait) {
				rec.setFill(new ImagePattern(bait));
			}
		}
		
		if(caseCour.getHunterBonusActifOnCase(map, posCase) != null) {
			IBonus bonusActif = caseCour.getHunterBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Trap) {
				rec.setFill(new ImagePattern(trap));
			}else if(bonusActif instanceof Ward) {
				rec.setFill(new ImagePattern(ward));
			}
		}
		
		if(caseCour.bonusOnCase(caseCour.getBonusBeast())) {
			rec.setFill(new ImagePattern(bonus));
		}
		
		if(caseCour.bonusOnCase(caseCour.getBonusHunter())) {
			rec.setFill(new ImagePattern(bonus));
		}
		
		if(caseCour.getBeastOnCaseBeastMode(map, posCase)) {
			rec.setFill(new ImagePattern(beast));
		}
		
		if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	
	private void paintTransitionView(Rectangle rec) {
		rec.setFill(new ImagePattern(fog));
	}
	
	/**
	 * Retourne le rectangle se trouvant a la case d'indice row, col, dans le gridpane
	 * @param row la ligne sur laquel se trouve le rectangle
	 * @param column la colone ou se trouve le rectangle voulu
	 * @param gridPane le gridpane ou le rectangle doit ce situer
	 * @return Rectangle le rectangle au coordonne rentre en parametre
	 */
	public Rectangle getNode (final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if(GridPane.getRowIndex(node) == column && GridPane.getColumnIndex(node) == row) {
				result = node;
				break;
			}
		}

		return (Rectangle)result;
	}
}