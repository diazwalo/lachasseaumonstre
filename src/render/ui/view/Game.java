package render.ui.view;


import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import map.AbstractMap;
import map.Mouvment;
import render.ui.component.Chat;
import render.ui.component.Inventory;
import render.ui.component.gamePlay.GameBoard;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
import render.ui.core.Window;
import render.ui.form.button.PlayButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Game {

	HBox top;
	HBox bottom;
	HBox core;
	
	PlayButton gamePad;
	Inventory inventaire;
	GameBoard plateau; 
	Chat chat;
	
	Button nextTurn;
	
	
	public Game(Window window, AbstractMap map, GameBoard gameType) throws FileNotFoundException {
		
		this.bottom = new HBox();
		this.top = new HBox();
		this.core = new HBox();
		
		this.gamePad = new PlayButton();
		
		this.gamePad.topBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.NORD)) {
				userAction(map);
			}
		});
		
		this.gamePad.upLeftBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.NORDOUEST)) {
				userAction(map);
			}
		});
		
		this.gamePad.upRightBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.NORDEST)) {
				userAction(map);
			}
		});
		
		this.gamePad.leftBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.OUEST)) {
				userAction(map);
			}
		});
		
		this.gamePad.rightBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.EST)) {
				userAction(map);
			}
		});
		
		this.gamePad.bottomBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.SUD)) {
				userAction(map);
			}
		});
		
		this.gamePad.bottomLeftBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.SUDOUEST)) {
				userAction(map);
			}
		});
		
		this.gamePad.bottomRightBtn.getBouton().setOnAction(e -> {
			if(gameType.play(Mouvment.SUDEST)) {
				userAction(map);
			}
		});
		
		if(gameType instanceof GamePlayHunter) {
			this.inventaire = new Inventory(map.getHunter());
		}else if(gameType instanceof GamePlayBeast) {
			this.inventaire = new Inventory(map.getBeast());
		}
		
		this.chat = new Chat();
		
		gameType.setInventory(this.inventaire);
		gameType.setPlayButton(this.gamePad);
		this.plateau = gameType;
		this.plateau.setWindow(window);
		
		
		nextTurn = new Button("Tour suivant");
		nextTurn.setDisable(true);
		
		nextTurn.setOnAction(e -> {
			nextTurn.setDisable(true);
			gameType.next();
			this.gamePad.activateButton();
		});

		this.core.getChildren().addAll(bottom, top);
		this.top.getChildren().addAll(plateau.getGrid(), inventaire.getCore(), nextTurn);
		this.bottom.getChildren().addAll(gamePad.getCore());
		
		this.core.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
	}
	
	public void userAction(AbstractMap map)
	{
		//this.inventaire.putInInventory(map.getHunter());
		this.gamePad.desactivateButton();
		nextTurn.setDisable(false);
	}
}
