package render.ui.view;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import config.Config;
import config.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import map.AbstractMap;
import map.CircularMap;
import map.SquareMap;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
import render.ui.component.gamePlay.GamePlayIA;
import render.ui.component.gamePlay.GamePlayMulti;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.form.button.HomeMinButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Home
{
	private HBox core;
	private VBox menu;
	private Window window;
	Config config;
	
	/**
	 * Cr�e une vue pour l'accueil
	 * @param window
	 * @param config
	 */
	public Home(Window window, Config config)
	{
		this.window = window;
		this.config = config;
		
		this.core = new HBox();
		this.menu = new VBox();
		
		this.core.setBackground(Interface.getBackground(Directory.HOME_BACKGROUND));
		Interface.maxHeight(this.menu);
		verticalMenu();
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		window.stage.setScene(scene);
		
	
		Media media;
		MediaPlayer mp;
		try {
			media = new Media(getClass().getClassLoader().getResource("sounds/game.mp3").toURI().toString());
			mp= new MediaPlayer(media);
			mp.play();
			mp.setCycleCount(MediaPlayer.INDEFINITE);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Intialise le menu 
	 */
	private void verticalMenu()
	{
		HomeButton btn1 = new HomeButton(" Dual", new Image("icons/multi.png"));		
		HomeButton btn2 = new HomeButton(" Chasseur", new Image("icons/gun.png"));		
		HomeButton btn3 = new HomeButton(" Monstre", new Image("icons/beast.png"));		
		HomeButton btn4 = new HomeButton(" Ordinateur", new Image("icons/ai.png"));
		
		btn1.setOnAction(e -> {
			AbstractMap map = this.getMapForm();
			try {
				new Game(window, map, new GamePlayMulti(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		btn2.setOnAction(e -> {
			AbstractMap map = this.getMapForm();
			try {
				new Game(window, map, new GamePlayHunter(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		btn3.setOnAction(e -> {
			AbstractMap map = this.getMapForm();
			try {
				new Game(window, map, new GamePlayBeast(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		btn4.setOnAction(e -> {
			AbstractMap map = this.getMapForm();
			try {
				new Game(window, map, new GamePlayIA(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		
		HBox horizontalFooter = new HBox();
		HomeButton stats = new HomeButton(" Statistiques", new Image("icons/chart.png"));
		stats.setOnAction(e -> { 
			new Stats(this.window, this.config);
		});
		
		HomeButton regles = new HomeButton(" Regles", new Image("icons/chart.png"));
		regles.setOnAction(e -> { 
			new Rules(this.window);
		});
		
		
		this.menu.getChildren().addAll(btn1, btn2, btn3, btn4, stats, regles);
		this.menu.setAlignment(Pos.CENTER);
		
		HomeMinButton settings = new HomeMinButton(" Settings", new Image("icons/gear.png"));
		settings.setOnAction(e -> { 
			new Settings(this.window, this.config);
		});
		
		HomeMinButton left = new HomeMinButton(" Quitter", new Image("icons/exit.png"));
		left.setOnAction(e -> {System.exit(0);});
		
		horizontalFooter.getChildren().addAll(settings, left);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
		
		this.core.setAlignment(Pos.CENTER);
		this.core.getChildren().add(this.menu);
	}
	
	/**
	 * Renvoie la forme de la map selectionner dans les setting du menu
	 * @return
	 */
	public AbstractMap getMapForm() {
		AbstractMap map = null;
		
		if(this.config.getMap().equals(Map.SQUARE)) {
			map = new SquareMap(this.config);
		}else if(this.config.getMap().equals(Map.CIRCULAR)) {
			map = new CircularMap(this.config);
		}

		return map;
	}
}
