package render.ui.form.text;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ComboBoxTheme extends ComboBox<String>
{
	public ComboBoxTheme()
	{
		super.setCellFactory(list -> new CelluleTheme());
		super.setStyle("-fx-background-color: transparent; -fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");

		Paint p = Color.WHITE;
		BorderStroke bs = new BorderStroke(p, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(8));
		super.setBorder(new Border(bs));
	}
}
