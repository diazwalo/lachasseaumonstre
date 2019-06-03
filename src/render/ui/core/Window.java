package render.ui.core;

import config.Config;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import render.ui.util.Directory;
import render.ui.view.Home;

public class Window extends Application
{
	public Stage stage;
	Config config = new Config();
	
	public void start(Stage stage) {
		this.stage = stage;
		
		new Home(this, config);

	    stage.setTitle("La chasse aux monstres");
	    stage.getIcons().add(new Image(Directory.ICON));
	    stage.setMaximized(true);
	    stage.setResizable(false);
	    stage.show();
	    
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
}
