package render.ui.view;


import java.io.FileNotFoundException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.AbstractMap;
import map.Mouvment;
import render.bonus.IBonus;
import render.ui.component.Chat;
import render.ui.component.Inventory;
import render.ui.component.gamePlay.AbstractGamePlay;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
import render.ui.core.Window;
import render.ui.form.button.PlayButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Game {

	VBox left;
	VBox middle;
	VBox right;
	HBox core;
	
	PlayButton gamePad;
	Inventory inventaire;
	AbstractGamePlay plateau; 
	Chat chat;
	
	Button nextTurn;
	
	
	public Game(Window window, AbstractMap map, AbstractGamePlay gameType) throws FileNotFoundException {
		
		this.left = new VBox();
		this.middle = new VBox();
		this.right = new VBox();
		this.core = new HBox();
		
		this.left.setAlignment(Pos.CENTER);
		this.middle.setAlignment(Pos.CENTER);
		this.middle.setPadding(new Insets(15));
		this.right.setAlignment(Pos.CENTER);
		
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
			this.inventaire = new Inventory(map.getHunter(), gameType);
		}else if(gameType instanceof GamePlayBeast) {
			this.inventaire = new Inventory(map.getBeast(), gameType);
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
			
			((GamePlayHunter)(gameType)).ag.map.getHunter().putItAllInInventory();
			
			List<IBonus> listInventory = ((GamePlayHunter)(gameType)).ag.map.getHunter().getInventory();
			inventaire.setBonusAble(listInventory);
		});

		this.left.getChildren().addAll(gamePad.getCore(), nextTurn);
		this.middle.getChildren().addAll(plateau.getGrid());
		this.right.getChildren().addAll(inventaire.getCore());
		this.core.getChildren().addAll(left, middle, right);
		
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
