package render.ui.form.button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeMinButton extends HomeButton
{

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
	
	public HomeMinButton(String value)
	{
		this(value, null);
	}

}
