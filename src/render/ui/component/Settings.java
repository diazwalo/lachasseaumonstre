package render.ui.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Settings
{
	private VBox menu;
	
	public Settings()
	{
		this.menu = new VBox();
		
		Label l1 = new Label("Test");
		TextField tf1 = new TextField("test");
		
		this.menu.getChildren().addAll(l1, tf1);
		
		HBox horizontalFooter = new HBox();
		HomeMinButton left = new HomeMinButton("Quitter");
		HomeMinButton save = new HomeMinButton("Sauver");
		
		horizontalFooter.getChildren().addAll(left, save);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
	}
	
	public VBox getMenu()
	{
		return this.menu;
	}
	
}
