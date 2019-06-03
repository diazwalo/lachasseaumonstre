package render.ui.component;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import map.AbstractMap;
import map.Case;
import map.CaseType;
import map.Position;
import render.bonus.Bait;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;
import render.ui.util.Directory;

public class GameBoard {
	HBox hBox = new HBox();
	GridPane grid = new GridPane();
	Image ground;
	Image obstacle;
	Image beast;
	Image hunter;
	Image bonus;
	Image trap;
	Image ward;
	Image bait;
	Image traceUn;
	Image traceDeux;
	Image traceTrois;
	Image traceQuatre;

	public GameBoard(AbstractMap map) throws FileNotFoundException {
		//TEST 
		/**
		 * pour voir si on peut afficher corectement les bonus actifs
		 */

		// TODO : tout ca est a deplacer dans les instances respective (beast, hunter, les diff bonus, l'enum CaseType)

		ground = new Image(Directory.GAME_GROUND);
		obstacle = new Image(Directory.GAME_OBSTACLE);
		beast = new Image(Directory.GAME_BEAST);
		hunter = new Image(Directory.GAME_HUNTER);
		bonus = new Image(Directory.GAME_BONUS);

		trap = new Image(Directory.GAME_TRAP);
		ward = new Image(Directory.GAME_WARD);
		bait = new Image(Directory.GAME_BAIT);

		traceUn = new Image(Directory.GAME_TRACE_UN);
		traceDeux = new Image(Directory.GAME_TRACE_DEUX);
		traceTrois = new Image(Directory.GAME_TRACE_TROIS);
		traceQuatre = new Image(Directory.GAME_TRACE_QUATRE);


		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {

				Rectangle rec = new Rectangle();
				rec.setWidth(50);
				rec.setHeight(50);

				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);

				/*
                if(map.getTab()[row][col].getCaseType().equals(CaseType.SOL)) {
                	rec.setFill(new ImagePattern(ground));
                }
                if(map.getTab()[row][col].getCaseType().equals(CaseType.OBSTACLE)) {
                	rec.setFill(new ImagePattern(obstacle));
                }
                if(map.getBeast().getPos().isPos(row, col)) {
                	rec.setFill(new ImagePattern(beast));
                }
                if(map.getHunter().getPos().isPos(row, col)) {
                	rec.setFill(new ImagePattern(hunter));
                }
				 */

				// Je ne sais pas si ca va bien la mais bon pour le test ca ira
				/**
				 * 
				 * TODO : OK DONC CA MARCHE SAUF QU IL FAUT FAIRE CA A CHAQUE TOUR DONC FAIRE UNE FCT QUI SOCCUPE QUE DE CA
				 * AH ET CA C EST LE FONCTIONNEMENT DE GAMEHUNTER DONC FAUDRA BIEN SEPARER POUR BEAST ET IA
				 * 
				 */

				//TEST
				if(caseCour.isObstacle()) {
					rec.setFill(new ImagePattern(obstacle));
				}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
					rec.setFill(new ImagePattern(ground));
				}

				if(caseCour.getHunterBonusActifOnCase(map, posCase) != null) {
					//afficher la texture du bonus actif
					IBonus bonusActif = caseCour.getHunterBonusActifOnCase(map, posCase);
					if(bonusActif instanceof Trap) {
						rec.setFill(new ImagePattern(trap));
					}else if(bonusActif instanceof Ward) {
						rec.setFill(new ImagePattern(ward));
					}
				}

				if(caseCour.getBaitOnCase(map, posCase) != null) {
					//rec.setFill(new ImagePattern(bait));
					// afficher le Bait
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
				if(map.getBeast().getPos().isPos(row, col)) {
					rec.setFill(new ImagePattern(beast));
				}

				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				grid.getChildren().addAll(rec);
			}
		}



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

				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);

				map.getHunter().getMvtToEmptyCase(map.getTab());

			}
		}	
	}



	private void paintRectangle(Rectangle rec , Case caseCour , AbstractMap map, int row , int col, Position posCase) {
		if(caseCour.isObstacle()) {
			rec.setFill(new ImagePattern(obstacle));
		}else if(caseCour.getCaseType().equals(CaseType.SOL)) {
			rec.setFill(new ImagePattern(ground));
		}

		if(caseCour.getHunterBonusActifOnCase(map, posCase) != null) {
			//afficher la texture du bonus actif
			IBonus bonusActif = caseCour.getHunterBonusActifOnCase(map, posCase);
			if(bonusActif instanceof Trap) {
				rec.setFill(new ImagePattern(trap));
			}else if(bonusActif instanceof Ward) {
				rec.setFill(new ImagePattern(ward));
			}
		}

		if(caseCour.getBaitOnCase(map, posCase) != null) {
			//rec.setFill(new ImagePattern(bait));
			// afficher le Bait
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
		if(map.getBeast().getPos().isPos(row, col)) {
			rec.setFill(new ImagePattern(beast));
		}

	}


	public void refreshBeastView(AbstractMap map) {
		for (int row = 0; row < (map.getTab().length); row++) {
			for (int col = 0; col < (map.getTab()[row].length); col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(50);
				rec.setHeight(50);

				
				Case caseCour = map.getTab()[row][col];
				Position posCase= new Position(row, col);
				this.paintRectangle(rec, caseCour, map, row, col, posCase);
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				grid.getChildren().addAll(rec);
			}
		}
	}
}