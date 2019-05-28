package render.ui.form.button;

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
	
	public void setMaxSize(int maxWidth, int  maxHeight ) {
		topBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		leftBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		rightBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		bottomBtn.getBouton().setMaxSize(maxWidth, maxHeight);
	}
	
	public void setMinSize(int minWidth, int  minHeight ) {
		topBtn.getBouton().setMaxSize(minWidth, minHeight );
		leftBtn.getBouton().setMaxSize(minWidth, minHeight );
		rightBtn.getBouton().setMaxSize(minWidth, minHeight );
		bottomBtn.getBouton().setMaxSize(minWidth, minHeight );
	}
	
	public void setPrefSize(int prefWidth, int  prefHeight ) {
		topBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		leftBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		rightBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		bottomBtn.getBouton().setMaxSize(prefWidth, prefHeight );
	}
	
	

}
