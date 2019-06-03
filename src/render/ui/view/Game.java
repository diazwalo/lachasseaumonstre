package render.ui.view;


import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.AbstractMap;
import render.ui.component.Chat;
import render.ui.component.GameBoard;
import render.ui.component.Inventory;
import render.ui.core.Window;
import render.ui.form.button.PlayButton;
import render.ui.util.Interface;

public class Game {

	HBox top;
	HBox bottom;
	VBox core;
	
	PlayButton gamePad;
	Inventory inventaire;
	GameBoard plateau; 
	Chat chat;
	
	
	public Game(Window window, AbstractMap map) throws FileNotFoundException {
		
		this.bottom = new HBox();
		this.top = new HBox();
		this.core = new VBox();
		
		
		this.gamePad = new PlayButton();
		this.inventaire = new Inventory();
		this.chat = new Chat();
		this.plateau = new GameBoard(map);
		
		this.core.getChildren().addAll(top , bottom);
		this.top.getChildren().addAll(plateau.getGrid(), inventaire.getCore());
		this.bottom.getChildren().addAll(gamePad.getCore());
		
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add("css/style.css");
		window.stage.setScene(scene);
	}
}
