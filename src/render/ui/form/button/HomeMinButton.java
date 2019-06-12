package render.ui.form.button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeMinButton extends HomeButton
{

	/**
	 * Instancie le HomeMinButton
	 * @param value le texte sur le bouton
	 * @param image la texture du boutton
	 */
	public HomeMinButton(String value, Image image)
	{
		super(value);
		
		if(image != null) {
			ImageView iv = new ImageView(image);
			iv.setFitHeight(40);
			iv.setFitWidth(40);
			super.setGraphic(iv);
		}
		
		super.setMinSize(250, 100);
	}
	
	/**
	 * Instancie le HomeMinButton
	 * @param value le texte sur le bouton
	 */
	public HomeMinButton(String value)
	{
		this(value, null);
	}

}
