package render.ui.component;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayButton {
	
	private VBox core;
	private HBox topside;
	private HBox bottom;
	
	private KeyboardButton topBtn;
	private KeyboardButton leftBtn;
	private KeyboardButton rightBtn;
	private KeyboardButton bottomBtn;
	
	public PlayButton() {
		this.core = new VBox();
		this.topside = new HBox();
		this.bottom = new HBox();
		
		this.topBtn  = new KeyboardButton("z");
		this.leftBtn  = new KeyboardButton("q");
		this.rightBtn  = new KeyboardButton("d");
		this.bottomBtn  = new KeyboardButton("s");
		
		this.topside.setPadding(new Insets(0, 50, 0, 50));
		
		this.topside.getChildren().add(this.topBtn.getBouton());
		this.bottom.getChildren().addAll(
			this.leftBtn.getBouton(),
			this.bottomBtn.getBouton(),
			this.rightBtn.getBouton()
		);
		this.core.getChildren().add(topside);
		this.core.getChildren().add(bottom);
	}

	public VBox getCore() {
		return core;
	}

}