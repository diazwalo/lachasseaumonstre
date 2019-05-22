package render.ui.component;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Inventory {

	private KeyboardButton bonus;
	private TextField inventaire;
	private VBox core;
	
	public Inventory() {
		core = new VBox();
		inventaire = new TextField();
		bonus = new KeyboardButton("Utiliser Bouton");
		
		inventaire.setMinSize(150, 500);
		inventaire.setEditable(false);
		bonus.getBouton().setMinSize(150, 50);
		
		core.getChildren().add(inventaire);
		core.getChildren().add(bonus.getBouton());
		
		
		
	}

	public VBox getCore() {
		return core;
	}
	
	
	

}
