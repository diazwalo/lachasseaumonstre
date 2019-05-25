package render.ui.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import render.ui.util.Directory;

public class GameBoard {
	
	GridPane grid = new GridPane();
	Image img;
	
	public GameBoard(int size) {
		try(FileInputStream is = new FileInputStream(new File(Directory.getGameGround()))){
	    	img = new Image(is);
	    } catch (FileNotFoundException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		} catch (IOException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		}
		
	    for (int row = 0; row < (size + 1); row++) {
	        for (int col = 0; col < (size + 1); col++) {
	    
	                Rectangle rec = new Rectangle();
	                rec.setWidth(50);
	                rec.setHeight(50);
	          
	                rec.setFill(new ImagePattern(img));
	               
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
