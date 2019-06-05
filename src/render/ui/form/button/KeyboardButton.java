package render.ui.form.button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KeyboardButton {
	
	private Button bouton;
	private Image img;
	private int size = 150;
	
	public KeyboardButton(String contenu) {
		this.bouton = new Button(contenu);
		bouton.setMaxSize(size, size);
		bouton.setMinSize(size, size);
	}
	
	public KeyboardButton(Image image) {
		this.bouton = new Button();
		bouton.setMaxSize(size, size);
		bouton.setMinSize(size, size);
		
		ImageView iv = new ImageView(image);
		iv.setFitWidth(size);
		iv.setFitHeight(size);
		
		this.bouton.setGraphic(iv);
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
