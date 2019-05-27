package render.ui.component;

import javafx.geometry.Pos;
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
		
		HomeButton btn1 = new HomeButton("Ordinateur");
		btn1.setMinSize(500, 100);
		
		HomeButton btn2 = new HomeButton("Chasseur");
		btn2.setMinSize(500, 100);
		
		HomeButton btn3 = new HomeButton("Monstre");
		btn3.setMinSize(500, 100);
		
		HomeButton btn4 = new HomeButton("Dual");
		btn4.setMinSize(500, 100);
		
		this.menu.getChildren().addAll(btn1, btn2, btn3, btn4);
		this.menu.setAlignment(Pos.CENTER);
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		this.menu.setMinHeight(bounds.getHeight());
		
		this.core.setAlignment(Pos.CENTER);
		this.core.setBackground(new Background(background));
		this.core.getChildren().add(this.menu);
	}

	public HBox getCore()
	{
		return this.core;
	}
}
