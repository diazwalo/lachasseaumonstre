package render.ui.view;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import core.game.AbstractGame;
import core.game.GameStatus;
import data.score.IScore;
import interaction.Interaction;
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
import render.ui.component.gamePlay.GamePlayIA;
import render.ui.component.gamePlay.GamePlayMulti;
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
	Button beastTurn;
	Button hunterTurn;	
	
	int playerTurn=1;
	String keyCapture = "";
	
	/**
	 * Cree une vue pour une partie
	 * @param window Le core de l'ihm
	 * @param map Le plateau de jeu de la partie.
	 * @param gameType Le mode de jeu de la partie.
	 * @throws FileNotFoundException
	 */
	
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
		if(gameType instanceof GamePlayMulti) {
			this.gamePad.desactivateButton();
		}
		this.setEventMouvmentButton(map, gameType);

		this.plateau = gameType;
		this.plateau.setWindow(window);
		this.setInventory();

		nextTurn = new Button("Tour suivant");
		nextTurn.setDisable(true);
		this.setEventNextTurnButton();

		this.core.setBackground(Interface.getBackground(Directory.GAME_BACKGROUND));
		this.setScene(window);
	}

	/**
	 * Initialise la fenetre, initiale tous les nodes et leur comportement
	 * @param window Le core de l'ihm.
	 */
	public void setScene(Window window) {
		this.chat = new Chat();
		
		
		if(this.plateau instanceof GamePlayMulti) {
			beastTurn=new Button ("Tour de la bete ");
			hunterTurn=new Button ("Tour du chasseur ");
			this.setEntityTurnButton();
			this.plateau.setInventory(this.inventaire);
			this.right.getChildren().addAll(inventaire.getCore());
			this.left.getChildren().addAll(gamePad.getCore(),this.beastTurn,this.hunterTurn);
			
			
			
		}
		else if(! (this.plateau instanceof GamePlayIA)) {
			this.plateau.setInventory(this.inventaire);
			this.right.getChildren().addAll(inventaire.getCore());
			this.left.getChildren().addAll(gamePad.getCore());
		}
		this.plateau.setPlayButton(this.gamePad);
		this.middle.getChildren().addAll(plateau.getGrid());
		this.left.getChildren().addAll(nextTurn);
		this.core.getChildren().addAll(left, middle, right);

		this.core.setAlignment(Pos.CENTER);

		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
		
		scene.setOnKeyPressed(e -> {
			keyCapture = keyCapture + e.getCode().name();
		});
		
		scene.setOnKeyReleased(e -> {
			if(this.gamePad.isActivated()) {
				actionKey(keyCapture.toLowerCase());
				e.consume();
				keyCapture = "";
			}
		});
	}
	
	/**
	 * Cette fonction recupere les boutons appuyes par l'utilisateur et regarde si il reconnait un mouvement.
	 * @param keyCapture Les touches appuyes par l'utilisateur.
	 */
	private void actionKey(String keyCapture)
	{
		if(keyCapture.equals(" ") && this.gamePad.isActivated()) {
			this.plateau.next();
			return;
		}
		
		String[] strMouvment = Interaction.generateStrMvt();
		Mouvment[] tabMvt= Interaction.getTabMvt(strMouvment);
		Mouvment mouv = null;

		for (int idx = 0; idx < strMouvment.length; idx++) {
			if(strMouvment[idx].equals(keyCapture)) mouv=tabMvt[idx];
		}
		
		if(this.gamePad.isActivated()) {
			if(mouv != null && this.plateau.play(mouv)) {
				userAction();
				this.gamePad.desactivateButton();
			}
		}
	}

	/**
	 * Initialise l'inventaire
	 */
	public void setInventory() {
		if(this.plateau instanceof GamePlayHunter) {
			GamePlayHunter g = ((GamePlayHunter)(this.plateau));
			this.inventaire = new Inventory(g.ag.map.getHunter(), this.plateau);
		}else if(this.plateau instanceof GamePlayBeast) {
			GamePlayBeast g = ((GamePlayBeast)(this.plateau));
			this.inventaire = new Inventory(g.ag.map.getBeast(), this.plateau);
		}else if(this.plateau instanceof GamePlayMulti) {
			GamePlayMulti g = ((GamePlayMulti)(this.plateau));
			this.inventaire = new Inventory(g.ag.map.getHunter(), this.plateau);
		}
	}
	
	/**
	 * Desactive le PlayButton de la Game et active le bouton pour passer le tour
	 */
	public void userAction()
	{
		this.gamePad.desactivateButton();
		nextTurn.setDisable(false);
		this.EndOfGame();
	}
	
	/**
	 * Initialise l'evenement du bouton pour passer au tour suivant
	 */
	public void setEventNextTurnButton() {
		nextTurn = new Button("Tour suivant");
		if( this.plateau instanceof GamePlayIA ) {
			
			nextTurn.setDisable(false);
			nextTurn.setOnAction(e -> {
				this.plateau.next();
				this.EndOfGame();
			});
		}else if(this.plateau instanceof GamePlayMulti) {
			
			nextTurn.setDisable(true);
			nextTurn.setOnAction(e -> {
				if(playerTurn==0) {
					this.plateau.next();
					this.beastTurn.setDisable(false);
				}else if(playerTurn==1) {
					this.plateau.next();
					this.hunterTurn.setDisable(false);
				}
				this.nextTurn.setDisable(true);
				
				this.EndOfGame();
			});
			this.inventaire.setBonusDisable();
		}else {
			
			nextTurn.setDisable(true);
			nextTurn.setOnAction(e -> {
				nextTurn.setDisable(true);
				this.plateau.next();
				this.gamePad.activateButton();
				this.EndOfGame();
				
				this.refreshBonusView();
			});
		}
	}
	
	private void refreshBonusView() {
		List<IBonus> listInventory = new ArrayList<>();
		
		if(this.plateau instanceof GamePlayHunter) {
			((GamePlayHunter)(this.plateau)).ag.map.getHunter().putItAllInInventory();
			listInventory = ((GamePlayHunter)(this.plateau)).ag.map.getHunter().getInventory();
		}else if(this.plateau instanceof GamePlayBeast) {
			((GamePlayBeast)(this.plateau)).ag.map.getBeast().putItAllInInventory();
			listInventory = ((GamePlayBeast)(this.plateau)).ag.map.getBeast().getInventory();
		}else if(this.plateau instanceof GamePlayMulti) {
			if(this.playerTurn == 1) {
				((GamePlayMulti)(this.plateau)).ag.map.getBeast().putItAllInInventory();
				listInventory = ((GamePlayMulti)(this.plateau)).ag.map.getBeast().getInventory();
			}else if(this.playerTurn == 0) {
				((GamePlayMulti)(this.plateau)).ag.map.getHunter().putItAllInInventory();
				listInventory = ((GamePlayMulti)(this.plateau)).ag.map.getHunter().getInventory();
			}
		}
		
		inventaire.setBonusAble(listInventory);
	}

	/**
	 * Initiase les evenement pour les bouton de mouvement
	 * @param map
	 * @param gameType
	 */
	public void setEventMouvmentButton(AbstractMap map, AbstractGamePlay gameType) {
		if(! (gameType instanceof GamePlayIA)) {
			this.gamePad.topBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.NORD)) {
					userAction();
				}
			});
			
			this.gamePad.upLeftBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.NORDOUEST)) {
					userAction();
				}
			});
			
			this.gamePad.upRightBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.NORDEST)) {
					userAction();
				}
			});
			
			this.gamePad.leftBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.OUEST)) {
					userAction();
				}
			});
			
			this.gamePad.rightBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.EST)) {
					userAction();
				}
			});
			
			this.gamePad.bottomBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.SUD)) {
					userAction();
				}
			});
			
			this.gamePad.bottomLeftBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.SUDOUEST)) {
					userAction();
				}
			});
			
			this.gamePad.bottomRightBtn.getBouton().setOnAction(e -> {
				if(this.plateau.play(Mouvment.SUDEST)) {
					userAction();
				}
			});
		}
		
	}
	
	/**
	 * Determine quelle entite doit jouer
	 */
	public void setEntityTurnButton() {
		this.beastTurn.setDisable(true);
		this.beastTurn.setOnAction(e -> {
			if(playerTurn==0) {
				((GamePlayMulti) (this.plateau)).setView(playerTurn);
				this.inventaire.setInventory(((GamePlayMulti)(this.plateau)).ag.map.getBeast(), this.plateau, ((GamePlayMulti)(this.plateau)).ag);
				this.right.getChildren().clear();
				this.right.getChildren().addAll(inventaire.getCore());
				playerTurn=(playerTurn+1)%2;
				this.beastTurn.setDisable(true);
				this.gamePad.activateButton();
				this.refreshBonusView();
			}
		});

		this.hunterTurn.setOnAction(e -> {
			if(playerTurn==1) {
				((GamePlayMulti) (this.plateau)).setView(playerTurn);
				this.inventaire.setInventory(((GamePlayMulti)(this.plateau)).ag.map.getHunter(), this.plateau, ((GamePlayMulti)(this.plateau)).ag);
				this.right.getChildren().clear();
				this.right.getChildren().addAll(inventaire.getCore());
				playerTurn=(playerTurn+1)%2;
				this.hunterTurn.setDisable(true);
				this.gamePad.activateButton();
				this.refreshBonusView();
			}
		});
	}

	/**
	 * Affiche l'ecran de fin jeu en fonction de la victoire ou de la defaite
	 */
	public void EndOfGame() {
		if(! AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			boolean victory = this.plateau.map.isHunterWin();
			String entityWinner = null;
			if(this.plateau instanceof GamePlayBeast) {
				victory = this.plateau.map.isBeastWin();
			}else if((this.plateau instanceof GamePlayMulti) || (this.plateau instanceof GamePlayIA)) {
				if(this.plateau.map.isBeastWin()) {
					entityWinner = "de la Bete";
				}else if(this.plateau.map.isHunterWin()) {
					entityWinner = "du Chasseur";
				}
			}
			
			manageScore();
			
			EndScreen es =new EndScreen(plateau.window);
			es.setEndScreen(AbstractGame.gameStatus, victory, this.plateau.map.getConfig(), entityWinner);
		}
	}

	// Regarde ca virgil, quelle si beau code sans instanceOf xD
	private void manageScore()
	{
		((IScore) this.plateau).buildScore();
		//new askPseudo(plateau.window);
	}
}
