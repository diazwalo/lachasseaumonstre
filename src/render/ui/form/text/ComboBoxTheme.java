package render.ui.form.text;

import javafx.scene.control.ComboBox;

public class ComboBoxTheme extends ComboBox<String>
{
	public ComboBoxTheme()
	{
		super.setCellFactory(list -> new CelluleTheme());
		super.setStyle("-fx-background-color: transparent; -fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
	}
}
