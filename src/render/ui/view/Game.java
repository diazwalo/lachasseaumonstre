package render.ui.view;


import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.AbstractMap;
import render.ui.component.Chat;
import render.ui.component.Inventory;
import render.ui.component.gamePlay.GameBoard;
import render.ui.core.Window;
import render.ui.form.button.PlayButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Game {

	HBox top;
	HBox bottom;
	VBox core;
	
	PlayButton gamePad;
	Inventory inventaire;
	GameBoard plateau; 
	Chat chat;
	
	
	public Game(Window window, AbstractMap map, GameBoard gameType) throws FileNotFoundException {
		
		this.bottom = new HBox();
		this.top = new HBox();
		this.core = new VBox();
		
		this.gamePad = new PlayButton();
		this.inventaire = new Inventory();
		this.chat = new Chat();
		this.plateau = gameType;
		
		//Example : this.gamePad.getMouvment();
		Button nextTurn = new Button("Tour suivant");
		nextTurn.setOnAction(e -> {
			nextTurn.setDisable(true);
		});
		
		Button lol = new Button("Activer tour suivant");
		lol.setOnAction(e -> {
			nextTurn.setDisable(false);
		});
	
		
		this.core.getChildren().addAll(top , bottom);
		this.top.getChildren().addAll(plateau.getGrid(), inventaire.getCore(), nextTurn, lol);
		this.bottom.getChildren().addAll(gamePad.getCore());
		
		this.core.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
	}
}
