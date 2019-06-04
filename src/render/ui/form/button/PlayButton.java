package render.ui.form.button;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.util.Directory;

public class PlayButton {
	
	private VBox core;
	private HBox topside;
	private HBox middle;
	private HBox bottom;
	
	private KeyboardButton topBtn;
	private KeyboardButton leftBtn;
	private KeyboardButton rightBtn;
	private KeyboardButton bottomBtn;
	private KeyboardButton upRightBtn;
	private KeyboardButton upLeftBtn;
	private KeyboardButton bottomRightBtn;
	private KeyboardButton bottomLeftBtn;
	
	private GridPane panal = new GridPane();
	
	public PlayButton() {
		this.core = new VBox();
		this.topside = new HBox();
		this.bottom = new HBox();
		this.middle = new HBox();
		
		this.topBtn  = new KeyboardButton(new Image(Directory.KEYBOARD_UP));
		this.leftBtn  = new KeyboardButton(new Image(Directory.KEYBOARD_LEFT));
		this.rightBtn  = new KeyboardButton(new Image(Directory.KEYBOARD_RIGHT));
		this.bottomBtn  = new KeyboardButton(new Image(Directory.KEYBOARD_DOWN));
		this.upRightBtn = new KeyboardButton(new Image(Directory.KEYBOARD_UP_RIGHT));
		this.upLeftBtn = new KeyboardButton(new Image(Directory.KEYBOARD_UP_LEFT));
		this.bottomRightBtn = new KeyboardButton(new Image(Directory.KEYBOARD_DOWN_RIGHT));
		this.bottomLeftBtn = new KeyboardButton(new Image(Directory.KEYBOARD_DOWN_LEFT));
		
		panal.add(this.upLeftBtn.getBouton(), 0, 0);
		panal.add(this.topBtn.getBouton(), 1, 0);
		panal.add(this.upRightBtn.getBouton(), 2, 0);
		panal.add(this.leftBtn.getBouton(), 0, 1);
		panal.add(this.rightBtn.getBouton(), 2, 1);
		panal.add(this.bottomLeftBtn.getBouton(), 0, 2);
		panal.add(this.bottomBtn.getBouton(), 1, 2);
		panal.add(this.bottomRightBtn.getBouton(), 2, 2);
		
		core.getChildren().addAll(panal);
	}

	public VBox getCore() {
		return core;
	}
	
	public void setMaxSize(int maxWidth, int  maxHeight ) {
		topBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		leftBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		rightBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		bottomBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		bottomLeftBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		bottomRightBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		upRightBtn.getBouton().setMaxSize(maxWidth, maxHeight);
		upLeftBtn.getBouton().setMaxSize(maxWidth, maxHeight);
	}
	
	public void setMinSize(int minWidth, int  minHeight ) {
		topBtn.getBouton().setMaxSize(minWidth, minHeight );
		leftBtn.getBouton().setMaxSize(minWidth, minHeight );
		rightBtn.getBouton().setMaxSize(minWidth, minHeight );
		bottomBtn.getBouton().setMaxSize(minWidth, minHeight );
		bottomLeftBtn.getBouton().setMaxSize(minWidth, minHeight );
		bottomRightBtn.getBouton().setMaxSize(minWidth, minHeight );
		upRightBtn.getBouton().setMaxSize(minWidth, minHeight );
		upLeftBtn.getBouton().setMaxSize(minWidth, minHeight );
		
	}
	
	public void setPrefSize(int prefWidth, int  prefHeight ) {
		topBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		leftBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		rightBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		bottomBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		bottomLeftBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		bottomRightBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		upRightBtn.getBouton().setMaxSize(prefWidth, prefHeight );
		upLeftBtn.getBouton().setMaxSize(prefWidth, prefHeight );
	}
	
	

}
