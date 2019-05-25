package render.ui.component;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import render.ui.util.Directory;

public class Home
{
	private HBox core;
	private VBox menu;
	
	public Home()
	{
		this.core = new HBox();
		this.menu = new VBox();
		
		Image image = new Image("file:"+Directory.getHomeBackground());

		BackgroundImage background = new BackgroundImage(
			image, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
		);
		
		/*Button btn = new Button("IA vs IA");
		btn.setMinSize(500, 100);
		this.menu.getChildren().add(btn);*/
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		this.menu.setMinHeight(bounds.getHeight());
		
		this.core.setBackground(new Background(background));
		this.core.getChildren().add(this.menu);
	}

	public HBox getCore()
	{
		return this.core;
	}
}
