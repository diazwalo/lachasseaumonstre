package render.ui.form.button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KeyboardButton {
	
	private Button bouton;
	private Image img;
	
	public KeyboardButton(String contenu) {
		this.bouton = new Button(contenu);
		bouton.setMaxSize(50, 50);
		bouton.setMinSize(50,50);
	}
	
	public KeyboardButton(Image image) {
		this.bouton = new Button();
		bouton.setMaxSize(50, 50);
		bouton.setMinSize(50,50);
		
		ImageView iv = new ImageView(image);
		iv.setFitWidth(50);
		iv.setFitHeight(50);
		
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
