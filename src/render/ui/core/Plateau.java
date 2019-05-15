package render.ui.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Plateau extends Application{
	public void start(Stage stage) {
		
		VBox root = new VBox();
		HBox mapAndBonus = new HBox();
		HBox buttonAndText = new HBox();
		VBox bonus = new VBox();
		VBox playButton = new VBox();
		HBox sidePlayButton = new HBox();
		HBox topsidePlayButton = new HBox();
		
		Button bonusActivate = new Button("Utiliser Bonus");
		TextField userCommunication = new TextField();
		TextField userBonus = new TextField();
		Button up = new Button("z");
		Button down = new Button("s");
		Button left = new Button("q");
		Button right = new Button("d");
		Label vide1 = new Label(" ");
		Label vide2 = new Label(" ");
		Label vide3 = new Label(" ");
		Label vide4 = new Label(" ");
		Label vide5 = new Label(" ");
		Label vide6 = new Label(" ");
		
		System.out.println(System.getProperty("user.dir") + "/IHM/Texture/texture.png");
	    Image img;
	    try(FileInputStream is = new FileInputStream(new File(System.getProperty("user.dir") + "/IHM/Texture/texture.png"))){
	    	img = new Image(is);
	    } catch (FileNotFoundException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		} catch (IOException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		}
		
	    GridPane grid = new GridPane();
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

	    
	    //up.setGraphic(new ImageView(img));
	    
	    
	    userBonus.setMinSize(150, 500);
	    userBonus.setEditable(false);
	    bonusActivate.setMinSize(150, 50);
	    vide3.setMinSize(30, 500);
	    vide4.setMinSize(1000, 30);
	    up.setMinSize(50, 50);
	    up.setMinSize(50, 50);
	    down.setMinSize(50, 50);
	    left.setMinSize(50, 50);
	    right.setMinSize(50, 50);
	    vide1.setMinSize(50, 50);
	    vide2.setMinSize(50, 50);
	    vide5.setMinSize(50, 50);
	    userCommunication.setMinSize(520, 100);
	    userCommunication.setEditable(false);
	    vide6.setMinSize(10, 10);
	    
	    root.getChildren().add(mapAndBonus);
	    root.getChildren().add(vide4);
	    root.getChildren().add(buttonAndText);
	    mapAndBonus.getChildren().add(grid);
	    mapAndBonus.getChildren().add(vide3);
	    mapAndBonus.getChildren().add(bonus);
	    bonus.getChildren().add(userBonus);
	    bonus.getChildren().add(bonusActivate);
	    buttonAndText.getChildren().add(playButton);
	    buttonAndText.getChildren().add(vide6);
	    buttonAndText.getChildren().add(userCommunication);
	    playButton.getChildren().add(topsidePlayButton);
	    playButton.getChildren().add(sidePlayButton);
	    topsidePlayButton.getChildren().add(vide1);
	    topsidePlayButton.getChildren().add(up);
	    topsidePlayButton.getChildren().add(vide2);
	    sidePlayButton.getChildren().add(left);
	    sidePlayButton.getChildren().add(down);
	    sidePlayButton.getChildren().add(right);
	    
	    
	    
	    Scene scene = new Scene(root, 750, 700);
	 
	    stage.setTitle(
	            "Grid");
	    stage.setScene(scene);
	 
	    stage.setResizable(false);
	    
	    stage.show();
	 
	}
	 
	private void textDisplay(GridPane grid, String theText, int row, int col) {
	    Text text = new Text();
	    text.setWrappingWidth(50);
	    text.setText(theText);
	    text.setTextAlignment(TextAlignment.CENTER);
	    GridPane.setRowIndex(text, row);
	    GridPane.setColumnIndex(text, col);
	    grid.getChildren().addAll(text);
	}
	
	 public static void main(String[] args) {
		    Application.launch(args);
		  }
	
	
}
