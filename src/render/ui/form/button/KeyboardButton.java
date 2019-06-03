package render.ui.form.button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import render.ui.util.Directory;

public class KeyboardButton {
	
	private Button bouton;
	private Image img;
	
	
	
	
	public KeyboardButton(String contenu) {
    	img = new Image(Directory.GAME_TEXTURE);

		this.bouton = new Button(contenu);
		bouton.setMaxSize(50, 50);
		bouton.setMinSize(50,50);
		//this.bouton.setGraphic(new ImageView(img));
	}


	public Button getBouton() {
		return bouton;
	}


	public void setBouton(Button bouton) {
		this.bouton = bouton;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
	}
	
	
}
