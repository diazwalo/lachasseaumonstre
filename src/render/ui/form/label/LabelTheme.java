package render.ui.form.label;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;

public class LabelTheme extends Label
{
	public LabelTheme(String value)
	{
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
	}
}
