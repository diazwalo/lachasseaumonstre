package render.ui.component;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class HomeButton extends Button{
	
	public HomeButton(String value)
	{
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 40px; -fx-text-fill: #eee;");
		super.setBackground(Background.EMPTY);
		super.setEffect(getShadow());
	}
	
	public DropShadow getShadow()
	{
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(new Color(0.6, 0.6, 0.6, 1));
		dropShadow.setRadius(1.0);
		dropShadow.setOffsetX(3.0);
		
		return dropShadow;
	}
	
}
