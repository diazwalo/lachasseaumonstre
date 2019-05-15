package render.ui.component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayButton {
	
	private VBox core;
	private HBox topside;
	private HBox bottom;
	private Label vide1;
	private Label vide2;
	private KeyboardButton b0;
	private KeyboardButton b1;
	private KeyboardButton b2;
	private KeyboardButton b3;
	
	public PlayButton() {
		this.b0  = new KeyboardButton("");
		this.b1  = new KeyboardButton("");
		this.b2  = new KeyboardButton("");
		this.b3  = new KeyboardButton("");
		this.vide1 = new Label("");
		this.vide2 = new Label("");
		
		vide1.setMaxSize(50, 50);
		vide2.setMaxSize(50, 50);
		
		this.core = new VBox();
		this.topside = new HBox();
		this.bottom = new HBox();
		
		core.getChildren().add(topside);
		core.getChildren().add(topside);
		topside.getChildren().add(vide1);
		topside.getChildren().add(b0.getBouton());
		topside.getChildren().add(vide2);
		bottom.getChildren().add(b1.getBouton());
		bottom.getChildren().add(b2.getBouton());
		bottom.getChildren().add(b3.getBouton());
	}

	public VBox getCore() {
		return core;
	}
	
	

}
