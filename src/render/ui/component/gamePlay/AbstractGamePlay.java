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

	public void setWindow(Window window)
	{
		this.window = window;
	}
	
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

	public void setPlayButton(PlayButton pb) {
		this.playButton = pb;
	}

	public void setInventory(Inventory i) {
		this.inventory = i;
	}

	public GridPane getGrid() {
		return this.grid;
	}

	public void setMaxSize(int maxWidth, int  maxHeight ) {
		grid.setMaxSize(maxWidth, maxHeight);
	}

	public void setMinSize(int minWidth, int  minHeight ) {
		grid.setMinSize(minWidth, minHeight);
	}

	public void setPrefSize(int prefWidth, int  prefHeight ) {
		grid.setPrefSize(prefWidth, prefHeight);
	}
	
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
	 * @param row
	 * @param column
	 * @param gridPane
	 * @return Rectangle
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