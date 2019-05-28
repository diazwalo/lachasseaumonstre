package render.ui.util;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Interface
{
	public static VBox maxHeight(VBox vbox)
	{
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		vbox.setMinHeight(bounds.getHeight());
		
		return vbox;
	}
}
