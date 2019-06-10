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
	/**
	 * Configure la taille minimale d'une VBox
	 * @param vbox
	 * @return
	 */
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
	
	/**
	 * Retourne l'image de fond l'interface
	 * @param path
	 * @return
	 */
	public static Background getBackground(String path)
	{
		Image image = new Image(path);

		BackgroundImage background = new BackgroundImage(
			image, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
		);
		
		return new Background(background);
	}
	
	/**
	 * Calcul la taille des optimal des case
	 * @param width
	 * @param height
	 * @return
	 */
	public static double calculCaseSize(int width, int height)
	{
		int mapSize = (width > height) ? width : height;
		
		double heightScreen = Interface.getSize().getHeight() - 100;  // Don't gameboard use fullscreen
		
		return heightScreen / mapSize;
	}
}
