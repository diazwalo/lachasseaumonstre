package render.ui.core;

import config.Config;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import render.ui.view.Home;

public class Window extends Application
{
	public Stage stage;
	Config config = new Config();
	
	public void start(Stage stage) {
		this.stage = stage;
		
		new Home(this, config);
		
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

	    stage.setTitle("La chasse aux monstres");
	    stage.getIcons().add(new Image("icons/icon.png"));
	    stage.setMaximized(true);
	    stage.setResizable(false);
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
