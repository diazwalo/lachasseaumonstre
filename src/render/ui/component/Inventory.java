package render.ui.component;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import render.ui.form.button.KeyboardButton;

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
	
	
	public void setMaxSize(int maxWidth, int  maxHeight ) {
		inventaire.setMaxSize(maxWidth, maxHeight*0.91);
		bonus.getBouton().setMaxSize(maxWidth, maxHeight*0.09);
	}
	
	public void setMinSize(int minWidth, int  minHeight ) {
		inventaire.setMinSize(minWidth, minHeight*0.91);
		bonus.getBouton().setMinSize(minWidth, minHeight*0.09);
	}
	
	public void setPrefSize(int prefWidth, int  prefHeight ) {
		inventaire.setPrefSize(prefWidth, prefHeight*0.91);
		bonus.getBouton().setPrefSize(prefWidth, prefHeight*0.9);
	}
	
	

}
