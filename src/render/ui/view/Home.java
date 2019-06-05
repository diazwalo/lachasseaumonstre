package render.ui.view;

import java.io.FileNotFoundException;

import config.Config;
import config.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.AbstractMap;
import map.CircularMap;
import map.SquareMap;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
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
	
	public Home(Window window, Config config)
	{
		this.window = window;
		this.config = config;
		
		this.core = new HBox();
		this.menu = new VBox();
		
		this.core.setBackground(Interface.getBackground());
		Interface.maxHeight(this.menu);
		verticalMenu();
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		window.stage.setScene(scene);
	}
	
	private void verticalMenu()
	{
		HomeButton btn1 = new HomeButton("Ordinateur");		
		HomeButton btn2 = new HomeButton("Chasseur");		
		HomeButton btn3 = new HomeButton("Monstre");		
		HomeButton btn4 = new HomeButton("Dual");
		
		//TEST
		btn2.setOnAction(e -> {
			System.out.println(config.getWidth());
			System.out.println(config.getHeight());
			AbstractMap map = null;
			if(this.config.getMap().equals(Map.SQUARE)) {
				map = new SquareMap(this.config);
			}else if(this.config.getMap().equals(Map.CIRCULAR)) {
				map = new CircularMap(this.config);
			}
			try {
				new Game(window, map, new GamePlayHunter(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		btn3.setOnAction(e -> {
			//Faire une fct juste pour ca
			AbstractMap map = null;
			if(this.config.getMap().equals(Map.SQUARE)) {
				map = new SquareMap(this.config);
			}else if(this.config.getMap().equals(Map.CIRCULAR)) {
				map = new CircularMap(this.config);
			}
			// justqu'a la
			try {
				new Game(window, map, new GamePlayBeast(map));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		this.menu.getChildren().addAll(btn1, btn2, btn3, btn4);
		this.menu.setAlignment(Pos.CENTER);
		
		HBox horizontalFooter = new HBox();
		
		HomeMinButton settings = new HomeMinButton("Settings");
		settings.setOnAction(e -> { 
			new Settings(this.window, this.config);
		});
		
		HomeMinButton left = new HomeMinButton("Quitter");
		left.setOnAction(e -> {System.exit(0);});
		
		horizontalFooter.getChildren().addAll(settings, left);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
		
		this.core.setAlignment(Pos.CENTER);
		this.core.getChildren().add(this.menu);
	}
}
