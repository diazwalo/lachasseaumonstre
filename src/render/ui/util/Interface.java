package render.ui.util;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Interface
{
	public static VBox maxHeight(VBox vbox)
	{
		vbox.setMinHeight(getSize().getHeight());
		
		return vbox;
	}
	
	public static Rectangle2D getSize()
	{
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		
		return bounds;
	}
	
	public static Background getBackground()
	{
		Image image = new Image(Directory.HOME_BACKGROUND);

		BackgroundImage background = new BackgroundImage(
			image, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
		);
		
		return new Background(background);
	}
	
	public static double calculCaseSize(int width, int height)
	{
		int mapSize = (width > height) ? width : height;
		
		double heightScreen = Interface.getSize().getHeight();
		
		return heightScreen / mapSize;
	}
}
