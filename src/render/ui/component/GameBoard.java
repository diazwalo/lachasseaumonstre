package render.ui.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import map.AbstractMap;
import map.CaseType;
import render.ui.util.Directory;

public class GameBoard {
	
	GridPane grid = new GridPane();
	Image ground;
	Image obstacle;
	Image beast;
	Image hunter;
	
	public GameBoard(AbstractMap map) throws FileNotFoundException {
		FileInputStream is = new FileInputStream(new File(Directory.getGameGround()));
    	ground = new Image(is);
    is  = new FileInputStream(new File(Directory.getGameObstacle()));
    	obstacle = new Image(is);
    is  = new FileInputStream(new File(Directory.getGameBeast()));
    	beast = new Image(is);
    is  = new FileInputStream(new File(Directory.getGameHunter()));
    	hunter = new Image(is);
    
	
    for (int row = 0; row < (map.getTab().length); row++) {
        for (int col = 0; col < (map.getTab()[row].length); col++) {
    
                Rectangle rec = new Rectangle();
                rec.setWidth(50);
                rec.setHeight(50);
                
             
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

}
