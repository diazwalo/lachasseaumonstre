package render.ui.component;

import javafx.geometry.Insets;
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
import render.ui.component.button.HomeButton;
import render.ui.component.button.HomeMinButton;
import render.ui.util.Directory;

public class Home
{
	private HBox core;
	private VBox menu;
	
	public Home()
	{
		this.core = new HBox();
		this.menu = new VBox();
		
		this.core.setBackground(getBackground());
		setHeight();
		verticalMenu();
	}

	public HBox getCore()
	{
		return this.core;
	}
	
	private Background getBackground()
	{
		Image image = new Image("file:"+Directory.getHomeBackground());

		BackgroundImage background = new BackgroundImage(
			image, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
		);
		
		return new Background(background);
	}
	
	private void setHeight()
	{
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		this.menu.setMinHeight(bounds.getHeight());
	}
	
	private void verticalMenu()
	{
		HomeButton btn1 = new HomeButton("Ordinateur");		
		HomeButton btn2 = new HomeButton("Chasseur");		
		HomeButton btn3 = new HomeButton("Monstre");		
		HomeButton btn4 = new HomeButton("Dual");
		
		this.menu.getChildren().addAll(btn1, btn2, btn3, btn4);
		this.menu.setAlignment(Pos.CENTER);
		
		HBox horizontalFooter = new HBox();
		
		HomeMinButton settings = new HomeMinButton("Settings");
		settings.setOnAction(e -> { 
			this.core.getChildren().clear();
			this.core.setBackground(getBackground());
			setHeight();
			
			Settings st = new Settings();
			this.core.getChildren().add(st.getMenu());
		});
		
		HomeMinButton left = new HomeMinButton("Quitter");
		left.setOnAction(e -> {System.exit(0);});
		
		horizontalFooter.getChildren().addAll(settings, left);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
		
		this.core.setAlignment(Pos.CENTER);
		this.core.getChildren().add(this.menu);
	}
}
