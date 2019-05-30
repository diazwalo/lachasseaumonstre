package render.ui.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
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
import render.ui.core.Window;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class GameBoard {

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

	public GameBoard(Window window, AbstractMap map) throws FileNotFoundException {
		//TEST 
		/**
		 * pour voir si on peut afficher corectement les bonus actifs
		 */
		map.addBonusActif(new Trap(6, 7));
		map.addBonusActif(new Ward(7, 6));
		Bait baitTest = new Bait(1, 1);
		baitTest.setVisible(true);
		map.addBonusActif(baitTest);
		
		// TODO : tout ca est a deplacer dans les instances respective (beast, hunter, les diff bonus, l'enum CaseType)
		
		FileInputStream is = new FileInputStream(new File(Directory.getGameGround()));
		ground = new Image(is);
		is  = new FileInputStream(new File(Directory.getGameObstacle()));
		obstacle = new Image(is);
		is  = new FileInputStream(new File(Directory.getGameBeast()));
		beast = new Image(is);
		is  = new FileInputStream(new File(Directory.getGameHunter()));
		hunter = new Image(is);
		is = new FileInputStream(new File(Directory.getGameBonus()));
		bonus = new Image(is);
		
		is = new FileInputStream(new File(Directory.getGameTrap()));
		trap = new Image(is);
		is = new FileInputStream(new File(Directory.getGameWard()));
		ward = new Image(is);
		is = new FileInputStream(new File(Directory.getGameBait()));
		bait = new Image(is);
		
		is = new FileInputStream(new File(Directory.getGameTraceUn()));
		traceUn = new Image(is);
		is = new FileInputStream(new File(Directory.getGameTraceDeux()));
		traceDeux = new Image(is);
		is = new FileInputStream(new File(Directory.getGameTraceTrois()));
		traceTrois = new Image(is);
		is = new FileInputStream(new File(Directory.getGameTraceQuatre()));
		traceQuatre = new Image(is);


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
						if(i == cptBait) {
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

		Scene scene = new Scene(this.grid, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add("css/style.css");
		window.stage.setScene(scene);
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

}
