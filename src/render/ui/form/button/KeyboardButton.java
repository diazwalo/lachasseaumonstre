package render.ui.form.button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KeyboardButton {
	
	private Button bouton;
	private Image img;
	private int size = 115;
	
	/**
	 * Instancie le KeyBoardButton
	 * @param contenu le texte a afficher dans le bouton
	 */
	public KeyboardButton(String contenu) {
		this.bouton = new Button(contenu);
		bouton.setMaxSize(size, size);
		bouton.setMinSize(size, size);
	}
	
	/**
	 * Instancie le KeyBoardButton
	 * @param image la texture a appliquer sur le bouton
	 */
	public KeyboardButton(Image image) {
		this.bouton = new Button();
		bouton.setMaxSize(size, size);
		bouton.setMinSize(size, size);
		
		ImageView iv = new ImageView(image);
		iv.setFitWidth(size);
		iv.setFitHeight(size);
		
		this.bouton.setGraphic(iv);
	}

	/**
	 * Retourne le Node du KeyboardButton
	 * @return bouton
	 */
	public Button getBouton() {
		return bouton;
	}

	/**
	 * Remplace la valeur du Button du KeyBoardButton par celle du bouton passer en paramètre
	 * @param bouton le bouton a mettre dans le KeyBoardButton
	 */
	public void setBouton(Button bouton) {
		this.bouton = bouton;
	}

	/**
	 * Retour la texture du KeyBoardButton
	 * @return img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Remplace l'image du KeyboardButton par l'image passer en paramètre
	 * @param img la texture du bouton
	 */
	public void setImg(Image img) {
		this.img = img;
	}
	
	
}
