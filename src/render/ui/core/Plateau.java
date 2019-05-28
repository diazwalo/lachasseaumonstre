package render.ui.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import config.Config;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import map.SquareMap;
import render.ui.component.GameBoard;
import render.ui.component.Home;
import render.ui.component.Inventory;
import render.ui.form.button.PlayButton;
import render.ui.util.Directory;

public class Plateau extends Application{
	public void start(Stage stage) throws FileNotFoundException {
	
		VBox root = new VBox();
		
		Home home = new Home();
		root.getChildren().add(home.getCore());
		
	  /*HBox mapAndBonus = new HBox();
		VBox bonus = new VBox();
		HBox gameButtonAndChat = new HBox();
		
		GameBoard board = new GameBoard(new SquareMap(new Config()));
		Inventory inventaire = new Inventory();
		
		PlayButton playButton = new PlayButton();
		TextField chat = new TextField();
		
	    chat.setMinSize(520, 100);
	    chat.setEditable(false);
	    
	    root.getChildren().add(mapAndBonus);
	    root.getChildren().add(gameButtonAndChat);
	    mapAndBonus.getChildren().add(board.getGrid());
	    mapAndBonus.getChildren().add(inventaire.getCore());
	    gameButtonAndChat.getChildren().add(playButton.getCore());
	    gameButtonAndChat.getChildren().add(chat);*/
	    
	    
		Scene scene = new Scene(root, 750, 700);
		 
	    scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Press+Start+2P");
	    scene.getStylesheets().add("css/style.css");
	    
	    stage.setTitle("La chasse aux monstres");
	    stage.getIcons().add(new Image("icons/icon.png"));
	    stage.setScene(scene);
	    stage.setMaximized(true);
	    stage.setResizable(true);
	    stage.show();
	 
	}
	
	/*private void textDisplay(GridPane grid, String theText, int row, int col) {
	    Text text = new Text();
	    text.setWrappingWidth(50);
	    text.setText(theText);
	    text.setTextAlignment(TextAlignment.CENTER);
	    GridPane.setRowIndex(text, row);
	    GridPane.setColumnIndex(text, col);
	    grid.getChildren().addAll(text);
	}*/
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
}
