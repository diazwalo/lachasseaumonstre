package render.ui.form.label;

import javafx.scene.control.Label;

public class LabelTheme extends Label
{
	/**
	 * Instancie le LabelTheme
	 * @param value le texte a afficher dans le label
	 */
	public LabelTheme(String value)
	{
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
	}
}
