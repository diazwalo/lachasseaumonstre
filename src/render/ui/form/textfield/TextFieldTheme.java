package render.ui.form.textfield;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TextFieldTheme extends TextField
{
	public TextFieldTheme(String value)
	{
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
		super.setBackground(Background.EMPTY);
		
		Paint p = Color.WHITE;
		BorderStroke bs = new BorderStroke(p, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(8));
		super.setBorder(new Border(bs));
	}
}
