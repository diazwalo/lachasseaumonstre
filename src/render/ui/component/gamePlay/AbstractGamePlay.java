package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import core.game.AbstractGame;
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

/**
 * Cette classe gere le deroulement de la partie lors d'un affichage visuelle. Elle gere le comportement du plateau de jeu et ordonne le tour par tour des entites selon leurs actions.
 */
public abstract class AbstractGamePlay {
	
	protected AbstractGame ag;
	protected AbstractMap map;
	
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
	protected Image beastTrap;

	public Window window;
	
	private final double size;

	/**
	 * Initialise la window de l'AbstractGame avec celle passe en parametre.
	 * @param window la fenetre de l'application
	 */
	public void setWindow(Window window)
	{
		this.window = window;
	}
	
	/**
	 * Instancie l'AbstractGamePlay grace a ses classes enfants.
	 * @param map Le plateau de jeu.
	 * @throws FileNotFoundException Une exception declanchee dans le cas ou il ne trouve pas le fichier.
	 */
	public AbstractGamePlay(AbstractMap map) throws FileNotFoundException {
		this.map = map;
		
		ground = new Image(Directory.GAME_GROUND);
		obstacle = new Image(Directory.GAME_OBSTACLE);
		beast = new Image(Directory.GAME_BEAST);
		hunter = new Image(Directory.GAME_HUNTER);
		bonus = new Image(Directory.GAME_BONUS);
		fog  = new Image(Directory.GAME_FOG);
		beastTrap = new Image(Directory.GAME_BEAST_TRAPPED);

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
	 * Intialise le PlayButton avec celui passe en parametre.
	 * @param pb les bouton de jeu
	 */
	public void setPlayButton(PlayButton pb) {
		this.playButton = pb;
	}

	/**
	 * Initialise l'Inventory avec celui passe en parametre.
	 * @param i l'inventaire permettant de voir les bonus
	 */
	public void setInventory(Inventory i) {
		this.inventory = i;
	}

	/**
	 * Retourne le GridPane d'AbstracrGame.
	 * @return GridPane le GridPane de la fenetre
	 */
	public GridPane getGrid() {
		return this.grid;
	}

	/**
	 * Configure la taille maximal du GridPane d'AbstractGame.
	 * @param maxWidth La largeur souhaite.
	 * @param maxHeight la longueur souhaite.
	 */
	public void setMaxSize(int maxWidth, int  maxHeight ) {
		grid.setMaxSize(maxWidth, maxHeight);
	}

	/**
	 * Configure la taille minimal du GridPane d'AbstractGame.
	 * @param minWidth La largeur souhaite
	 * @param minHeight La longueur souhaite
	 */
	public void setMinSize(int minWidth, int  minHeight ) {
		grid.setMinSize(minWidth, minHeight);
	}

	/**
	 * Configure la taille optimal du GridPane d'AbstractGame.
	 * @param prefWidth La largeur souhaite.
	 * @param prefHeight La longueur souhaite.
	 */
	public void setPrefSize(int prefWidth, int  prefHeight ) {
		grid.setPrefSize(prefWidth, prefHeight);
	}
	
	/**
	 * Adapte le GridPane pour qu'il soit conforme a la vision d'une intelligence artificielle.
	 * @param map Le plateau de jeu.
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
	 * Adapte le GridPane pour qu'il soit conforme a la vision du chasseur.
	 * @param map Le plateau de jeu.
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
	 * Adapte le GridPane pour qu'il soit conforme a la vision de la bete.
	 * @param map Le plateau de jeu.
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
	 * Adapte le GridPane pour qu'il soit adequat a la vue de transition.
	 * @param map Le plateau de jeu.
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
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue du chasseur.
	 * @param rec Le rectangle sur lequel appliquer une texture.
	 * @param caseCour La case ou appliquer la texture.
	 * @param map Le plateau de jeu.
	 * @param row La ligne sur laquelle est la case.
	 * @param col La colonne sur laquelle est la case.
	 * @param posCase La position de la case.
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
				if(map.getBeast().getTrapped()) {
					rec.setFill(new ImagePattern(beastTrap));
				}
				else {
					rec.setFill(new ImagePattern(trap));
				}
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
			if(map.getBeast().getTrapped()) {
				rec.setFill(new ImagePattern(beastTrap));
			}
			else {
				rec.setFill(new ImagePattern(beast));
			}
		}

		if(caseCour.getBeastIfRevealed(map, posCase) != null) {
			if(map.getBeast().getTrapped()) {
				rec.setFill(new ImagePattern(beastTrap));
			}
			else {
				rec.setFill(new ImagePattern(beast));
			}
		}
		
		if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	

	/**
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue de la bete.
	 * @param rec Le rectangle sur lequel appliquer une texture.
	 * @param caseCour La case ou appliquer la texture.
	 * @param map Le plateau de jeu.
	 * @param row La ligne sur laquelle est la case.
	 * @param col La colonne sur laquelle est la case.
	 * @param posCase La position de la case.
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
			if(map.getBeast().getTrapped()) {
				rec.setFill(new ImagePattern(beastTrap));
			}
			else {
				rec.setFill(new ImagePattern(beast));
			}
		}
		
		if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	
	/**
	 * Applique une texture a un rectangle en fonction de la case qu'il represente et de la vue de l'intelligence artificielle.
	 * @param rec Le rectangle sur lequel appliquer une texture.
	 * @param caseCour la case ou appliquer la texture.
	 * @param map La map de jeu.
	 * @param row La ligne sur laquelle est la case.
	 * @param col La colonne sur laquelle est la case.
	 * @param posCase La position de la case.
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
	 * Retourne le rectangle se trouvant a la case d'indice row, l'incide col dans le gridpane.
	 * @param row La ligne sur laquelle se trouve le rectangle.
	 * @param column La colonne ou se trouve le rectangle voulu.
	 * @param gridPane Le GridPane ou le rectangle doit se situer.
	 * @return Rectangle Le rectangle aux coordonnees rentrees en parametres.
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
	
	/**
	 * Retourne la game en cours
	 * @return La partie en cours.
	 */
	public AbstractGame getGame()
	{
		return this.ag;
	}
	
	/**
	 * Retourne Le plateau de jeu.
	 * @return Le plateau de jeu de la partie en cours.
	 */
	public AbstractMap getMap()
	{
		return this.map;
	}
}