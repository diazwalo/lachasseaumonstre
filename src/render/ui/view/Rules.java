package render.ui.view;



import config.Config;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Rules {
	
	private VBox core;
	private HBox upside;
	private String[] pages;
	private Window window;
	private Button before;
	private Button retour;
	private Button next;
	private static int index = 0;
	private Pane regles;
	private Label compteur;
	private Rectangle rec;

	public Rules(Window win) {
		regles = new Pane();
		core = new VBox();
		upside = new HBox();
		pages = new String[] {Directory.GAMEPLAY_RULES, Directory.BONUS_RULES};
		before = new Button("<-");
		next = new Button("->");
		window = win;
		retour = new Button("Retour");
		rec = new Rectangle(900,750);
		compteur = new Label(index+1+"/"+pages.length);
		rec.setFill(new ImagePattern(new Image(pages[index])));
		
		
		regles.getChildren().add(rec);
		upside.getChildren().addAll(before,regles,next);
		core.getChildren().addAll(upside,compteur,retour);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		next.setOnAction(e -> { 
			if(index < this.pages.length) {
				index++;
				new Rules(this.window);
		  }
		});
		
		before.setOnAction(e -> { 
			if(index > 0) {
				index--;
				new Rules(this.window);
		    }
		});
		
		retour.setOnAction(e -> {
			new Home(window, new Config());
		});
		
		window.stage.setScene(scene);
		window.stage.setMaximized(true);
		
	}
	
	
	
}