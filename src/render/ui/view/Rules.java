package render.ui.view;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import render.ui.core.Window;
import render.ui.form.button.HomeMinButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Rules
{
	
	private Window window;
	
	private static int index = 0;

	/**
	 * Cree une vue pour les regles
	 * @param window Le core de l'ihm.
	 */
	public Rules(Window window)
	{
		this.window = window;
		
		Pane regles = new Pane();
		VBox core = new VBox();
		HBox upside = new HBox();
		HBox downside = new HBox();
		
		String[] pages = new String[] {Directory.GAMEPLAY_RULES, Directory.BONUS_RULES};
		HomeMinButton retour = new HomeMinButton("Retour");
		HomeMinButton before = new HomeMinButton("Precedent");
		HomeMinButton next = new HomeMinButton("Suivant");
		
		core.setBackground(Interface.getBackground(Directory.ALT_BACKGROUND));
		core.setAlignment(Pos.CENTER);
		upside.setAlignment(Pos.CENTER);
		downside.setAlignment(Pos.CENTER);
		downside.setPadding(new Insets(20, 20, 20, 20));
		downside.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7)");
		
		Rectangle rec = new Rectangle(900,750);
		Label compteur = new Label("Page " + (index+1)+"/"+pages.length);
		compteur.setStyle("-fx-font-size:24px;-fx-font-weight:bold;");
		rec.setFill(new ImagePattern(new Image(pages[index])));
		
		upside.getChildren().add(regles);
		regles.getChildren().add(rec);
		downside.getChildren().addAll(before, retour, next);
		core.getChildren().addAll(upside, compteur, downside);
		
		Scene scene = new Scene(core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		next.setOnAction(e -> { 
			if(index < pages.length) {
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
