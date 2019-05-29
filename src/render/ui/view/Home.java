package render.ui.view;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.form.button.HomeMinButton;
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
		scene.getStylesheets().add("css/style.css");
		
		window.stage.setScene(scene);
	}
	
	private void verticalMenu()
	{
		HomeButton btn1 = new HomeButton("Ordinateur");		
		HomeButton btn2 = new HomeButton("Chasseur");		
		HomeButton btn3 = new HomeButton("Monstre");		
		HomeButton btn4 = new HomeButton("Dual");
		
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
