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

public abstract class GameBoard {
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
	
	protected AbstractMap map;

	public Window window;

	public void setWindow(Window window)
	{
		this.window = window;
	}
	
	public GameBoard(AbstractMap map) throws FileNotFoundException {
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
		
	}

	public abstract boolean play(Mouvment mouvment);
	public abstract boolean useBonus(IBonus bonus);
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

	public void refreshHunterView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(50);
				rec.setHeight(50);

				Case caseCour = map.getTab()[col][row];
				Position posCase= new Position(col, row);

				this.paintRectangleHunterView(rec, caseCour, map, row, col, posCase);
				
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
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
			//afficher un ?
			rec.setFill(new ImagePattern(bonus));
		}

		if(caseCour.getBeastWalkNearHunter(map, posCase) != -1) {
			//afficher les pas de la beast
			rec.setFill(new ImagePattern(ground));
		}

		if(caseCour.getBeastNearHunter(map, posCase) != null) {
			//afficher la beast
			rec.setFill(new ImagePattern(beast));
		}

		if(caseCour.getHunterOnCase(map, posCase)) {
			//affiche le Hunter
			rec.setFill(new ImagePattern(hunter));
		}

		if(caseCour.getBeastIfRevealed(map, posCase) != null) {
			//affiche la Beast
			rec.setFill(new ImagePattern(beast));
		}

		if(caseCour.getEntityOnSameCase(map, posCase) != null) {
			// afficher une image du chasseur attrapant la bete ou juste le chasseur
			rec.setFill(new ImagePattern(hunter));
		}
	}

	public void refreshBeastView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(50);
				rec.setHeight(50);
				
				Case caseCour = map.getTab()[col][row];
				Position posCase= new Position(col, row);
				
				this.paintRectangleBeastView(rec, caseCour, map, row, col, posCase);
				
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				grid.getChildren().addAll(rec);
			}
		}
	}
	
	private void paintRectangleBeastView(Rectangle rec , Case caseCour , AbstractMap map, int row , int col, Position posCase) {
		if(caseCour.isObstacle()) {
			rec.setFill(new ImagePattern(obstacle));
		}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
			rec.setFill(new ImagePattern(ground));
		}
		if(caseCour.getBeastWalk()>=1) {
			//afficher une case avec des pas
			rec.setFill(new ImagePattern(tracePas));
		}
		
		if(caseCour.getHunterBonusActifOnCase(map, posCase) != null) {
			IBonus bonusActif = caseCour.getHunterBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Bait) {
				rec.setFill(new ImagePattern(bait));
			}
		}
		
		if(caseCour.bonusOnCase(caseCour.getBonusHunter())) {
			//afficher un ?
			rec.setFill(new ImagePattern(bonus));
		}
		
		if(caseCour.getBeastOnCaseBeastMode(map, posCase)) {
			rec.setFill(new ImagePattern(beast));
		}else if(caseCour.getHunterOnCase(map, posCase)) {
			rec.setFill(new ImagePattern(hunter));
		}
	}
	
	public Rectangle getNode (final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return (Rectangle)result;
	}

	public void setFog(AbstractMap map) {

		int[] huntPos = map.getHunter().getPos().getTabPosition();

		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Case caseCour = map.getTab()[row][col];
				if(huntPos[0] == row && huntPos[1] == col) {
					this.getNode(row, col, this.getGrid()).setFill(new ImagePattern(hunter));
				}else if(caseCour.isObstacle()){
					this.getNode(row, col, this.getGrid()).setFill(new ImagePattern(obstacle));
				}else {
					this.getNode(row, col, this.getGrid()).setFill(new ImagePattern(fog));
				}

			}
		}

		if(!map.getHunter().isBlinded()) {

			if(map.getBeast().getRevealedByWard()) {
				int[] beastPos = map.getBeast().getPos().getTabPosition();
				this.getNode(beastPos[0], beastPos[1], this.getGrid()).setFill(new ImagePattern(beast));
			}
			for(Mouvment m : map.getHunter().getMvtToEmptyCase(map.getTab())) {
				int[] mouv = m.getMvt();
				Rectangle rec  = this.getNode(huntPos[0]+mouv[0], huntPos[1]+mouv[1], this.getGrid());
				Case caseCour = map.getTab()[huntPos[0]+mouv[0]][huntPos[1]+mouv[1]];
				Position posCase= new Position(huntPos[0]+mouv[0], huntPos[1]+mouv[1]);

				this.paintRectangleBeastView(rec, caseCour, map, huntPos[0]+mouv[0], huntPos[1]+mouv[1], posCase);
			}
		}
	}
}