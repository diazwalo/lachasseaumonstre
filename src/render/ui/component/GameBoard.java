package render.ui.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameBoard {
	
	GridPane grid = new GridPane();
	Image img;
	
	public GameBoard() {
		try(FileInputStream is = new FileInputStream(new File(System.getProperty("user.dir") + "/IHM/Texture/texture.png"))){
	    	img = new Image(is);
	    } catch (FileNotFoundException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		} catch (IOException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		}
		
	    int i = 10;
	 
	    for (int row = 0; row < (i + 1); row++) {
	        for (int col = 0; col < (i + 1); col++) {
	    
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

}
